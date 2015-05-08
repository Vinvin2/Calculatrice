/*
 * ActionCalculer.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Permet d'ouvrir le menu et de fermer la calculatrice
 * @author 20-20
 * @version 0.1
 */
public class ActionAccueil implements ActionListener {

    /** Fen�tre que l'on devra fermer apr�s avoir lanc� la fen�tre d'accueil */
    private JFrame fenetre;

    /**
     * On r�cup�re la fen�tre � fermer
     * @param fenetre
     */
    public ActionAccueil(JFrame fenetre) {

        this.fenetre = fenetre;

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        new Menu();
        fenetre.dispose();
    }

}
