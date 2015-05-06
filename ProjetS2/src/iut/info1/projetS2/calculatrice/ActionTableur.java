/*
 * ActionTableur.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.calculatrice;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Permet d'ouvrir le tableur et de fermer le menu de l'appli
 * @author 20-20
 * @version 0.1
 */
public class ActionTableur implements ActionListener {

	/** fenêtre que l'on devra fermer */
	private JFrame fenetre;
	
    /**
     * On récupère la fenêtre à fermer
     * @param fenetre
     */
    public ActionTableur(JFrame fenetre) {
    	
        this.fenetre = fenetre;
        
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Tableur();
		fenetre.dispose();
	}

}
