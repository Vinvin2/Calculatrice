/**
 * ExecuteurCommandes.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import java.awt.Dimension;

import javax.swing.JTextField;

/**
 * Créé le champ permettant d'exécuter des commandes entrées en console
 * @author Sébastien
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ExecuteurCommandes extends JTextField {

    /** Dimensions de l'écran */
    private static final Dimension dimensionExecuteur = new Dimension(495,30);

    /**
     * Créé un JTextField avec les propriétés de l'exécuteur de commandes
     */
    public ExecuteurCommandes() {
        super();

        // taille
        setPreferredSize(dimensionExecuteur);
    }
}
