package es.ieslavereda.Chess.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import es.ieslavereda.Chess.model.common.Tablero;


public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel panelTablero;
	private JPFichasEliminadas panelEliminados;
	private JPTurno panelTurno;
	private JPMovements panelMovimientos;
	private JMenuItem mntmPreferences;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 561);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		
		mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnAbout.add(mntmAbout);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panelTablero = new Tablero();
		
		panelEliminados = new JPFichasEliminadas();
		
		panelTurno = new JPTurno();
		
		panelMovimientos = new JPMovements();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelTablero, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panelTurno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelMovimientos, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
						.addComponent(panelEliminados, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(4))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelTablero, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(panelMovimientos, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addComponent(panelTurno, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelEliminados, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
					.addContainerGap())
		);
		GroupLayout gl_panelMovimientos = new GroupLayout(panelMovimientos);
		gl_panelMovimientos.setHorizontalGroup(
			gl_panelMovimientos.createParallelGroup(Alignment.LEADING)
				.addGap(0, 135, Short.MAX_VALUE)
		);
		gl_panelMovimientos.setVerticalGroup(
			gl_panelMovimientos.createParallelGroup(Alignment.LEADING)
				.addGap(0, 157, Short.MAX_VALUE)
		);
		
		contentPane.setLayout(gl_contentPane);
	}
	public Tablero getPanelTablero() {
		return (Tablero)panelTablero;
	}
	public JMenuItem getMntmPreferences() {
		return mntmPreferences;
	}
	public JPFichasEliminadas getPanelEliminados() {
		return panelEliminados;
	}
	public JPTurno getPanelTurno() {
		return panelTurno;
	}
	public JPMovements getPanelMovimientos() {
		return panelMovimientos;
	}
	public JMenuItem getMntmNewGame() {
		return mntmNewGame;
	}
	public void setMntmNewGame(JMenuItem mntmNewGame) {
		this.mntmNewGame = mntmNewGame;
	}
	public JMenuItem getMntmOpen() {
		return mntmOpen;
	}
	public JMenuItem getMntmSave() {
		return mntmSave;
	}
	public void setPanelTablero(JPanel panelTablero) {
		this.panelTablero = panelTablero;
	}
	public void setPanelMovimientos(JPMovements panelMovimientos) {
		this.panelMovimientos = panelMovimientos;
	}
	public void setPanelEliminados(JPFichasEliminadas panelEliminados) {
		this.panelEliminados = panelEliminados;
	}
	public void setPanelTurno(JPTurno panelTurno) {
		this.panelTurno = panelTurno;
	}
	
	
	
}
