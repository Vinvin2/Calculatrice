/*
 * Couper.java				22 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Calculatrice;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Permet de couper le texte à l'intérieur de la console dans le presse-papier
 * lors de l'activation de l'évènement
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Couper extends AbstractAction {

	/** Fenetre de notre application */
    private Calculatrice fenetre;

    /**
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre tableur
     * @param texte nom du menu
     */
    public Couper(Calculatrice fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
    	fenetre.getExecuteur().cut();
    }
}