/**
 * Bouton.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * Classe qui permet de construire un bouton personnalis�
 * @author groupe Projet
 *
 */
@SuppressWarnings("serial")
public class Bouton extends JButton {

    /**
     * Cr�� un bouton
     * @param texte le texte du bouton
     */
    public Bouton(String texte) {
        super(texte);

        // On g�re la taille des boutons
        setPreferredSize(new Dimension(100, 60));

        // On d�finit la couleur des boutons
        setBackground(new Color(250,128,114));

    }

}
