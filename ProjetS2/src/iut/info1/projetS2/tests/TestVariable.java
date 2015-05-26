/*
 * TestVariable.java					26 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.tests;

import java.util.Arrays;

import iut.info1.projetS2.utilitaires.Variable;

/**
 * Programme de test de la classe variable
 * @author Sébastien
 * @versio 1.0
 */
public class TestVariable {

    /**
     * Programme principal
     * @param args unused
     */
    public static void main(String[] args) {
        // initialisation de variables qui sont correctes
        Variable[] varOk = {new Variable('A', 20), new Variable('B',-10), 
                new Variable('C', 10.12), new Variable('D', 0),
                new Variable('E', 0.0), new Variable ('F', -120.154)};
        
        // initialisation de variables erronées
//        Variable[] varErronnees = {
//                            new Variable('a', 20), new Variable('\0',-10), 
//                            new Variable("abc", 20), new Variable(15, "abc")
//                            new Variable('A', "quinze")
//                                  };
        // renvoie un IllegalArgumentException    
        
        // le toString de Variable est fonctionnel, même sur un tableau
        System.out.println(Arrays.toString(varOk));

        // test des getters et setters
        char A = varOk[0].getNom();
        double valA = varOk[0].getValeur();
        System.out.println(varOk[0].toString());
        
        varOk[0].setNom('G');
        System.out.println(varOk[0].toString());
        
        varOk[0].setNom('a');
        System.out.println(varOk[0].toString());
        
        
    }

}
