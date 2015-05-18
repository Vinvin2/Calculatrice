/*
 * Tests.java				13 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import static iut.info1.projetS2.tableur.Tableur.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

/** TODO commenter la responsabilité de cette classe
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Tests extends JFrame {
    
    /** fenêtre à laquelle cette classe sera liée */
    private Tableur fenetre;
    
    /** Container principal de notre fenetre */
    private Container panel;
    
    /** Champs de texte de notre fenetre */
    private JTextArea text;
    
    /**
     * Fenetre dans laquelle apparaitrons les tests
     * @param fenetre 
     */
    public Tests(Tableur fenetre) {
       
        super();
        this.fenetre = fenetre;       
        build();
    }

    /** 
     * Initialisation de la fenetre de tests
     */
    private void build() {
        
        //On donne un titre à l'application
        setTitle("Tableur projet S2"); 
        
        //On donne une taille à notre fenêtre
        setSize(700,600);             
        
        //On centre la fenêtre sur l'écran
        setLocationRelativeTo(null);  
        
        //On interdit la redimensionnement de la fenêtre
        setResizable(false);           
        
        //On dit à l'application de se fermer lors du clic sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        //On la rend visible
        setVisible(true);
        
        // On initialise notre contentPane
        setContentPane(buildContentPane());

    }

    /** TODO commenter le rôle de la méthode
     * @return notre panel
     */
    private Container buildContentPane() {
        
        // Initialisation de notre container principal
        panel = new Container(700,500);
        
        // Création de notre JTextArea
        buildText();
        
        // Ajout du JTextArea à notre container
        panel.add(text);
        
        return panel;
    }

    /** 
     * Initialisation d'une JTextArea
     */
    private void buildText() {
        
        // initialisation de notre JTextArea
        text = new JTextArea();
        
        // Taille de l'écran
        text.setSize(new Dimension(700, 500));

        // Police
        text.setFont(new Font("Verdana", Font.PLAIN, 15));

        // Texte initial
        text.setText("Affiche les valeurs du tableur : \n");
        
        System.out.println(text.getLineCount());
        
        // recupération du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        
        String test ="Test";
        
        text.insert(aRenvoyer, text.getText().length());
        

        // Couleur de fond
        text.setBackground(new Color(253,245,230));

        // On empêche l'écran d'être éditable 
        text.setEditable(true);
        
    }
}