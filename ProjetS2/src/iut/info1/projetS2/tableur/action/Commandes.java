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

    /** tableau contenant les entr�es */
    private String[][] entrees = new String[20][26];

    /** pattern d'une lettre de cellule */
    private static final String REG_LETTRE = "($?)([A-Z])";

    /** pattern d'un nombre de cellule */
    private static final String REG_NBRE = "[0]*((1?\\d)||(20))";

    /** Pattern d'une case type nombreLettre */
    private static final String REG_CASE1 = REG_NBRE + REG_LETTRE;

    /** Pattern d'une case type lettreNombre */
    private static final String REG_CASE2 = REG_LETTRE + REG_NBRE;

    /** REGEX de type 'simple affichage' type: nombreLettre aAfficher */
    private static final String REG_affich1 = REG_CASE1 + "\\s+((.)*)";

    /** REGEX de type 'simple affichage' type: lettreNombre aAfficher */
    private static final String REG_affich2 = REG_CASE2 + "\\s+((.)*)";

    /** REGEX pour d�finir s'il y a calcul, type: nombreLettre =aAfficher */
    private static final String REG_needCalc = REG_CASE1 + "\\s*=\\s*((.)*)";

    /** REGEX pour d�finir s'il y a calcul, type: lettreNombre =aAfficher */
    private static final String REG_needCalc2 = REG_CASE2 + "\\s*=\\s*((.)*)";

    /** Identifie une demande de copie */
    private static final String REG_COPIER = "((COPIER)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande de copie de valeur */
    private static final String REG_COPVAL = "((COPVAL)\\s+(.+)\\s+(.+)\\s*)";

    /** Identifie une demande d'effa�age */
    private static final String REG_RAZ = "((RAZ)\\s+((.)+)\\s*)";

    /** D�termine si une commande a �t� entr�e */
    private static final String REG_needCommande
    = REG_COPIER + "||" + REG_COPVAL + "||" + REG_RAZ;

    /**
     * Appel� sur l'�v�nement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tap� dans sa 'ligne de commande'
     */
    public void actionValider() {
        // recup�ration du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        // test via pattern si une commande est appel�e
        Pattern testSiCom = Pattern.compile(REG_needCommande);
        Matcher matchSiCom = testSiCom.matcher(aRenvoyer);
        // test via pattern si besoin de calcul ou pas
        Pattern testSiCalc = Pattern.compile(REG_needCalc);
        Pattern testSiCalc2 = Pattern.compile(REG_needCalc2);
        Matcher matchSiCalc = testSiCalc.matcher(aRenvoyer);
        Matcher matchSiCalc2 = testSiCalc2.matcher(aRenvoyer);
        // test si un simpleaffichage peut �tre r�alis�
        Pattern testSiAffich = Pattern.compile(REG_affich1);
        Pattern testSiAffich2 = Pattern.compile(REG_affich2);
        Matcher matchSiAffich = testSiAffich.matcher(aRenvoyer);
        Matcher matchSiAffich2 = testSiAffich2.matcher(aRenvoyer);


        // on oriente selon l'entr�e
        if (matchSiCom.matches()) {
            System.out.println(matchSiCom.group());
            for (int i = 0; i < matchSiCom.groupCount(); i++) {
                System.out.println(i + " " + matchSiCom.group(i));
            }
        } else if (matchSiCalc.matches() || matchSiCalc2.matches()) {
            this.affichageCalcule();
        } else if (matchSiAffich.matches() || matchSiAffich2.matches()) {
            this.affichageSimple();
        } else {
            this.fenetre.getLabel().setText("Erreur de syntaxe");
        }
    }


    /**
     * Affichage de texte param�tre, la position est r�cup�r�e automatiquement
     * depuis la ligne de commande
     * @param aAfficher String � afficher dans le tableur
     */
    private void affichage(String aAfficher) {
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
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */             
            lig = Integer.parseInt(lul.group(4)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lul.group(2).charAt(0) - 65;
            aAfficherTraite = String.valueOf(
                    Utilitaires.calculIntermediaire(lul.group(6)));
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getLabel().setText("Calcul effectu�");
            } else {
                this.fenetre.getLabel().setText("Calcul non effectu�");  
            }
        } else if (lil.matches()) { // lettre/nombre
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */
            lig = Integer.parseInt(lil.group(1)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lil.group(5).charAt(0) - 65;
            aAfficherTraite = String.valueOf(
                    Utilitaires.calculIntermediaire(lil.group(6)));
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getLabel().setText("Calcul effectu�");
            } else {
                this.fenetre.getLabel().setText("Calcul non effectu�");
            }
        } else if (lel.matches()) {
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */
            lig = Integer.parseInt(lel.group(3)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lel.group(2).charAt(0) - 65;  
            aAfficherTraite = lel.group(6);
        } else if (lol.matches()) {
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */             
            lig = Integer.parseInt(lol.group(1)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lol.group(5).charAt(0) - 65; 
            aAfficherTraite = lol.group(6);
        }
        // else lig = -1 et col = -1

        if (lig > -1 & col > -1) { // La syntaxe est ok et tout est r�cup�r�
            this.fenetre.getModele().setValueAt(aAfficherTraite, lig, col);
            if (!aAfficherTraite.equals("NaN")) {
                this.fenetre.getConsole().setText("");
            }
            this.entrees[lig][col] = aAfficherTraite;
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
    private void affichageSimple() {
        String aAfficher = this.fenetre.getConsole().getText();
        this.affichage(aAfficher); // affichage du texte voulu
        this.fenetre.getLabel().setText("Texte affich�");
    }


    /**
     * Permet d'afficher dans une case du tableur le r�sultat d'un calcul
     * entr� dans la 'ligne de commande'
     */
    private void affichageCalcule() {
        String aAfficher = this.fenetre.getConsole().getText();
        this.affichage(aAfficher); // affichage du texte (apr�s calcul)
    }


    /**
     * Red�finition du contructeur par d�faut cr�e par java, il est rendu
     * unitilisable pour �viter qu'une instance de Commandes se retrouve sans
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

    //  private

    /**
     * @return the entrees
     */
    public String[][] getEntrees() {
        return entrees;
    }


    /**
     * @param entrees the entrees to set
     */
    public void setEntrees(String[][] entrees) {
        this.entrees = entrees;
    }
}