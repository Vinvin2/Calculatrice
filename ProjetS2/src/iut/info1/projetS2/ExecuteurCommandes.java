/**
 * ExecuteurCommandes.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2;

import java.awt.Dimension;

import javax.swing.JTextField;

/**
 * Cr�� le champ permettant d'ex�cuter des commandes
 * @author S�bastien
 *
 */
@SuppressWarnings("serial")
public class ExecuteurCommandes extends JTextField {
    
    /** Dimensions de l'�cran */
    private static final Dimension dimensionExecuteur = new Dimension(400,30);

    /**
     * Cr�� un JTextField avec les propri�t�s de l'ex�cuteur de commandes
     */
    public ExecuteurCommandes() {
        super();
        
        // taille
        setPreferredSize(dimensionExecuteur);
    }
}
