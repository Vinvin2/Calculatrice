/**
 * ActionCalculer.java					3 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * On calcule le résultat de la commande entrée, on affiche cette commande
 * et le résultat sur l'écran
 * @author Sébastien
 * @version 0.1
 */
public class ActionCalculer implements ActionListener {

    /** Modèle d'expression du calcul sous forme de chaîne de caractères */
    public static final Pattern REG_EX_CALCUL_SIMPLE = 
            Pattern.compile("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");

    /** Champ de texte pour les exécutions de commandes */
    private ExecuteurCommandes executeurAssocie;

    /** Ecran où les commandes et leur résultat seront affichées */
    private Ecran ecran;

    /**
     * Permettra de récupérer le texte présent dans l'ecran et l'executeur
     * de commande, et ainsi de le modifier
     * @param executeur
     * @param ecran 
     */
    public ActionCalculer(ExecuteurCommandes executeur, Ecran ecran) {
    	
        this.executeurAssocie = executeur;
        this.ecran = ecran;
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        calcul();
    }

    /**
     * Calcule et affiche le résultat de la commande
     */
    private void calcul() {
    	
        // on récupère le texte du textfield exécuteur de commandes
        String commande = executeurAssocie.getText();
        
        // on en insère le contenu dans l'écran
        ecran.insert(commande + "\n", ecran.getText().length() + 1);

        Matcher correcte = REG_EX_CALCUL_SIMPLE.matcher(commande);
        double operande1;       // 1ère opérande de l'opération
        double operande2;       // 2ème opérande de l'opération
        char operateur;         // opérateur de l'opération
        

        if (correcte.matches()) {           
            double resultat;    // résultat de l'opération
            
            // On transforme le premier nombre récupéré en double
            // et on le stocke dans operande1, on fait de même pour l'operande2
            operande1 = Double.parseDouble(correcte.group(1)); 
            // On stocke l'opérateur
            operateur = correcte.group(2).charAt(0);
            operande2 = Double.parseDouble(correcte.group(3));

            // On réalise le calcul associé à l'opérateur
            switch (operateur) {
            case '+':
                resultat = operande1 + operande2;
                break;
            case '-':
                resultat = operande1 - operande2;
                break;
            case '*':
                resultat = operande1 * operande2;
                break;
            default:
                resultat = operande1 / operande2;
                break;
            }
            // On l'affiche à la fin de l'écran
            ecran.insert("= " + resultat + "\n", ecran.getText().length() + 1);

        } else {
            // Si la syntaxe est erronée, on affiche une erreur
            ecran.insert("Erreur, la commande entrée n\'est pas disponible.\n", 
                    ecran.getText().length() + 1);
        }
        // On vide le textfield executeur de commandes
        executeurAssocie.setText("");

    }

}
