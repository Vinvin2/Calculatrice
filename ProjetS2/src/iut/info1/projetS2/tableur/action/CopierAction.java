/*
 * CopierAction.java				4 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet de copier le texte à l'intérieur de la console dans le presse-papier
 * lors de l'activation de l'évènement
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CopierAction extends AbstractAction {

    /** Fenetre de notre application */
    private Tableur fenetre;

    /**
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre tableur
     * @param texte nom du menu
     */
    public CopierAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }
    
    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        fenetre.getConsole().copy();
    }
}