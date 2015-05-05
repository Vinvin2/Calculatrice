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

/** TODO commenter la responsabilit� de cette classe
 * @author Micka�l
 * @version 0.1
 */
public class CopierAction extends AbstractAction {

    private JTextComponent comp;
    
    /**
     * TODO commenter l'�tat initial atteint
     * @param fenetre 
     * @param texte nom du menu
     */
    public CopierAction(String texte) {
        super(texte);

    }
    
    /** TODO commenter le r�le de la m�thode
     * @param comp
     */
    public void setTextComponent(JTextComponent comp){
        this.comp = comp;
      }

    /**
     * Permet d'affecter une tache lors du d�clenchement de l'�vennement
     */
    public void actionPerformed(ActionEvent e) {
        comp.cut();
    }
}