/*
9 * Ecran.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextArea;

/**
 * L'écran est un JTextField ayant les propriétés de l'écran de contrôle des
 * exécutions de commandes
 * @author Sébastien
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Ecran extends JTextArea {
    
    /** Police d'écriture de l'écran */
    private static final Font PoliceEcran = new Font("Verdana", Font.PLAIN, 15);
    
    /** Dimensions de l'écran */
    
    private static final Dimension dimensionEcran = new Dimension(600, 3000);

    /**
     * Créé l'aire de texte correspondant à l'écran de contrôle des exécutions
     * de commandes
     */
    public Ecran() {
        super();

        // Taille de l'écran
        setSize(dimensionEcran);

        // Police
        setFont(PoliceEcran);

        // Texte initial
        setText(" Entrez votre calcul, puis cliquez sur \"Calculer\".\n");

        // Couleur de fond
        setBackground(new Color(253,245,230));
        
        // On empêche l'écran d'être éditable 
        setEditable(false);
        
    }

}
