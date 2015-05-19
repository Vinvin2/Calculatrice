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
 * On calcule le résultat de la commande entrée, on affiche cette commande
 * et le résultat sur l'écran
 * @author Sébastien
 * @version 0.1
 */
public class ActionCalculer implements ActionListener {
    
    /** Champ de texte pour les exécutions de commandes */
    private ExecuteurCommandes executeurAssocie;

    /** Ecran où les commandes et leur résultat seront affichées */
    private Ecran ecran;

    /** Vérifie si on est en mode mémoire ou non */
    private static boolean modeMem = false;
    

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
        // on récupère le texte du textfield exécuteur de commandes
        String commande = executeurAssocie.getText();

        // on insère la commande à l'écran
        ecran.insert(commande + "\n", ecran.getText().length());
        if (!modeMem) {
            // on cherche une fonction non liée au mode mémoire et correspondant
            // à la commande
            int fonctionok = fonctionAUtiliserNonMem(commande);

            if (fonctionok == 1){       // commande MEM
                
                // on informe l'utilisateur qu'il passe en mode mémoire
                ecran.insert("Mode mémoire actif.\n", ecran.getText().length());
                
            } else if (fonctionok == 2) {       // commande affectation
                
                double resultat = CommandesMemoire.affectation(commande);
                // on insère le résultat à l'écran
                ecran.insert(" = " + resultat + "\n", ecran.getText().length());
                
            } else if (fonctionok == 3) {       // commande calcul
                
                double resultat = Utilitaires.calculIntermediaire(commande);
                // on insère le résultat à l'écran
                ecran.insert(" = " + resultat + "\n", ecran.getText().length());
                
            }
            
        } else {
            
            // on cherche une fonction liée au mode mémoire et correspondant
            // à la commande
            int fonctionok = fonctionAUtiliserMem(commande);
            
            if (fonctionok == 1) {      // commande RAZ
                // on informe l'utilisateur que la commande a été effectuée
                ecran.insert(" OK\n", ecran.getText().length());
            }
        }
        // On vide le textfield executeur de commandes
        executeurAssocie.setText("");
        

    }
    
    /**
     * On va tester quelle méthode fonctionne avec la commande entrée, si on
     * est en mode normal
     * @param commande la commande entrée
     * @return fonctionok le numéro de la fonction à utiliser
     */
    public static int fonctionAUtiliserNonMem (String commande) {
        
        int fonctionok = 0;
        // on créé des patterns permettant de détecter chaque fonction utilisable
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
     * On va tester quelle méthode fonctionne avec la commande entrée, si on
     * est en mode Mémoire
     * @param commande la commande entrée
     * @return fonctionok le numéro de la fonction à utiliser
     */
    public static int fonctionAUtiliserMem (String commande) {
        
        int fonctionok = 0;
        // on créé des patterns permettant de détecter chaque fonction utilisable
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
