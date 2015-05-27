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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

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

    /** fenetre d'enregistrement */
    private JFileChooser dialogue;

    /** regex pour le nom de notre fichier */
    private Pattern mauvaisCarac = Pattern.compile(".*([\"><|])+.*");

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

        // nom du bouton qui permet l'enregistrement
        String sauver = "ENREGISTRER";

        // On choisi notre filtre
        FileFilter tabix = new FiltreSimple("Fichiers Tableur",".tabix");

        // On initialise la fenetre permettant le choix des fichiers
        dialogue = new JFileChooser();

        // on lui ajoute notre filtre
        dialogue.addChoosableFileFilter(tabix);

        // on lui donne un nouveau titre
        dialogue.setDialogTitle("Enregistrer ");

        // affichage de notre fenetre de choix de fichiers    
        int resultatEnregistrer = dialogue.showDialog(dialogue, sauver);

        // si l'utilisateur appui sur enregistrer
        if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) {

            verification();

        } else {

            // Affichage d'un message d'erreur en cas d'�chec
            JOptionPane.showMessageDialog(fenetre, "Erreur, impossible de "
                    + "sauvegarder le tableur.");
        }

    }

    /**
     * Permet de faire la v�rification de notre fichier, c'est � dire :
     *     - v�rification du nom
     *     - v�rification de l'extension
     *     - v�rification de son existence
     */
    private void verification() {

        // r�cup�ration du fichier s�lectionn�
        String nom = new String(dialogue.getSelectedFile().toString());

        // matcher pour la v�rification du nom de notre fichier
        Matcher verifNom = mauvaisCarac.matcher(nom);

        // si mauvaise syntaxe
        if (verifNom.matches()) {

            // Affichage d'un message d'erreur en cas d'�chec
            JOptionPane.showMessageDialog(fenetre, "Erreur, impossible de "
                    + "sauvegarder le tableur.");
        } else {

            // si le fichier � d�ja le bon nom d'extension
            if (nom.endsWith(".tabix")) {
                // on ne fait rien

            // si le nom entr� est vide
            }else if (nom.equals(0)) {
                nom = "sans_nom.tabix";

            // si nous n'avons pas mis d'extension
            } else {
                // on lui rajoute l'extension
                nom = nom + ".tabix";
            }
            
            OutilsFichier.nomFichier = new File(nom);

            // si le fichier existe d�j�
            if (OutilsFichier.nomFichier.exists()) {
                
                // on demande � l'utilisateur ce qu'il veut faire
                remplacement();

            } else {
                
                // on enregistre notre fichier
                enregistrement();
            }    
        }
    }

    /** 
     * Permet de demander � l'utilisateur s'il veut �craser le fichier existant
     * pour le remplacer par le nouveau
     */
    private void remplacement() {
        
        // On ouvre une fenetre qui demande � l'utilisateur s'il veut oui ou
        // non remplacer le fichier
        int choix = JOptionPane.showConfirmDialog(fenetre, "Voulez vous"
                    + " rempacer le fichier existant par le nouveau ?", 
                    "Remplacement", JOptionPane.YES_NO_OPTION); 
        
        // si on veut revenir au menu principal
        if (choix == JOptionPane.YES_NO_OPTION) {
            
            // on enregistre notre fichier
            enregistrement();
        } else { 
            
            // Affichage d'un message d'erreur en cas d'�chec
            JOptionPane.showMessageDialog(fenetre, "Tableur non enregistr�.");
        }     
    }

    /**
     * Permet de sauvegarder notre tabbleur dans un fichier
     */
    public void enregistrement() {

        // Sauvegarde le tableau de donn�es du tableur dans un fichier
        OutilsFichier.enregistrerTableur(ModeleDeTable.getDonnees(),
                fenetre.getActions().getEntrees());

        // Ouverture d'une fenetre avec un message
        JOptionPane.showMessageDialog(fenetre, "Votre tableur a �t� "
                + "sauvegard� avec succ�s.");
    }
}
