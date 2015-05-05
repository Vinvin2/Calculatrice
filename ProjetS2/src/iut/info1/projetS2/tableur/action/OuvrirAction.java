/*
 * OuvrirAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet de charger un fichier tableur lors de l'activation de 
 * cet �v�nement
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class OuvrirAction extends AbstractAction {

    /**
     * 
     * @param texte nom du menu
     */
    public OuvrirAction(String texte) {
        super(texte);
    }

    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}
