/*
 * BoutonStyle.java					26 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;



/**
 * Permet de changer la couleur du bouton quand on clique dessus
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class BoutonStyle extends JButton {

	/** fenetre de notre menu */
	private Menu fenetre;
	
	/**
	 * 
	 * @param fenetre
	 */
	public BoutonStyle(Menu fenetre) {
		this.fenetre = fenetre;
		
		actionSouris();
	}
	/**
	 * Quand on effectue un clic sur le bouton sans relacher la souris,
	 * la couleur de fond du bouton change
	 * @param e
	 */
	public void actionSouris() {
		fenetre.addMouseListener(new MouseAdapter() {
			public void MoussePressed(ActionEvent e) {
		
		// On définit la nouvelle couleur des boutons
        setBackground(new Color(255,127,36));
        
			}
		});
	}
}

