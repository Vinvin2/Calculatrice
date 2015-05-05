/**
 * Menu.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.calculatrice;

import javax.swing.JFrame;

/**
 * Création d'un menu qui offrira trois possibilités :
 * <ul><li>Mini Calculatrice<li>
 * 	   <li>Mini Tableur</li>
 * 	   <li>Quitter l'application</li>
 * @author 20-20
 */

@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	/** Container principal de l'application */
	private Container containerPrincipal = new Container(900, 700);

	/** panel contenant les boutons de navigation */
	private Container containerNavigation = new Container(700, 700);

	/** Bouton Calculatrice */
	private BoutonMenu boutonCalculatrice = new BoutonMenu("MINI CALCULATRICE");

	/** Bouton Tableur */
	private BoutonMenu boutonTab = new BoutonMenu("MINI TABLEUR");

	/** Bouton Quitter */
	private BoutonMenu boutonQuit2 = new BoutonMenu("QUITTER");

	/**
	 * Création de la fenêtre contenant le menu principal de l'application
	 */
	public Menu() {

		super();

		//Définit un titre pour la fenêtre
		this.setTitle("Mini-Calculatrice - Mini Tableur ");

		// On utilisera les mêmes caractéristiques pour les autres fenêtres
		// afin d'avoir une IHM la plus homogène possible
		// On définit la taille de la fenêtre
		this.setSize(900, 700);

		// On place la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);

		// On ferme la fenêtre en cliquant sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On empêche la modification de la taille de la fenêtre
		this.setResizable(false);

		// On ajoute les boutons
		this.setContentPane(containerPrincipal);
		
		// On place les boutons au centre de l'écran sur une ligne verticale
		containerNavigation.add(boutonCalculatrice);
		containerNavigation.add(boutonTab);
		containerNavigation.add(boutonQuit2);
		
		// On affecte une action pour chaque bouton
		boutonQuit2.addActionListener(new ActionQuitter());

		// On rend visible la fenêtre
		this.setVisible(true);
		
	}
}