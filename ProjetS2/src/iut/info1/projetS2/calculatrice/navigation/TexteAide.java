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
 * On créé une JTextArea afin de regrouper toutes les commandes d'aide
 * à l'intérieur 
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class TexteAide extends JTextArea {
    
    /** Police d'écriture de l'écran */
    private static final Font PoliceEcran = new Font("Verdana", Font.PLAIN, 15);
    
    /** Dimensions de l'écran */
    private static final Dimension dimensionEcran = new Dimension(630, 450);

    /**
     * Créé l'aire de texte correspondant à l'écran de contrôle des exécutions
     * de commandes
     */
    public TexteAide() {
        super();

        // Taille de l'écran
        setSize(dimensionEcran);

        // Police
        setFont(PoliceEcran);

        // Couleur de fond
        setBackground(new Color(255,228,196));
        
        // On empêche l'écran d'être éditable 
        setEditable(false);
        
        // Texte pour les instructions de calcul simples,
        //            les instructions de calcul avec des parenthèses
        //         et les instructions de calcul utilisant la mémoire
        setText(" - Commandes pour la Mini-Calculatrice de base :\n\n"
        +"* Les Instructions de calcul simples :\n\n"
        +"Une instruction est constituée de trois parties :\n"
        +"valeur_numerique    operateur    valeur_numerique\n\n"
        +"Les opérateur disponibles sont :\n"
        + "    +                  -                    *                  /\n"
        + "addition    soustraction    multiplication    division\n\n"
        +"Symbole '.' pour les nombres décimaux\n\n"
        + "* Les Instructuins de calculs avec des parenthèses :\n\n"
        + "Imbrication d'au moins 3 niveaux de parenthèses possible\n\n"
        + "* Instructions de calcul utilisant la mémoire : \n\n"
        + "26 mémoires nommées de A à Z disponibles\n\n"
        + "Conservation d'un calcul dans une mémoire : \n"
        + "expression     opéarateur     expression  =  memoire\n"
        + "La valeur d'une mémoire peut être utilisée pour un calcul\n\n"       
        // Texte regroupant les commandes disponibles pour le gestionnaire
        // de la mémoire
        +"- Commandes pour le Gestionnaire de la mémoire : \n\n"
        +"MEM  :  On accède à la partie mémoire de la Calculatrice\n\n"
        +"QUIT :  On quitte le gestionnaire de la mémoire\n\n"
        +"RAZ  :  Remet à 0 les cases mémoires spécifiées\n\n"
        +"INCR :  Ajoute 1 aux cases mémoires spécifiées\n\n"
        +"SOM  :  Effectue la somme des cases mémoires spécifiées\n\n"
        +"PROD :  Effectue le produit des cases mémoires spécifiées\n\n"
        +"MOY  :  Calcule la moyenne des cases mémoires spécifiées\n\n"
        +"VOIR :  Affiche la valeur des cases mémoires spécifiées\n\n"
        +"SQRT :  Modifie la valeur des cases mémoires spécifiées pour leur \n"
        + "affecter la racine carrée de leur valeur initiale\n\n"
        +"CAR  :  Modifie la valeur des cases mémoires spécifiées pour leur \n"
        + "affecter le carré de leur valeur initiale\n\n"
        +"INIT :  Initialise la zone mémoire avec la valeur spécifiée\n\n"
        +"ADD  :  Ajoute la valeur à chacune des cases mémoires  de la zone \n"
        + "spécifiée\n\n"
        +"MUL  :  Multiplie par la valeur chacune des cases mémoires de la \n"
        + "zone spécifiée\n\n"
        +"EXP  :  Elève la valeur de chacune des cases de la zone mémoire \n"
        + "avec la puissance spécifiée par la valeur\n");
        
    }

}
