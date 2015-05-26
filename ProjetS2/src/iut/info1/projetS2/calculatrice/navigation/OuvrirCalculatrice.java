/*
 * OuvrirCalculatrice.java					26 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Calculatrice;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Permet d'ouvrir la calculatrice et de fermer le tableur
 * @author groupe projet
 * @version 0.1
 */
@SuppressWarnings("serial")
public class OuvrirCalculatrice extends AbstractAction {

	/** Fen�tre que l'on fermera apr�s avoir ouvert la fen�tre de la calculatrice */
	private JFrame fenetre;
	
    /**
     * R�cup�re la fen�tre que l'on devra fermer
     * @param fenetre
     */
    public OuvrirCalculatrice(JFrame fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    	// On ouvre une fenetre qui demande � l'utilisateur s'il veut oui ou non
    	// fermer le tableur afin d'ouvrir la calculatrice
    	int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous"
    			+ " ouvrir la calculatrice et fermer le tableur ?\n Attention, "
    			+ "les donn�es non sauvegard�es seront perdues !", "Vers la Calculatrice",
    			JOptionPane.YES_NO_OPTION); 

    	// si on veut aller sur la calculatrice
    	if (choix == JOptionPane.YES_NO_OPTION) {

    		//on ouvre la calculatrice
    		new Calculatrice();
    		// on ferme la fenetre
    		fenetre.dispose();
    	}

    }

}
