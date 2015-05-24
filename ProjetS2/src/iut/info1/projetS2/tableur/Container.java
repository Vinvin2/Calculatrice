/*
 * Container.java                   5 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */

package iut.info1.projetS2.tableur;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Paramétrage des panels de la calculatrice
 * @author Mickael
 *
 */
@SuppressWarnings("serial")
public class Container extends JPanel {

    /**
     * Créé un JPanel avec les propriétés voulues
     * @param longueur du panel
     * @param largeur du panel
     */
    public Container(int largeur, int longueur) {
        super();

        // On définit la couleur de fond du panel
        setBackground(new Color(175,238,238));

        // dimensions du panel
        Dimension dimContainer = new Dimension(largeur, longueur);
        setPreferredSize(dimContainer);
    }

}
