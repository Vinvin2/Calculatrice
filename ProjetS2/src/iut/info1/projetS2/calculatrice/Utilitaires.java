/*
 * Utilitaires.java					6 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.calculatrice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitaire permettant de r�aliser les op�rations en fonction des
 * commandes entr�es ou des boutons cliqu�s
 * @author S�bastien
 * @version 1.0
 */
public class Utilitaires {

    /** Mod�le d'expression du calcul sous forme de cha�ne de caract�res */
    public static final String REG_EX_CALCUL_SIMPLE = 
            ("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
    
    /** Mod�le d'une expression entre parenth�ses */
    public static final String REG_EX_PARENTHESES = "[(]" + REG_EX_CALCUL_SIMPLE + "[)]";
    
    /** Mod�le d'un calcul avec des parenth�ses */
    public static final String REG_EX_CALCUL_PARENTHESES = 
            (REG_EX_PARENTHESES + "([+-/*])" + REG_EX_PARENTHESES);
            
    /**
     * Calcule et affiche le r�sultat de la commande
     * @param commande 
     * @return aInserer le texte � ins�rer dans l'�cran
     */
    public static String calcul(String commande) {
        
        Pattern patCalcSimple = Pattern.compile(REG_EX_CALCUL_SIMPLE);

        String aInserer; // le resultat de la commande

        Matcher calcSimpleOk = patCalcSimple.matcher(commande);

        double operande1;       // 1�re op�rande de l'op�ration
        double operande2;       // 2�me op�rande de l'op�ration
        char operateur;         // op�rateur de l'op�ration
        
        if (calcSimpleOk.matches() && calcSimpleOk.group(1).charAt(calcSimpleOk.group(1).length() - 1) != '.'
                && calcSimpleOk.group(3).charAt(calcSimpleOk.group(3).length() - 1) != '.') {           
            double resultat;    // r�sultat de l'op�ration

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
            // On l'affiche � la fin de l'�cran
            aInserer = "= " + resultat + "\n";

        } else {
            // Si la syntaxe est erron�e, on affiche une erreur
            aInserer = "Erreur, le calcul entr� est erron�.\n";
        }
        // si le r�sultat est un entier, on l'�crit tel quel
        if (aInserer.contains(".0\n")) {
            aInserer = aInserer.substring(0, aInserer.length()-3);
            aInserer = aInserer.concat("\n");
        }

        return (aInserer);
        
    }
     
    /**
     * Calcule et affiche le r�sultat de la commande
     * @param commande 
     * @return aInserer le texte � ins�rer dans l'�cran
     */
    public static String calculParentheses(String commande) {
        
        Pattern patCalcParentheses = Pattern.compile(REG_EX_CALCUL_PARENTHESES);

        String aInserer; // le resultat de la commande

        Matcher calcParenthesesOk = patCalcParentheses.matcher(commande);

        if (calcParenthesesOk.matches()) {           
            String strRes1;     // r�sultat du calcul1
            String strRes2;      // r�sultat du calcul2
            String resultat;    // resultat final       
            
            int posdeb1 = commande.indexOf('(');
            int posfin1 = commande.indexOf(')');
            
            int posdeb2 = commande.lastIndexOf('(');
            int posfin2 = commande.lastIndexOf(')');
            
            strRes1 = commande.substring(posdeb1+1, posfin1);
            strRes2 = commande.substring(posdeb2+1, posfin2);            
            // On transforme le premier nombre r�cup�r� en double
            // et on le stocke dans operande1, on fait de m�me pour l'operande2
            strRes1 = calcul(strRes1);
            strRes2 = calcul(strRes2);
            
            strRes1 = strRes1.substring(2, strRes1.length() - 1);
            strRes2 = strRes2.substring(2, strRes2.length() - 1);
            
            
            resultat = calcul(strRes1 + commande.charAt(posfin1+1) + strRes2);
            
            // On l'affiche � la fin de l'�cran
            aInserer = resultat;

        } else {
            // Si la syntaxe est erron�e, on affiche une erreur
            aInserer = "Erreur, le calcul entr� est erron�.\n";
        }
        return (aInserer);
        
    }

    
}
