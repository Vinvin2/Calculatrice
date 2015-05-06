/*
 * GetAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;
import iut.info1.projetS2.tableur.*;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet d'obtenir le contenu de la console lors de l'appui sur le 
 * bouton de validation
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class GetAction extends AbstractAction {
    
    /** */
    @SuppressWarnings("unused")
    private Tableur fenetre;
    
    /**
     * 
     * @param fenetre
     * @param texte nom de notre sous-menu
     */
    public GetAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
        
    }
    
    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        String aRenvoyer = fenetre.getConsole().getText();
        fenetre.getLabel().setText(aRenvoyer);
    }
}
