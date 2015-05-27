/*
 * SauvegardeFichier.java				27 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tests;

import java.io.File;

import iut.info1.projetS2.tableur.ModeleDeTable;
import iut.info1.projetS2.tableur.OutilsFichier;
import iut.info1.projetS2.tableur.Tableur;

/** TODO commenter la responsabilité de cette classe
 * @author Mickaël
 * @version 0.1
 */
public class SauvegardeFichier {
    
    /** fenetre de notre appli */
    private static Tableur fenetre;
    
    /**
     * convertie une collection de mots (tableau) en texte au format [ elt0 elt1
     * elt2... eltn ]
     * 
     * @param aConvertir
     *            référence du tableau à mettre sous forme texte
     */
    private static void toString(Object[][] aConvertir) {

        for (int i = 0; i < aConvertir.length; i++) {
            for (int j = 0; j < aConvertir[i].length; j++) {
                System.out.print(aConvertir[i][j] + " | "); 
            }
            System.out.println();
        }
    }
    
    /**
     * convertie une collection de mots (tableau) en texte au format [ elt0 elt1
     * elt2... eltn ]
     * 
     * @param aConvertir
     *            référence du tableau à mettre sous forme texte
     */
    private static void toString(String[][] aConvertir) {

        for (int i = 0; i < aConvertir.length; i++) {
            for (int j = 0; j < aConvertir[i].length; j++) {
                System.out.print(aConvertir[i][j] + " | "); 
            }
            System.out.println();
        }
    }
    
    
    /** TODO commenter le rôle de la méthode
     * @param args
     */
    public static void main(String[] args) {
        
        new Tableur();
        
        OutilsFichier.nomFichier = new File("test.tabix");
        System.out.println(OutilsFichier.nomFichier.getAbsolutePath());
        OutilsFichier.restaurerPaireLignTableur();
 //       toString(fenetre.getActions().getEntrees());
        toString(ModeleDeTable.getDonnees());
    }

}
