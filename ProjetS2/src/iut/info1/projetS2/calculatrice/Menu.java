/*
 * Menu.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.calculatrice;

import javax.swing.JFrame;

/**
 * Cr�ation d'un menu qui offrira trois possibilit�s :
 * <ul><li>Mini Calculatrice</li>
 * 	   <li>Mini Tableur</li>
 * 	   <li>Quitter l'application</li>
 * </ul>
 * @author 20-20
 * @version 0.1
 */

@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	/** Container principal de l'application */
	private Container containerPrincipal = new Container(900, 700);

	/** panel contenant les boutons de navigation */
	private Container containerNavigation = new Container(700, 700);

	/** panel contenant du vide */
	private Container vide = new Container(900,80);
	
	/** panel contenant un espace */
	private Container espace1 = new Container(900,10);
	
	/** panel contenant un second espace */
	private Container espace2 = new Container(900,10);
	
	/** Bouton Calculatrice */
	private BoutonMenu boutonCalculatrice = new BoutonMenu("MINI CALCULATRICE");

	/** Bouton Tableur */
	private BoutonMenu boutonTab = new BoutonMenu("MINI TABLEUR");

	/** Bouton Quitter */
	private BoutonMenu boutonQuit2 = new BoutonMenu("QUITTER");

	/**
	 * Cr�ation de la fen�tre contenant le menu principal de l'application
	 */
	public Menu() {

		super();

		//D�finit un titre pour la fen�tre
		this.setTitle("Mini-Calculatrice - Mini Tableur ");

		// On utilisera les m�mes caract�ristiques pour les autres fen�tres
		// afin d'avoir une IHM la plus homog�ne possible
		// On d�finit la taille de la fen�tre
		this.setSize(900, 700);

		// On place la fen�tre au centre de l'�cran
		this.setLocationRelativeTo(null);

		// On ferme la fen�tre en cliquant sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On emp�che la modification de la taille de la fen�tre
		this.setResizable(false);

		// On ajoute les boutons
		this.setContentPane(containerPrincipal);
		
		// On place les boutons au centre de l'�cran sur une ligne verticale
		containerNavigation.add(boutonCalculatrice);
		containerNavigation.add(espace1);
		containerNavigation.add(boutonTab);
		containerNavigation.add(espace2);
        containerNavigation.add(boutonQuit2);
        
		
		// On affecte une action pour chaque bouton
		boutonQuit2.addActionListener(new ActionQuitter());
		boutonCalculatrice.addActionListener(new ActionCalculatrice(this));
		boutonTab.addActionListener(new ActionTableur(this));

		// On rend visible la fen�tre
		this.setVisible(true);
		
		containerPrincipal.add(vide);
		containerPrincipal.add(containerNavigation);
		
	}
}