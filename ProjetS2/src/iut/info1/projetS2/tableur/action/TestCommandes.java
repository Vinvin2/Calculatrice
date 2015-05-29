/*
 * TestCommandes.java					29 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.tableur.action;
import iut.info1.projetS2.tableur.*;

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

        System.out.println("\nTests de chaines incorrectes");
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
     * lance les tests
     * @param args
     */
    public static void main(String[] args) {
        testRecupCases();
    }

}
