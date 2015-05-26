/*
 * APropos.java				24 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Calculatrice;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/** 
 * Affiche des informations à propos du projet
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class APropos extends AbstractAction {
  
    /** fenetre de notre calculatrice */
    private Calculatrice fenetre;
    
    /**
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre fenetre dans laquelle on affiche le message
     * @param texte nom du sous-menu
     */
    public APropos(Calculatrice fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }
    
    /**
     * Ouvre une nouvelle fenetre (JOptionPane) qui affiche un message pour 
     * donner de plus amples informations sur les développeurs.
     */
    public void actionPerformed(ActionEvent e) {
        
        // Ouverture d'une fenetre avec un message
        JOptionPane.showMessageDialog(fenetre, "Ce programme a été développé "
        						 + "par BAUBE Maxime, GRANIER Vincent, MIQUEL "
        						 + "Jonathan, PERIES Mickaël et "
        						 + "SANCHEZ Sebastien");
    }
}
