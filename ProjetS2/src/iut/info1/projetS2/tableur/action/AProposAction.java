/*
 * AProposAction.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/** TODO commenter la responsabilité de cette classe
 * @author Mickaël
 * @version 0.1
 */
public class AProposAction extends AbstractAction {
  
    private Tableur fenetre;
    
    /**
     * TODO commenter l'état initial atteint
     * @param fenetre
     * @param texte
     */
    public AProposAction(Tableur fenetre, String texte) {
        super(texte);
        
        this.fenetre = fenetre;
    }
    
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(fenetre, "Ce programme a été développé "
                                     + "par PERIES Mickaël et MIQUEL Jonathan");
    }
}
