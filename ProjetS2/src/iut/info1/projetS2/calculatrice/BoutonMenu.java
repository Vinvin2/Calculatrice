/*
 * BoutonMenu.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

/**
 * Classe qui permet de construire un bouton personnalisé
 * @author groupe Projet
 * @version 0.1
 */
@SuppressWarnings("serial")
public class BoutonMenu extends JButton {

    /**
     * Créé un type de bouton que l'on utilisera pour la partie mini-calculatrice
     * @param texte le texte du bouton
     */
    public BoutonMenu(String texte) {
        super(texte);

        // On gère la taille des boutons
        setPreferredSize(new Dimension(500, 150));

        // On définit la couleur des boutons
        setBackground(new Color(126,192,238));
        
       // Déclaration d'une police et d'une taille
        Font f = new Font("Calibri", Font.PLAIN, 32);
        
        // Ajout de la police à notre console
        setFont(f);
        

    }

}
