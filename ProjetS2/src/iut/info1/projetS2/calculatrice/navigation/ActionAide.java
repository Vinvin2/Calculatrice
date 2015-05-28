/*
 * ActionAide.java               26 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Calculatrice;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet d'appeler la classe Aide car on ne peut pas appeler cette dernière 
 * directement via le JMenuBar étant donné que la classe n'hérite pas de 
 * AbstractAction.
 * @author Vincent
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ActionAide extends AbstractAction {
    
    /**
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame 
     * @param fenetre de notre calculatrice
     * @param texte nom du menu
     */
    public ActionAide(Calculatrice fenetre, String texte) {
        super(texte);
    }

    /**
	 * On ouvre une fenêtre contenant les aides et exemples d'utilisation
     */
    public void actionPerformed(ActionEvent e) {

    	// On ouvre la fenêtre d'aide
    	new Aide();

    }

}
