/*
 * QuitterAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.calculatrice.Menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** TODO commenter la responsabilit� de cette classe
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class QuitterAction extends AbstractAction {
    
    /**
     * TODO commenter l'�tat initial atteint
     * @param texte nom du menu
     */
    public QuitterAction(String texte) {
        super(texte);
    }

    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
    	new Menu();
		//TODO fermer la fenetre tableur
    }
}
