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
 * Cr�ation d'une interface graphique pour l'application Calculatrice
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

	/** Ecran o� les commandes et leur r�sultat seront affich�es */
	private Ecran ecran = new Ecran();
        /** Champ de texte pour les ex�cutions de commandes */
        private ExecuteurCommandes executeur = new ExecuteurCommandes();

	/**
	 * Cr�� la fen�tre 
	 */
	public Calculatrice() {

		super();

		//D�finit un titre pour la fen�tre
		this.setTitle("Calculatrice");

		// On d�finit la taille de la fen�tre
		this.setSize(900, 700);

		// On place la fen�tre au centre de l'�cran
		this.setLocationRelativeTo(null);

		// On ferme la fen�tre en cliquant sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On emp�che la modification de la taille de la fen�tre
		this.setResizable(false);

		// On d�finit la couleur de fond de la fen�tre 
		pan.setBackground(new Color(255,228,196)); 
		
		// On pr�vient notre JFrame que notre JPanel sera son content pane
		this.setContentPane(pan);

		//Ajout des boutons � notre content pane
		this.setContentPane(pan);
		// Ces boutons sont plac�s sur la partie gauche de la fen�tre
		pan.add(ecran);
		pan.add(executeur);
		pan.add(boutonAcc);
		pan.add(boutonAide);
		pan.add(boutonQuit);
		pan.add(boutonCalc);
		
//		GridLayout gl = new GridLayout();
//		gl.setColumns(1); // Les boutons sont plac�s sur une colonne
//		gl.setRows(3);    // et sur trois lignes
//		this.setLayout(gl);
//		gl.setHgap(8); //Cinq pixels d'espace entre les colonnes 
//		gl.setVgap(8); //Cinq pixels d'espace entre les lignes 
		
        // On rend visible la fen�tre
		this.setVisible(true);

	}

}
