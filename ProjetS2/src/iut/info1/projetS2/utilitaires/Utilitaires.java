/*
 * Utilitaires.java					6 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import java.util.ArrayList;
import java.util.Scanner;
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
    
    /** Mod�le d'un calcul avec des parenth�ses */
    public static final String REG_EX_CALCUL_PARENTHESES_NIVEAU1A = 
            ("[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*([+-/*])" + "[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");

    /** mod�le d'un calcul avec des parenth�ses simple � 1 niveau */
    private static final String REG_EX_CALCUL_PARENTHESES_NIVEAU1B = 
            "[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*" + "([+-/*])[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*";
    
    
    /** Mod�le d'un calcul avec des parenth�ses simple : (15 + 1) / (1 + 1) */
    public static final String REG_EX_CALCUL_PARENTHESES_NIVEAU2 = 
            ("[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*([+-/*])[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*");
    

            
    /** pattern calcul sans parentheses */
    private static Pattern patCalcSimple = Pattern.compile(REG_EX_CALCUL_SIMPLE);
    
    
    /** pattern calcul parentheses niveau 1 : type (2-1) / 1 */
    private static Pattern patCalcParenth1A = Pattern.compile(REG_EX_CALCUL_PARENTHESES_NIVEAU1A);

    /** pattern calcul parentheses niveau 1 : type 1 + (2-1) */
    private static Pattern patCalcParenth1B = Pattern.compile(REG_EX_CALCUL_PARENTHESES_NIVEAU1B);
    
    
    /** pattern calcul parentheses niveau 2 */
    private static Pattern patCalcParenth2 = Pattern.compile(REG_EX_CALCUL_PARENTHESES_NIVEAU2);
    
    
    /**
     * On essaye un d'avancer l�, surtout sur le calcul typiquement le 
     * 4+4+...+(ins�rer ici n calculs)+...+2 on impl�mentera plus tard les
     * parenth�ses et le syst�me de priorit� mais j'ai une petite id�e pour
     * les priorit�s
     * @param aCalculer String d'ou il faudra d�cortiquer le calcul
     * @return <ul><li>le calcul r�alis� tout beau tout propre sous un double 
     *                 facile � exploter � la fois pour la calculatrice et le 
     *                 tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculIntermediaire(String aCalculer) {
        /*
         * je vais probablement pas avoir le temps de tout faire d'un coup
         * alors je t'explique l'id�e puis �a me servira � pas oublier � moi
         * 
         * 0_ tester via un matcher que le calcul est au bon format
         * 1_ je declare deux listes �a facilite la reutilisation des donn�es
         *    qui y sont stock�e, une liste contiendra toutes les op�randes
         *    et une contiendra tous les op�rateurs � savoir que l'op�rateur(i)
         *    aura commande op�rande l'op�rande(i) et l'op�rande(i+1) tu
         *    saisis ?
         * 2_ je declare un Scanner avec un delimiter de type Pattern
         *    en fait je juste pouvoir r�cup�rer l'ensemble de digits qui
         *    constitueront le nombre, faudra rendre le Pattern plus �volu�
         *    pour g�rer les parenth�ses parceque je compte g�rer les
         *    priorit�s d'une autre mani�re
         * 3_ On fait gentilement le calcul sans se soucier des priorit�s
         *    op�rateur par op�rateur en supposant pour le moment que ya pas
         *    de multiplicaton ni de division le premier pattern ne consid�rera
         *    comme calcul que le + et le -
         */
        
        // TODO tester si calcul bon format via regex et return un NaN si err
        
        // resultat du calcul
        double resultat = 0;
        
        /*
         *  contiendra toutes les op�randes stock�es dans l'ordre entr� par
         *  l'utilisateur
         */
        ArrayList<Double> listeOperande = new ArrayList<Double>();
        /*
         * contiendra tous les op�rateurs stock�e dans l'ordre entr� par
         * l'utilisateur, � noter que l'op�rateur[n] utilise comme op�rande
         * l'op�rateur[n] (gauche) et l'op�rateur[n+1] (droite)
         */
        ArrayList<Character> listeOperateur = new ArrayList<Character>();
        /*
         * analyse le calcul entr� par l'user pour r�cup�rer facilement
         * et uniquement tous les op�randes
         */
        Scanner testeurOperande = new Scanner(aCalculer);
        // pattern d'analyse d'op�randes suppos�es OK
        Pattern operandeV1 = Pattern.compile("\\s*[+-]\\s*");
        // testeurOperande est pr�t � analyser la calcul gr�ce � ce pattern
        testeurOperande.useDelimiter(operandeV1);
        
        /*
         * analyse le calcul entr� par l'user pour r�cup�rer uniquement les
         * op�rateurs
         */
        Scanner testeurOperateur = new Scanner(aCalculer);
        // pattern d'analyse d'op�rateurs suppos�s OK
        Pattern operateurV1 = Pattern.compile(
                "\\s*\\d+(\\0056\\d+){0,1}\\s*");
        // testeurOperateur est pr�t � analyser le calcul gr�ce � ce pattern
        testeurOperateur.useDelimiter(operateurV1);
        
        // r�cup�re toutes les op�randes
        while (testeurOperande.hasNext()) {
            String verific = testeurOperande.next();
            listeOperande.add(Double.parseDouble(verific));
//            System.out.println(verific + "\n");
        }
        // r�cup�re tous les op�rateurs
        while (testeurOperateur.hasNext()) {
            char verif = testeurOperateur.next().charAt(0);
            listeOperateur.add(verif);
//            System.out.println(verif + "\n");
        }
        
        /*
         * pr�paration pour les calculs, devra �voluer pour prendre en compte
         * tous les calculs
         */
        resultat = listeOperande.get(0);
        
        for (int i=0; i < listeOperateur.size() ; i++) {
            switch (listeOperateur.get(i)) {
            case '+': resultat += listeOperande.get(i+1);
                      break;
            case '-': resultat -= listeOperande.get(i+1);
                      break;
            }
            System.out.println(resultat + "\n");
        }
        
        return resultat; // resultat du calcul
    }
    
    /**
     * Calcule et affiche le r�sultat de la commande
     * @param commande 
     * @return aInserer le texte � ins�rer dans l'�cran
     */
    public static String calculSimple(String commande) {
        


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
    public static String calculParenth1A(String commande) {
        


        String aInserer; // le resultat de la commande

        Matcher calcOk = patCalcParenth1A.matcher(commande);

        aInserer = "\0";
        if (calcOk.matches()) {           
            String strRes1;     // r�sultat du calcul1     
            
            int posdeb = commande.indexOf('(');
            int posfin = commande.indexOf(')');
            
            strRes1 = commande.substring(posdeb+1, posfin);           
            // On transforme le premier nombre r�cup�r� en double
            // et on le stocke dans operande1, on fait de m�me pour l'operande2
            strRes1 = calculSimple(strRes1);
            
            strRes1 = strRes1.substring(2, strRes1.length() - 1);
            
            aInserer = calculSimple(strRes1 + calcOk.group(4) + calcOk.group(5));
            

        }
        return (aInserer);
        
    }
    
    /**
     * Calcule et affiche le r�sultat de la commande
     * @param commande 
     * @return aInserer le texte � ins�rer dans l'�cran
     */
    public static String calculParenth1B(String commande) {
        
        String aInserer; // le resultat de la commande

        Matcher calcOk = patCalcParenth1B.matcher(commande);

        aInserer = "\0";
        if (calcOk.matches()) {           
            String strRes1;     // r�sultat du calcul1     
            
            int posdeb = commande.indexOf('(');
            int posfin = commande.indexOf(')');
            
            strRes1 = commande.substring(posdeb+1, posfin);           
            // On transforme le premier nombre r�cup�r� en double
            // et on le stocke dans operande1, on fait de m�me pour l'operande2
            strRes1 = calculSimple(strRes1);
            
            strRes1 = strRes1.substring(2, strRes1.length() - 1);
            
            aInserer = calculSimple(calcOk.group(1) + calcOk.group(2) + strRes1);
            

        }
        return (aInserer);
        
    }
    
    
    
    
    /**
     * Calcule et affiche le r�sultat de la commande
     * @param commande 
     * @return aInserer le texte � ins�rer dans l'�cran
     */
    public static String calculParenth2(String commande) {
        


        String aInserer; // le resultat de la commande

        Matcher calcParenthesesOk = patCalcParenth2.matcher(commande);

        aInserer = "\0";
        if (calcParenthesesOk.matches()) {           
            String strRes1;     // r�sultat du calcul1
            String strRes2;      // r�sultat du calcul2      
            
            int posdeb1 = commande.indexOf('(');
            int posfin1 = commande.indexOf(')');
            
            int posdeb2 = commande.lastIndexOf('(');
            int posfin2 = commande.lastIndexOf(')');
            
            strRes1 = commande.substring(posdeb1+1, posfin1);
            strRes2 = commande.substring(posdeb2+1, posfin2);            
            // On transforme le premier nombre r�cup�r� en double
            // et on le stocke dans operande1, on fait de m�me pour l'operande2
            strRes1 = calculSimple(strRes1);
            strRes2 = calculSimple(strRes2);
            
            strRes1 = strRes1.substring(2, strRes1.length() - 1);
            strRes2 = strRes2.substring(2, strRes2.length() - 1);
            
            aInserer = calculSimple(strRes1 + calcParenthesesOk.group(4) + strRes2);
            

        }
        return (aInserer);
        
    }

    
    /**
     * Utilise la m�thode ad�quate en fonction de la commande
     * @param commande � ex�cuter
     * @return aInserer le texte � ins�rer dans l'�cran
     */
    public static String calcul(String commande) {        

        String aInserer; // le resultat de la commande

        Matcher calcParenth2Ok = patCalcParenth2.matcher(commande);
        Matcher calcSimpleOk = patCalcSimple.matcher(commande);
        Matcher calcParenth1AOk = patCalcParenth1A.matcher(commande);
        Matcher calcParenth1BOk = patCalcParenth1B.matcher(commande);
        
        aInserer = "\0";
        if (calcParenth2Ok.matches()) {                       
            aInserer = calculParenth2(commande);
        } else if (calcSimpleOk.matches()){
            aInserer = calculSimple(commande);
        } else if (calcParenth1AOk.matches()){
            aInserer = calculParenth1A(commande);
        } else if (calcParenth1BOk.matches()){
            aInserer = calculParenth1B(commande);
        } else {
            aInserer = "Erreur de commande.\n";
        }
            
        return (aInserer);
        
    }

}
