/*
 * BoutonMenu.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 * Classe qui permet de construire un bouton personnalis�
 * @author groupe Projet
 * @version 0.1
 */
@SuppressWarnings("serial")
public class BoutonMenu extends JButton {
	
	/** fenetre de notre menu */
	private Menu fenetre;

    /**
     * Cr�� un type de bouton que l'on utilisera pour la partie mini-calculatrice
     * @param texte le texte du bouton
     */
    public BoutonMenu(Menu fenetre, String texte) {
        super(texte);

        // On g�re la taille des boutons
        setPreferredSize(new Dimension(500, 100));

        // On d�finit la couleur des boutons
        setBackground(new Color(255,160,122));
        
       // D�claration d'une police et d'une taille
        Font f = new Font("Calibri", Font.PLAIN, 32);
        
        // Ajout de la police � notre console
        setFont(f);
        
        // Le curseur devient une main quandon survole un bouton
        setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));    
        
		
		//actionSouris();
	}
//	/**
//	 * Quand on effectue un clic sur le bouton sans relacher la souris,
//	 * la couleur de fond du bouton change
//	 * @param e
//	 */
//	public void actionSouris() {
//		fenetre.getContainerPrincipal().addMouseListener(new MouseAdapter() {
//			public void MousseClicked(ActionEvent e) {
//				
//				if (SwingUtilities.isLeftMouseButton(null)) {
//					// On d�finit la nouvelle couleur des boutons
//					setBackground(new Color(255,127,36));
//				}
//			}
//		});
//	}
}
