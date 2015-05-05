/**
 * Calculatrice.java					30 avril 2015
 * IUT Info 1 2014/2015 groupe projet
 */
package iut.info1.projetS2;

import javax.swing.JFrame;

/**
 * Cr�ation d'une interface graphique pour l'application Calculatrice
 * @author 20-20
 */

@SuppressWarnings("serial")
public class Calculatrice extends JFrame {

    /** Container principal de l'application */
    private Container containerPrincipal = new Container(900, 700);

    /** panel contenant les boutons de navigation */
    private Container containerNavigation = new Container(100, 700);

    /** panel contenant les boutons de navigation */
    private Container containerExecution = new Container(700, 700);

    /** Bouton accueil */
    private Bouton boutonAcc = new Bouton("ACCUEIL");

    /** Bouton aide */
    private Bouton boutonAide = new Bouton("AIDE");

    /** Bouton quitter */
    private Bouton boutonQuit = new Bouton("QUITTER");

    /** Bouton calculer */
    private Bouton boutonCalc = new Bouton("CALCULER");

    /** Ecran o� les commandes et leur r�sultat seront affich�es */
    private Ecran ecran = new Ecran();
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
        
        // Ces boutons sont plac�s sur la partie gauche de la fen�tre
        containerNavigation.add(boutonAcc);
        containerNavigation.add(boutonAide);
        containerNavigation.add(boutonQuit);

        // Les autres champs seront � droite
        containerExecution.add(ecran);
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
        boutonQuit.addActionListener(new ActionQuitter());
        getRootPane().setDefaultButton(boutonCalc);

    }

}
