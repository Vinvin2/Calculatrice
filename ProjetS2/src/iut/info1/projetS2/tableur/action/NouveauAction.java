/*
 * NouveauAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.ModeleDeTable;
import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/** 
 * Permet d'ouvrir un nouveau tableur lors de l'activation de notre �v�nement
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class NouveauAction extends AbstractAction {
    
    /** Fenetre de notre application */
    private Tableur fenetre;

    /**
     * R�cup�ration des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre tableur
     * @param texte nom du menu
     */
    public NouveauAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Affiche une fenetre contenant un message pour savoir si l'utilisateur
     * veut vraiment ouvrir un nouveau tableur, si oui le tableur sera
     * r�initialis�.
     * Si non on revient au tableur sans rien faire.
     */
    public void actionPerformed(ActionEvent e) {
        
        // On ouvre une fenetre qui demande � l'utilisateur s'il veut oui ou
        // non ouvrir un nouveau tableur, tout en l'avertissant que les 
        // travaux non sauvegard�s seront perdus
        int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous"
                    + " vraiment ouvrir un nouveau tableur ?\n Attention, "
                    + " les donn�es non sauvegard�es seront perdues.", 
                    "Accueil", JOptionPane.YES_NO_OPTION); 
        
        // Si on veut ouvrir une nouveau tableur
        if (choix == JOptionPane.YES_NO_OPTION) {
            
            // On r�initialise notre tableur 
            ModeleDeTable.setDonnees(new Object[20][26]);

            // On r�initialise le texte � l'int�rieur de la console
            fenetre.getConsole().setText("");
            
            // Et on raffraichie notre tableur pour voir la mise � jour
            Tableur.refresh(fenetre);
        }
    }
}
