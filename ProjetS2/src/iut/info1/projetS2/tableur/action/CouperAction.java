/*
 * CouperAction.java				4 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de couper le texte à l'intérieur de la console dans le presse-papier
 * lors de l'activation de l'évènement
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CouperAction extends AbstractAction {

    /**
     * 
     * @param texte nom du menu
     */
    public CouperAction(String texte) {
        
        // Prend les attributs de AbstractAction
        super(texte);
    }

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}