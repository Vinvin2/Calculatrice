/*
 * Utilitaires.java					6 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.calculatrice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitaire permettant de réaliser les opérations en fonction des
 * commandes entrées ou des boutons cliqués
 * @author Sébastien
 * @version 1.0
 */
public class Utilitaires {

    /** Modèle d'expression du calcul sous forme de chaîne de caractères */
    public static final String REG_EX_CALCUL_SIMPLE = 
            ("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
    
    /** Modèle d'un calcul avec des parenthèses simple : (15 + 1) / (1 + 1) */
    public static final String REG_EX_CALCUL_PARENTHESES_NIVEAU2 = 
            ("[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*([+-/*])[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*");
    
    /** Modèle d'un calcul avec des parenthèses */
    public static final String REG_EX_CALCUL_PARENTHESES_NIVEAU1 = 
            ("[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*([+-/*])" + "[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
            
    /** pattern calcul sans parentheses */
    public static Pattern patCalcSimple = Pattern.compile(REG_EX_CALCUL_SIMPLE);
    
    /** pattern calcul parentheses niveau 2 */
    public static Pattern patCalcParenth2 = Pattern.compile(REG_EX_CALCUL_PARENTHESES_NIVEAU2);
    
    /** pattern calcul parentheses niveau 1 */
    public static Pattern patCalcParenth1 = Pattern.compile(REG_EX_CALCUL_PARENTHESES_NIVEAU1);
    
    
    /**
     * Calcule et affiche le résultat de la commande
     * @param commande 
     * @return aInserer le texte à insérer dans l'écran
     */
    public static String calculSimple(String commande) {
        


        String aInserer; // le resultat de la commande

        Matcher calcSimpleOk = patCalcSimple.matcher(commande);

        double operande1;       // 1ère opérande de l'opération
        double operande2;       // 2ème opérande de l'opération
        char operateur;         // opérateur de l'opération
        
        if (calcSimpleOk.matches() && calcSimpleOk.group(1).charAt(calcSimpleOk.group(1).length() - 1) != '.'
                && calcSimpleOk.group(3).charAt(calcSimpleOk.group(3).length() - 1) != '.') {           
            double resultat;    // résultat de l'opération

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
            // On l'affiche à la fin de l'écran
            aInserer = "= " + resultat + "\n";

        } else {
            // Si la syntaxe est erronée, on affiche une erreur
            aInserer = "Erreur, le calcul entré est erroné.\n";
        }
        // si le résultat est un entier, on l'écrit tel quel
        if (aInserer.contains(".0\n")) {
            aInserer = aInserer.substring(0, aInserer.length()-3);
            aInserer = aInserer.concat("\n");
        }

        return (aInserer);
        
    }
     
    
    /**
     * Calcule et affiche le résultat de la commande
     * @param commande 
     * @return aInserer le texte à insérer dans l'écran
     */
    public static String calculParenth1(String commande) {
        


        String aInserer; // le resultat de la commande

        Matcher calcOk = patCalcParenth1.matcher(commande);

        aInserer = "\0";
        if (calcOk.matches()) {           
            String strRes1;     // résultat du calcul1     
            
            int posdeb = commande.indexOf('(');
            int posfin = commande.indexOf(')');
            
            strRes1 = commande.substring(posdeb+1, posfin);           
            // On transforme le premier nombre récupéré en double
            // et on le stocke dans operande1, on fait de même pour l'operande2
            strRes1 = calculSimple(strRes1);
            
            strRes1 = strRes1.substring(2, strRes1.length() - 1);
            
            aInserer = calculSimple(strRes1 + calcOk.group(4) + calcOk.group(5));
            

        }
        return (aInserer);
        
    }
    
    
    
    
    /**
     * Calcule et affiche le résultat de la commande
     * @param commande 
     * @return aInserer le texte à insérer dans l'écran
     */
    public static String calculParenth2(String commande) {
        


        String aInserer; // le resultat de la commande

        Matcher calcParenthesesOk = patCalcParenth2.matcher(commande);

        aInserer = "\0";
        if (calcParenthesesOk.matches()) {           
            String strRes1;     // résultat du calcul1
            String strRes2;      // résultat du calcul2      
            
            int posdeb1 = commande.indexOf('(');
            int posfin1 = commande.indexOf(')');
            
            int posdeb2 = commande.lastIndexOf('(');
            int posfin2 = commande.lastIndexOf(')');
            
            strRes1 = commande.substring(posdeb1+1, posfin1);
            strRes2 = commande.substring(posdeb2+1, posfin2);            
            // On transforme le premier nombre récupéré en double
            // et on le stocke dans operande1, on fait de même pour l'operande2
            strRes1 = calculSimple(strRes1);
            strRes2 = calculSimple(strRes2);
            
            strRes1 = strRes1.substring(2, strRes1.length() - 1);
            strRes2 = strRes2.substring(2, strRes2.length() - 1);
            
            aInserer = calculSimple(strRes1 + calcParenthesesOk.group(4) + strRes2);
            

        }
        return (aInserer);
        
    }

    
    /**
     * Utilise la méthode adéquate en fonction de la commande
     * @param commande à exécuter
     * @return aInserer le texte à insérer dans l'écran
     */
    public static String calcul(String commande) {
        


        String aInserer; // le resultat de la commande

        Matcher calcParenth2Ok = patCalcParenth2.matcher(commande);
        Matcher calcSimpleOk = patCalcSimple.matcher(commande);
        Matcher calcParenth1Ok = patCalcParenth1.matcher(commande);
        aInserer = "\0";
        if (calcParenth2Ok.matches()) {                       
            aInserer = calculParenth2(commande);
        } else if (calcSimpleOk.matches()){
            aInserer = calculSimple(commande);
        } else if (calcParenth1Ok.matches()){
            aInserer = calculParenth1(commande);
        } else {
            aInserer = "Erreur de commande.\n";
        }
            
        return (aInserer);
        
    }

}
