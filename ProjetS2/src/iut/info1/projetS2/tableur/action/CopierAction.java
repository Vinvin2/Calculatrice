/*
 * CopierAction.java				4 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet de copier le texte � l'int�rieur de la console dans le presse-papier
 * lors de l'activation de l'�v�nement
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CopierAction extends AbstractAction {

    /** Fenetre de notre application */
    private Tableur fenetre;

    /**
     * R�cup�ration des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre tableur
     * @param texte nom du menu
     */
    public CopierAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }
    
    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        fenetre.getConsole().copy();
    }
}