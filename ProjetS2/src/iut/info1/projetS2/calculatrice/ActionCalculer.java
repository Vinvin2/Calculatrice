/*
 * ActionCalculer.java					3 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import iut.info1.projetS2.utilitaires.Utilitaires;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * On calcule le résultat de la commande entrée, on affiche cette commande
 * et le résultat sur l'écran
 * @author Sébastien
 * @version 0.1
 */
public class ActionCalculer implements ActionListener {

    /** booleen permettant de savoir si on est en mode MEM ou non */
    public static boolean modeMem = false;
    
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
        // on récupère le texte du textfield exécuteur de commandes
        String commande = executeurAssocie.getText();
        
        // on insère la commande à l'écran
        ecran.insert(commande + "\n", ecran.getText().length());
        
        if (!modeMem) {
            double resultat = Utilitaires.calculIntermediaire(commande);
            // on insère le résultat à l'écran
            ecran.insert(" = " + resultat + "\n", ecran.getText().length());
        } else {
            
        }
        // On vide le textfield executeur de commandes
        executeurAssocie.setText("");
        

    }
}
