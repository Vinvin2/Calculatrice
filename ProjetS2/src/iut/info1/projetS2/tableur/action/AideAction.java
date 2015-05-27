/*
 * AideAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet d'afficher l'aide lors de l'activation de l'évènement
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AideAction extends AbstractAction {
    
    /** Fenetre de notre application */
    @SuppressWarnings("unused")
    private Tableur fenetre;

    /**
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre 
     * @param texte nom du sous-menu
     */
    public AideAction(Tableur fenetre, String texte) {
        super(texte);

        this.fenetre = fenetre;
    }

    /**
     * Ouvre une fenêtre d'aide 
     */
    public void actionPerformed(ActionEvent e) {
        
    	new AideTab();
    }
}
