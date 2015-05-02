/**
 * Calculatrice.java					30 avril 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JOptionPane;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Font;

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
	private JButton boutonAcc = new JButton("ACCUEIL");
	/** Bouton aide */
	private JButton boutonAide = new JButton("AIDE");
	/** Bouton quitter */
	private JButton boutonQuit = new JButton("QUITTER");

	/**
	 * Cr�� la fen�tre 
	 */
	public Calculatrice() {

		super();

		//D�finit un titre pour la fen�tre
		this.setTitle("Calculatrice");

		// On d�finit la taille de la fen�tre
		this.setSize(800, 600);

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
		pan.add(boutonAcc);
		pan.add(boutonAide);
		pan.add(boutonQuit);

//		GridLayout gl = new GridLayout();
//		gl.setColumns(1); // Les boutons sont plac�s sur une colonne
//		gl.setRows(3);    // et sur trois lignes
//		this.setLayout(gl);
//		gl.setHgap(8); //Cinq pixels d'espace entre les colonnes 
//		gl.setVgap(8); //Cinq pixels d'espace entre les lignes 

		// On g�re la taille des boutons
		boutonAcc.setPreferredSize(new Dimension(100, 60));
		boutonAide.setPreferredSize(new Dimension(100, 60));
		boutonQuit.setPreferredSize(new Dimension(100, 60));
		
		// On d�finit la couleur des boutons
		boutonAcc.setBackground(new Color(250,128,114));
		boutonAide.setBackground(new Color(250,128,114));
		boutonQuit.setBackground(new Color(250,128,114));
		
        // On rend visible la fen�tre
		this.setVisible(true);

	}

}
