/*
 * TestUtilitaires.java					12 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.calculatrice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * test d'une autre méthode pour les calculs
 * @author Sébastien
 * @version 1.0
 */
public class TestUtilitaires {
    /** Modèle d'expression de l'opérateur */
    public static final String REG_EX_OPERATEUR = 
            ("([+-/*])");
    
    /** Modèle d'expression d'un nombre réel */
    public static final String REG_EX_NOMBRE = 
            ("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
    
    /** Modèle d'une expression arithmétique correcte */
    public static final String REG_EX_EXPRESSION = 
            (REG_EX_NOMBRE + REG_EX_OPERATEUR + REG_EX_NOMBRE);
    
    /** Modèle d'une expression arithmétique suivant l'expression de départ
     * ex : 1 + 2 - 1 --> le -1 est l'expression désignée ici
     */
    public static final String REG_EX_EXPRESSION_SUPP = 
            (REG_EX_OPERATEUR + REG_EX_NOMBRE);
    
    /** pattern associé à une expression simple*/
    public static final Pattern PATTERN_EXPRESSION = Pattern.compile(REG_EX_EXPRESSION);
    
    /** pattern associé à cette expression complexe (plusieurs opérateurs) */
    public static final Pattern PATTERN_COMPLEXE = Pattern.compile("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
    
    
    /**
     * Réalise le calcul demandé
     * @param commande le calcul demandé
     * @return le résultat de l'opération
     */
    public static double calcul(String commande) {
        

        Matcher calcSimpleOk = PATTERN_EXPRESSION.matcher(commande);

        double operande1;       // 1ère opérande de l'opération
        double operande2;       // 2ème opérande de l'opération
        char operateur;         // opérateur de l'opération
        
        double resultat;    // résultat de l'opération
        
        if (calcSimpleOk.matches() && calcSimpleOk.group(1).charAt(calcSimpleOk.group(1).length() - 1) != '.'
                && calcSimpleOk.group(3).charAt(calcSimpleOk.group(3).length() - 1) != '.') {           


            // On transforme le premier nombre récupéré en double
            // et on le stocke dans operande1, on fait de même pour l'operande2
            operande1 = Double.parseDouble(calcSimpleOk.group(1)); 
            // On stocke l'opérateur
            operateur = calcSimpleOk.group(2).charAt(0);
            operande2 = Double.parseDouble(calcSimpleOk.group(3));

            // On réalise le calcul associé à l'opérateur
            switch (operateur) {
            case '+':
                resultat = operande1 + operande2;
                break;
            case '-':
                resultat = operande1 - operande2;
                break;
            case '*':
                resultat = operande1 * operande2;
                break;
            default:
                resultat = operande1 / operande2;
                break;
            }
            // on retourne le résultat
            return resultat;
        } else {
            return Double.NaN;
        }
    }
     
    /**
     * Traite un calcul complexe
     * @param commande le calcul demandé
     * @return le résultat de l'opération
     */
    public static double calculComplexe(String commande) {
        
        String commandeSimplifiee = commande;
        double resultat = Double.NaN;
        Matcher calcComplexeOk = PATTERN_COMPLEXE.matcher(commandeSimplifiee);
        int nbOperateur;
        nbOperateur = compteOperateurs(commandeSimplifiee);
        if(calcComplexeOk.matches()) {
            do {
                String expressionATraiter;
                expressionATraiter = commandeSimplifiee.substring(0, calcComplexeOk.end(3));
                resultat = calcul(expressionATraiter);
                commandeSimplifiee = commandeSimplifiee.replace(expressionATraiter, Double.toString(resultat));
                nbOperateur--;
            } while (nbOperateur > 1);
            
            resultat = calcul(commandeSimplifiee);
        }

        return resultat;
        
    }

    
    /**
     * Utilise la méthode adéquate en fonction de la commande
     * @param commande à exécuter
     * @return aInserer le texte à insérer dans l'écran
     */
    private static int compteOperateurs(String commande) {        
        
        int nbOperateur;
        nbOperateur = 0;
        for (int i = 0 ; i < commande.length(); i++) {
            if (commande.charAt(i) == '+' || 
                commande.charAt(i) == '*' ||
                commande.charAt(i) == '-' ||
                commande.charAt(i) == '/') {
                    nbOperateur++;
            }

        }

        return nbOperateur;
        
    }
}
