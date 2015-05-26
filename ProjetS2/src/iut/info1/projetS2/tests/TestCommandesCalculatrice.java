/*
 * TestCommandesCalculatrice.java					26 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.tests;

import iut.info1.projetS2.utilitaires.CommandesMemoire;

/**
 * Classe de tests des commandes effectuées avec la calculatrice
 * @author Sébastien
 * @version 1.0
 */
public class TestCommandesCalculatrice {
    
    /**
     * programme principal
     * @param args unused
     */
    public static void main(String[] args) {
        // affectation de résultat de calcul à une variable
        System.out.println(CommandesMemoire.affectation("15+15= A"));
        // calculs avec des variables
        System.out.println(CommandesMemoire.calculVariable("15+A"));
        // commande RAZ
        System.out.println(CommandesMemoire.raz("RAZ A"));
        // commande VOIR
        System.out.println(CommandesMemoire.voir("VOIR A"));
        // commande INCR
        System.out.println(CommandesMemoire.incr("INCR A"));
        // commande CAR
        System.out.println(CommandesMemoire.car("CAR A"));
        // commande SQRT
        System.out.println(CommandesMemoire.sqrt("SQRT A"));
        // commande SOM
        System.out.println(CommandesMemoire.som("SOM A"));
        // commande PROD
        System.out.println(CommandesMemoire.prod("PROD A"));
        // commande MOY
        System.out.println(CommandesMemoire.moy("MOY A"));
        // commande INIT
        System.out.println(CommandesMemoire.init("INIT A 2"));
        // commande ADD
        System.out.println(CommandesMemoire.add("ADD A 2"));
        // commande MUL
        System.out.println(CommandesMemoire.mul("MUL A 2"));
        // commande EXP
        System.out.println(CommandesMemoire.exp("EXP A 2"));
    }
}
