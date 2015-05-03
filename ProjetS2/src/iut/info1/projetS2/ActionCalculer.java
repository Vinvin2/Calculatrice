/**
 * ActionCalculer.java					3 mai 2015
 * IUT Info 1 2014/2015 groupe 3
 */
package iut.info1.projetS2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * On calcule le r�sultat de la commande entr�e, on affiche cette commande
 * et le r�sultat sur l'�cran
 * @author S�bastien
 *
 */
public class ActionCalculer implements ActionListener, KeyListener {

    /** Mod�le d'expression du calcul sous forme de cha�ne de caract�res */
    public static final Pattern REG_EX_CALCUL_SIMPLE = Pattern.compile("(\\d{1,15})[ ]*([+-/*])[ ]*(\\d{1,15})");

    /** Champ de texte pour les ex�cutions de commandes */
    private ExecuteurCommandes executeurAssocie;

    /** Ecran o� les commandes et leur r�sultat seront affich�es */
    private Ecran ecran;

    /**
     * permettra de r�cup�rer le texte pr�sent dans l'ecran et l'executeur
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
        String commande = executeurAssocie.getText();

        ecran.insert(commande + "\n", ecran.getText().length() + 1);

        Matcher correcte = REG_EX_CALCUL_SIMPLE.matcher(commande);
        int operande1,
        operande2;
        char operateur;

        int resultat = 0;

        if (correcte.matches()) {
            operande1 = Integer.parseInt(correcte.group(1));
            operateur = correcte.group(2).charAt(0);
            operande2 = Integer.parseInt(correcte.group(3));

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
            case '/':
                resultat = operande1 / operande2;
                break;
            default: 
                break;
            }

            ecran.insert("= " + resultat + "\n", ecran.getText().length() + 1);

        } else {
            ecran.insert("Erreur, la commande entr�e n\'est pas disponible.\n", 
                    ecran.getText().length() + 1);
        }

        executeurAssocie.setText("");

    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
            calcul();
        }

    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // ne fait rien
    }

    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // ne fait rien
    }

}
