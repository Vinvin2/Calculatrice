/**
 * Ecran.java					2 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * L'écran est un JTextField ayant les propriétés de l'écran de contrôle des
 * exécutions de commandes
 * @author Sébastien
 *
 */
@SuppressWarnings("serial")
public class Ecran extends JTextArea {
    
    /** Police d'écriture de l'écran */
    private static final Font PoliceEcran = new Font("Verdana", Font.PLAIN, 15);
    /** Dimensions de l'écran */
    private static final Dimension dimensionEcran = new Dimension(600, 550);

    /**
     * Créé l'aire de texte correspondant à l'écran de contrôle des exécutions
     * de commandes
     */
    public Ecran() {
        super();
        
        // taille de l'écran
        setPreferredSize(dimensionEcran);
        
        // police
        setFont(PoliceEcran);
        
        // texte initial
        String msgAccueil= new String("Entrez votre calcul, puis cliquez sur \"Calculer\".\n");
        insert(msgAccueil, 0);
        
        String operation = new String("5 + 1\n");
        int pos = msgAccueil.length() + 1;
        
        insert(operation, pos);
        pos += operation.length();
        insert("= 6\n", pos);
        
        // couleur de fond
        setBackground(new Color(253,245,230));
        
        // zone non modifiable de texte
        setEditable(false);
    }

}
