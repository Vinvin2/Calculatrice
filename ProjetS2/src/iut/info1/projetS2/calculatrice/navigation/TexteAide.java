/*
9 * TexteAide.java					27 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice.navigation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextArea;

/**
 * On cr�� une JTextArea afin de regrouper toutes les commandes d'aide
 * � l'int�rieur 
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class TexteAide extends JTextArea {
    
    /** Police d'�criture de l'�cran */
    private static final Font PoliceEcran = new Font("Verdana", Font.PLAIN, 15);
    
    /** Dimensions de l'�cran */
    private static final Dimension dimensionEcran = new Dimension(630, 450);

    /**
     * Cr�� l'aire de texte correspondant � l'�cran de contr�le des ex�cutions
     * de commandes
     */
    public TexteAide() {
        super();

        // Taille de l'�cran
        setSize(dimensionEcran);

        // Police
        setFont(PoliceEcran);

        // Couleur de fond
        setBackground(new Color(255,228,196));
        
        // On emp�che l'�cran d'�tre �ditable 
        setEditable(false);
        
        // Texte pour les instructions de calcul simples,
        //            les instructions de calcul avec des parenth�ses
        //         et les instructions de calcul utilisant la m�moire
        setText(" - Commandes pour la Mini-Calculatrice de base :\n\n"
        +"* Les Instructions de calcul simples :\n\n"
        +"Une instruction est constitu�e de trois parties :\n"
        +"valeur_numerique    operateur    valeur_numerique\n\n"
        +"Les op�rateur disponibles sont :\n"
        + "    +                  -                    *                  /\n"
        + "addition    soustraction    multiplication    division\n\n"
        +"Symbole '.' pour les nombres d�cimaux\n\n"
        + "* Les Instructuins de calculs avec des parenth�ses :\n\n"
        + "Imbrication d'au moins 3 niveaux de parenth�ses possible\n\n"
        + "* Instructions de calcul utilisant la m�moire : \n\n"
        + "26 m�moires nomm�es de A � Z disponibles\n\n"
        + "Conservation d'un calcul dans une m�moire : \n"
        + "expression     op�arateur     expression  =  memoire\n"
        + "La valeur d'une m�moire peut �tre utilis�e pour un calcul\n\n"       
        // Texte regroupant les commandes disponibles pour le gestionnaire
        // de la m�moire
        +"- Commandes pour le Gestionnaire de la m�moire : \n\n"
        +"MEM  :  On acc�de � la partie m�moire de la Calculatrice\n\n"
        +"QUIT :  On quitte le gestionnaire de la m�moire\n\n"
        +"RAZ  :  Remet � 0 les cases m�moires sp�cifi�es\n\n"
        +"INCR :  Ajoute 1 aux cases m�moires sp�cifi�es\n\n"
        +"SOM  :  Effectue la somme des cases m�moires sp�cifi�es\n\n"
        +"PROD :  Effectue le produit des cases m�moires sp�cifi�es\n\n"
        +"MOY  :  Calcule la moyenne des cases m�moires sp�cifi�es\n\n"
        +"VOIR :  Affiche la valeur des cases m�moires sp�cifi�es\n\n"
        +"SQRT :  Modifie la valeur des cases m�moires sp�cifi�es pour leur \n"
        + "affecter la racine carr�e de leur valeur initiale\n\n"
        +"CAR  :  Modifie la valeur des cases m�moires sp�cifi�es pour leur \n"
        + "affecter le carr� de leur valeur initiale\n\n"
        +"INIT :  Initialise la zone m�moire avec la valeur sp�cifi�e\n\n"
        +"ADD  :  Ajoute la valeur � chacune des cases m�moires  de la zone \n"
        + "sp�cifi�e\n\n"
        +"MUL  :  Multiplie par la valeur chacune des cases m�moires de la \n"
        + "zone sp�cifi�e\n\n"
        +"EXP  :  El�ve la valeur de chacune des cases de la zone m�moire \n"
        + "avec la puissance sp�cifi�e par la valeur\n");
        
    }

}
