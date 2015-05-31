/*
 * TestCommandes.java					29 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.tableur.action;
import java.util.Scanner;

/**
 * tests de la classe commande
 * @author Jonathan
 *
 */
public class TestCommandes {

    /**
     * tests de la méthode recupCases
     */
    private static void testRecupCases() {
        boolean testOk = true;
        int[] coords;

        final String[] CASES_OK = {
                "A1",
                "1A",
                "$A10",
                "10$A",
                "$10Z",
                "Z$10",
                "$A$1",
                "$1$A"
        };

        final int[][] COORDS = {
                new int[] {0, 0},
                new int[] {0, 0},
                new int[] {9, 0},
                new int[] {9, 0},
                new int[] {9, 25},
                new int[] {9, 25},
                new int[] {0, 0},
                new int[] {0, 0}
        };

        final String[] CASES_PAS_OK = {
                "AA1",
                "1A1",
                "A1A",
                "bonjour",
                "A21",
                "21A",
                "$$A$1",
                "$1$$$A"
        };

        System.out.println("Tests de chaines correctes");
        for (int i = 0; i < CASES_OK.length; i++) {
            coords = Commandes.recupCase(CASES_OK[i]);
            if (coords[0] == COORDS[i][0] && coords[1] == COORDS[i][1]) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }

        System.out.println("Tests de chaines incorrectes");
        for (int i = 0; i < CASES_PAS_OK.length; i++) {
            if (Commandes.recupCase(CASES_PAS_OK[i]) == null) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }

        if (testOk) {
            System.out.println("Tests de recupCases OK");
        } else {
            System.out.println("Tests de recupCases PAS OK");
        }
    }
    
    /**
     * permet de testerla méthode adapteCalc
     */
    private static void testAdapteCalc() {
        boolean testOk = true;
        final String[] aTester = {
                "$A$1 + $A2 + $3A",
                "A1toto$A2",
                "A1 + A2",
                "1 + 4 + B1",
                "bonjour F21",
                "F20"
        };
        
        final String[] decalage = {
                "1 1",
                "1 1",
                "0 0",
                "2 -1",
                "-1 -1",
                "-1 -1"
        };
        Scanner dec;
        
        final String[] resultat = {
                "$A$1 + $A3 + $3B",
                "B2toto$A3",
                "A1 + A2",
                "1 + 4 + A3",
                "bonjour E11",
                "E19"
        };
        
        System.out.println("\nTests de adapteCalc");
        for (int i = 0; i < resultat.length; i++) {
            dec = new Scanner(decalage[i]);
            if (Commandes.adapteCalc(aTester[i], dec.nextInt(), dec.nextInt())
                    .equals(resultat[i])) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }

        if (testOk) {
            System.out.println("Tests de adapteCalc OK");
        } else {
            System.out.println("Tests de adapteCalc PAS OK");
        }
    }

    /**
     * test la méthode fermetureParentheseA
     */
    private static void testFermetureParentheseA() {
        boolean testOk = true;
        final String[] aTester = {
                "(1 +2 )",
                "((((bonjour))))",
                "((toto)tata)",
                "l(br)",
                "(toto"
        };
        
        final int[] resultat = {
                6,
                14,
                11,
                -1,
                -1
        };
        
        System.out.println("\nTests de fermetureParentheseA");
        for (int i = 0; i < resultat.length; i++) {
            if (Commandes.fermetureParentheseA(aTester[i]) == resultat[i]) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }
        
        if (testOk) {
            System.out.println("Tests de fermetureParentheseA OK");
        } else {
            System.out.println("Tests de fermetureParentheseA PAS OK");
        }
    }
    
    /**
     * Test de colonneLocked et ligneLocked
     */
    private static void testLock() {
        boolean testOk = true;
        final String[] aTester = {
                "A19",
                "$A1",
                "A$20",
                "$A$1",
                "20A",
                "1$Z",
                "$20A",
                "$10$F"
        };
        
        final boolean[][] resultat = {
                new boolean[] {false, false},
                new boolean[] {false, true},
                new boolean[] {true, false},
                new boolean[] {true, true},
                new boolean[] {false, false},
                new boolean[] {false, true},
                new boolean[] {true, false},
                new boolean[] {true, true}
        };
        
        System.out.println("\nTests des vérifications de '$'");
        for (int i = 0; i < resultat.length; i++) {
            if ((Commandes.ligneLocked(aTester[i]) == resultat[i][0])
                    && (Commandes.colonneLocked(aTester[i])== resultat[i][1])) {
                System.out.println("Test " + i + " OK");
            } else {
                System.out.println("Test " + i + " PAS OK");
                testOk = false;
            }
        }
        
        if (testOk) {
            System.out.println("Tests de colonneLocked et ligneLocked OK");
        } else {
            System.out.println("Tests de colonneLocked et ligneLocked PAS OK");
        } 
    }
    
    /**
     * lance les tests
     * @param args
     */
    public static void main(String[] args) {
        testRecupCases();
        testAdapteCalc();
        testFermetureParentheseA();
        testLock();
    }

}
