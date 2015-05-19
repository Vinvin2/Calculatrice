/*
 * CommandesMemoire.java					19 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import iut.info1.projetS2.calculatrice.ActionCalculer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regroupe toutes les commandes qui modifieront les cases mémoires de la calculatrice
 * @author Sébastien
 * @version 1.0
 */
public class CommandesMemoire {
    
    /** Tableau contenant toutes les cases mémoires */
    private static Variable[] casesMem = new Variable[26];
    
    /**
     * Si la commande est une affectation de type 15 + 4 = A, alors cette
     * méthode est utilisée. On réalise l'opération demandée, puis on affecte
     * le résultat à la variable concernée
     * @param commande la commande à traiter
     * @return le résultat de la commande
     */
    public static double affectation (String commande) {
        String calculARealiser; // le calcul que l'on devra effectuer
        double resultat;        // son résultat
        char nomvar;            // le nom de la variable à affecter
        
        // TODO : moyen de prendre en compte les espaces
        calculARealiser = commande.substring(0, commande.length() - 4);

        // on fait le calcul
        resultat = Utilitaires.calculIntermediaire(calculARealiser);
        
        // on prend le nom de la variable
        nomvar = commande.charAt(commande.length()-1);
        
        // on vérifie si la variable n'a jamais été initialisée
        if (casesMem[nomvar - 65] == null) {
            // on créé alors cette variable
            casesMem[nomvar - 65] = new Variable(nomvar, resultat);
        } else {
            // sinon on met à jour sa valeur
            casesMem[nomvar - 65].setValeur(resultat);
        }
        return resultat;
    }
    
    
    /**
     * Lorsque l'utilisateur entre "MEM", la calculatrice passe en mode mémoire,
     * ainsi, de nouvelles méthodes seront utilisables (dans la classe 
     * CommandesMemoires)
     * @param commande la commande entrée par l'utilisateur
     * null sinon
     */
    public static void passageMem(String commande) {
        ActionCalculer.setModeMem(true);        // le mode mémoire sera détecté
    }
    
    /**
     * Remet à zéro les cases mémoires spécifiées
     * @param commande la commande entrée par l'utilisateur
     */
    public static void raz(String commande) {
        // on regarde si le pattern convient
        Pattern patRaz = Pattern.compile("\\s*RAZ\\s*[A-Z]");
        Matcher razok = patRaz.matcher(commande);
        
        if (razok.matches()) {
            char nomvar;    
            
            // on récupère le nom de la variable à initialiser
            nomvar = commande.charAt(commande.length() - 1);
            // on l'initialise
            casesMem[nomvar - 65] = new Variable(nomvar, 0);
        }
    }
}
