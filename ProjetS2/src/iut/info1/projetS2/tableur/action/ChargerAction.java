/*
 * OuvrirAction.java                2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.ModeleDeTable;
import iut.info1.projetS2.tableur.OutilsFichier;
import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

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
        
        Scanner delimiteur = null;
        String extension = null;

        FileFilter tabix = new FiltreSimple("Fichiers Tableur",".tabix");
        
        JFileChooser dialogue = new JFileChooser();
        
        dialogue.addChoosableFileFilter(tabix);

        // affichage
        dialogue.showOpenDialog(null);
        
        // Permet de ne pouvoir choisir seulement des fichiers
        dialogue.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // récupération du fichier sélectionné
        OutilsFichier.nomFichier = dialogue.getSelectedFile();

        if (OutilsFichier.nomFichier.exists()) {
            try {
                delimiteur = new Scanner(OutilsFichier.nomFichier);
                delimiteur.useDelimiter("\\056");
                delimiteur.next();
                extension = delimiteur.next();
                
            } catch (FileNotFoundException e1) { // branche morte
               
            }

            if (extension == "tabix") {
                // On remplace notre tableau par celui à l'intérieur
                // de notre fichier
                ModeleDeTable.setDonnees(
                         OutilsFichier.restaurerPaireLignTableur());

                // Et on raffraichie notre tableur pour voir la mise à jour
                Tableur.refresh(fenetre);
            } else {
                
                // On affiche un message d'erreur
                JOptionPane.showMessageDialog(fenetre,
                        "Erreur, type de fichier incorrect.");
            }
        } else {
            
            // Sinon on affiche un message d'erreur
            JOptionPane.showMessageDialog(fenetre,
                    "Erreur, fichier introuvable !");
        }

    }
}
