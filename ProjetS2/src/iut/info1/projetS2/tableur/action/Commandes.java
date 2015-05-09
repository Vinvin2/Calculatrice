/*
 * Commandes.java					30 avr. 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.*;

/**
 * Méthode contenant l'ensemble des commandes interprétables par le tableur
 * --> Important : <--
 * La classe commande est instaciable et sera FORCEMENT liée à un tableur (lors de
 * l'instanciation de ce dernier)
 * @author Jonathan
 * @version 0.1
 */
public class Commandes { // SMOKE WEED EVERY DAY
    
    /** fenêtre à laquelle cette classe sera liée */
    private Tableur fenetre;

    /**
     * appelé sur l'évènement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tapé dans sa 'ligne de commande'
     */
    public void actionValider() {
        String aRenvoyer = fenetre.getConsole().getText();
        this.fenetre.getLabel().setText(aRenvoyer);
    }
    
    /**
     * Redéfinition du contructeur par défaut crée par java, il est rendu
     * unitilisable pour éviter qu'une instance de Commandes soit sans
     * fenêtre.
     */
    private Commandes() {
    }
    
    /**
     * Unique constructeur disponible pour la classe Commandes permet une
     * navigabilité vers le tableur
     * @param fenetre fenêtre à laquelle cette classe sera liée
     */
    public Commandes(Tableur fenetre) {
        this();
        this.fenetre = fenetre;
    }
}
