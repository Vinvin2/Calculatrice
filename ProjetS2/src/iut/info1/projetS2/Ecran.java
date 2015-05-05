/**
 * Ecran.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * L'�cran est un JTextField ayant les propri�t�s de l'�cran de contr�le des
 * ex�cutions de commandes
 * @author S�bastien
 *
 */
@SuppressWarnings("serial")
public class Ecran extends JTextArea {

    /** Police d'�criture de l'�cran */
    private static final Font PoliceEcran = new Font("Verdana", Font.PLAIN, 15);
    /** Dimensions de l'�cran */
    private static final Dimension dimensionEcran = new Dimension(600, 550);

    /**
     * Cr�� l'aire de texte correspondant � l'�cran de contr�le des ex�cutions
     * de commandes
     */
    public Ecran() {
        super();

        // taille de l'�cran
        setPreferredSize(dimensionEcran);

        // police
        setFont(PoliceEcran);

        // texte initial
        setText("Entrez votre calcul, puis cliquez sur \"Calculer\".\n");


        // couleur de fond
        setBackground(new Color(253,245,230));

        // zone non modifiable de texte
        setEditable(false);
    }

}
