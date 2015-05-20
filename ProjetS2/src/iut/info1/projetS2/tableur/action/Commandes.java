/*
 * Commandes.java                                       30 avr. 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.tableur.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iut.info1.projetS2.tableur.*;
import iut.info1.projetS2.utilitaires.Utilitaires;

/**
 * M�thode contenant l'ensemble des commandes interpr�tables par le tableur
 * --> Important : <--
 * La classe commande est instaciable et sera FORCEMENT li�e � un tableur (lors
 * de l'instanciation de ce dernier)
 * @author jo
 * @version 0.1
 */
public class Commandes {

    /** fen�tre � laquelle cette classe sera li�e */
    private Tableur fenetre;

    /** REGEX de type 'simple affichage' type: nombreLettre aAfficher */
    public static final String REG_affich1
    = "([0]*[1]{0,1}\\d||[0]*[2][0])\\s*([A-Z])\\s+(.*)";

    /** REGEX de type 'simple affichage' type: lettreNombre aAfficher */
    public static final String REG_affich2
    = "([A-Z])\\s*([0]*[1]{0,1}\\d||[0]*[2][0])\\s+(.*)";

    /** REGEX pour d�finir s'il y a calcul, type: nombreLettre =aAfficher */
    private static final String REG_needCalc
    = "([A-Z])\\s*([0]*[1]{0,1}\\d||[0]*[2][0])\\s+[=]\\s*(.*)";

    /** REGEX pour d�finir s'il y a calcul, type: nombreLettre =aAfficher */
    private static final String REG_needCalc2
    = "([0]*[1]{0,1}\\d||[0]*[2][0])\\s*([A-Z])\\s+[=]\\s*(.*)";

    /** REGEX pour identifier les diff�rentes m�thodes de copie */
    private static final String REG_COP = "COP\\s+(.+)\\s+(.+)\\s+";

    /**
     * Appel� sur l'�v�nement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tap� dans sa 'ligne de commande'
     */
    public void actionValider() {
        // recup�ration du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        // TODO test via pattern si une commande est appel�e
        
        // test via pattern si besoin de calcul ou pas
        Pattern testSiCalc = Pattern.compile(REG_needCalc);
        Pattern testSiCalc2 = Pattern.compile(REG_needCalc2);
        Matcher matchSiCalc = testSiCalc.matcher(aRenvoyer);
        Matcher matchSiCalc2 = testSiCalc2.matcher(aRenvoyer);

        // on oriente selon l'entr�e
        if (matchSiCalc.matches() || matchSiCalc2.matches()) {
            this.affichageCalcule();
        } else {
            this.affichageSimple();
        }
    }


    /**
     * Affichage de texte param�tre, la position est r�cup�r�e automatiquement
     * depuis la ligne de commande
     * @param aAfficher String � afficher dans le tableur
     */
    public void affichage(String aAfficher) {
        int col = -1; // colonne de la case � modifier
        int lig = -1; // ligne de la case � modifier

        String aAfficherTraite = ""; // texte � afficher apr�s traitement
        // test sur les deux pattern autoris�s
        Pattern ok = Pattern.compile(REG_affich1);
        Pattern ok2 = Pattern.compile(REG_affich2);
        Pattern ok3 = Pattern.compile(REG_needCalc);
        Pattern ok4 = Pattern.compile(REG_needCalc2);
        Matcher lol = ok.matcher(aAfficher);
        Matcher lel = ok2.matcher(aAfficher);
        Matcher lil = ok3.matcher(aAfficher);
        Matcher lul = ok4.matcher(aAfficher);

        // orientation selon l'ordre nombre/lettre - lettre/nombre - calculs
        if (lul.matches()) { // nombre/lettre
            this.fenetre.getLabel().setText("ok"); // simple confirmation
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */             
            lig = Integer.parseInt(lul.group(1)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lul.group(2).charAt(0) - 65;
            aAfficherTraite = String.valueOf(
                    Utilitaires.calculIntermediaire(lul.group(3)));
        } else if (lil.matches()) { // lettre/nombre
            this.fenetre.getLabel().setText("ok"); // simple confirmation
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */
            lig = Integer.parseInt(lil.group(2)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lil.group(1).charAt(0) - 65;
            aAfficherTraite = String.valueOf(
                    Utilitaires.calculIntermediaire(lil.group(3)));
        } else if (lel.matches()) {
            this.fenetre.getLabel().setText("ok"); // simple confirmation
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */
            lig = Integer.parseInt(lel.group(2)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lel.group(1).charAt(0) - 65;  
            aAfficherTraite = lel.group(3);
        } else if (lol.matches()) {
            this.fenetre.getLabel().setText("ok"); // simple confirmation
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */             
            lig = Integer.parseInt(lol.group(1)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lol.group(2).charAt(0) - 65; 
            aAfficherTraite = lol.group(3);
        }
        // else lig = -1 et col = -1

        if (lig > -1 & col > -1) { // La syntaxe est ok on a pu tout r�cup�rer
            this.fenetre.getModele().setValueAt(aAfficherTraite, lig, col);
            this.fenetre.getConsole().setText("");
        } else { // syntaxiquement faux
            this.fenetre.getLabel().setText("Erreur de syntaxe type: "
                    + "A1 texte ou 1A texte");
        }
    }
    
    
    /**
     * Permet un simple affichage de ce que l'utilisateur a entr� dans la
     * 'ligne de commande' de type nombreLettre aAfficher
     *                          ou lettreNombre aAfficher
     */
    public void affichageSimple() {
        String aAfficher = this.fenetre.getConsole().getText();
        this.affichage(aAfficher); // affichage du texte voulu
        this.fenetre.getLabel().setText("Texte affich�");
    }


    /**
     * Permet d'afficher dans une case du tableur le r�sultat d'un calcul
     * entr� dans la 'ligne de commande'
     */
    public void affichageCalcule() {
        String aAfficher = this.fenetre.getConsole().getText();
        this.affichage(aAfficher); // affichage du texte (apr�s calcul)
        this.fenetre.getLabel().setText("Calcul effectu�");
    }


    /**
     * Red�finition du contructeur par d�faut cr�e par java, il est rendu
     * unitilisable pour �viter qu'une instance de Commandes soit sans
     * fen�tre.
     */
    private Commandes() {
    }

    /**
     * Unique constructeur disponible pour la classe Commandes permet une
     * navigabilit� vers le tableur
     * @param fenetre fen�tre � laquelle cette classe sera li�e
     */
    public Commandes(Tableur fenetre) {
        this();
        this.fenetre = fenetre;
    }
}