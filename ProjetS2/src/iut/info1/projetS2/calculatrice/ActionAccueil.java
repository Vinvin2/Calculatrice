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

    /** Fenêtre que l'on devra fermer aprés avoir lancé la fenêtre d'accueil */
    private JFrame fenetre;

    /**
     * On récupère la fenêtre à fermer
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
