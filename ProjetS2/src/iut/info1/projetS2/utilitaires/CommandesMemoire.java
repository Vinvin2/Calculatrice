/*
 * CommandesMemoire.java					19 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2.utilitaires;

import iut.info1.projetS2.calculatrice.ActionCalculer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regroupe toutes les commandes qui modifieront les cases m�moires de la calculatrice
 * @author S�bastien
 * @version 1.0
 */
public class CommandesMemoire {
    
    /** Tableau contenant toutes les cases m�moires */
    private static Variable[] casesMem = new Variable[26];
    
    /**
     * Si la commande est une affectation de type 15 + 4 = A, alors cette
     * m�thode est utilis�e. On r�alise l'op�ration demand�e, puis on affecte
     * le r�sultat � la variable concern�e
     * @param commande la commande � traiter
     * @return le r�sultat de la commande
     */
    public static double affectation (String commande) {
        String calculARealiser; // le calcul que l'on devra effectuer
        double resultat;        // son r�sultat
        char nomvar;            // le nom de la variable � affecter
        
        // TODO : moyen de prendre en compte les espaces
        calculARealiser = commande.substring(0, commande.length() - 4);

        // on fait le calcul
        resultat = Utilitaires.calculIntermediaire(calculARealiser);
        
        // on prend le nom de la variable
        nomvar = commande.charAt(commande.length()-1);
        
        // on v�rifie si la variable n'a jamais �t� initialis�e
        if (casesMem[nomvar - 65] == null) {
            // on cr�� alors cette variable
            casesMem[nomvar - 65] = new Variable(nomvar, resultat);
        } else {
            // sinon on met � jour sa valeur
            casesMem[nomvar - 65].setValeur(resultat);
        }
        return resultat;
    }
    
    
    /**
     * Lorsque l'utilisateur entre "MEM", la calculatrice passe en mode m�moire,
     * ainsi, de nouvelles m�thodes seront utilisables (dans la classe 
     * CommandesMemoires)
     * @param commande la commande entr�e par l'utilisateur
     * null sinon
     */
    public static void passageMem(String commande) {
        ActionCalculer.setModeMem(true);        // le mode m�moire sera d�tect�
    }
    
    /**
     * Remet � z�ro les cases m�moires sp�cifi�es
     * @param commande la commande entr�e par l'utilisateur
     */
    public static void raz(String commande) {
        // on regarde si le pattern convient
        Pattern patRaz = Pattern.compile("\\s*RAZ\\s*[A-Z]");
        Matcher razok = patRaz.matcher(commande);
        
        if (razok.matches()) {
            char nomvar;    
            
            // on r�cup�re le nom de la variable � initialiser
            nomvar = commande.charAt(commande.length() - 1);
            // on l'initialise
            casesMem[nomvar - 65] = new Variable(nomvar, 0);
        }
    }
}
