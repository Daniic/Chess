package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class JPFichasEliminadas extends JPanel{
	private JPanel panelBlancas;
	private JPanel panelNegras;
	public JPFichasEliminadas() {
		setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		panelBlancas = new JPFichas("WHITE");
		add(panelBlancas, "cell 0 0,grow");
		
		panelNegras = new JPFichas("BLACK");
		add(panelNegras, "cell 0 1,grow");
	}
	public JPanel getPanelBlancas() {
		return panelBlancas;
	}
	public JPanel getPanelNegras() {
		return panelNegras;
	}

}
