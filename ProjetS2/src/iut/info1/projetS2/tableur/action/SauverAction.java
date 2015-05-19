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
 * cet �v�nement
 * @author Micka�l
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
     * Permet de r�cup�rer le tableau � deux dimensions d'objects de notre
     * tableur et de le sauvegarder dans un fichier, on affichera un message 
     * en cas de r�ussite mais aussi en cas d'�chec.
     */
    public void actionPerformed(ActionEvent e) {
        
        // On boucle tant que le nom du fichier est incorrect
        do {
            
            // Ouvre une fenetre demandant le nom du fichier � sauvegarder
            OutilsFichier.nomFichier = JOptionPane.showInputDialog(fenetre,
                             "Veuillez entrer le nom du fichier � sauvegarder");
        } while (OutilsFichier.nomFichier.length() == 0);
        
        // Saugarde le tableau de donn�es du tableur dans un fichier
        if (OutilsFichier.enregistrerTableur(ModeleDeTable.getDonnees())) {
        
            // Ouverture d'une fenetre avec un message
            JOptionPane.showMessageDialog(fenetre, "Votre tableur a �t� "
                                          + "sauvegard� avec succ�s");
        } else {
            
            // Affichage d'un message d'erreur en cas d'�chec
            JOptionPane.showMessageDialog(fenetre, "Erreur, impossible de "
                    + "sauvegarder le tableur.");
        }
                                     
    }
}
