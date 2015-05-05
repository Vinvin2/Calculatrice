/*
 * GetAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/** 
 * Permet d'obtenir le contenu de la console lors de l'appui sur le 
 * bouton de validation
 * @author Mickaël
 * @version 0.1
 */
public class GetAction extends AbstractAction {
    
    private Tableur fenetre;
    
    /**
     * TODO commenter l'état initial atteint
     * @param fenetre
     * @param texte
     */
    public GetAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
        
    }
    
    /**
     * 
     */
    public void actionPerformed(ActionEvent e) {
        String aRenvoyer = fenetre.getConsole().getText();
        fenetre.getLabel().setText(aRenvoyer);
    }
}
