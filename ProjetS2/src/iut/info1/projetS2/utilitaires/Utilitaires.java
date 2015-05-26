/*
 * Utilitaires.java                                     6 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitaire permettant de réaliser les opérations en fonction des
 * commandes entrées ou des boutons cliqués
 * @author Sébi & jo
 * @version 1.0
 */
public class Utilitaires {    
    /** regex identifiant les opérateurs d'un calcul */
    public final static String REG_OPERATEUR = "\\s*[/+*-]\\s*";

    /** regex permettant d'identifier les opérandes d'un calcul */
    public final static String REG_OPERANDE = "\\s*\\d+(\\0056\\d+)?\\s*";

    /** regex vérifiant le format d'un calcul */
    public final static String REG_CALCUL = 
            "-?" + REG_OPERANDE + "(" + REG_OPERATEUR + REG_OPERANDE + ")*";

    /** regex identifiant une opérande plus complexe*/
    private final static String REG_OP_EVO = "(-?([(].*[)])*||([^/+*-]))";

    /** regex vérifiant un calcul évolué */
    private final static String REG_CALC_EVO =
            "((" + REG_OP_EVO + ")+"+REG_OPERATEUR+")*" + REG_OP_EVO;

    /**
     * Type de calcul le plus évolué que la calculatrice et le tableur peuvent
     * réaliser, prends en compte les parenthèses
     * @param aCalculer
     * @return <ul><li>le calcul réalisé sous un double facile à exploiter
     *                 à la fois pour la calculatrice et le tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculEvolue(String aCalculer) {
        /*
         *  teste si calcul bon format via regex et retourne un NaN si err
         *  la première opérande peut être négative
         */
        Pattern testCalcEvo = Pattern.compile(REG_CALC_EVO);
        Matcher estCalcEvo = testCalcEvo.matcher(aCalculer);
        if (!estCalcEvo.matches()) {
            return Double.NaN; // le calcul n'est pas réalisable
        }
        // else, calcul potentiellement réalisable
        String resultat = "";
        String tmp;
        Scanner calculateur = new Scanner(aCalculer);

        // 1+(1+1)+1
        while (calculateur.hasNext()) {
            calculateur.skip("\\s*");
            tmp = calculateur.nextLine();
            System.out.println("TMP = " + tmp);
            calculateur = new Scanner(tmp);
            calculateur.useDelimiter(REG_OPERATEUR + "[(]");
            if (tmp.length() > 0 && tmp.charAt(0) == '(') {
                int deb = tmp.indexOf("(") + 1;
                int fin = tmp.lastIndexOf(")");
                tmp = String.valueOf(calculEvolue(tmp.substring(deb, fin)));
                calculateur.findInLine("[(].*[)]");
                resultat = resultat.concat(tmp);
            } else {
                int deb = tmp.indexOf("(") + 1;
                int fin = tmp.lastIndexOf(")");
                if (deb > 1 && fin > 1 && deb > fin) {
                    calculateur.useDelimiter("[)]" + Utilitaires.REG_OPERATEUR);
                    tmp = calculateur.next();
                    calculateur.useDelimiter("[^*/+-]");
                    tmp = tmp.concat(calculateur.next());
                    calculateur.useDelimiter("[(]");
                    tmp = tmp.concat(calculateur.next());
                    resultat = resultat.concat(
                            String.valueOf(calculEvolue(tmp)));
                } else {
                    tmp = String.valueOf(calculIntermediaire(
                            calculateur.next()));
                    resultat = resultat.concat(tmp);
                }
            }
            try {
                calculateur.useDelimiter("[^*/+-]+");
                resultat = resultat.concat(calculateur.next());
                calculateur.useDelimiter(Utilitaires.REG_OPERATEUR + "[(]");
            } catch (NoSuchElementException e) {
                System.out.println("FIN = " + calculIntermediaire(resultat));
                calculateur.close();
                return calculIntermediaire(resultat);
            }
        }
        System.out.println("FIN = " + calculIntermediaire(resultat));
        calculateur.close();
        return calculIntermediaire(resultat);
    }

    /**
     * Le calcul intermidiaire permet d'effectuer un nombre inconnu à l'avance
     * d'opérations,les parenthèses ne sont pas prises en compte
     * @param aCalculer String d'ou il faudra décortiquer le calcul
     * @return <ul><li>le calcul réalisé sous un double facile à exploiter
     *                 à la fois pour la calculatrice et le tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculIntermediaire(String aCalculer) {
        /*
         *  teste si calcul bon format via regex et retourne un NaN si err
         *  la première opérande peut être négative
         */
        Pattern testCalc = Pattern.compile(REG_CALCUL);
        Matcher estCalc = testCalc.matcher(aCalculer);
        if (!estCalc.matches()) {
            return Double.NaN; // le calcul n'est pas réalisable
        }
        // else
        double resultat = 0;      // resultat du calcul
        char signeSuiv = '\0';    // opérateur suivant à prendre en compte
        double resultTmp;         // resutalt d'un sous calcul de * et /
        boolean debutNeg = false; // true si le cacul commance par un '-'
        String aCalculerVerif = aCalculer; // chaine utilisée pour le calcul

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

        // vérifie si la première opérande est négative
        if (aCalculer.charAt(0) == '-') {
            debutNeg = true;
            aCalculerVerif = aCalculer.substring(1);
        }

        Scanner testeurOperande = new Scanner(aCalculerVerif);
        // pattern d'analyse d'opérandes supposées OK
        Pattern operandeV1 = Pattern.compile(REG_OPERATEUR);
        // testeurOperande est prêt à analyser la calcul grâce à ce pattern
        testeurOperande.useDelimiter(operandeV1);

        /*
         * analyse le calcul entré par l'user pour récupérer uniquement les
         * opérateurs
         */
        Scanner testeurOperateur = new Scanner(aCalculerVerif);
        // pattern d'analyse d'opérateurs supposés OK
        Pattern operateurV1 = Pattern.compile(REG_OPERANDE );
        // testeurOperateur est prêt à analyser le calcul grâce à ce pattern
        testeurOperateur.useDelimiter(operateurV1);

        // récupère toutes les opérandes
        while (testeurOperande.hasNext()) {
            String verific = testeurOperande.next();
            listeOperande.add(Double.parseDouble(verific));
        }
        // récupère tous les opérateurs
        while (testeurOperateur.hasNext()) {
            char verif = testeurOperateur.next().charAt(0);
            listeOperateur.add(verif);
        }
        /*
         * préparation pour les calculs, devra évoluer pour prendre en compte
         * tous les calculs
         */
        if (debutNeg) {
            resultat = 0 - listeOperande.get(0);
        } else {
            resultat = listeOperande.get(0);
        }

        // pour chaques opérateur on effectue les calculs associés
        for (int i=0; i < listeOperateur.size(); i++) {
            // on modifie le résultat intermédiaire selon l'opérateur
            switch (listeOperateur.get(i)) {
            case '+': // cas de l'addition
                // listeOperande.get(i+1) est la première opérande
                resultTmp = listeOperande.get(i+1);
                // si il y a un autre calcul à effectuer
                if (i+1 < listeOperateur.size()) {
                    // pour savoir si l'opération suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i+1);
                    // tant que l'opération suivante est prioritaire
                    while (signeSuiv == '*' || signeSuiv == '/') {
                        i++;
                        if (signeSuiv == '*') {
                            resultTmp *= listeOperande.get(i+1);
                        } else { // signeSuiv == /
                            resultTmp /= listeOperande.get(i+1);
                        }
                        try { // essai de récupérerl'opérateur suivant
                            signeSuiv = listeOperateur.get(i+1);
                        } catch (IndexOutOfBoundsException e) {
                            // il n'y a pas d'opérateur suivant
                            signeSuiv = '\0';
                        }
                    }
                }
                // on modifie le résultat final
                resultat += resultTmp;
                break;
            case '-': // cas de la soustraction
                // listeOperande.get(i+1) est la première opérande
                resultTmp = listeOperande.get(i+1);
                // si il y a un autre calcul à effectuer
                if (i+1 < listeOperateur.size()) {
                    // pour savoir si l'opération suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i+1);
                    // tant que l'opération suivante est prioritaire
                    while (signeSuiv == '*' || signeSuiv == '/') {
                        i++;
                        if (signeSuiv == '*') {
                            resultTmp *= listeOperande.get(i+1);
                        } else { // signeSuiv == /
                            resultTmp /= listeOperande.get(i+1);
                        }
                        try { // essai de récupérerl'opérateur suivant
                            signeSuiv = listeOperateur.get(i+1);
                        } catch (IndexOutOfBoundsException e) {
                            // il n'y a pas d'opérateur suivant
                            signeSuiv = '\0';
                        }
                    }
                }
                // on modifie le résultat final
                resultat -= resultTmp;
                break;
            default: // si le premier opérateur est * ou /
                // listeOperande.get(i+1) est la première opérande
                resultTmp = debutNeg ? 0 - listeOperande.get(i)
                        : listeOperande.get(i);
                // si il y a un autre calcul à effectuer
                if (i < listeOperateur.size()) {
                    // pour savoir si l'opération suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i);
                    // tant que l'opération suivante est prioritaire
                    while (signeSuiv == '*' || signeSuiv == '/') {
                        i++;
                        if (signeSuiv == '*') {
                            // System.out.println(resultTmp);
                            resultTmp *= listeOperande.get(i);
                            // System.out.println(resultTmp);
                        } else { // signeSuiv == '/'
                            // System.out.println(resultTmp);
                            resultTmp /= listeOperande.get(i);
                            // System.out.println(resultTmp);
                        }
                        try { // essai de récupérerl'opérateur suivant
                            signeSuiv = listeOperateur.get(i);
                        } catch (IndexOutOfBoundsException e) {
                            // il n'y a pas d'opérateur suivant
                            signeSuiv = '\0';
                        }
                    }
                    i--; // retire l'incrémentation de trop faite dans le while
                }
                // on modifie le résultat final
                resultat = resultTmp;
            }
        }
        testeurOperande.close();
        testeurOperateur.close();
        return resultat; // resultat du calcul terminé
    }
}
