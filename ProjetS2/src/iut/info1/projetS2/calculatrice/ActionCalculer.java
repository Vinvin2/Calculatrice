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

    /** V�rifie si on est en mode m�moire ou non */
    private static boolean modeMem = false;
    

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
        if (!modeMem) {
            // on cherche une fonction non li�e au mode m�moire et correspondant
            // � la commande
            int fonctionok = fonctionAUtiliserNonMem(commande);

            if (fonctionok == 1){       // commande MEM
                
                // on informe l'utilisateur qu'il passe en mode m�moire
                ecran.insert("Mode m�moire actif.\n", ecran.getText().length());
                
            } else if (fonctionok == 2) {       // commande affectation
                
                double resultat = CommandesMemoire.affectation(commande);
                // on ins�re le r�sultat � l'�cran
                ecran.insert(" = " + resultat + "\n", ecran.getText().length());
                
            } else if (fonctionok == 3) {       // commande calcul
                
                double resultat = Utilitaires.calculIntermediaire(commande);
                // on ins�re le r�sultat � l'�cran
                ecran.insert(" = " + resultat + "\n", ecran.getText().length());
                
            }
            
        } else {
            
            // on cherche une fonction li�e au mode m�moire et correspondant
            // � la commande
            int fonctionok = fonctionAUtiliserMem(commande);
            
            if (fonctionok == 1) {      // commande RAZ
                // on informe l'utilisateur que la commande a �t� effectu�e
                ecran.insert(" OK\n", ecran.getText().length());
            }
        }
        // On vide le textfield executeur de commandes
        executeurAssocie.setText("");
        

    }
    
    /**
     * On va tester quelle m�thode fonctionne avec la commande entr�e, si on
     * est en mode normal
     * @param commande la commande entr�e
     * @return fonctionok le num�ro de la fonction � utiliser
     */
    public static int fonctionAUtiliserNonMem (String commande) {
        
        int fonctionok = 0;
        // on cr�� des patterns permettant de d�tecter chaque fonction utilisable
        Pattern patMem = Pattern.compile("(\\s*)MEM(\\s*)");
        Matcher memok = patMem.matcher(commande);
        
        Pattern patAffect = Pattern.compile(".*( = [A-Z])");
        Matcher affectok = patAffect.matcher(commande);
        
        if (memok.matches()) {
            fonctionok = 1;     // MEM
        } else if (affectok.matches()) {
            fonctionok = 2;     // affectation (15+15 = A)
        } else {
            fonctionok = 3;     // calcul normal
        }

        return fonctionok;
    }
    
    /**
     * On va tester quelle m�thode fonctionne avec la commande entr�e, si on
     * est en mode M�moire
     * @param commande la commande entr�e
     * @return fonctionok le num�ro de la fonction � utiliser
     */
    public static int fonctionAUtiliserMem (String commande) {
        
        int fonctionok = 0;
        // on cr�� des patterns permettant de d�tecter chaque fonction utilisable
        Pattern patRaz = Pattern.compile("(\\s*)RAZ.*");
        Matcher razok = patRaz.matcher(commande);
        
        if (razok.matches()) {
            fonctionok = 1;     // RAZ
        }
        return fonctionok;
    }

    /**
     * @param nouveauMode the modeMem to set
     */
    public static void setModeMem(boolean nouveauMode) {
        modeMem = nouveauMode;
    }
    
}
