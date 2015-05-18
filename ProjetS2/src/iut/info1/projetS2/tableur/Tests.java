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

/** TODO commenter la responsabilit� de cette classe
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Tests extends JFrame {
    
    /** fen�tre � laquelle cette classe sera li�e */
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
        
        //On donne un titre � l'application
        setTitle("Tableur projet S2"); 
        
        //On donne une taille � notre fen�tre
        setSize(700,600);             
        
        //On centre la fen�tre sur l'�cran
        setLocationRelativeTo(null);  
        
        //On interdit la redimensionnement de la fen�tre
        setResizable(false);           
        
        //On dit � l'application de se fermer lors du clic sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        //On la rend visible
        setVisible(true);
        
        // On initialise notre contentPane
        setContentPane(buildContentPane());

    }

    /** TODO commenter le r�le de la m�thode
     * @return notre panel
     */
    private Container buildContentPane() {
        
        // Initialisation de notre container principal
        panel = new Container(700,500);
        
        // Cr�ation de notre JTextArea
        buildText();
        
        // Ajout du JTextArea � notre container
        panel.add(text);
        
        return panel;
    }

    /** 
     * Initialisation d'une JTextArea
     */
    private void buildText() {
        
        // initialisation de notre JTextArea
        text = new JTextArea();
        
        // Taille de l'�cran
        text.setSize(new Dimension(700, 500));

        // Police
        text.setFont(new Font("Verdana", Font.PLAIN, 15));

        // Texte initial
        text.setText("Affiche les valeurs du tableur : \n");
        
        System.out.println(text.getLineCount());
        
        // recup�ration du texte de la console
        String aRenvoyer = fenetre.getConsole().getText();
        
        String test ="Test";
        
        text.insert(aRenvoyer, text.getText().length());
        

        // Couleur de fond
        text.setBackground(new Color(253,245,230));

        // On emp�che l'�cran d'�tre �ditable 
        text.setEditable(true);
        
    }
}