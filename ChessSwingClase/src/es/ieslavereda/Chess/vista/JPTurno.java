package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class JPTurno extends JPanel {
	private JPanel panelPieza;
	private JPanel panelTurnoPieza;
	private JLabel labelPieza;
	private JLabel labelTurno;

	/**
	 * Create the panel.
	 */
	public JPTurno() {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Turn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		panelTurnoPieza = new JPanel();
		
		panelPieza = new JPanel();
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panelPieza.setLayout(new BorderLayout(0, 0));
		
		labelPieza = new JLabel("");
		labelPieza.setHorizontalAlignment(SwingConstants.CENTER);
		panelPieza.add(labelPieza, BorderLayout.CENTER);
		panelTurnoPieza.setLayout(new BorderLayout(0, 0));
		
		labelTurno = new JLabel("");
		labelTurno.setHorizontalAlignment(SwingConstants.CENTER);
		panelTurnoPieza.add(labelTurno, BorderLayout.CENTER);
		
		JLabel lblSelected = new JLabel("Selected");
		panel_1.add(lblSelected);
		
		JLabel lblMove = new JLabel("Move");
		panel.add(lblMove);
		setLayout(new MigLayout("", "[126px]", "[27px][100.00px][29px][90.00px]"));
		add(panelPieza, "cell 0 3,grow");
		add(panelTurnoPieza, "cell 0 1,grow");
		add(panel_1, "cell 0 2,grow");
		add(panel, "cell 0 0,grow");

	}

	public JPanel getPanelPieza() {
		return panelPieza;
	}

	public JPanel getPanelTurnoPieza() {
		return panelTurnoPieza;
	}

	public JLabel getLabelPieza() {
		return labelPieza;
	}

	public JLabel getLabelTurno() {
		return labelTurno;
	}
	
}
