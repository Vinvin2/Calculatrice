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
 * On calcule le r�sultat de la commande entr�e, on affiche cette commande
 * et le r�sultat sur l'�cran
 * @author S�bastien
 * @version 0.1
 */
public class ActionCalculer implements ActionListener {

    /** Mod�le d'expression du calcul sous forme de cha�ne de caract�res */
    public static final Pattern REG_EX_CALCUL_SIMPLE = 
            Pattern.compile("[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*([+-/*])[ ]*([-+]?[ ]*\\d+\\.?\\d*)[ ]*");

    /** Champ de texte pour les ex�cutions de commandes */
    private ExecuteurCommandes executeurAssocie;

    /** Ecran o� les commandes et leur r�sultat seront affich�es */
    private Ecran ecran;

    /**
     * Permettra de r�cup�rer le texte pr�sent dans l'ecran et l'executeur
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
     * Calcule et affiche le r�sultat de la commande
     */
    private void calcul() {
    	
        // on r�cup�re le texte du textfield ex�cuteur de commandes
        String commande = executeurAssocie.getText();
        
        // on en ins�re le contenu dans l'�cran
        ecran.insert(commande + "\n", ecran.getText().length() + 1);

        Matcher correcte = REG_EX_CALCUL_SIMPLE.matcher(commande);
        double operande1;       // 1�re op�rande de l'op�ration
        double operande2;       // 2�me op�rande de l'op�ration
        char operateur;         // op�rateur de l'op�ration
        

        if (correcte.matches()) {           
            double resultat;    // r�sultat de l'op�ration
            
            // On transforme le premier nombre r�cup�r� en double
            // et on le stocke dans operande1, on fait de m�me pour l'operande2
            operande1 = Double.parseDouble(correcte.group(1)); 
            // On stocke l'op�rateur
            operateur = correcte.group(2).charAt(0);
            operande2 = Double.parseDouble(correcte.group(3));

            // On r�alise le calcul associ� � l'op�rateur
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
            // On l'affiche � la fin de l'�cran
            ecran.insert("= " + resultat + "\n", ecran.getText().length() + 1);

        } else {
            // Si la syntaxe est erron�e, on affiche une erreur
            ecran.insert("Erreur, la commande entr�e n\'est pas disponible.\n", 
                    ecran.getText().length() + 1);
        }
        // On vide le textfield executeur de commandes
        executeurAssocie.setText("");

    }

}
