package es.ieslavereda.Chess.controladores;

import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.GestionFichasEliminadas;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.vista.JPFichasEliminadas;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorFichasEliminadas implements GestionFichasEliminadas{

	private JPFichasEliminadas vista;
	private HashMap<Pieza,JLabel> fichasEliminadas;
	
	public ControladorFichasEliminadas(JPFichasEliminadas panel) {
		vista = panel;
		fichasEliminadas = new HashMap<Pieza,JLabel>();
	}
	
	private void add(Pieza pieza, JPanel panel) {

	    JLabel label = new JLabel();
	    label.setOpaque(true);
	    ImageIcon image = new ImageIcon(VistaPrincipal.class.getResource("/es/ieslavereda/Chess/recursos/" + pieza.getFileName()));
	    ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(30, 30, 30));
	    label.setIcon(scaledImage);
	    panel.add(label);

	    fichasEliminadas.put(pieza, label);
	}


	private void remove(Pieza pieza, JPanel panel) {
		// Eliminamos la JLabel y repintamos
	    panel.remove(fichasEliminadas.get(pieza));
	    panel.repaint();

	    // Eliminamos la ficha de la coleccion
	    fichasEliminadas.remove(pieza);
		
	}

	@Override
	public void addPiece(Pieza pieza) {
		if(pieza.getColor()==Color.WHITE) {
			add(pieza,vista.getPanelBlancas());
		}else {
			add(pieza,vista.getPanelNegras());
		}
		
	}

	@Override
	public void removePiece(Pieza pieza) {
		JLabel label = fichasEliminadas.get(pieza);
		
		if (pieza.getColor() == Color.WHITE) {
			vista.getPanelBlancas().remove(label);
			vista.getPanelBlancas().repaint();
		}else {
			vista.getPanelNegras().remove(label);
			vista.getPanelNegras().repaint();
		}
		
	}

}
