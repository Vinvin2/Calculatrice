/*
 * TestUtilitaires.java					29 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.tests;

import iut.info1.projetS2.utilitaires.Utilitaires;

/**
 * tests de la classe utilitaire
 * @author Jonathan
 *
 */
public class TestUtilitaires {

    /**
     * test les calculs intermédiaires (= calculs sans parenthèses)
     */
    private static void testCalculIntermediaires() {
        boolean testOk = true;
        final String[] CALCULS_OK = {
                "2+3",
                "2*-1",
                "2+-1--1*5-2/2",
                "-1*-3",
                "-4/-2",
                "6/-3",
                "1+ 1+  1+1+ 1 +1+ 1 +1 + 1",
                "5",
                "-5"
        };
        
        final double[] RESULTS = {
                5,
                -2,
                5,
                3,
                2,
                -2,
                9,
                5,
                -5
        };
        
        final String[] CALCULS_PAS_OK = {
                "",
                "bonjour",
                "-5-+4",
                "5+*2",
                "NaN",
                "5++2-1"
        };
        
        System.out.println("Tests de chaines correctes");
        for (int i = 0; i < CALCULS_OK.length; i++) {
            if (Utilitaires.calculIntermediaire(CALCULS_OK[i]) == RESULTS[i]) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }
        
        System.out.println("Tests de chaines incorrectes");
        for (int i = 0; i < CALCULS_PAS_OK.length; i++) {
            if (Double.isNaN(
                    Utilitaires.calculIntermediaire(CALCULS_PAS_OK[i]))) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }
        
        if (testOk) {
            System.out.println("Les tests de calculIntermediaire sont OK");
        } else {
            System.out.println("Les tests de calculIntermediaire sont PAS OK");
        }
    }
    
    /**
     * test sur les calculs évolué, c'est à dire incluant les parenthèses
     */
    private static void testCalculEvolue() {
        boolean testOk = true;
        
        final String[] CALCULS_OK = {
                "(2+2)",
                "(2-1)*3+4",
                "2*3-1*(3+2)",
                "((1+1)+(1+1)*(1+1)+1)+1",
                "((-2/4)*-2)+-1"
        };
        
        final double[] RESULTS = {
                4,
                7,
                1,
                8,
                0
        };
        
        final String[] CALCULS_PAS_OK = {
                "(2+2",
                "1-4)",
                "(1+*2)",
                "(1-(1-(2+)))",
                "1+2+(2-4)*-1+"
        };
        System.out.println("\nTests calculEvolue()");
        System.out.println("Tests de chanes correctes");
        for (int i = 0; i < CALCULS_OK.length; i++) {
            if (Utilitaires.calculEvolue(CALCULS_OK[i]) == RESULTS[i]) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }
        
        System.out.println("Tests de chaines incorrectes");
        for (int i = 0; i < CALCULS_PAS_OK.length; i++) {
            if (Double.isNaN(
                    Utilitaires.calculEvolue(CALCULS_PAS_OK[i]))) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }
        
        if (testOk) {
            System.out.println("Les tests de calculEvolue sont OK");
        } else {
            System.out.println("Les tests de calculEvolue sont PAS OK");
        }        
    }
    
    /**
     * lancement des tests
     * @param args
     */
    public static void main(String[] args) {
        testCalculIntermediaires();
        testCalculEvolue();
    }

}
