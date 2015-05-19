/*
 * OuvrirAction.java                2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.ModeleDeTable;
import iut.info1.projetS2.tableur.OutilsFichier;
import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/** 
 * Permet de charger un fichier tableur lors de l'activation de 
 * cet évènement
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ChargerAction extends AbstractAction {

    /** Fenetre de notre application */
    private Tableur fenetre;

    /**
     * Récupération des informations essentielles comme l'instance de la 
     * JFrame et le nom de notre sous-menu.
     * @param fenetre de notre tableur
     * @param texte nom du menu
     */
    public ChargerAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Affiche une fenetre contenant un message pour savoir si l'utilisateur
     * veut vraiment charger un nouveau tableur, si oui le tableur sera
     * remplacé par le tableur à l'intérieur de notre fichier.
     * Si non on revient au tableur sans rien faire.
     */
    public void actionPerformed(ActionEvent e) {
        
        // On boucle tant que le nom du fichier est incorrect
        do {
            
            // Ouvre une fenetre demandant le nom du fichier à charger
            OutilsFichier.nomFichier = JOptionPane.showInputDialog(fenetre,
                             "Veuillez entrer le nom du fichier à charger");
        } while (OutilsFichier.nomFichier.length() == 0);
        
        // On ouvre une fenetre qui demande à l'utilisateur s'il veut oui ou
        // non ouvrir un nouveau tableur, tout en l'avertissant que les 
        // travaux non sauvegardés seront perdus
        int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous"
                    + " vraiment charger un nouveau tableur ?\n Attention, "
                    + " les données non sauvegardées seront perdues.", "Accueil",
                    JOptionPane.YES_NO_OPTION); 
        
        // Si on veut ouvrir une nouveau tableur
        if (choix == JOptionPane.YES_NO_OPTION) {
            
            // On remplace notre tableau par celui à l'intérieur de notre fichier
            ModeleDeTable.setDonnees(OutilsFichier.restaurerPaireLignTableur());
            
            // Et on raffraichie notre tableur pour voir la mise à jour
            Tableur.refresh();
        }
    }
}
