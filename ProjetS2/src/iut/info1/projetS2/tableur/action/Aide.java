/*
 * Aide.java					26 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.calculatrice.Container;

import javax.swing.JFrame;

/**
 * Classe qui permet d'ouvrir une fenêtre contenant les aides,
 * exemples d'utilisation et qui regroupe les commandes disponibles 
 * @author 20-20
 * @version 0.1
 */

@SuppressWarnings("serial")
public class Aide extends JFrame {
	
	/** Container principal de l'application */
	private Container containerPrincipal = new Container(900, 700);

	/**
	 * Création de la fenêtre contenant le menu principal de l'application
	 */
	public Aide() {

		super();

		//Définit un titre pour la fenêtre
		this.setTitle("AIDE : Commandes-Exemples");

		// On utilisera les mêmes caractéristiques pour les autres fenêtres
		// afin d'avoir une IHM la plus homogène possible
		// On définit la taille de la fenêtre
		this.setSize(630, 450);

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

	}
	
}
