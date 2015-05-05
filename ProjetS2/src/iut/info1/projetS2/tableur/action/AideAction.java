/*
 * AideAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * 
 * @author Mickaël
 * @version 0.1
 */
public class AideAction extends AbstractAction {
    
    private Tableur fenetre;
    
    /**
     * TODO commenter l'état initial atteint
     * @param fenetre à afficher
     * @param texte nom du menu
     */
    public AideAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}
