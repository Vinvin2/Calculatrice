/*
 * Accueil.java               22 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import iut.info1.projetS2.calculatrice.Calculatrice;
import iut.info1.projetS2.calculatrice.Menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/** 
 * Permet de revenir au Menu de l'application
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Accueil extends AbstractAction {
    
    /** Fenetre de notre application */
    private Calculatrice fenetre;

    /**
     * R�cup�ration des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre calculatrice
     * @param texte nom du menu
     */
    public Accueil(Calculatrice fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Affiche une fenetre contenant un message pour savoir si l'utilisateur
     * veut vraiment quitter, si la fenetre de la calculatrice sera ferm�e et la fenetre
     * du menu ouverte.
     * Si non on revient � la calculatrice sans rien faire.
     */
    public void actionPerformed(ActionEvent e) {
        
        // On ouvre une fenetre qui demande � l'utilisateur s'il veut oui ou
        // non revenir au menu principal, tout en l'avertissant que les 
        // travaux non sauvegard�s seront perdus
        int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous"
                    + " vraiment revenir au menu principal ?\n Attention, "
                    + " les donn�es non sauvegard�es seront perdues.", "Accueil",
                    JOptionPane.YES_NO_OPTION); 
        
        // si on veut revenir au menu principal
        if (choix == JOptionPane.YES_NO_OPTION) {
           
        	// On ouvre le menu
            new Menu();
            
            // On ferme notre calculatrice
            fenetre.dispose();
            
            
        }
        
    }
}