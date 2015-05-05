/*
 * CopierAction.java				4 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.text.JTextComponent;

/** TODO commenter la responsabilité de cette classe
 * @author Mickaël
 * @version 0.1
 */
public class CopierAction extends AbstractAction {

    private JTextComponent comp;
    
    /**
     * TODO commenter l'état initial atteint
     * @param fenetre 
     * @param texte nom du menu
     */
    public CopierAction(String texte) {
        super(texte);

    }
    
    /** TODO commenter le rôle de la méthode
     * @param comp
     */
    public void setTextComponent(JTextComponent comp){
        this.comp = comp;
      }

    /**
     * Permet d'affecter une tache lors du déclenchement de l'évennement
     */
    public void actionPerformed(ActionEvent e) {
        comp.cut();
    }
}