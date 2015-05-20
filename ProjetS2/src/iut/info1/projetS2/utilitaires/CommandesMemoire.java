/*
 * CommandesMemoire.java					19 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import iut.info1.projetS2.calculatrice.Aide;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regroupe toutes les commandes qui modifieront les cases m�moires 
 * de la calculatrice
 * @author S�bastien
 * @version 1.0
 */
public class CommandesMemoire {
    
    /** Tableau contenant toutes les cases m�moires */
    private static Variable[] casesMem = new Variable[26];
    
    /**
     * Si la commande est une affectation de type 15 + 4 = A, alors cette
     * m�thode est utilis�e. On r�alise l'op�ration demand�e, puis on affecte
     * le r�sultat � la variable concern�e
     * @param commande la commande � traiter
     * @return le r�sultat de la commande
     */
    public static double affectation (String commande) {
        String calculARealiser; // le calcul que l'on devra effectuer
        double resultat;        // son r�sultat
        char nomvar;            // le nom de la variable � affecter
        
        
        // TODO : moyen de prendre en compte les espaces
        calculARealiser = commande.substring(0, commande.length() - 4);

        // on fait le calcul
        resultat = Utilitaires.calculIntermediaire(calculARealiser);
        
        // on prend le nom de la variable
        nomvar = commande.charAt(commande.length()-1);
        
        // on v�rifie si la variable n'a jamais �t� initialis�e
        if (casesMem[nomvar - 65] == null) {
            // on cr�� alors cette variable
            casesMem[nomvar - 65] = new Variable(nomvar, resultat);
        } else {
            // sinon on met � jour sa valeur
            casesMem[nomvar - 65].setValeur(resultat);
        }
        return resultat;
    }
    
    /**
     * Remet � z�ro les cases m�moires sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     */
    public static void raz(String commande) {
        // on regarde si le pattern convient
        Pattern patRazSimple = Pattern.compile("\\s*RAZ\\s*[A-Z]");
        Pattern patRazMultiple = Pattern.compile("\\s*RAZ\\s*[A-Z]..[A-Z]");
        Matcher razok = patRazSimple.matcher(commande);
        
        if (razok.matches()) {
            char nomvar;    
            
            // on r�cup�re le nom de la variable � initialiser
            nomvar = commande.charAt(commande.length() - 1);
            // on l'initialise
            casesMem[nomvar - 65] = new Variable(nomvar, 0);
        }
        
        razok = patRazMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les remet toutes � z�ro
        if (razok.matches()) {
            char nomvar1 = commande.charAt(commande.length() - 4);
            char nomvar2 = commande.charAt(commande.length() - 1);
            
            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    casesMem[nomvar - 65] = new Variable(nomvar, 0);
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    casesMem[nomvar - 65] = new Variable(nomvar, 0);
                } 
            }
        }
    }
    
    /**
     * Affiche les noms et valeurs des variables sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     * @return les variables � afficher et leur valeur
     */
    public static String voir(String commande) {
        
        String aRetourner = "";
        // on regarde si le pattern convient
        Pattern patVoirSimple = Pattern.compile("\\s*VOIR\\s*[A-Z]");
        Pattern patVoirMultiple = Pattern.compile("\\s*VOIR\\s*[A-Z]..[A-Z]");
        Matcher voirok = patVoirSimple.matcher(commande);
        
        if (voirok.matches()) {
            char nomvar;    
            
            // on r�cup�re le nom de la variable � afficher
            nomvar = commande.charAt(commande.length() - 1);
            // on l'initialise
            if (casesMem[nomvar - 65] != null) {
                aRetourner = casesMem[nomvar - 65].toString();
            } else {
                aRetourner = "Variable " + nomvar + " non d�clar�e";
            }
        }

        voirok = patVoirMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les affiches toutes
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
                        ("Variable " + nomvar + " non d�clar�e\n");
                    }
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        aRetourner = aRetourner.concat
                        (casesMem[nomvar - 65].toString() + "\n");
                    } else {
                        aRetourner = aRetourner.concat
                        ("Variable " + nomvar + " non d�clar�e\n");
                    }
                } 
            }
        }
        
        return aRetourner;
    }
    
    /**
     * Ajoute 1 aux variables sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     */
    public static void incr(String commande) {
        // on regarde si le pattern convient
        Pattern patIncrSimple = Pattern.compile("\\s*INCR\\s*[A-Z]");
        Pattern patIncrMultiple = Pattern.compile("\\s*INCR\\s*[A-Z]..[A-Z]");
        Matcher incrok = patIncrSimple.matcher(commande);
        
        if (incrok.matches()) {
            char nomvar;    
            
            // on r�cup�re le nom de la variable � incr�menter
            nomvar = commande.charAt(commande.length() - 1);
            // on y ajoute 1 si la variable a �t� initialis�e
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                                (casesMem[nomvar - 65].getValeur() + 1);
            }
        }
        
        incrok = patIncrMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les incr toutes
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
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        casesMem[nomvar - 65].setValeur
                                        (casesMem[nomvar - 65].getValeur() + 1);
                    }
                } 
            }
        }
    }
    
    /**
     * Affiche la fen�tre d'aide
     * @param commande la commande entr�e par l'utilisateur
     */
    public static void aide(String commande) {
        // on regarde si le pattern convient
        Pattern patAide = Pattern.compile("\\s*AIDE\\s*");
        Matcher aideok = patAide.matcher(commande);
        // on affiche la fen�tre d'aide si c'est le cas
        if (aideok.matches()) {
            new Aide();
        }
    }
    
    /**
     * fonction CAR : modifie la valeur des variables sp�cifi�es pour leur 
     * affecter leur valeur carr�
     * @param commande la commande entr�e par l'utilisateur
     */
    public static void car(String commande) {
        // on regarde si le pattern convient
        Pattern patCarSimple = Pattern.compile("\\s*CAR\\s*[A-Z]");
        Pattern patCarMultiple = Pattern.compile("\\s*CAR\\s*[A-Z]..[A-Z]");
        Matcher carok = patCarSimple.matcher(commande);
        
        if (carok.matches()) {
            char nomvar;    
            
            // on r�cup�re le nom de la variable � traiter
            nomvar = commande.charAt(commande.length() - 1);
            // on la met au carr� si la variable a �t� initialis�e
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                                (casesMem[nomvar - 65].getValeur()
                                 * casesMem[nomvar - 65].getValeur());
            }
        }
        
        carok = patCarMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les met toutes au carr�
        if (carok.matches()) {
            char nomvar1 = commande.charAt(commande.length() - 4);
            char nomvar2 = commande.charAt(commande.length() - 1);
            
            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    if (casesMem[nomvar - 65] != null) {
                        casesMem[nomvar - 65].setValeur
                                        (casesMem[nomvar - 65].getValeur()
                                         * casesMem[nomvar - 65].getValeur());
                    }
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        casesMem[nomvar - 65].setValeur
                                        (casesMem[nomvar - 65].getValeur()
                                         * casesMem[nomvar - 65].getValeur());
                    }
                } 
            }
        }
    }
    
    /**
     * fonction SQRT : modifie la valeur des variables sp�cifi�es pour leur 
     * affecter leur racine carr�e
     * @param commande la commande entr�e par l'utilisateur
     */
    public static void sqrt(String commande) {
        // on regarde si le pattern convient
        Pattern patSqrtSimple = Pattern.compile("\\s*SQRT\\s*[A-Z]");
        Pattern patSqrtMultiple = Pattern.compile("\\s*SQRT\\s*[A-Z]..[A-Z]");
        Matcher sqrtok = patSqrtSimple.matcher(commande);
        
        if (sqrtok.matches()) {
            char nomvar;    
            
            // on r�cup�re le nom de la variable � traiter
            nomvar = commande.charAt(commande.length() - 1);
            // on la met � la racine carr�e si la variable a �t� initialis�e
            if (casesMem[nomvar - 65] != null) {
                casesMem[nomvar - 65].setValeur
                                (Math.sqrt(casesMem[nomvar - 65].getValeur()));
            }
        }
        
        sqrtok = patSqrtMultiple.matcher(commande);
        // lorsqu'on a plusieurs cases en param�tres, on les met toutes 
        // � la racine carr�e
        if (sqrtok.matches()) {
            char nomvar1 = commande.charAt(commande.length() - 4);
            char nomvar2 = commande.charAt(commande.length() - 1);
            
            if (nomvar1 <= nomvar2) {
                for(char nomvar = nomvar1 ; nomvar <= nomvar2 ; nomvar++) {
                    if (casesMem[nomvar - 65] != null) {
                        casesMem[nomvar - 65].setValeur
                        (Math.sqrt(casesMem[nomvar - 65].getValeur()));
                    }
                }              
            } else {
                for(char nomvar = nomvar1 ; nomvar >= nomvar2 ; nomvar--) {
                    if (casesMem[nomvar - 65] != null) {
                        casesMem[nomvar - 65].setValeur
                        (Math.sqrt(casesMem[nomvar - 65].getValeur()));
                    }
                } 
            }
        }
    }
}
