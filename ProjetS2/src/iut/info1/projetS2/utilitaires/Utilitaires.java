/*
 * Utilitaires.java					6 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Classe utilitaire permettant de r�aliser les op�rations en fonction des
 * commandes entr�es ou des boutons cliqu�s
 * @author S�bastien
 * @version 1.0
 */
public class Utilitaires {
       
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
    
}
