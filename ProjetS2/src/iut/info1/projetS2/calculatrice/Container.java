/*
 * Container.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Param�trage des panels de la calculatrice
 * @author S�bastien
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Container extends JPanel {

    /**
     * Cr�� un JPanel avec les propri�t�s voulues
     * @param longueur du panel
     * @param largeur du panel
     */
    public Container(int largeur, int longueur) {
        super();

        // On d�finit la couleur de fond du panel
        setBackground(new Color(175,238,238));

        // dimensions du panel
        Dimension dimContainer = new Dimension(largeur, longueur);
        setPreferredSize(dimContainer);
        
        Image icone = Toolkit.getDefaultToolkit().getImage("calculette.jpg");
        //setIcon(new ImageIcon(icone));
      
       
    }

}
