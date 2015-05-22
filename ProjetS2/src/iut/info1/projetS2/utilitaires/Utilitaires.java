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
 * Classe utilitaire permettant de r�aliser les op�rations en fonction des
 * commandes entr�es ou des boutons cliqu�s
 * @author S�bi & jo
 * @version 1.0
 */
public class Utilitaires {
    /**
     * Le calcul intermidiaire permet d'effectuer un nombre inconnu � l'avance
     * d'op�rations,les parenth�ses ne sont pas prises en compte
     * @param aCalculer String d'ou il faudra d�cortiquer le calcul
     * @return <ul><li>le calcul r�alis� tout beau tout propre sous un double 
     *                 facile � exploter � la fois pour la calculatrice et le 
     *                 tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculIntermediaire(String aCalculer) {
        /*
         *  teste si calcul bon format via regex et retourne un NaN si err
         *  la premi�re op�rande peut �tre n�gative
         */
        Pattern testCalc = Pattern.compile("-?\\s*\\d+(\\0056\\d+)?\\s*"
                + "(\\s*[/*+-]\\s*\\d+(\\0056\\d+)?\\s*)*");
        Matcher estCalc = testCalc.matcher(aCalculer);
        if (!estCalc.matches()) {
            return Double.NaN; // le calcul n'est pas r�alisable
        }

        double resultat = 0;      // resultat du calcul
        char signeSuiv = '\0';    // op�rateur suivant � prendre en compte
        double resultTmp;         // resutalt d'un sous calcul de * et /
        boolean debutNeg = false; // true si le cacul commance par un '-'
        String aCalculerVerif = aCalculer;

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

        // v�rifie si la premi�re op�rande est n�gative
        if (aCalculer.charAt(0) == '-') {
            debutNeg = true;
            aCalculerVerif = aCalculer.substring(1);
        }

        Scanner testeurOperande = new Scanner(aCalculerVerif);
        // pattern d'analyse d'op�randes suppos�es OK
        Pattern operandeV1 = Pattern.compile("\\s*[/+*-]\\s*");
        // testeurOperande est pr�t � analyser la calcul gr�ce � ce pattern
        testeurOperande.useDelimiter(operandeV1);

        /*
         * analyse le calcul entr� par l'user pour r�cup�rer uniquement les
         * op�rateurs
         */
        Scanner testeurOperateur = new Scanner(aCalculerVerif);
        // pattern d'analyse d'op�rateurs suppos�s OK
        Pattern operateurV1 = Pattern.compile("\\s*\\d+(\\0056\\d+){0,1}\\s*");
        // testeurOperateur est pr�t � analyser le calcul gr�ce � ce pattern
        testeurOperateur.useDelimiter(operateurV1);

        // r�cup�re toutes les op�randes
        while (testeurOperande.hasNext()) {
            String verific = testeurOperande.next();
            listeOperande.add(Double.parseDouble(verific));
        }
        // r�cup�re tous les op�rateurs
        while (testeurOperateur.hasNext()) {
            char verif = testeurOperateur.next().charAt(0);
            listeOperateur.add(verif);
        }
        /*
         * pr�paration pour les calculs, devra �voluer pour prendre en compte
         * tous les calculs
         */
        if (debutNeg) {
            resultat = 0 - listeOperande.get(0);
        } else {
            resultat = listeOperande.get(0);
        }

        // pour chaques op�rateur on effectue les calculs associ�s
        for (int i=0; i < listeOperateur.size(); i++) {
            // on modifie le r�sultat interm�diaire selon l'op�rateur
            switch (listeOperateur.get(i)) {
            case '+': // cas de l'addition
                // listeOperande.get(i+1) est la premi�re op�rande
                resultTmp = listeOperande.get(i+1);
                // si il y a un autre calcul � effectuer
                if (i+1 < listeOperateur.size()) {
                    // pour savoir si l'op�ration suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i+1);
                    // tant que l'op�ration suivante est prioritaire
                    while (signeSuiv == '*' || signeSuiv == '/') {
                        i++;
                        if (signeSuiv == '*') {
                            resultTmp *= listeOperande.get(i+1);
                        } else { // signeSuiv == /
                            resultTmp /= listeOperande.get(i+1);
                        }
                        try { // essai de r�cup�rerl'op�rateur suivant
                            signeSuiv = listeOperateur.get(i+1);
                        } catch (IndexOutOfBoundsException e) {
                            // il n'y a pas d'op�rateur suivant
                            signeSuiv = '\0';
                        }
                    }
                }
                // on modifie le r�sultat final
                resultat += resultTmp;
                break;
            case '-': // cas de la soustraction
                // listeOperande.get(i+1) est la premi�re op�rande
                resultTmp = listeOperande.get(i+1);
                // si il y a un autre calcul � effectuer
                if (i+1 < listeOperateur.size()) {
                    // pour savoir si l'op�ration suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i+1);
                    // tant que l'op�ration suivante est prioritaire
                    while (signeSuiv == '*' || signeSuiv == '/') {
                        i++;
                        if (signeSuiv == '*') {
                            resultTmp *= listeOperande.get(i+1);
                        } else { // signeSuiv == /
                            resultTmp /= listeOperande.get(i+1);
                        }
                        try { // essai de r�cup�rerl'op�rateur suivant
                            signeSuiv = listeOperateur.get(i+1);
                        } catch (IndexOutOfBoundsException e) {
                            // il n'y a pas d'op�rateur suivant
                            signeSuiv = '\0';
                        }
                    }
                }
                // on modifie le r�sultat final
                resultat -= resultTmp;
                break;
            default: // si le premier op�rateur est * ou /
                // listeOperande.get(i+1) est la premi�re op�rande
                resultTmp = listeOperande.get(i);
                // si il y a un autre calcul � effectuer
                if (i < listeOperateur.size()) {
                    // pour savoir si l'op�ration suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i);
                    // tant que l'op�ration suivante est prioritaire
                    while (signeSuiv == '*' || signeSuiv == '/') {
                        i++;
                        if (signeSuiv == '*') {
                            resultTmp *= listeOperande.get(i);
                        } else { // signeSuiv == /
                            resultTmp /= listeOperande.get(i);
                        }
                        try { // essai de r�cup�rerl'op�rateur suivant
                            signeSuiv = listeOperateur.get(i+1);
                        } catch (IndexOutOfBoundsException e) {
                            // il n'y a pas d'op�rateur suivant
                            signeSuiv = '\0';
                        }
                    }
                    i--; // retire l'incr�mentation de trop faite dans le while
                }
                // on modifie le r�sultat final
                resultat = resultTmp;
            }
        }
        testeurOperande.close();
        testeurOperateur.close();
        return resultat; // resultat du calcul termin�
    }
}
