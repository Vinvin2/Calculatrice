/*
 * CollerAction.java				4 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de coller du texte dans notre console lors de l'activation de 
 * l'�v�nement
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CollerAction extends AbstractAction {

    /**
     * 
     * @param texte nom du menu
     */
    public CollerAction(String texte) {
        super(texte);
    }

    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}
