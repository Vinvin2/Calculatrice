/**
 * ActionQuitter.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * On quitte l'application via le bouton quitter
 * @author Sébastien
 * @version 0.1
 */
public class ActionQuitter implements ActionListener {

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0); // Quitte l'application sans erreur
    }

}
