package es.ieslavereda.Chess.controladores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import es.ieslavereda.Chess.config.MyConfig;
import es.ieslavereda.Chess.model.common.Celda;
import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Coordenada;
import es.ieslavereda.Chess.model.common.GestionFichasEliminadas;
import es.ieslavereda.Chess.model.common.Movimiento;
import es.ieslavereda.Chess.model.common.Pawn;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.model.common.Tablero;
import es.ieslavereda.Chess.vista.JPFichasEliminadas;
import es.ieslavereda.Chess.vista.JPMovements;
import es.ieslavereda.Chess.vista.JPTurno;
import es.ieslavereda.Chess.vista.Preferencias;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorPrincipal implements ActionListener, MouseListener {

	private VistaPrincipal vista;
	private Color turno;
	private Pieza piezaSeleccionada;
	private Preferencias jfPreferencias;
	private GestionFichasEliminadas gestionFichasEliminadas;
	private ControladorTurno controladorTurno;
	private DefaultListModel<Movimiento> dlm;
	private Deque<Movimiento> stack;

	public ControladorPrincipal(VistaPrincipal vista) {
		super();
		this.vista = vista;
		stack = new ArrayDeque<Movimiento>();

		inicializar();
	}

	private void inicializar() {

		dlm = new DefaultListModel<Movimiento>();
		vista.getPanelMovimientos().getList().setModel(dlm);
		gestionFichasEliminadas = new ControladorFichasEliminadas(vista.getPanelEliminados());
		controladorTurno = new ControladorTurno(vista.getPanelTurno());

		turno = Color.WHITE;

		Component[] components = vista.getPanelTablero().getComponents();

		for (Component component : components) {
			if (component instanceof Celda) {
				((Celda) component).addActionListener(this);
			}
		}
		activaCeldas(true);

		// Mouse listener
		vista.getPanelMovimientos().getList().addMouseListener(this);

		// Action Listeners
		vista.getMntmPreferences().addActionListener(this);
		vista.getPanelMovimientos().getButtonNext().addActionListener(this);
		vista.getPanelMovimientos().getButtonPrevious().addActionListener(this);
		vista.getMntmNewGame().addActionListener(this);
		vista.getMntmOpen().addActionListener(this);
		vista.getMntmSave().addActionListener(this);

		// Action Commands
		vista.getMntmPreferences().setActionCommand("Abrir preferencias");
		vista.getMntmNewGame().setActionCommand("Reiniciar");
		vista.getPanelMovimientos().getButtonNext().setActionCommand("Next movement");
		vista.getPanelMovimientos().getButtonPrevious().setActionCommand("Previous movement");
		vista.getMntmOpen().setActionCommand("Open");
		vista.getMntmSave().setActionCommand("Save");
	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();
		if (comando.equals("Abrir preferencias")) {
			abrirPreferencias();
		} else if (comando.equals("Abrir celda blanca")) {
			abrirCeldaBlanca();
		} else if (comando.equals("Abrir celda negra")) {
			abrirCeldaNegra();
		} else if (comando.equals("Abrir celda borde celda")) {
			abrirBorde();
		} else if (comando.equals("Abrir celda borde celda matar")) {
			abrirBordeMatar();
		} else if (comando.equals("Reiniciar")) {
			reiniciaPartida();
		} else if (arg0.getSource() instanceof Celda) {
			comprobarMovimiento((Celda) arg0.getSource());
		} else if (comando.equals("Previous movement")) {
			previousMovement();
		} else if (comando.equals("Next movement")) {
			nextMovement();
		}else if(comando.equals("Open")) {
			open();
		}else if(comando.equals("Save")) {
			save();
		}

	}

	private void open() {
		JFileChooser jfc = new JFileChooser();
		 
		 int opcion = jfc.showOpenDialog(vista);
		 
		 if(opcion == JFileChooser.APPROVE_OPTION) {
			 try {
				 ObjectInputStream ois = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
				 reiniciaPartida();
				 dlm = (DefaultListModel<Movimiento>) ois.readObject();
				 stack = (Deque<Movimiento>) ois.readObject();

			 }catch(Exception e) {
				 e.printStackTrace();
			 }
			 turno = Color.WHITE;
			 vista.getPanelMovimientos().getList().setModel(dlm);
			 Movimiento m;
			 for (int i = 0; i<dlm.getSize();i++) {
				 m = dlm.get(i);
				 vista.getPanelTablero().getPiezaAt(m.getOrigen()).moveTo(m.getDestino());
				 cambioTurno();
				 if(m.getFicha()!=null) {
					gestionFichasEliminadas.addPiece(m.getFicha());
			 
				 }
			 }
			controladorTurno.actualizaTurno(turno);

		 }
		 
	}

	private void save() {
		 JFileChooser jfc = new JFileChooser();
		 		 
		 int opcion = jfc.showSaveDialog(vista);
		 
		 if(opcion == JFileChooser.APPROVE_OPTION) {
			 try {
				 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(jfc.getSelectedFile()));
				 oos.writeObject(dlm);
				 oos.writeObject(stack);

				 
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		 }
	}

	private void activaCeldas(boolean b) {
		for (Celda c : vista.getPanelTablero().getTablero().values()) {
			c.setEnabled(b);
			c.setBorder(null);
		}
	}

	private void reiniciaPartida() {
		activaCeldas(true);
		while (dlm.size() > 0) {
			previousMovement();
		}
		dlm.removeAllElements();
		stack.removeAll(stack);
	}

	private void abrirBorde() {
		java.awt.Color c = JColorChooser.showDialog(jfPreferencias.getBtnBordeCelda(),
				"Selecciona color del borde al mover", jfPreferencias.getBtnBordeCelda().getBackground());

		if (c != null) {
			jfPreferencias.getBtnBordeCelda().setBackground(c);
			MyConfig.getInstancia().setBorderCell(c);
			;
			Celda.colorBordeCeldaNormal = c;
			((Tablero) vista.getPanelTablero()).repaintBoard();
		}

	}

	private void abrirBordeMatar() {
		java.awt.Color c = JColorChooser.showDialog(jfPreferencias.getBtnBordeCeldaMatar(),
				"Selecciona color del borde al comer", jfPreferencias.getBtnBordeCeldaMatar().getBackground());

		if (c != null) {
			jfPreferencias.getBtnBordeCeldaMatar().setBackground(c);
			MyConfig.getInstancia().setBorderKillCell(c);
			;
			Celda.colorBordeCeldaComer = c;
			((Tablero) vista.getPanelTablero()).repaintBoard();
		}

	}

	private void abrirCeldaBlanca() {
		java.awt.Color c = JColorChooser.showDialog(jfPreferencias.getBtnCeldaBlanca(),
				"Selecciona color de las celdas blancas", jfPreferencias.getBtnCeldaBlanca().getBackground());

		if (c != null) {
			jfPreferencias.getBtnCeldaBlanca().setBackground(c);
			MyConfig.getInstancia().setWhiteCellColor(c);
			Celda.colorCeldaBlanca = c;
			((Tablero) vista.getPanelTablero()).repaintBoard();
		}
	}

	private void abrirCeldaNegra() {
		java.awt.Color c = JColorChooser.showDialog(jfPreferencias.getBtnCeldaNegra(),
				"Selecciona color de las celdas negras", jfPreferencias.getBtnCeldaNegra().getBackground());

		if (c != null) {
			jfPreferencias.getBtnCeldaNegra().setBackground(c);
			MyConfig.getInstancia().setBlackCellColor(c);
			Celda.colorCeldaNegra = c;
			((Tablero) vista.getPanelTablero()).repaintBoard();
		}

	}

	private void nextMovement() {
		try {
			Movimiento m = stack.pop();

			Coordenada origen, destino;
			origen = m.getOrigen();
			destino = m.getDestino();
			switch (m.getTipoAccion()) {
			case Movimiento.NOT_KILL:
				vista.getPanelTablero().getPiezaAt(origen).moveTo(destino);

				break;
			case Movimiento.KILL:
				vista.getPanelTablero().getPiezaAt(origen).moveTo(destino);
				gestionFichasEliminadas.addPiece(m.getFicha());
				break;
			case Movimiento.RISE:
				vista.getPanelTablero().getPiezaAt(origen).moveTo(destino);
				break;
			case Movimiento.RISE_KILLING:
				vista.getPanelTablero().getPiezaAt(origen).moveTo(destino);
				gestionFichasEliminadas.addPiece(m.getFicha());
				break;
			default:
				throw new Exception("Tipo no conocido");
			}
			dlm.addElement(m);
			Movimiento.increaseNumberOfMovements();
			cambioTurno();
			controladorTurno.actualizaTurno(turno);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista, "No hay movimientos para rehacer", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void previousMovement() {
		try {
			Movimiento m = dlm.remove(dlm.size() - 1);
			stack.push(m);
			Coordenada origen, destino;
			origen = m.getOrigen();
			destino = m.getDestino();
			switch (m.getTipoAccion()) {
			case Movimiento.NOT_KILL:
				vista.getPanelTablero().getPiezaAt(destino).moveTo(origen);
				break;
			case Movimiento.KILL:
				vista.getPanelTablero().getPiezaAt(destino).moveTo(origen);
				m.getFicha().colocate(destino);
				gestionFichasEliminadas.removePiece(m.getFicha());
				if (m.getFicha().getColor() == Color.WHITE) {
					vista.getPanelTablero().getBlancas().add(m.getFicha());
				} else {
					vista.getPanelTablero().getNegras().add(m.getFicha());
				}
				break;
			case Movimiento.RISE:
				m.getFichaPeon().moveTo(origen);

				if (m.getFichaPeon().getColor() == Color.WHITE) {
					vista.getPanelTablero().getBlancas().remove(m.getFichaGenerada());
					vista.getPanelTablero().getBlancas().add(m.getFichaPeon());

				} else {
					vista.getPanelTablero().getNegras().remove(m.getFichaGenerada());
					vista.getPanelTablero().getNegras().add(m.getFichaPeon());
				}

				break;
			case Movimiento.RISE_KILLING:
				m.getFichaPeon().moveTo(origen);
				m.getFicha().moveTo(destino);

				if (m.getFichaPeon().getColor() == Color.WHITE) {
					vista.getPanelTablero().getBlancas().remove(m.getFichaGenerada());
					vista.getPanelTablero().getBlancas().add(m.getFichaPeon());
					vista.getPanelTablero().getNegras().add(m.getFicha());

				} else {
					vista.getPanelTablero().getNegras().remove(m.getFichaGenerada());
					vista.getPanelTablero().getNegras().add(m.getFichaPeon());
					vista.getPanelTablero().getBlancas().add(m.getFicha());
				}

				gestionFichasEliminadas.removePiece(m.getFicha());

				break;
			default:
				throw new Exception("Tipo no conocido");
			}
			Movimiento.decreaseNumberOfMovements();
			cambioTurno();
			controladorTurno.actualizaTurno(turno);
		} catch (ArrayIndexOutOfBoundsException ae) {
			JOptionPane.showMessageDialog(vista, "No hay movimientos para deshacer", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(vista, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void abrirPreferencias() {
		jfPreferencias = new Preferencias();

		jfPreferencias.setVisible(true);

		// Action Listeners
		jfPreferencias.getBtnCeldaBlanca().addActionListener(this);
		jfPreferencias.getBtnCeldaNegra().addActionListener(this);
		jfPreferencias.getBtnBordeCelda().addActionListener(this);
		jfPreferencias.getBtnBordeCeldaMatar().addActionListener(this);
		// Action Commands
		jfPreferencias.getBtnCeldaBlanca().setActionCommand("Abrir celda blanca");
		jfPreferencias.getBtnCeldaNegra().setActionCommand("Abrir celda negra");
		jfPreferencias.getBtnBordeCelda().setActionCommand("Abrir celda borde celda");
		jfPreferencias.getBtnBordeCeldaMatar().setActionCommand("Abrir celda borde celda matar");
	}

	private void comprobarMovimiento(Celda c) {

		if (piezaSeleccionada == null) {
			movimientoSinPiezaSeleccionada(c);
		} else {
			movimientoConPiezaSeleccionada(c);
		}

	}

	private void movimientoConPiezaSeleccionada(Celda c) {
		Tablero tablero = (Tablero) vista.getPanelTablero();
		Coordenada coord = tablero.getCoordenadaFromCell(c);
		Set<Coordenada> movimientos = piezaSeleccionada.getNextMovements();
		Movimiento m = null;
		Coordenada origen = piezaSeleccionada.getPosicion();
		Coordenada destino = tablero.getCoordenadaFromCell(c);
		if (movimientos.contains(coord)) {
			// Comprobamos si matamos pieza
			if (c.contienePieza()) {
				// Comprobamos si el peon se transforma en reina matando
				if ((tablero.getCoordenadaOfCelda(c).getRow() == 8 || tablero.getCoordenadaOfCelda(c).getRow() == 1)
						&& piezaSeleccionada instanceof Pawn) {
					m = new Movimiento(origen, destino, Movimiento.RISE_KILLING, c.getPieza(), null, piezaSeleccionada);
				} else {
					m = new Movimiento(origen, destino, Movimiento.KILL, c.getPieza(), null, null);
				}
				gestionFichasEliminadas.addPiece(c.getPieza());
			}
			// Si m=null no comemos pieza

			// Comprobamos si es un movimiento normal o si es un peon coronando
			if (m == null
					&& (tablero.getCoordenadaOfCelda(c).getRow() == 8 || tablero.getCoordenadaOfCelda(c).getRow() == 1)
					&& piezaSeleccionada instanceof Pawn) {
				m = new Movimiento(origen, destino, Movimiento.RISE, null, null, piezaSeleccionada);
			} else if (m == null) {
				m = new Movimiento(origen, destino, Movimiento.NOT_KILL, null, null, null);
			}
			piezaSeleccionada.moveTo(coord);

			if (m.getTipoAccion() == 1 || m.getTipoAccion() == 2) {
				m.setFichaGenerada(tablero.getPiezaAt(coord));
			}

			dlm.addElement(m);

			for (Coordenada movimiento : movimientos) {
				((Tablero) vista.getPanelTablero()).getCeldaAt(movimiento).setBorder(null);
			}
			piezaSeleccionada = null;
			controladorTurno.actualizaFichaSeleccionada(piezaSeleccionada);
			cambioTurno();
			controladorTurno.actualizaTurno(turno);
			stack.removeAll(stack);
		} else {
			JOptionPane.showMessageDialog(vista, "No te puedes mover ahi", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void movimientoSinPiezaSeleccionada(Celda c) {

		if (!c.contienePieza()) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza", "Error", JOptionPane.ERROR_MESSAGE);

		} else if (c.getPieza().getColor() != turno) {
			JOptionPane.showMessageDialog(vista, "Debes seleccionar una pieza de tu color", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (c.getPieza().getNextMovements().size() == 0) {
			JOptionPane.showMessageDialog(vista, "Esa pieza no la puedes mover", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			piezaSeleccionada = c.getPieza();
			controladorTurno.actualizaFichaSeleccionada(piezaSeleccionada);
			Set<Coordenada> posiblesMovimientos = piezaSeleccionada.getNextMovements();
			Tablero tablero = (Tablero) vista.getPanelTablero();
			for (Coordenada coord : posiblesMovimientos) {
				Celda celda = tablero.getCeldaAt(coord);
				if (celda.contienePieza()) {
					celda.resaltar(Celda.colorBordeCeldaComer, 3);
				} else {
					celda.resaltar(Celda.colorBordeCeldaNormal, 3);
				}
			}

		}
	}

	private void cambioTurno() {
		if (turno == Color.BLACK) {
			turno = Color.WHITE;
			compruebaJaque(Color.BLACK);
		} else {
			turno = Color.BLACK;
			compruebaJaque(Color.WHITE);
		}
		compruebaFin();

	}

	private void compruebaJaque(Color color) {
		ArrayList<Coordenada> movements = new ArrayList<Coordenada>();
		if (color == Color.BLACK) {
			for (Pieza p : vista.getPanelTablero().getNegras()) {
				movements.addAll(p.getNextMovements());
			}
			if(movements.contains(vista.getPanelTablero().getWhiteKing().getPosicion())){
				JOptionPane.showMessageDialog(vista, "Turno de blancas, estas en jaque", "JAQUE", JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			for (Pieza p : vista.getPanelTablero().getBlancas()) {
				movements.addAll(p.getNextMovements());
			}
			if(movements.contains(vista.getPanelTablero().getBlackKing().getPosicion())){
				JOptionPane.showMessageDialog(vista, "Turno de negras, estas en jaque", "JAQUE", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		

	}
	
	private void compruebaFin() {
		if(!vista.getPanelTablero().whiteKingIsAlive()) {
			JOptionPane.showMessageDialog(vista, "El rey blanco ha MUERTO, \n ganan NEGRAS", "Fin partida", JOptionPane.INFORMATION_MESSAGE);
			activaCeldas(false);
		}else if(!vista.getPanelTablero().blackKingIsAlive()) {
			JOptionPane.showMessageDialog(vista, "El rey negro ha MUERTO, \n ganan BLANCAS", "Fin partida", JOptionPane.INFORMATION_MESSAGE);
			activaCeldas(false);
		}else {
			activaCeldas(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component c = e.getComponent();
		if (c == vista.getPanelMovimientos().getList()) {
			int index = vista.getPanelMovimientos().getList().getSelectedIndex();

			while (dlm.size() > index) {
				previousMovement();

			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
