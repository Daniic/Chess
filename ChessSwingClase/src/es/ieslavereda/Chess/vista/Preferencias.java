package es.ieslavereda.Chess.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.ieslavereda.Chess.config.MyConfig;

import java.awt.GridLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Preferencias extends JFrame {

	private JPanel contentPane;
	private JButton btnCeldaBlanca;
	private JButton btnCeldaNegra;
	private JButton btnBordeCelda;
	private JButton btnBordeCeldaMatar;

	/**
	 * Create the frame.
	 */
	public Preferencias() {
		setTitle("Preferences");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 323, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][]", "[][][][][][][]"));

		JLabel lblColorCeldaBlanca = new JLabel("Color Celda Blanca");
		contentPane.add(lblColorCeldaBlanca, "cell 0 1");

		btnCeldaBlanca = new JButton("");
		btnCeldaBlanca.setBackground(new Color(MyConfig.getInstancia().getWhiteCellColor()));
		contentPane.add(btnCeldaBlanca, "cell 2 1");

		JLabel lblColorCeldaNegra = new JLabel("Color Celda Negra");
		contentPane.add(lblColorCeldaNegra, "cell 0 2");

		btnCeldaNegra = new JButton("");
		btnCeldaNegra.setBackground(new Color(MyConfig.getInstancia().getBlackCellColor()));
		contentPane.add(btnCeldaNegra, "cell 2 2");

		JLabel lblColorBordeCelda = new JLabel("Color Borde Celda");
		contentPane.add(lblColorBordeCelda, "cell 0 3");

		btnBordeCelda = new JButton("");
		btnBordeCelda.setBackground(new Color(MyConfig.getInstancia().getBorderNormalCell()));
		contentPane.add(btnBordeCelda, "cell 2 3");

		JLabel lblColorBordeCelda_1 = new JLabel("Color Borde Celda Comer");
		contentPane.add(lblColorBordeCelda_1, "cell 0 4");

		btnBordeCeldaMatar = new JButton("");
		btnBordeCeldaMatar.setBackground(new Color(MyConfig.getInstancia().getBorderKillCell()));
		contentPane.add(btnBordeCeldaMatar, "cell 2 4");

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnCerrar, "cell 2 6");
	}

	public JButton getBtnCeldaBlanca() {
		return btnCeldaBlanca;
	}

	public JButton getBtnCeldaNegra() {
		return btnCeldaNegra;
	}

	public JButton getBtnBordeCelda() {
		return btnBordeCelda;
	}

	public JButton getBtnBordeCeldaMatar() {
		return btnBordeCeldaMatar;
	}
	
	

}
