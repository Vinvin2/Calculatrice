/*
 * OuvrirTableur.java					26 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Permet d'ouvrir le tableur et de fermer la calculatrice
 * @author Vincent
 * @version 0.1
 */
@SuppressWarnings("serial")
public class OuvrirTableur extends AbstractAction {

	/** fenêtre que l'on devra fermer */
	private JFrame fenetre;
	
    /**
     * On récupère la fenêtre à fermer
     * @param fenetre fenêtre concernée
     * @param texte message à afficher
     */
    public OuvrirTableur(JFrame fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    	// On ouvre une fenetre qui demande à l'utilisateur s'il veut oui ou non
    	// fermer la calculatrice afin d'ouvrir le tableur
    	int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous"
    			+ " ouvrir le tableur et fermer la calculatrice ?",
    			  "Vers le Tableur",JOptionPane.YES_NO_OPTION); 

    	// si on veut aller sur le tableur
    	if (choix == JOptionPane.YES_NO_OPTION) {


    		//on ouvre la calculatrice
    		new Tableur();
    		// on ferme la fenetre
    		fenetre.dispose();

    	}

    }

}
