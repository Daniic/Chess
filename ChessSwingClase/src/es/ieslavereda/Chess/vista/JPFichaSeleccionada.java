package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class JPFichaSeleccionada extends JPanel {
	private JLabel label;

	public JPFichaSeleccionada() {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.CENTER);

	}

	public JLabel getLabel() {
		return label;
	}
	

}
