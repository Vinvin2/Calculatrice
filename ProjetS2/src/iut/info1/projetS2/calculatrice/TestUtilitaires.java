/*
 * TestUtilitaires.java					12 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.calculatrice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * test d'une autre m�thode pour les calculs
 * @author S�bastien
 * @version 1.0
 */
public class TestUtilitaires {
    /** Mod�le d'expression de l'op�rateur */
    public static final String REG_EX_OPERATEUR = 
            ("([+-/*])");
    
    /** Mod�le d'expression d'un nombre r�el */
    public static final String REG_EX_NOMBRE = 
            ("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
    
    /** Mod�le d'une expression arithm�tique correcte */
    public static final String REG_EX_EXPRESSION = 
            (REG_EX_NOMBRE + REG_EX_OPERATEUR + REG_EX_NOMBRE);
    
    /** Mod�le d'une expression arithm�tique suivant l'expression de d�part
     * ex : 1 + 2 - 1 --> le -1 est l'expression d�sign�e ici
     */
    public static final String REG_EX_EXPRESSION_SUPP = 
            (REG_EX_OPERATEUR + REG_EX_NOMBRE);
    
    /** pattern associ� � une expression simple*/
    public static final Pattern PATTERN_EXPRESSION = Pattern.compile(REG_EX_EXPRESSION);
    
    /** pattern associ� � cette expression complexe (plusieurs op�rateurs) */
    public static final Pattern PATTERN_COMPLEXE = Pattern.compile("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
    
    
    /**
     * R�alise le calcul demand�
     * @param commande le calcul demand�
     * @return le r�sultat de l'op�ration
     */
    public static double calcul(String commande) {
        

        Matcher calcSimpleOk = PATTERN_EXPRESSION.matcher(commande);

        double operande1;       // 1�re op�rande de l'op�ration
        double operande2;       // 2�me op�rande de l'op�ration
        char operateur;         // op�rateur de l'op�ration
        
        double resultat;    // r�sultat de l'op�ration
        
        if (calcSimpleOk.matches() && calcSimpleOk.group(1).charAt(calcSimpleOk.group(1).length() - 1) != '.'
                && calcSimpleOk.group(3).charAt(calcSimpleOk.group(3).length() - 1) != '.') {           


            // On transforme le premier nombre r�cup�r� en double
            // et on le stocke dans operande1, on fait de m�me pour l'operande2
            operande1 = Double.parseDouble(calcSimpleOk.group(1)); 
            // On stocke l'op�rateur
            operateur = calcSimpleOk.group(2).charAt(0);
            operande2 = Double.parseDouble(calcSimpleOk.group(3));

            // On r�alise le calcul associ� � l'op�rateur
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
            // on retourne le r�sultat
            return resultat;
        } else {
            return Double.NaN;
        }
    }
     
    /**
     * Traite un calcul complexe
     * @param commande le calcul demand�
     * @return le r�sultat de l'op�ration
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
     * Utilise la m�thode ad�quate en fonction de la commande
     * @param commande � ex�cuter
     * @return aInserer le texte � ins�rer dans l'�cran
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
