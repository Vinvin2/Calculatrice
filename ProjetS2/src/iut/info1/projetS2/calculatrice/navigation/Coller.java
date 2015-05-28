/*
 * Coller.java				22 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Calculatrice;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de coller du texte dans notre console lors de l'activation de 
 * l'évènement
 * @author Vincent
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Coller extends AbstractAction {

	/** Fenetre de notre application */
    private Calculatrice fenetre;
    
    /**
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre calculatrice
     * @param texte nom du menu
     */
    public Coller(Calculatrice fenetre, String texte) {
        super(texte);

        this.fenetre = fenetre;
    }

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        fenetre.getExecuteur().paste();
    }
}
