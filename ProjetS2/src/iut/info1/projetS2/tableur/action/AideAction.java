/*
 * AideAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet d'afficher l'aide lors de l'activation de l'évènement
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AideAction extends AbstractAction {
    
    /**
     * 
     * @param texte nom du sous-menu
     */
    public AideAction(String texte) {
        super(texte);

    }

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}
