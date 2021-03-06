/*
 * ActionCalculatrice.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.menu;

import iut.info1.projetS2.calculatrice.Calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Permet d'ouvrir la calculatrice et de fermer le menu de l'appli
 * @author groupe projet
 * @version 0.1
 */
public class ActionCalculatrice implements ActionListener {

	/** Fen�tre que l'on fermera apr�s avoir ouvert la fen�tre de la 
	 * calculatrice 
	 */
	private JFrame fenetre;
	
    /**
     * R�cup�re la fen�tre que l'on devra fermer
     * @param fenetre
     */
    public ActionCalculatrice(JFrame fenetre) {
    	
        this.fenetre = fenetre;
        
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Calculatrice();
		fenetre.dispose();
	}

}
