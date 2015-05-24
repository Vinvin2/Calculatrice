/**
 * Calculatrice.java					30 avril 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2.calculatrice;

import iut.info1.projetS2.calculatrice.navigation.Navigation;
import iut.info1.projetS2.tableur.Menu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Cr�ation d'une interface graphique pour l'application Calculatrice
 * @author 20-20
 * @version 0.1
 */

@SuppressWarnings("serial")
public class Calculatrice extends JFrame {

    /** Container principal de l'application */
    private Container containerPrincipal = new Container(900, 700);

    /** panel contenant les boutons de navigation */
    private Container containerNavigation = new Container(100, 700);

    /** panel contenant les boutons de navigation */
    private Container containerExecution = new Container(700, 700);

    /** Bouton calculer */
    private Bouton boutonCalc = new Bouton("CALCULER");

    /** Ecran o� les commandes et leur r�sultat seront affich�es */
    private Ecran ecran = new Ecran();
    
    /** Barre de d�filement pour l'�cran */
    private JScrollPane scroll = new JScrollPane(ecran);
    

	public void setExecuteur(ExecuteurCommandes executeur) {
		this.executeur = executeur;
	}


	/** Champ de texte pour les ex�cutions de commandes */
    private ExecuteurCommandes executeur = new ExecuteurCommandes();


    /**
     * Cr�� la fen�tre qui contiendra la mini-calculatrice
     */
    public Calculatrice() {

        super();

        // On d�finit un titre pour la fen�tre
        this.setTitle("Calculatrice");

        // On d�finit la taille de la fen�tre
        this.setSize(900, 700);

        // On place la fen�tre au centre de l'�cran
        this.setLocationRelativeTo(null);

        // On ferme la fen�tre en cliquant sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On emp�che la modification de la taille de la fen�tre
        this.setResizable(false);

        // On pr�vient notre JFrame que notre JPanel sera son content pane
        this.setContentPane(containerPrincipal);

        // Ajout des boutons � notre content pane
        this.setContentPane(containerPrincipal);

        // Les autres champs seront � droite
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(600, 550));
        containerExecution.add(scroll);
        containerExecution.add(executeur);
        containerExecution.add(boutonCalc);

        // Les containers sont plac�s dans l'ordre suivant
        // Cela permet d'avoir les boutons de navigation en premier
        // (a gauche)
        containerPrincipal.add(containerNavigation);	        
        containerPrincipal.add(containerExecution);		

        // On rend visible la fen�tre
        this.setVisible(true);

        // on ajoute les actions qu'auront les diff�rents boutons
        boutonCalc.addActionListener(new ActionCalculer(this.executeur, this.ecran));
        getRootPane().setDefaultButton(boutonCalc);
        
        // Affichage de la barre de menu
        setJMenuBar(new Navigation(this));

    }


	public Object executeur() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Ne pas oublier que private veut dire seulement r�cup�rable depuis cette
	 * classe donc on met un getters pour pouvoir r�cup�rer l'instance de
	 * l'�x�cuteur dans une autre classe.
	 * Exemple : fenetre.getExecuteur.cut()
	 */
    public ExecuteurCommandes getExecuteur() {
		return executeur;
	}


}
