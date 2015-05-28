/*
 * Utilitaires.java                                     6 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import iut.info1.projetS2.tableur.action.Commandes;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitaire permettant de réaliser les opérations en fonction des
 * commandes entrées ou des boutons cliqués
 * @author Sebastien et Jonathan
 * @version 1.0
 */
public class Utilitaires {    
    /** regex identifiant les opérateurs d'un calcul */
    public final static String REG_OPERATEUR = "\\s*[/+*-]\\s*";

    /** regex permettant d'identifier les opérandes d'un calcul */
    public final static String REG_OPERANDE = "\\s*[-]?\\d+(\\0056\\d+)?\\s*";

    /** regex vérifiant le format d'un calcul */
    public final static String REG_CALCUL = 
            REG_OPERANDE + "(" + REG_OPERATEUR + REG_OPERANDE + ")*";

    /**
     * verifie s'il y a autant de parenthèses ouvrantes que de parenthèses
     * fermante
     * @param aVerifier chaine de caractère ou effectuer la verification
     * @return true s'il y a autant de parenthèses ouvrante que fermantes
     *         false sinon
     */
    public static boolean verifNbParent(String aVerifier) {
        int nbOuv = 0;  // nombre de parenthèses ouvrantes trouvé
        int nbFerm = 0; // nombre de parenthèses fermantes trouvé
        // recherche et compte toutes les occurrences de '(' et ')'
        for (int i =0; i < aVerifier.length(); i++) {
            if (aVerifier.charAt(i) == '(') {
                nbOuv++;
            } else if (aVerifier.charAt(i) == ')') {
                nbFerm++;
            }
            // else aVerifier.charAt(i) est un caractère autre
        }
        return nbOuv == nbFerm;
    }

    /**
     * Type de calcul le plus évolué que la calculatrice et le tableur peuvent
     * réaliser, prends en compte les parenthèses
     * @param aCalculer
     * @return <ul><li>le calcul réalisé sous un double facile à exploiter
     *                 à la fois pour la calculatrice et le tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculEvolue(String aCalculer) {
        // la vérification de syntaxe se fait dans calculIntermedaire()
        //String aCalculerVerif;
        String resultat = ""; // chaine transformée à renvoyer
        String tmp; // chaine temporaire
        String subTmp; // coupure de chaine temporaire

        if (!verifNbParent(aCalculer)) {
            return Double.NaN;
        }
        // Scanner d'analyse du calcul
        Scanner calculateur = new Scanner(aCalculer);


        // on boucle tant qu'il reste quelquechose à calcul
        while (calculateur.hasNext()) {
            // on supprimer les occurences d'espaces, inutiles au calcul
            calculateur.skip("\\s*");
            // on stocke le contenu du Scanner dans tmp
            tmp = calculateur.nextLine();
            /*
             * calcul analyse une String equals à celle analysée avant
             * l'instruction précédente
             */
            calculateur = new Scanner(tmp);
            /*
             *  la délimitation entre calculs simple et calcul évolué est
             *  toujours de type opérande suivit d'une parenthèse ouvrante
             *  donc on modifie le delimier pour séparer ces deux types de 
             *  calculs
             */
            calculateur.useDelimiter(REG_OPERATEUR + "[(]");
            // le prochain calcul est un calcul évolué
            if (tmp.length() > 0 && tmp.charAt(0) == '(') {
                // récupère l'indexe de fin de ce calcul évolué
                int fin = Commandes.fermetureParentheseA(tmp);
                // sauvegarde de tmp qui va être modifiée
                String sauvegarde = tmp.toString();
                // réupère le calcul évolué dans subTmp 
                subTmp = String.valueOf(calculEvolue(tmp.substring(1, fin)));
                // occurence de cases remplacées
                tmp = subTmp.toString();
                resultat = resultat.concat(tmp);
                tmp = sauvegarde.substring(fin + 1);
                /* 
                 * créé un nouveau scanner qui a été 'avancé' de la chaine 
                 * récupérée à la main
                 */
                calculateur.close();
                calculateur = new Scanner(tmp);
            } else {
                /*
                 *  le calcul est assez simple pour être réalisé directement 
                 *  par calculIntermediaire
                 */
                tmp = calculateur.next();
                resultat = resultat.concat(tmp);
            }
            try {
                // calcul simple que remplaceCases peut modifier correctement
                calculateur.useDelimiter("[^*/+-]+");
                resultat = resultat.concat(calculateur.next());
                calculateur.useDelimiter(Utilitaires.REG_OPERATEUR + "[(]");
            } catch (NoSuchElementException e) {
                /* 
                 * calcul n'a plus d'opérateur et est donc vide, la 
                 * transformation est terminée
                 */
                calculateur.close();
                return calculIntermediaire(resultat);
            }
        }
        // si le calcul est syntaxiquement correcte on ne passe pas ici
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

        if (aCalculer.matches(REG_OPERANDE)) {
            return Double.parseDouble(aCalculer);
        }

        if (!estCalc.matches()) {
            return Double.NaN; // le calcul n'est pas réalisable
            //System.out.println("detecté comme faux");
        }
        // else
        double resultat = 0;      // resultat du calcul
        char signeSuiv = '\0';    // opérateur suivant à prendre en compte
        double resultTmp;         // resutalt d'un sous calcul de * et /
        String verific;           // variable de récupération

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
        // Scanner d'analyse du calcul
        Scanner testeur = new Scanner(aCalculer);
        // pattern d'analyse

        // récupère toutes les opérandes
        while (testeur.hasNext()) {
            verific = testeur.findInLine(REG_OPERANDE);
            //System.out.println(verific);
            listeOperande.add(Double.parseDouble(verific));

            testeur.skip("\\s*");
            verific = testeur.findInLine(REG_OPERATEUR);
            //System.out.println(verific);
            if (verific != null) {
                listeOperateur.add(verific.charAt(0));
                testeur.skip("\\s*");
            }
        }
        // System.out.println(listeOperande.toString());
        // System.out.println(listeOperateur.toString());
        // System.out.println(aCalculer);
        resultat = listeOperande.get(0);
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
        testeur.close();
        return resultat; // resultat du calcul terminé
    }
}