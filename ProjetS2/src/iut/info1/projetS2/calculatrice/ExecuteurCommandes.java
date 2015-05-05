/**
 * ExecuteurCommandes.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import java.awt.Dimension;

import javax.swing.JTextField;

/**
 * Cr�� le champ permettant d'ex�cuter des commandes entr�es en console
 * @author S�bastien
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ExecuteurCommandes extends JTextField {

    /** Dimensions de l'�cran */
    private static final Dimension dimensionExecuteur = new Dimension(495,30);

    /**
     * Cr�� un JTextField avec les propri�t�s de l'ex�cuteur de commandes
     */
    public ExecuteurCommandes() {
        super();

        // taille
        setPreferredSize(dimensionExecuteur);
    }
}
