/*
 * TestCommandesCalculatrice.java			            26 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.tests;

import iut.info1.projetS2.utilitaires.CommandesMemoire;
import iut.info1.projetS2.utilitaires.Utilitaires;

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
        // calculs simples/parentheses
        System.out.println("Calculs simples : \n");
        System.out.println(Utilitaires.calculEvolue("15+15"));
        System.out.println(Utilitaires.calculEvolue("15.12-15.12"));
        System.out.println(Utilitaires.calculEvolue("15*15"));
        System.out.println(Utilitaires.calculEvolue("15/5"));
        
        // calcul avec espaces fonctionnel
        System.out.println("\nCalculs simples avec espaces : \n");
        System.out.println(Utilitaires.calculEvolue("   15   /   5  "));
        
        // calculs avec parenthèses (autant de niveaux que l'on souhaite
        System.out.println("\nCalculs avec parenthèses : \n");
        System.out.println(Utilitaires.calculEvolue("(15/5)"));
        System.out.println(Utilitaires.calculEvolue("2*(15+5)"));
        System.out.println(Utilitaires.calculEvolue("(15+5)*2"));
        System.out.println(Utilitaires.calculEvolue("(15+5)*(2+1)"));
        System.out.println(Utilitaires.calculEvolue("(15*(5+1))+4"));
        System.out.println(Utilitaires.calculEvolue("(15*(5+1))+4"));
        // avec parentheses et espaces
        System.out.println("\nCalculs avec parenthèses et espaces : \n");
        System.out.println(Utilitaires.calculEvolue(" ( 15 * (5 +1))+ 4 "));
        // autant de niveaux de parenthèses que l'on souhaite
        System.out.println("\nPlusieurs niveaux de parenthèses : \n");
        System.out.println(Utilitaires.
                calculEvolue("(((5.12*(5+1))+4)*4)-1"));
        // sans parenthèses mais avec plusieurs opérandes
        System.out.println("\nplusieurs opérandes, sans parenthèses : \n");
        System.out.println(Utilitaires.calculEvolue("15+1*4+45-1/2"));
        
        // ces calculs renverront un NaN, qui seront traités pour renvoyer une
        // erreur à l'écran
        System.out.println("\nCalculs avec erreur (renvoient NaN) : \n");
        System.out.println(Utilitaires.calculEvolue("15/0"));
        System.out.println(Utilitaires.calculEvolue("15 /blbl"));
     // erreur renvoyée, gérée via les regex et l'affichage dans ActionCalculer 
     // (parenthese non refermée)   
     //   System.out.println(Utilitaires.calculEvolue("(15+12"));
        
        // affectation de résultat de calcul à une variable
        System.out.println("\nAffectation de résultat à une variable : \n");
        System.out.println(CommandesMemoire.affectation("15+15=A"));
        // espaces
        System.out.println(CommandesMemoire.affectation(" 15 + 15  =  A  "));
        // avec parentheses
        System.out.println(CommandesMemoire.affectation("15+15*(1+2)= A"));
        
        // affectation avec d'autres variables
        System.out.println(CommandesMemoire.affectation("A+1= B"));
        System.out.println(CommandesMemoire.affectation("A-B= C"));
        
        // erreurs (NaN géré via l'affichage dans ActionCalculer)
        System.out.println("\nErreurs d'affectation (renvoient NaN) : \n");
        System.out.println(CommandesMemoire.affectation("Ah-1= C")); 
        // renvoie une excpetion, gérée via les regex dans ActionCalculer
        // System.out.println(CommandesMemoire.affectation("A-1= Cg"));
 
        // calculs avec des variables
        System.out.println("\nCalculs avec variables : \n");
        System.out.println(CommandesMemoire.calculVariable("15+A"));
        System.out.println(CommandesMemoire.calculVariable("B+A/C"));
        
        // commande RAZ
        System.out.println("\nCommande RAZ : \n");
        System.out.println(CommandesMemoire.raz("RAZ A"));
        System.out.println(CommandesMemoire.raz("RAZ A..C")); 
        System.out.println(CommandesMemoire.raz(" RAZ   G"));
        // erreurs
        System.out.println(CommandesMemoire.raz("RAZ C..A")); 
        
        // commande VOIR
        System.out.println("\nCommande VOIR : \n");
        System.out.println(CommandesMemoire.voir("VOIR A"));
        System.out.println(CommandesMemoire.voir("VOIR J"));
        System.out.println(CommandesMemoire.voir("VOIR A..H"));
        // erreurs
        System.out.println(CommandesMemoire.voir("VOIR H..A"));
        System.out.println(CommandesMemoire.voir("VOIR AZ"));
        
        // commande INCR
        System.out.println("\nCommande INCR : \n");
        System.out.println(CommandesMemoire.incr("INCR A"));
        System.out.println(CommandesMemoire.incr("INCR J"));
        System.out.println(CommandesMemoire.incr("INCR A..H"));
        // erreurs
        System.out.println(CommandesMemoire.incr("INCR H..A"));
        System.out.println(CommandesMemoire.incr("INCR AZ"));
        
        // commande CAR
        System.out.println("\nCommande CAR : \n");
        System.out.println(CommandesMemoire.car("CAR A"));
        System.out.println(CommandesMemoire.car("CAR J"));
        System.out.println(CommandesMemoire.car("CAR A..H"));
        // erreurs
        System.out.println(CommandesMemoire.car("CAR H..A"));
        System.out.println(CommandesMemoire.car("CAR AZ"));
        
        // commande SQRT
        System.out.println("\nCommande SQRT : \n");
        System.out.println(CommandesMemoire.sqrt("SQRT A"));
        System.out.println(CommandesMemoire.sqrt("SQRT J"));
        System.out.println(CommandesMemoire.sqrt("SQRT A..H"));
        // erreurs
        System.out.println(CommandesMemoire.sqrt("SQRT H..A"));
        System.out.println(CommandesMemoire.sqrt("SQRT AZ"));
        
        // commande SOM
        System.out.println("\nCommande SOM : \n");
        CommandesMemoire.init("INIT A..D 2");
        System.out.println(CommandesMemoire.som("SOM A..D"));
        // erreurs (NaN gérés via ActionCalculer)
        System.out.println(CommandesMemoire.som("SOM A..H"));
        System.out.println(CommandesMemoire.som("SOM H..A"));
        System.out.println(CommandesMemoire.som("SOM AZ"));
        System.out.println(CommandesMemoire.som("SOM A"));
        
        // commande PROD
        System.out.println("\nCommande PROD : \n");
        CommandesMemoire.init("INIT A..D 2");
        System.out.println(CommandesMemoire.prod("PROD A..D"));
        // erreurs (NaN gérés via ActionCalculer)
        System.out.println(CommandesMemoire.prod("PROD A..H"));
        System.out.println(CommandesMemoire.prod("PROD H..A"));
        System.out.println(CommandesMemoire.prod("PROD AZ"));
        System.out.println(CommandesMemoire.prod("PROD A"));
        
        // commande MOY
        System.out.println("\nCommande MOY : \n");
        CommandesMemoire.init("INIT A 2");
        CommandesMemoire.init("INIT B 4");
        CommandesMemoire.init("INIT C 6");
        CommandesMemoire.init("INIT D 8");
        System.out.println(CommandesMemoire.moy("MOY A..D"));
        // erreurs (NaN gérés via ActionCalculer)
        System.out.println(CommandesMemoire.moy("MOY A..H"));
        System.out.println(CommandesMemoire.moy("MOY H..A"));
        System.out.println(CommandesMemoire.moy("MOY AZ"));
        System.out.println(CommandesMemoire.moy("MOY A"));
        
        // commande INIT
        System.out.println("\nCommande INIT : \n");
        System.out.println(CommandesMemoire.init("INIT A 2"));
        System.out.println(CommandesMemoire.init("INIT A..C 3")); 
        System.out.println(CommandesMemoire.init(" INIT   G 4"));
        // erreurs
        System.out.println(CommandesMemoire.init("INIT C..A 5")); 
        System.out.println(CommandesMemoire.init("INIT A..C a")); 
        
        // commande ADD
        System.out.println("\nCommande ADD : \n");
        System.out.println(CommandesMemoire.add("ADD A 2"));
        System.out.println(CommandesMemoire.add("ADD A..C 3")); 
        System.out.println(CommandesMemoire.add(" ADD   G 4"));
        // erreurs
        System.out.println(CommandesMemoire.add("ADD C..A 5")); 
        System.out.println(CommandesMemoire.add("ADD A..C a"));
        
        // commande MUL
        System.out.println("\nCommande MUL : \n");
        System.out.println(CommandesMemoire.mul("MUL A 2"));
        System.out.println(CommandesMemoire.mul("MUL A..C 3")); 
        System.out.println(CommandesMemoire.mul(" MUL   G 4"));
        // erreurs
        System.out.println(CommandesMemoire.mul("MUL C..A 5")); 
        System.out.println(CommandesMemoire.mul("MUL A..C a"));
        
        // commande EXP
        System.out.println("\nCommande EXP : \n");
        System.out.println(CommandesMemoire.exp("EXT A 2"));
        System.out.println(CommandesMemoire.exp("EXP A..C 3")); 
        System.out.println(CommandesMemoire.exp(" EXP   G 4"));
        // erreurs
        System.out.println(CommandesMemoire.exp("EXP C..A 5")); 
        System.out.println(CommandesMemoire.exp("EXP A..C a"));
    }
}
