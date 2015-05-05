/*
 * QuitterAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.calculatrice.Menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

/** 
 * Permet de fermer le tableur et de revenir au menu lors de l'activation de 
 * cet �v�nement
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class QuitterAction extends AbstractAction {
    
    /** fen�tre � fermer */
    private JFrame fenetre;
    
    /**
     *
     * @param fenetre fenetre � fermer
     * @param texte nom du menu
     */
    public QuitterAction(JFrame fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        
        // On ouvre le menu
    	new Menu();
    	
    	// On ferme notre tableur
    	fenetre.dispose();
		
    }
}
