/*
 * AProposAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/** 
 * Permet d'afficher des informations lors de l'activation de l'�v�nement
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class AProposAction extends AbstractAction {
  
    /** Tableur */
    private Tableur fenetre;
    
    /**
     * @param fenetre fenetre dans laquelle on affiche le message
     * @param texte nom du sous-menu
     */
    public AProposAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }
    
    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        
        // Ouverture d'une fenetre avec un message
        JOptionPane.showMessageDialog(fenetre, "Ce programme a �t� d�velopp� "
                                     + "par PERIES Micka�l et MIQUEL Jonathan");
    }
}
