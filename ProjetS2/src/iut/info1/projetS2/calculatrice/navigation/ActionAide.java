/*
 * ActionAide.java				24 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Aide;
import iut.info1.projetS2.calculatrice.Calculatrice;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet d'ouvrir une fenêtre aide pour expliquer le fonctionnement 
 * de la mini-calculatrice
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ActionAide extends AbstractAction {
    
    /** Fenetre de notre application */
    @SuppressWarnings("unused")
    private Calculatrice fenetre;

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new Aide();
        
    }
}