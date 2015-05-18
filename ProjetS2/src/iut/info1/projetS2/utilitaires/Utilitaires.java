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
 * Classe utilitaire permettant de réaliser les opérations en fonction des
 * commandes entrées ou des boutons cliqués
 * @author Sébastien
 * @version 1.0
 */
public class Utilitaires {

    /** Modèle d'expression du calcul sous forme de chaîne de caractères */
    public static final String REG_EX_CALCUL_SIMPLE = 
            ("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");
    
    /** Modèle d'un calcul avec des parenthèses */
    public static final String REG_EX_CALCUL_PARENTHESES_NIVEAU1A = 
            ("[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*([+-/*])" + "[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");

    /** modèle d'un calcul avec des parenthèses simple à 1 niveau */
    private static final String REG_EX_CALCUL_PARENTHESES_NIVEAU1B = 
            "[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*" + "([+-/*])[ ]*[(]" + REG_EX_CALCUL_SIMPLE + "[)][ ]*";
    
    
    /** Modèle d'un calcul avec des parenthèses simple : (15 + 1) / (1 + 1) */
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
     * On essaye un d'avancer là, surtout sur le calcul typiquement le 
     * 4+4+...+(insérer ici n calculs)+...+2 on implémentera plus tard les
     * parenthèses et le système de priorité mais j'ai une petite idée pour
     * les priorités
     * @param aCalculer String d'ou il faudra décortiquer le calcul
     * @return <ul><li>le calcul réalisé tout beau tout propre sous un double 
     *                 facile à exploter à la fois pour la calculatrice et le 
     *                 tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculIntermediaire(String aCalculer) {
        /*
         * je vais probablement pas avoir le temps de tout faire d'un coup
         * alors je t'explique l'idée puis ça me servira à pas oublier à moi
         * 
         * 0_ tester via un matcher que le calcul est au bon format
         * 1_ je declare deux listes ça facilite la reutilisation des données
         *    qui y sont stockée, une liste contiendra toutes les opérandes
         *    et une contiendra tous les opérateurs à savoir que l'opérateur(i)
         *    aura commande opérande l'opérande(i) et l'opérande(i+1) tu
         *    saisis ?
         * 2_ je declare un Scanner avec un delimiter de type Pattern
         *    en fait je juste pouvoir récupérer l'ensemble de digits qui
         *    constitueront le nombre, faudra rendre le Pattern plus évolué
         *    pour gérer les parenthèses parceque je compte gérer les
         *    priorités d'une autre manière
         * 3_ On fait gentilement le calcul sans se soucier des priorités
         *    opérateur par opérateur en supposant pour le moment que ya pas
         *    de multiplicaton ni de division le premier pattern ne considèrera
         *    comme calcul que le + et le -
         */
        
        // TODO tester si calcul bon format via regex et return un NaN si err
        
        // resultat du calcul
        double resultat = 0;
        
        /*
         *  contiendra toutes les opérandes stockées dans l'ordre entré par
         *  l'utilisateur
         */
        ArrayList<Double> listeOperande = new ArrayList<Double>();
        /*
         * contiendra tous les opérateurs stockée dans l'ordre entré par
         * l'utilisateur, à noter que l'opérateur[n] utilise comme opérande
         * l'opérateur[n] (gauche) et l'opérateur[n+1] (droite)
         */
        ArrayList<Character> listeOperateur = new ArrayList<Character>();
        /*
         * analyse le calcul entré par l'user pour récupérer facilement
         * et uniquement tous les opérandes
         */
        Scanner testeurOperande = new Scanner(aCalculer);
        // pattern d'analyse d'opérandes supposées OK
        Pattern operandeV1 = Pattern.compile("\\s*[+-]\\s*");
        // testeurOperande est prêt à analyser la calcul grâce à ce pattern
        testeurOperande.useDelimiter(operandeV1);
        
        /*
         * analyse le calcul entré par l'user pour récupérer uniquement les
         * opérateurs
         */
        Scanner testeurOperateur = new Scanner(aCalculer);
        // pattern d'analyse d'opérateurs supposés OK
        Pattern operateurV1 = Pattern.compile(
                "\\s*\\d+(\\0056\\d+){0,1}\\s*");
        // testeurOperateur est prêt à analyser le calcul grâce à ce pattern
        testeurOperateur.useDelimiter(operateurV1);
        
        // récupère toutes les opérandes
        while (testeurOperande.hasNext()) {
            String verific = testeurOperande.next();
            listeOperande.add(Double.parseDouble(verific));
//            System.out.println(verific + "\n");
        }
        // récupère tous les opérateurs
        while (testeurOperateur.hasNext()) {
            char verif = testeurOperateur.next().charAt(0);
            listeOperateur.add(verif);
//            System.out.println(verif + "\n");
        }
        
        /*
         * préparation pour les calculs, devra évoluer pour prendre en compte
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
    public static String calculParenth1A(String commande) {
        


        String aInserer; // le resultat de la commande

        Matcher calcOk = patCalcParenth1A.matcher(commande);

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
    public static String calculParenth1B(String commande) {
        
        String aInserer; // le resultat de la commande

        Matcher calcOk = patCalcParenth1B.matcher(commande);

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
            
            aInserer = calculSimple(calcOk.group(1) + calcOk.group(2) + strRes1);
            

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
