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

        // On choisi notre filtre
        FileFilter tabix = new FiltreSimple("Fichiers Tableur",".tabix");

        // On initialise la fenetre permettant le choix des fichiers
        JFileChooser dialogue = new JFileChooser();

        // on lui ajoute notre filtre
        dialogue.addChoosableFileFilter(tabix);

        // Permet de ne pouvoir choisir seulement des fichiers
        dialogue.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // affichage de notre fenetre de choix de fichiers    
        int resultatEnregistrer = dialogue.showOpenDialog(dialogue);

        if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) {
            
            // récupération du fichier sélectionné
            OutilsFichier.nomFichier = dialogue.getSelectedFile();
            
            // si le nom du fichier existe
            if (OutilsFichier.nomFichier.exists()) {

                // si notre fichier est un .tabix
                if (OutilsFichier.nomFichier.toString().endsWith(".tabix")) {

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
            
        } else {
            
            // Sinon on affiche un message d'erreur
            JOptionPane.showMessageDialog(fenetre,
                    "Attention, aucun fichier chargé !");   
        }

    }
}
