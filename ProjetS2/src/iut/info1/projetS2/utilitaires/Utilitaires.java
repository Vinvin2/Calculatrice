/*
 * Utilitaires.java					6 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import iut.info1.projetS2.calculatrice.ActionCalculer;

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

    /** Tableau contenant toutes les cases mémoires */
    public static Variable[] casesMem = new Variable[26];
    

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
     * Si la commande est une affectation de type 15 + 4 = A, alors cette
     * méthode est utilisée. On réalise l'opération demandée, puis on affecte
     * le résultat à la variable concernée
     * @param commande la commande à traiter
     * @return le résultat de la commande
     */
    public static double affectation (String commande) {
        String calculARealiser; // le calcul que l'on devra effectuer
        double resultat;        // son résultat
        char nomvar;            // le nom de la variable à affecter
        
        // TODO : moyen de prendre en compte les espaces
        calculARealiser = commande.substring(0, commande.length() - 4);

        // on fait le calcul
        resultat = calculIntermediaire(calculARealiser);
        
        // on prend le nom de la variable
        nomvar = commande.charAt(commande.length()-1);
        
        // on vérifie si la variable n'a jamais été initialisée
        if (casesMem[nomvar - 65] == null) {
            // on créé alors cette variable
            casesMem[nomvar - 65] = new Variable(nomvar, resultat);
        } else {
            // sinon on met à jour sa valeur
            casesMem[nomvar - 65].setValeur(resultat);
        }
        
        return resultat;
    }

    /**
     * Lorsque l'utilisateur entre "MEM", la calculatrice passe en mode mémoire,
     * ainsi, de nouvelles méthodes seront utilisables (dans la classe 
     * CommandesMemoires)
     * @param commande la commande entrée par l'utilisateur
     * @return un message de confirmation si la commande a bien été exécutée,
     * null sinon
     */
    public String passageMem(String commande) {
        // on regarde si la commande MEM a bien été entrée
        Pattern patMem = Pattern.compile("(\\s*)MEM(\\s*)");
        Matcher memok = patMem.matcher(commande);
        // si c'est le cas, le boolean modeMem de ActionCalculer est activé
        if (memok.matches()) {
            ActionCalculer.modeMem = true;
            return "Mode mémoire actif.";
        } else {
            return null;
        }

        
    }
}
