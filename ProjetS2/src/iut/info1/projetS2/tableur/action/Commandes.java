/*
 * Commandes.java					30 avr. 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.*;

/**
 * M�thode contenant l'ensemble des commandes interpr�tables par le tableur
 * --> Important : <--
 * La classe commande est instaciable et sera FORCEMENT li�e � un tableur (lors de
 * l'instanciation de ce dernier)
 * @author Jonathan
 * @version 0.1
 */
public class Commandes { // SMOKE WEED EVERY DAY
    
    /** fen�tre � laquelle cette classe sera li�e */
    private Tableur fenetre;

    /**
     * appel� sur l'�v�nement 'click sur Valider', permet d'agir en fonction
     * de ce que l'user a tap� dans sa 'ligne de commande'
     */
    public void actionValider() {
        String aRenvoyer = fenetre.getConsole().getText();
        this.fenetre.getLabel().setText(aRenvoyer);
    }
    
    /**
     * Red�finition du contructeur par d�faut cr�e par java, il est rendu
     * unitilisable pour �viter qu'une instance de Commandes soit sans
     * fen�tre.
     */
    private Commandes() {
    }
    
    /**
     * Unique constructeur disponible pour la classe Commandes permet une
     * navigabilit� vers le tableur
     * @param fenetre fen�tre � laquelle cette classe sera li�e
     */
    public Commandes(Tableur fenetre) {
        this();
        this.fenetre = fenetre;
    }
}
