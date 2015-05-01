/**
 * Calculatrice.java					30 avril 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JOptionPane;

//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.GridLayout;

/**
 * Création d'une interface graphique pour l'application Calculatrice
 * @author 20-20
 *
 */

@SuppressWarnings("serial")
public class Calculatrice extends JFrame {
	
	/** Container principal de l'application */
	private JPanel pan = new JPanel();
	/** Bouton accueil */
	private Bouton boutonAcc = new Bouton("ACCUEIL");
	/** Bouton aide */
	private Bouton boutonAide = new Bouton("AIDE");
	/** Bouton quitter */
	private Bouton boutonQuit = new Bouton("QUITTER");
	/** Bouton calculer */
	private Bouton boutonCalc = new Bouton("CALCULER");

	/** Ecran où les commandes et leur résultat seront affichées */
	private Ecran ecran = new Ecran();
        /** Champ de texte pour les exécutions de commandes */
        private ExecuteurCommandes executeur = new ExecuteurCommandes();

	/**
	 * Créé la fenêtre 
	 */
	public Calculatrice() {

		super();

		//Définit un titre pour la fenêtre
		this.setTitle("Calculatrice");

		// On définit la taille de la fenêtre
		this.setSize(900, 700);

		// On place la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);

		// On ferme la fenêtre en cliquant sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On empêche la modification de la taille de la fenêtre
		this.setResizable(false);

		// On définit la couleur de fond de la fenêtre 
		pan.setBackground(new Color(255,228,196)); 
		
		// On prévient notre JFrame que notre JPanel sera son content pane
		this.setContentPane(pan);

		//Ajout des boutons à notre content pane
		this.setContentPane(pan);
		// Ces boutons sont placés sur la partie gauche de la fenêtre
		pan.add(ecran);
		pan.add(executeur);
		pan.add(boutonAcc);
		pan.add(boutonAide);
		pan.add(boutonQuit);
		pan.add(boutonCalc);
		
//		GridLayout gl = new GridLayout();
//		gl.setColumns(1); // Les boutons sont placés sur une colonne
//		gl.setRows(3);    // et sur trois lignes
//		this.setLayout(gl);
//		gl.setHgap(8); //Cinq pixels d'espace entre les colonnes 
//		gl.setVgap(8); //Cinq pixels d'espace entre les lignes 
		
        // On rend visible la fenêtre
		this.setVisible(true);

	}

}
