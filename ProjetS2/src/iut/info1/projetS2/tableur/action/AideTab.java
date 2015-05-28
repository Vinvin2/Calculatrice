/*
 * Aide.java					26 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.tableur.action;

import java.awt.Dimension;
import java.awt.Insets;

import iut.info1.projetS2.calculatrice.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Classe qui permet d'ouvrir une fenêtre contenant les aides,
 * exemples d'utilisation et qui regroupe les commandes disponibles 
 * @author Vincent
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AideTab extends JFrame {
    
    /** Container principal de l'application */
    private Container containerPrincipal = new Container(900, 700);

    /** Ecran où les commandes disponibles seront affichées */
    private TexteTabAide ecranCommandes = new TexteTabAide();

    /** Barre de défilement pour l'écran */
    private JScrollPane scroll = new JScrollPane(ecranCommandes);

	/**
	 * Création de la fenêtre contenant le menu principal de l'application
	 */
	public AideTab() {

		super();

		//Définit un titre pour la fenêtre
		this.setTitle("AIDE : Commandes Mini-Tableur");
		
		// On définit la taille de la fenêtre
		this.setSize(630, 500);

		// On place la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);

		// On ferme la fenêtre en cliquant sur la croix rouge
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// On empêche la modification de la taille de la fenêtre
		this.setResizable(false);

		// On ajoute les boutons
		this.setContentPane(containerPrincipal);

		// On rend visible la fenêtre
		this.setVisible(true);
		
		// propriétés du scroll pour le défilement de la fenêtre
        scroll.setVerticalScrollBarPolicy
                                    (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(610, 460));

        // On ajoute l'écran contenant les commandes à la fenêtre
        containerPrincipal.add(scroll);
        ecranCommandes.setMargin(new Insets(20,30,20,20));

        // On fait en sorte que la scrollbar démarre bien en haut de la fenêtre
        ecranCommandes.setCaretPosition(0);
        
	}
	
}
