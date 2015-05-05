/*
 * NouveauAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet d'ouvrir un nouveau tableur lors de l'activation de notre �v�nement
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class NouveauAction extends AbstractAction {
    
    /**
     * 
     * @param texte nom du menu
     */
    public NouveauAction(String texte) {
        super(texte);
    }

    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}
