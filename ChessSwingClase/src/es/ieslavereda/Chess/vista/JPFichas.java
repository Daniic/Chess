package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import java.awt.Cursor;

public class JPFichas extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPFichas(String titulo) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBorder(new TitledBorder(null, titulo, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

	}

}
