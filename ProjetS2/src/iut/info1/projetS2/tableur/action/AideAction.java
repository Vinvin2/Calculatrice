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
 * @author Micka�l
 * @version 0.1
 */
public class AideAction extends AbstractAction {
    
    private Tableur fenetre;
    
    /**
     * TODO commenter l'�tat initial atteint
     * @param fenetre � afficher
     * @param texte nom du menu
     */
    public AideAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}
