/*
 * CommandesMemoire.java					19 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import iut.info1.projetS2.calculatrice.Aide;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regroupe toutes les commandes qui modifieront les cases mémoires 
 * de la calculatrice
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
     * Remet à zéro les cases mémoires spécifiées
     * @param commande la commande entrée par l'utilisateur
     */
    public static void raz(String commande) {
        // on regarde si le pattern convient
        Pattern patRazSimple = Pattern.compile("\\s*RAZ\\s*[A-Z]");
        Pattern patRazMultiple = Pattern.compile("\\s*RAZ\\s*[A-Z]..[A-Z]");
        Matcher razok = patRazSimple.matcher(commande);
        
        if (razok.matches()) {
            char nomvar;    
            
            // on récupère le nom de la variable à initialiser
            nomvar = commande.charAt(commande.length() - 1);
            // on l'initialise
            casesMem[nomvar - 65] = new Variable(nomvar, 0);
        }
        
        razok = patRazMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en paramètres, on les remet toutes à zéro
        if (razok.matches()) {
            char nomvar1 = commande.charAt(commande.length() - 4);
            char nomvar2 = commande.charAt(commande.length() - 1);
            
            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    casesMem[nomvar - 65] = new Variable(nomvar, 0);
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    casesMem[nomvar - 65] = new Variable(nomvar, 0);
                } 
            }
        }
    }
    
    /**
     * Affiche les noms et valeurs des variables spécifiées
     * @param commande la commande entrée par l'utilisateur
     * @return les variables à afficher et leur valeur
     */
    public static String voir(String commande) {
        
        String aRetourner = "";
        // on regarde si le pattern convient
        Pattern patVoirSimple = Pattern.compile("\\s*VOIR\\s*[A-Z]");
        Pattern patVoirMultiple = Pattern.compile("\\s*VOIR\\s*[A-Z]..[A-Z]");
        Matcher voirok = patVoirSimple.matcher(commande);
        
        if (voirok.matches()) {
            char nomvar;    
            
            // on récupère le nom de la variable à initialiser
            nomvar = commande.charAt(commande.length() - 1);
            // on l'initialise
            if (casesMem[nomvar - 65] != null) {
                aRetourner = casesMem[nomvar - 65].toString();
            } else {
                aRetourner = "Variable " + nomvar + " non déclarée";
            }
        }

        voirok = patVoirMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en paramètres, on les affiches toutes
        if (voirok.matches()) {
            char nomvar1 = commande.charAt(commande.length() - 4);
            char nomvar2 = commande.charAt(commande.length() - 1);
            
            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    if (casesMem[nomvar - 65] != null) {
                        aRetourner = aRetourner.concat
                        (casesMem[nomvar - 65].toString() + "\n");
                    } else {
                        aRetourner = aRetourner.concat
                        ("Variable " + nomvar + " non déclarée");
                    }
                }              
            } else {
                for(char nomvar = nomvar2 ; nomvar >= nomvar1 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        aRetourner = aRetourner.concat
                        (casesMem[nomvar - 65].toString() + "\n");
                    } else {
                        aRetourner = aRetourner.concat
                        ("Variable " + nomvar + " non déclarée");
                    }
                } 
            }
        }
        
        return aRetourner;
    }
    
    /**
     * Ajoute 1 aux variables spécifiées
     * @param commande la commande entrée par l'utilisateur
     */
    public static void incr(String commande) {
        // on regarde si le pattern convient
        Pattern patIncrSimple = Pattern.compile("\\s*INCR\\s*[A-Z]");
        Pattern patIncrMultiple = Pattern.compile("\\s*INCR\\s*[A-Z]..[A-Z]");
        Matcher incrok = patIncrSimple.matcher(commande);
        
        if (incrok.matches()) {
            char nomvar;    
            
            // on récupère le nom de la variable à initialiser
            nomvar = commande.charAt(commande.length() - 1);
            // on y ajoute 1 si la variable a été initialisée
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                                (casesMem[nomvar - 65].getValeur() + 1);
            }
        }
        
        incrok = patIncrMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en paramètres, on les remet toutes à zéro
        if (incrok.matches()) {
            char nomvar1 = commande.charAt(commande.length() - 4);
            char nomvar2 = commande.charAt(commande.length() - 1);
            
            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    if (casesMem[nomvar - 65] != null) {
                        casesMem[nomvar - 65].setValeur
                                        (casesMem[nomvar - 65].getValeur() + 1);
                    }
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    if (casesMem[nomvar - 65] != null) {
                        casesMem[nomvar - 65].setValeur
                                        (casesMem[nomvar - 65].getValeur() + 1);
                    }
                } 
            }
        }
    }
    
    /**
     * Affiche la fenêtre d'aide
     * @param commande la commande entrée par l'utilisateur
     */
    public static void aide(String commande) {
        // on regarde si le pattern convient
        Pattern patAide = Pattern.compile("\\s*AIDE\\s*");
        Matcher aideok = patAide.matcher(commande);
        
        if (aideok.matches()) {
            new Aide();
        }
    }
}
