/*
 * QuitterAction.java					24 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Calculatrice;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * Permet d'ouvrir la calculatrice et de fermer le menu de l'appli
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class QuitterAction extends AbstractAction {

	/** fenêtre que l'on devra fermer */
	private Calculatrice fenetre;
	
    /**
     * On récupère la fenêtre à fermer
     * @param fenetre
     * @param texte
     */
    public QuitterAction(Calculatrice fenetre, String texte) {
    	
    	super(texte);
    	
        this.fenetre = fenetre;
  
    }
    
    /**
     * Affiche une fenetre contenant un message pour savoir si l'utilisateur
     * veut vraiment quitter, si la fenetre de la calculatrice sera fermée
     * Si non on revient à la calculatrice sans rien faire.
     */
    public void actionPerformed(ActionEvent e) {
        
        // On ouvre une fenetre qui demande à l'utilisateur s'il veut oui ou
        // fermer l'application, tout en l'avertissant que les 
        // travaux non sauvegardés seront perdus
        int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous"
                    + " vraiment quitter l'application ?", "Quitter",
                    JOptionPane.YES_NO_OPTION); 
        
        // si on veut revenir au menu principal
        if (choix == JOptionPane.YES_NO_OPTION) {
         
            // On ferme l'application
        	System.exit(0);          
        }
        
    }
}
