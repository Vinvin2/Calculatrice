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
        if (nom >= 65 && nom <= 90) {   // si le nom est un caractère majuscule
            this.nom = nom;
            this.valeur = valeur;
        }
    }

    /**
     * @return le nom
     */
    public char getNom() {
        return nom;
    }

    /**
     * @param nom le nom à set
     */
    public void setNom(char nom) {
        this.nom = nom;
    }

    /**
     * @return la valeur
     */
    public double getValeur() {
        return valeur;
    }

    /**
     * @param valeur la valeur à set
     */
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Variable [nom=" + nom + ", valeur=" + valeur + "]";
    }
    
    
}
