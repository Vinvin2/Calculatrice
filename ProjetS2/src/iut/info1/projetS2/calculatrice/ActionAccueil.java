/**
 * ActionCalculer.java					5 mai 2015
 * IUT Info 1 2014/2015 groupe projet
 */

package iut.info1.projetS2.calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Permet d'ouvrir la calculatric et de fermer le menu de l'appli
 * @author 20-20
 * @version 0.1
 */
public class ActionAccueil implements ActionListener {

	/** TODO commenter le r�le de ce champ */
	private JFrame fenetre;
	
    /**
     * TODO commenter l'�tat initial atteint
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
