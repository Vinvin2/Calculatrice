/*
9 * TexteAide.java					27 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.tableur.action;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextArea;

/**
 * On créé une JTextArea afin de regrouper toutes les commandes d'aide 
 * concernant le tableur à l'intérieur 
 * @author 20-20
 * @version 0.1
 */
@SuppressWarnings("serial")
public class TexteTabAide extends JTextArea {
    
    /** Police d'écriture de l'écran */
    private static final Font PoliceEcran = new Font("Verdana", Font.PLAIN, 15);
    
    /** Dimensions de l'écran */
    private static final Dimension dimensionEcran = new Dimension(630, 450);

    /**
     * Créé l'aire de texte correspondant à l'écran de contrôle des exécutions
     * de commandes
     */
    public TexteTabAide() {
        super();

        // Taille de l'écran
        setSize(dimensionEcran);

        // Police
        setFont(PoliceEcran);

        // Couleur de fond
        setBackground(new Color(255,228,196));
        
        // On empêche l'écran d'être éditable 
        setEditable(false);

        // Texte pour expliquer le fonctionnement général du tableur
        setText(" - Fonctionnement du Mini-Tableur :\n\n"
        +"Les formules de calcul pourront faire intervenir les opérations \n"
        +"de la mini-calculatrice, les parenthèses, des opérandes qui  \n"
        +"pourront être des valeurs ou bien une cellule de la feuille de \n"
        +"calcul référencée par ses coordonnées, ou bien une expression. \n"
        + "-> Exemple : A1 6, B2 A1 * 5\n\n"

        + "Les coordonnées d'une cellule peuvent comporter le symbole $,\n"
        +"une seule fois ou deux fois, afin de figer la ligne/colonne "
        + "pour la copie\n"
        + "-> Exemple :  $A20, A$20, $A$20\n\n"

        + "Les commandes ont en argument soit les coordonnées d'une cellule\n"
        + " comme par exemple A1 ou Z20, soit une plage de cellule comme : \n "
        + "A1..D5 ou B5..B20\n\n"
        // Texte regroupant les commandes disponibles pour le mini-tableur
        +"- Commandes pour le Mini-Tableur : \n\n"
        +"VOIR   : Affiche soit le contenu de toutes les cellules de la \n"
        + "feuille , soit le contenu de la cellule spécifiée, soit celui\n"
        + "des cellules de la plage spécifiée\n"
        + "--> Argument 1 : aucun ou cellule ou plage \n\n" 

        +"VALEUR : Affiche soit le contenu de toutes les cellules de la \n"
        + "feuille, soit la valeur de la cellule spécifiée, soit celle des\n"
        + "cellules de la plage spécifiée\n"
        + "--> Argument 1 : aucun ou cellule ou plage \n\n"

        +"CONT   : Affiche soit le contenu de toutes les cellules de la \n"
        + "feuille , soit le contenu de la cellule spécifiée, soit celui\n"
        + "des cellules de la plage spécifiée. On entend par contenu la \n"
        + "valeur de la cellule et éventuellement la formule qu'elle contient\n"
        + "--> Argument 1 : aucun ou cellule ou plage \n\n"

        +"INIT   : Entrer la valeur de la cellule spécifiée, ou bien de \n"
        + "toutes les cellules de la plage spécifiée \n"
        + "Valeur identique pour toutes les cellules ou valeurs distinctes\n"
        + "--> Argument 1 : cellule ou plage\n\n"

        +"COPIER : Copie le contenu de la première cellule dans la deuxième\n"
        +"--> Argument 1 : cellule   --> Argument 2 : cellule\n\n"

        +"COPIER : Copie le contenu de la cellule spécifiée dans toutes\n"
        + " les cellules de la plage\n"
        +"--> Argument 1 : cellule   --> Argument 2 : plage\n\n"

		+"COPIER : Copie le contenu des cellules de la première plage\n"
		+ " dans celles de la deuxième plage\n"
		+"--> Argument 1 : plage   --> Argument 2 : plage\n\n"

        +"COPVAL : Copie la valeur de la première cellule dans la deuxième\n"      
		+"--> Argument 1 : cellule   --> Argument 2 : cellule\n\n"
        
        +"COPVAL : Copie la valeur de la cellule spécifiée dans toutes les \n"
        + "cellules de la plage\n"
		+"--> Argument 1 : cellule   --> Argument 2 : plage\n\n"
        
        +"COPVAL : Copie la valeur des cellules de la première plage dans \n"
        +"celles de la deuxième plage\n"
		+"--> Argument 1 : plage   --> Argument 2 : plage\n\n"
        
        +"RAZ    : Vide le contenu de la cellule \n"
        +"--> Argument 1 : cellule\n\n"

        +"RAZ    : Vide le contenu de toutes les cellules de la plage \n"
        + "spécifiée \n"
        + "--> Argument 1 : plage");

    }

}
