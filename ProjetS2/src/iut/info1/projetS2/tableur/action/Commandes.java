/*
 * Commandes.java                                         10 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import iut.info1.projetS2.tableur.*;

/**
 * Unique constructeur disponible pour la classe Commandes permet une
 * navigabilit� vers le tableur
 * @version 0.1
 */
public class Commandes { // SMOKE WEED EVERY DAY
    

    /** fen�tre � laquelle cette classe sera li�e */
    private Tableur fenetre;
    
    /** REGEX de type 'simple affichage' type: nombreLettre aAfficher */
    public static final String REG_affich1
                = "([0]*[1]{0,1}\\d||[0]*[2][0])\\s*([A-Z])\\s+(.*)";
    
    /** REGEX de type 'simple affichage' type: lettreNombre aAfficher */
    public static final String REG_affich2
                = "([A-Z])\\s*([0]*[1]{0,1}\\d||[0]*[2][0])\\s+(.*)";
    
    /** REGEX pour d�finir s'il y a calcul � faire ou non (via '=') */
    private static final String REG_needCalc
    = "(([A-Z])\\s*([0]*[1]{0,1}\\d||[0]*[2][0])\\s+[=].*)||"
            + "(([0]*[1]{0,1}\\d||[0]*[2][0])\\s*([A-Z])\\s+[=].*)";

    /**
     * appel� sur l'�venement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tap� dans sa 'ligne de commande'
     */
    public void actionValider() {
        // recup�ration du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        this.fenetre.getLabel().setText(aRenvoyer);
        
        // test via pattern si besoin de calcul ou pas
        Pattern testSiCalc = Pattern.compile(REG_needCalc);
        Matcher matchSiCalc = testSiCalc.matcher(aRenvoyer);
        
        // on oriente selon l'entr�e
        if (matchSiCalc.matches()) {
            this.fenetre.getLabel().setText("Pas g�r� encore lel");
        } else {
            this.affichageSimple();
        }
    }

    
    /**
     * permet un simple affichage de ce que l'utilisateur a entr� dans la
     * 'ligne de commande' de type nombreLettre aAfficher
     *                          ou lettreNombre aAfficher
     */
    public void affichageSimple() {
        int col = -1; // colonne de la case � modifier
        int lig = -1; // ligne de la case � modifier
        String aAfficher = ""; // texte � afficher
        
     // recup�ration du texte de la console
        String aRenvoyer = this.fenetre.getConsole().getText();
        // test sur les deux pattern autoris�s
        Pattern ok = Pattern.compile(REG_affich1);
        Pattern ok2 = Pattern.compile(REG_affich2);
        Matcher lol = ok.matcher(aRenvoyer);
        Matcher lel = ok2.matcher(aRenvoyer);
        
        // orientation selon l'ordre nombre/lettre - lettre/nombre
        if (lol.matches()) { // nombre/lettre
            this.fenetre.getLabel().setText("ok"); // simple confirmation
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */             
            lig = Integer.parseInt(lol.group(1)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lol.group(2).charAt(0) - 65;
            aAfficher = lol.group(3);
        } else if (lel.matches()) { // lettre/nombre
            this.fenetre.getLabel().setText("ok"); // simple confirmation
            /* 
             * on enleve pour simplifier l'utilisation du tableur (1~20)
             * au lieu de (0~20)
             */
            lig = Integer.parseInt(lel.group(2)) - 1;
            // on recup�re la lettre et on la transforme en int utilisable
            col = lel.group(1).charAt(0) - 65;
            aAfficher = lel.group(3);
        }
        
        if (lig > -1 & col > -1) {
            this.fenetre.getModele().setValueAt(aAfficher, lig, col);
        } else { // syntaxiquement faux
            this.fenetre.getLabel().setText("Erreur de syntaxe type: "
                    + "A1 texte ou 1A texte");
        }
    }
    
    /**
     * Unique constructeur disponible pour la classe Commandes permet une
     * navigabilit� vers le tableur
     */
    private Commandes() {
    }
}
    