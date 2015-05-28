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
 * Classe qui permet d'ouvrir une fen�tre contenant les aides,
 * exemples d'utilisation et qui regroupe les commandes disponibles 
 * @author Vincent
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AideTab extends JFrame {
    
    /** Container principal de l'application */
    private Container containerPrincipal = new Container(900, 700);

    /** Ecran o� les commandes disponibles seront affich�es */
    private TexteTabAide ecranCommandes = new TexteTabAide();

    /** Barre de d�filement pour l'�cran */
    private JScrollPane scroll = new JScrollPane(ecranCommandes);

	/**
	 * Cr�ation de la fen�tre contenant le menu principal de l'application
	 */
	public AideTab() {

		super();

		//D�finit un titre pour la fen�tre
		this.setTitle("AIDE : Commandes Mini-Tableur");
		
		// On d�finit la taille de la fen�tre
		this.setSize(630, 500);

		// On place la fen�tre au centre de l'�cran
		this.setLocationRelativeTo(null);

		// On ferme la fen�tre en cliquant sur la croix rouge
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// On emp�che la modification de la taille de la fen�tre
		this.setResizable(false);

		// On ajoute les boutons
		this.setContentPane(containerPrincipal);

		// On rend visible la fen�tre
		this.setVisible(true);
		
		// propri�t�s du scroll pour le d�filement de la fen�tre
        scroll.setVerticalScrollBarPolicy
                                    (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(610, 460));

        // On ajoute l'�cran contenant les commandes � la fen�tre
        containerPrincipal.add(scroll);
        ecranCommandes.setMargin(new Insets(20,30,20,20));

        // On fait en sorte que la scrollbar d�marre bien en haut de la fen�tre
        ecranCommandes.setCaretPosition(0);
        
	}
	
}
