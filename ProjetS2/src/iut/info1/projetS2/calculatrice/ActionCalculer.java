/*
 * ActionCalculer.java					3 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import iut.info1.projetS2.utilitaires.CommandesMemoire;
import iut.info1.projetS2.utilitaires.Utilitaires;

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
        // on r�cup�re le texte du textfield ex�cuteur de commandes
        String commande = executeurAssocie.getText();
        
        // on ins�re la commande � l'�cran
        ecran.insert(commande + "\n", ecran.getText().length());
        
        int fonctionok = fonctionAUtiliser(commande);
        
        if (fonctionok == 1){
            String message = CommandesMemoire.passageMem(commande);
            ecran.insert(message, ecran.getText().length());
        } else if (fonctionok == 2) {
            double resultat = CommandesMemoire.affectation(commande);
            // on ins�re le r�sultat � l'�cran
            ecran.insert(" = " + resultat + "\n", ecran.getText().length());
        } else if (fonctionok == 3) {
            double resultat = Utilitaires.calculIntermediaire(commande);
            // on ins�re le r�sultat � l'�cran
            ecran.insert(" = " + resultat + "\n", ecran.getText().length());
        }
        // On vide le textfield executeur de commandes
        executeurAssocie.setText("");
        

    }
    
    /**
     * On va tester quelle m�thode fonctionne avec la commande entr�e
     * @param commande la commande entr�e
     * @return fonctionok le num�ro de la fonction � utiliser
     */
    public static int fonctionAUtiliser (String commande) {
        
        int fonctionok = 0;
        // on cr�� des patterns permettant de d�tecter chaque fonction utilisable
        Pattern patMem = Pattern.compile("(\\s*)MEM(\\s*)");
        Matcher memok = patMem.matcher(commande);
        
        Pattern patAffect = Pattern.compile(".*( = [A-Z]");
        Matcher affectok = patAffect.matcher(commande);
        // si c'est le cas, le boolean modeMem de ActionCalculer est activ�
        if (memok.matches()) {
            fonctionok = 1;
        } else if (affectok.matches()) {
            fonctionok = 2;
        } else {
            fonctionok = 3;
        }
        
        return fonctionok;
    }
}
