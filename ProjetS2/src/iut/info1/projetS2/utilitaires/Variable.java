/*
 * Variable.java					18 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

/**
 * Une variable qui sera définie par son nom : caractère majuscule
 * et sa valeur (un réel)
 * @author Sébastien
 * @version 1.0
 */
public class Variable {
    /** La lettre qui correspond au nom de la variable */
    private char nom;
    
    /** La valeur de la variable */
    private double valeur;
    
    /**
     * Créé une variable
     * @param nom
     * @param valeur
     */
    public Variable(char nom, double valeur) {
        if (nom >= 65 && nom <= 90) {
            this.nom = nom;
            this.valeur = valeur;
        }
    }
}
