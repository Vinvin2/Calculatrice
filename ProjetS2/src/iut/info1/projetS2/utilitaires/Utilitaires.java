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
 * Classe utilitaire permettant de r�aliser les op�rations en fonction des
 * commandes entr�es ou des boutons cliqu�s
 * @author S�bi & jo
 * @version 1.0
 */
public class Utilitaires {    
    /** regex identifiant les op�rateurs d'un calcul */
    public final static String REG_OPERATEUR = "\\s*[/+*-]\\s*";

    /** regex permettant d'identifier les op�randes d'un calcul */
    public final static String REG_OPERANDE = "\\s*\\d+(\\0056\\d+)?\\s*";

    /** regex v�rifiant le format d'un calcul */
    public final static String REG_CALCUL = 
            "-?" + REG_OPERANDE + "(" + REG_OPERATEUR + REG_OPERANDE + ")*";

    /** regex identifiant une op�rande plus complexe*/
    private final static String REG_OP_EVO = "(-?([(].*[)])*||([^/+*-]))";

    /** regex v�rifiant un calcul �volu� */
    private final static String REG_CALC_EVO =
            "((" + REG_OP_EVO + ")+"+REG_OPERATEUR+")*" + REG_OP_EVO;

    /**
     * Type de calcul le plus �volu� que la calculatrice et le tableur peuvent
     * r�aliser, prends en compte les parenth�ses
     * @param aCalculer
     * @return <ul><li>le calcul r�alis� sous un double facile � exploiter
     *                 � la fois pour la calculatrice et le tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculEvolue(String aCalculer) {
        /*
         *  teste si calcul bon format via regex et retourne un NaN si err
         *  la premi�re op�rande peut �tre n�gative
         */
        Pattern testCalcEvo = Pattern.compile(REG_CALC_EVO);
        Matcher estCalcEvo = testCalcEvo.matcher(aCalculer);
        if (!estCalcEvo.matches()) {
            return Double.NaN; // le calcul n'est pas r�alisable
        }
        // else, calcul potentiellement r�alisable
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
     * Le calcul intermidiaire permet d'effectuer un nombre inconnu � l'avance
     * d'op�rations,les parenth�ses ne sont pas prises en compte
     * @param aCalculer String d'ou il faudra d�cortiquer le calcul
     * @return <ul><li>le calcul r�alis� sous un double facile � exploiter
     *                 � la fois pour la calculatrice et le tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculIntermediaire(String aCalculer) {
        /*
         *  teste si calcul bon format via regex et retourne un NaN si err
         *  la premi�re op�rande peut �tre n�gative
         */
        Pattern testCalc = Pattern.compile(REG_CALCUL);
        Matcher estCalc = testCalc.matcher(aCalculer);
        if (!estCalc.matches()) {
            return Double.NaN; // le calcul n'est pas r�alisable
        }
        // else
        double resultat = 0;      // resultat du calcul
        char signeSuiv = '\0';    // op�rateur suivant � prendre en compte
        double resultTmp;         // resutalt d'un sous calcul de * et /
        boolean debutNeg = false; // true si le cacul commance par un '-'
        String aCalculerVerif = aCalculer; // chaine utilis�e pour le calcul

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
        Pattern operandeV1 = Pattern.compile(REG_OPERATEUR);
        // testeurOperande est pr�t � analyser la calcul gr�ce � ce pattern
        testeurOperande.useDelimiter(operandeV1);

        /*
         * analyse le calcul entr� par l'user pour r�cup�rer uniquement les
         * op�rateurs
         */
        Scanner testeurOperateur = new Scanner(aCalculerVerif);
        // pattern d'analyse d'op�rateurs suppos�s OK
        Pattern operateurV1 = Pattern.compile(REG_OPERANDE );
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
                resultTmp = debutNeg ? 0 - listeOperande.get(i)
                        : listeOperande.get(i);
                // si il y a un autre calcul � effectuer
                if (i < listeOperateur.size()) {
                    // pour savoir si l'op�ration suivante est prioritaire (* /)
                    signeSuiv = listeOperateur.get(i);
                    // tant que l'op�ration suivante est prioritaire
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
                        try { // essai de r�cup�rerl'op�rateur suivant
                            signeSuiv = listeOperateur.get(i);
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
