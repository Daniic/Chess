package es.ieslavereda.Chess.vista;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class JPMovements extends JPanel {
	private JButton buttonPrevious;
	private JButton buttonNext;
	private JList list;

	/**
	 * Create the panel.
	 */
	public JPMovements() {
		setBorder(new TitledBorder(null, "Movements", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[108.00][grow]", "[grow][]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0 2 1,grow");
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		buttonPrevious = new JButton("<");
		add(buttonPrevious, "cell 0 1");
		
		buttonNext = new JButton(">");
		buttonNext.setHorizontalAlignment(SwingConstants.RIGHT);
		add(buttonNext, "cell 1 1,alignx right");

	}

	public JButton getButtonPrevious() {
		return buttonPrevious;
	}

	public void setButtonPrevious(JButton buttonPrevious) {
		this.buttonPrevious = buttonPrevious;
	}

	public JButton getButtonNext() {
		return buttonNext;
	}

	public void setButtonNext(JButton buttonNext) {
		this.buttonNext = buttonNext;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	
}
