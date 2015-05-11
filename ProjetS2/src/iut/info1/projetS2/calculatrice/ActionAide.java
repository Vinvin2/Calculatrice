/*
 * ActionAide.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Permet d'ouvrir une fenêtre aide pour le fonctionnement de la mini-calculatrice
 * @author 20-20
 * @version 0.1
 */
public class ActionAide implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        new Aide();
        
    }

}
