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
 * Classe utilitaire permettant de r�aliser les op�rations en fonction des
 * commandes entr�es ou des boutons cliqu�s
 * @author Sebastien et Jonathan
 * @version 1.0
 */
public class Utilitaires {    
    /** regex identifiant les op�rateurs d'un calcul */
    public final static String REG_OPERATEUR = "\\s*[/+*-]\\s*";

    /** regex permettant d'identifier les op�randes d'un calcul */
    public final static String REG_OPERANDE = "\\s*[-]?\\d+(\\0056\\d+)?\\s*";

    /** regex v�rifiant le format d'un calcul */
    public final static String REG_CALCUL = 
            REG_OPERANDE + "(" + REG_OPERATEUR + REG_OPERANDE + ")*";

    /**
     * verifie s'il y a autant de parenth�ses ouvrantes que de parenth�ses
     * fermante
     * @param aVerifier chaine de caract�re ou effectuer la verification
     * @return true s'il y a autant de parenth�ses ouvrante que fermantes
     *         false sinon
     */
    public static boolean verifNbParent(String aVerifier) {
        int nbOuv = 0;  // nombre de parenth�ses ouvrantes trouv�
        int nbFerm = 0; // nombre de parenth�ses fermantes trouv�
        // recherche et compte toutes les occurrences de '(' et ')'
        for (int i =0; i < aVerifier.length(); i++) {
            if (aVerifier.charAt(i) == '(') {
                nbOuv++;
            } else if (aVerifier.charAt(i) == ')') {
                nbFerm++;
            }
            // else aVerifier.charAt(i) est un caract�re autre
        }
        return nbOuv == nbFerm;
    }

    /**
     * Type de calcul le plus �volu� que la calculatrice et le tableur peuvent
     * r�aliser, prends en compte les parenth�ses
     * @param aCalculer
     * @return <ul><li>le calcul r�alis� sous un double facile � exploiter
     *                 � la fois pour la calculatrice et le tableur</li>
     *             <li>Double.NaN si erreur dans le calcul</li></ul>
     */
    public static double calculEvolue(String aCalculer) {
        // la v�rification de syntaxe se fait dans calculIntermedaire()
        //String aCalculerVerif;
        String resultat = ""; // chaine transform�e � renvoyer
        String tmp; // chaine temporaire
        String subTmp; // coupure de chaine temporaire

        if (!verifNbParent(aCalculer)) {
            return Double.NaN;
        }
        // Scanner d'analyse du calcul
        Scanner calculateur = new Scanner(aCalculer);


        // on boucle tant qu'il reste quelquechose � calcul
        while (calculateur.hasNext()) {
            // on supprimer les occurences d'espaces, inutiles au calcul
            calculateur.skip("\\s*");
            // on stocke le contenu du Scanner dans tmp
            tmp = calculateur.nextLine();
            /*
             * calcul analyse une String equals � celle analys�e avant
             * l'instruction pr�c�dente
             */
            calculateur = new Scanner(tmp);
            /*
             *  la d�limitation entre calculs simple et calcul �volu� est
             *  toujours de type op�rande suivit d'une parenth�se ouvrante
             *  donc on modifie le delimier pour s�parer ces deux types de 
             *  calculs
             */
            calculateur.useDelimiter(REG_OPERATEUR + "[(]");
            // le prochain calcul est un calcul �volu�
            if (tmp.length() > 0 && tmp.charAt(0) == '(') {
                // r�cup�re l'indexe de fin de ce calcul �volu�
                int fin = Commandes.fermetureParentheseA(tmp);
                // sauvegarde de tmp qui va �tre modifi�e
                String sauvegarde = tmp.toString();
                // r�up�re le calcul �volu� dans subTmp 
                subTmp = String.valueOf(calculEvolue(tmp.substring(1, fin)));
                // occurence de cases remplac�es
                tmp = subTmp.toString();
                resultat = resultat.concat(tmp);
                tmp = sauvegarde.substring(fin + 1);
                /* 
                 * cr�� un nouveau scanner qui a �t� 'avanc�' de la chaine 
                 * r�cup�r�e � la main
                 */
                calculateur.close();
                calculateur = new Scanner(tmp);
            } else {
                /*
                 *  le calcul est assez simple pour �tre r�alis� directement 
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
                 * calcul n'a plus d'op�rateur et est donc vide, la 
                 * transformation est termin�e
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

        if (aCalculer.matches(REG_OPERANDE)) {
            return Double.parseDouble(aCalculer);
        }

        if (!estCalc.matches()) {
            return Double.NaN; // le calcul n'est pas r�alisable
            //System.out.println("detect� comme faux");
        }
        // else
        double resultat = 0;      // resultat du calcul
        char signeSuiv = '\0';    // op�rateur suivant � prendre en compte
        double resultTmp;         // resutalt d'un sous calcul de * et /
        String verific;           // variable de r�cup�ration

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
        // Scanner d'analyse du calcul
        Scanner testeur = new Scanner(aCalculer);
        // pattern d'analyse

        // r�cup�re toutes les op�randes
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
        testeur.close();
        return resultat; // resultat du calcul termin�
    }
}