/*
 * SauverAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.ModeleDeTable;
import iut.info1.projetS2.tableur.OutilsFichier;
import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

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
        
        // nom du bouton qui permet l'enregistrement
        String sauver = "ENREGISTRER";
        
        // On choisi notre filtre
        FileFilter tabix = new FiltreSimple("Fichiers Tableur",".tabix");
        
        // On initialise la fenetre permettant le choix des fichiers
        JFileChooser dialogue = new JFileChooser();
        
        // on lui ajoute notre filtre
        dialogue.addChoosableFileFilter(tabix);
        
        // on lui donne un nouveau titre
        dialogue.setDialogTitle("Enregistrer ");
        
        // affichage de notre        
        int resultatEnregistrer = dialogue.showDialog(dialogue, sauver);
        
        // si l'utilisateur appui sur enregistrer
        if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) {
            
            // récupération du fichier sélectionné
            String nom = new String(dialogue.getSelectedFile().toString());
          
            // si le fichier à déja le bon nom d'extension
            if (nom.endsWith(".tabix")) {
                // on ne fait rien
            
            // si le nom entré est vide
            }else if (nom.equals(0)) {
                nom = "sans_nom.tabix";
            
            // si nous n'avons pas mis d'extension
            } else {
                // on lu rajoute l'extension
                nom = nom + ".tabix";
            }
            
            OutilsFichier.nomFichier = new File(nom);
            
            if (OutilsFichier.nomFichier.exists()) {
            

            } else {
        
            }
        } else {
            
            // Affichage d'un message d'erreur en cas d'échec
            JOptionPane.showMessageDialog(fenetre, "Erreur, impossible de "
                    + "sauvegarder le tableur.");
        }
                                     
    }
        
        /**
         * Permet de sauvegarder notre tabbleur dans un fichier
         */
        public void enregistrement() {

            // Sauvegarde le tableau de données du tableur dans un fichier
            OutilsFichier.enregistrerTableur(ModeleDeTable.getDonnees(),
                    fenetre.getActions().getEntrees());

            // Ouverture d'une fenetre avec un message
            JOptionPane.showMessageDialog(fenetre, "Votre tableur a été "
                    + "sauvegardé avec succés");
        }
}
