/*
 * Utilitaires.java                                     6 mai 2015
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
 * @author Sébi & jo
 * @version 1.0
 */
public class Utilitaires {
    /**
     * Le calcul intermidiaire permet d'effectuer un nombre inconnu à l'avance
     * d'opérations,les parenthèses ne sont pas prises en compte
     * @param aCalculer String d'ou il faudra décortiquer le calcul
     * @return <ul><li>le calcul réalisé tout beau tout propre sous un double 
     *                 facile à exploter à la fois pour la calculatrice et le 
     *                 tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculIntermediaire(String aCalculer) {
        /*
         *  teste si calcul bon format via regex et retourne un NaN si err
         *  la première opérande peut être négative
         */
        Pattern testCalc = Pattern.compile("-?\\s*\\d+(\\0056\\d+)?\\s*"
                + "(\\s*[/*+-]\\s*\\d+(\\0056\\d+)?\\s*)*");
        Matcher estCalc = testCalc.matcher(aCalculer);
        if (!estCalc.matches()) {
            return Double.NaN; // le calcul n'est pas réalisable
        }

        double resultat = 0;      // resultat du calcul
        char signeSuiv = '\0';    // opérateur suivant à prendre en compte
        double resultTmp;         // resutalt d'un sous calcul de * et /
        boolean debutNeg = false; // true si le cacul commance par un '-'
        String aCalculerVerif = aCalculer;

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
        Pattern operandeV1 = Pattern.compile("\\s*[/+*-]\\s*");
        // testeurOperande est prêt à analyser la calcul grâce à ce pattern
        testeurOperande.useDelimiter(operandeV1);

        /*
         * analyse le calcul entré par l'user pour récupérer uniquement les
         * opérateurs
         */
        Scanner testeurOperateur = new Scanner(aCalculerVerif);
        // pattern d'analyse d'opérateurs supposés OK
        Pattern operateurV1 = Pattern.compile("\\s*\\d+(\\0056\\d+){0,1}\\s*");
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
                resultTmp = listeOperande.get(i);
                // si il y a un autre calcul à effectuer
                if (i < listeOperateur.size()) {
                    // pour savoir si l'opération suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i);
                    // tant que l'opération suivante est prioritaire
                    while (signeSuiv == '*' || signeSuiv == '/') {
                        i++;
                        if (signeSuiv == '*') {
                            resultTmp *= listeOperande.get(i);
                        } else { // signeSuiv == /
                            resultTmp /= listeOperande.get(i);
                        }
                        try { // essai de récupérerl'opérateur suivant
                            signeSuiv = listeOperateur.get(i+1);
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
