/*
 * Menu.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import iut.info1.projetS2.calculatrice.Container;
import iut.info1.projetS2.calculatrice.navigation.Quitter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Création d'un menu qui offrira trois possibilités :
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

	/** panel contenant du vide */
	private Container vide = new Container(900,80);
	
	/** panel contenant un espace */
	private Container espace = new Container(900,50);
	
	/** panel contenant un second espace */
	private Container espace2 = new Container(900,60);
	
	/** panel contenant un troisième espace */
	private Container espace3 = new Container(900,30);
	
	/** panel contenant un quatrième espace */
	private Container espace4 = new Container(900,30);
	
	/** Bouton Calculatrice */
	private BoutonMenu boutonCalculatrice = 
			new BoutonMenu(this, "MINI CALCULATRICE");

	/** Bouton Tableur */
	private BoutonMenu boutonTab = new BoutonMenu(this, "MINI TABLEUR");

	/** Bouton Quitter */
	private BoutonMenu boutonQuit2 = new BoutonMenu(this, "QUITTER");
	
	/** Police d'écriture pour le titre */
    private static final Font PoliceTitre = new Font("Verdana", Font.BOLD, 24);

	/**
	 * Création de la fenêtre contenant le menu principal de l'application
	 */
	public Menu() {

		super();

		//Définit un titre pour la fenêtre
		this.setTitle("Mini-Calculatrice - Mini-Tableur ");

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
		
		// On définit une image comme fond d'écran
		setContentPane(new JLabel(new ImageIcon("imgmenu.png")));
		
		// On ajoute un titre/slogan au dessus des boutons du menu
		JLabel titre = 
				new JLabel("Avec Mini-Calculator et Mini-Tablor, devenez "
						 + "le plus fort !");
		titre.setFont(PoliceTitre);
		titre.setForeground(new Color(255,160,122));
		containerPrincipal.add(espace);
		containerPrincipal.add(titre);
		containerPrincipal.add(espace2);
		
		// On place les boutons au centre de l'écran sur une ligne verticale
		containerPrincipal.add(boutonCalculatrice);
		containerPrincipal.add(espace3);
		containerPrincipal.add(boutonTab);
		containerPrincipal.add(espace4);
		containerPrincipal.add(boutonQuit2);
        
		// On affecte une action pour chaque bouton
		boutonQuit2.addActionListener(new Quitter(this));
		boutonCalculatrice.addActionListener(new ActionCalculatrice(this));
		boutonTab.addActionListener(new ActionTableur(this));

		// On rend visible la fenêtre
		this.setVisible(true);
		
		containerPrincipal.add(vide);
		
		// On change l'icone de la fenêtre
        Image icone = Toolkit.getDefaultToolkit().getImage("logo2.jpg");
        setIconImage(icone);
            
	}

}