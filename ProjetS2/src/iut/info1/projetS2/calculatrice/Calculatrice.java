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
 * Création d'une interface graphique pour l'application Calculatrice
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

    /** Ecran où les commandes et leur résultat seront affichées */
    private Ecran ecran = new Ecran();
    
    /** Barre de défilement pour l'écran */
    private JScrollPane scroll = new JScrollPane(ecran);
    

	public void setExecuteur(ExecuteurCommandes executeur) {
		this.executeur = executeur;
	}


	/** Champ de texte pour les exécutions de commandes */
    private ExecuteurCommandes executeur = new ExecuteurCommandes();


    /**
     * Créé la fenêtre qui contiendra la mini-calculatrice
     */
    public Calculatrice() {

        super();

        // On définit un titre pour la fenêtre
        this.setTitle("Calculatrice");

        // On définit la taille de la fenêtre
        this.setSize(900, 700);

        // On place la fenêtre au centre de l'écran
        this.setLocationRelativeTo(null);

        // On ferme la fenêtre en cliquant sur la croix rouge
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On empêche la modification de la taille de la fenêtre
        this.setResizable(false);

        // On prévient notre JFrame que notre JPanel sera son content pane
        this.setContentPane(containerPrincipal);

        // Ajout des boutons à notre content pane
        this.setContentPane(containerPrincipal);

        // Les autres champs seront à droite
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(600, 550));
        containerExecution.add(scroll);
        containerExecution.add(executeur);
        containerExecution.add(boutonCalc);

        // Les containers sont placés dans l'ordre suivant
        // Cela permet d'avoir les boutons de navigation en premier
        // (a gauche)
        containerPrincipal.add(containerNavigation);	        
        containerPrincipal.add(containerExecution);		

        // On rend visible la fenêtre
        this.setVisible(true);

        // on ajoute les actions qu'auront les différents boutons
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
	 * Ne pas oublier que private veut dire seulement récupérable depuis cette
	 * classe donc on met un getters pour pouvoir récupérer l'instance de
	 * l'éxécuteur dans une autre classe.
	 * Exemple : fenetre.getExecuteur.cut()
	 */
    public ExecuteurCommandes getExecuteur() {
		return executeur;
	}


}
