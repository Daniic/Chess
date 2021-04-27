package es.ieslavereda.Chess.controladores;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ieslavereda.Chess.model.common.Color;
import es.ieslavereda.Chess.model.common.Pieza;
import es.ieslavereda.Chess.vista.JPFichaSeleccionada;
import es.ieslavereda.Chess.vista.JPTurno;
import es.ieslavereda.Chess.vista.VistaPrincipal;

public class ControladorTurno {

	private JPTurno panel;

	public ControladorTurno(JPTurno vista) {
		panel = vista;
		actualizaTurno(Color.WHITE);
	}

	public void actualizaFichaSeleccionada(Pieza pieza) {
		if (pieza != null) {
			ImageIcon image = new ImageIcon(
					VistaPrincipal.class.getResource("/es/ieslavereda/Chess/recursos/" + pieza.getFileName()));
			ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(60, 60, 60));
			panel.getLabelPieza().setIcon(scaledImage);
		}else {
			panel.getLabelPieza().setIcon(null);
		}
	}

	public void actualizaTurno(Color color) {
		ImageIcon image;
		if (color == Color.WHITE) {
			image = new ImageIcon(VistaPrincipal.class.getResource("/es/ieslavereda/Chess/recursos/b_peon.gif"));
		} else {
			image = new ImageIcon(VistaPrincipal.class.getResource("/es/ieslavereda/Chess/recursos/n_peon.gif"));
		}
		ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(60, 60, 60));
		panel.getLabelTurno().setIcon(scaledImage);
	}

}
