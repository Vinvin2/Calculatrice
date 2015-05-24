/*
 * ActionAide.java				24 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Aide;
import iut.info1.projetS2.calculatrice.Calculatrice;
import iut.info1.projetS2.tableur.Tableur;

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
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre tableur
     * @param texte nom du sous-menu
     */
    public ActionAide(Calculatrice fenetre, String texte) {
        super(texte);

        this.fenetre = fenetre;
    }

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        
    }
}