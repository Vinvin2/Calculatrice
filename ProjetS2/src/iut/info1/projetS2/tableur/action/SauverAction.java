/*
 * SauverAction.java				2 mai 2015
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
 * Permet de sauvgarder un fichier tableur lors de l'activation de 
 * cet évènement
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class SauverAction extends AbstractAction {
    
    /** fenetre de notre tableur */
    private Tableur fenetre;

    /**
     * 
     * @param fenetre fenetre de notre application
     * @param texte nom du menu
     */
    public SauverAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }

    /**
     * Permet de récupérer le tableau à deux dimensions d'objects de notre
     * tableur et de le sauvegarder dans un fichier, on affichera un message 
     * en cas de réussite mais aussi en cas d'échec.
     */
    public void actionPerformed(ActionEvent e) {
        
        // On boucle tant que le nom du fichier est incorrect
        do {
            
            // Ouvre une fenetre demandant le nom du fichier à sauvegarder
            OutilsFichier.nomFichier = JOptionPane.showInputDialog(fenetre,
                             "Veuillez entrer le nom du fichier à sauvegarder");
        } while (OutilsFichier.nomFichier.length() == 0);
        
        // Saugarde le tableau de données du tableur dans un fichier
        if (OutilsFichier.enregistrerTableur(ModeleDeTable.getDonnees())) {
        
            // Ouverture d'une fenetre avec un message
            JOptionPane.showMessageDialog(fenetre, "Votre tableur a été "
                                          + "sauvegardé avec succés");
        } else {
            
            // Affichage d'un message d'erreur en cas d'échec
            JOptionPane.showMessageDialog(fenetre, "Erreur, impossible de "
                    + "sauvegarder le tableur.");
        }
                                     
    }
}
