/*
 * Tableur.java                         2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import iut.info1.projetS2.tableur.Menu;
import iut.info1.projetS2.tableur.action.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/** 
 * Classe principale du tableur
 * Cr�ation de l'interface graphique
 * @author Micka�l
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Tableur extends JFrame{

    /** Container principal de l'application */
    private Container container;

    /** JPanel contenant la console */
    private Container panelConsole;

    /** Champs de texte de l'application */
    private JTextField console;

    /** Modele de table par d�faut */
    private AbstractTableModel modele;

    /** Tableur de notre application */
    private JTable tableur;

    /** Barre de scroll pour notre tableur */
    private JScrollPane scroll;

    /** Liste pour notre tableur */
    @SuppressWarnings("rawtypes")
    private JList enteteLigne;

    /** Bouton valider de notre application */
    private JButton valider;

    /** Dollar � afficher */
    private JLabel dollar;

    /** Texte � afficher */
    private JLabel label;

    /** contient les commandes li�es � cette fen�tre */
    private Commandes actions;
    
    /** contient les actions li�es au tableur */
    @SuppressWarnings("unused")
    private ActionTableur actionsTableur;

    /** contient les methodes de sauvegardes associ�es � ce fchier */
    private OutilsFichier sauvegarde; 

    /**
     * Cr�ation d'un objet tableur
     */
    public Tableur() {
        super();

        // On initialise notre fenetre
        build();

    }


    /** 
     * Initialisation de la fenetre du tableur
     */
    private void build() {

        //On donne un titre � l'application
        setTitle("Mini - Tableur"); 

        //On donne une taille � notre fen�tre
        setSize(900,700);             

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

        // On initialise notre talbeur
        buildTableur();

        // Affichage de la barre de menu
        setJMenuBar(new Menu(this));

        // On change l'icone de la fen�tre
        Image icone = Toolkit.getDefaultToolkit().getImage("tableur.png");
        setIconImage(icone);


        /*
         *  On lie le tableur avec une classe Commandes car l'execution de
         *  commandes est li�e � un tableur pr�cis
         */
        actions = new Commandes(this);

        // lie le tableur avec une instance de methodes de sauvegarde
        sauvegarde = new OutilsFichier(this);
    }

    /**
     * Permet de raffraichir le tableur apr�s une modification
     * @param fenetre de notre application
     */
    public static void refresh(Tableur fenetre) {
        fenetre.tableur.setModel(fenetre.modele);
        fenetre.modele.fireTableDataChanged();
    }

    /**
     * Permet d'implanter une ListModel gr�ce � la classe AbstractListModel
     */
    @SuppressWarnings("rawtypes")
    ListModel lm = new AbstractListModel() {
        String headers[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9","10"
                ,"11","12","13","14","15","16","17","18","19","20"};

        // Retourne le nombre d'�l�ments pr�sents dans la liste
        public int getSize() {
            return headers.length;
        }

        public Object getElementAt(int index) {
            return headers[index];
        }
    };


    /**
     * Permet de cr�er et d'initialiser notre table de 20 lignes et 26 colonnes
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void buildTableur() {

        // Cr�ation d'un mod�le de table
        modele = new DefaultTableModel(lm.getSize(), 26); 

        // Cr�ation d'un mod�le de table
        modele = new ModeleDeTable(this);

        // Cr�ation du tableur
        tableur = new JTable(modele);

        // On emp�che le repositionnement automatique
        tableur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // On met la hauteur de chaque ligne � 40px
        tableur.setRowHeight(40);

        // On met la marge � 0 entre chaque ligne
        tableur.setRowMargin(0);

        // D�claration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 20);

        // Ajout de la police � notre tableur
        tableur.setFont(f);

        // Cr�ation d'un JList qui affiche les donn�es contenues dans lm
        enteteLigne = new JList(lm);

        // On met la longueur de chaque en-t�tes � 50px
        enteteLigne.setFixedCellWidth(50);

        // On aligne la hauteur de nos en-t�tes avec les autres lignes du tableur
        enteteLigne.setFixedCellHeight(tableur.getRowHeight()
                + tableur.getRowMargin());

        // Permet de modifier l'affichage des �l�ments de la liste d'en-t�tes
        enteteLigne.setCellRenderer(new AffichageEnTeteLigne(tableur));

        // Ajout du tableur dans une JSrcollPane
        scroll = new JScrollPane(tableur);

        // Taille du tableur
        scroll.setPreferredSize(new Dimension(740, 478));

        // Ajout des en-t�tes de ligne � notre tableur
        scroll.setRowHeaderView(enteteLigne);

        // Ajout  de notre tableau dans le JPanel principal
        container.add(scroll, BorderLayout.CENTER);
        
        // permet de faire des actions dans le tableur
        actionsTableur = new ActionTableur(this);
        
        
       
    }   

    /**
     * Permet de cr�er et d'initialiser toutes nos JPanel, notre JButton et 
     * notre JTextField
     * @return notre panel
     */
    private JPanel buildContentPane() {

        // Cr�ation de notre JPanel principal
        container = new Container(900,700);

        // Cr�ation de notre JPanel contenant la console et son bouton
        panelConsole = new Container(900,100);

        // On utilise le layout par d�faut
        container.setLayout(new FlowLayout());

        // On d�finit la couleur du fond
        container.setBackground(new Color(255,228,196));

        // On initialise notre JTextField
        buildJTextField();

        // On initialise notre JButton
        buildJButton();

        // On initialise nos JLabel
        buildJLabel();

        container.add(label);

        // Ajout du dollar dans notre JPane
        panelConsole.add(dollar);

        // ajout de la console dans notre JPanel
        panelConsole.add(console);

        // Ajout du bouton valider dans notre JPanel 
        panelConsole.add(valider);

        // Ajout du JPanel de la console dans notre JPanel principal
        container.add(panelConsole);

        return container;

    }

    /** 
     * Initialisation de notre JLabel
     */
    private void buildJLabel() {
        label = new JLabel("Rien pour le moment");


        dollar = new JLabel("$");

        // D�claration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 32);

        // Ajout de la police � notre console
        dollar.setFont(f);

    }

    /** 
     * cr�ation et initialisation du bouton valider pour la console
     */
    private void buildJButton() {

        // Cr�ation du bouton valider
        valider = new JButton(new GetAction(this, "Valider"));

        // On g�re la taille des boutons
        valider.setPreferredSize(new Dimension(100, 60));

        // D�claration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 20);

        // Ajout de la police � notre console
        valider.setFont(f);

        // On d�finit la couleur des boutons
        valider.setBackground(new Color(255,160,122));

        // Le curseur devient une main quandon survole un bouton
        valider.setCursor(Cursor.getPredefinedCursor((Cursor.HAND_CURSOR)));

        // valider devient le bouton par d�faut (c-�-d : activation avec entr�e)
        this.getRootPane().setDefaultButton(valider);

    }

    /** 
     * Initialisation d'un champs de texte qui va servir de console
     */
    private void buildJTextField() {
        // Cr�ation de notre champs de texte
        console = new JTextField();

        // Taille de notre console
        console.setPreferredSize(new Dimension(500, 60));

        // D�claration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 32);

        // Ajout de la police � notre console
        console.setFont(f);

        // On peut �crire � l'int�rieur
        console.setEditable(true);

    }

    /**
     * @return the panelConsole
     */
    public Container getPanelConsole() {
        return panelConsole;
    }

    /**
     * @return the console
     */
    public JTextField getConsole() {
        return console;
    }

    /**
     * @return the modele
     */
    public AbstractTableModel getModele() {
        return modele;
    }

    /**
     * @return the tableur
     */
    public JTable getTableur() {
        return tableur;
    }

    /**
     * @return the scroll
     */
    public JScrollPane getScroll() {
        return scroll;
    }

    /**
     * @return the enteteLigne
     */
    @SuppressWarnings("rawtypes")
    public JList getenteteLigne() {
        return enteteLigne;
    }

    /**
     * @return the valider
     */
    public JButton getValider() {
        return valider;
    }

    /**
     * @return the label
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * @return the lm
     */
    public ListModel<?> getLm() {
        return lm;
    }

    /**
     * @return the actions
     */
    public Commandes getActions() {
        return actions;
    }

    /**
     * @return the sauvegarde
     */
    public OutilsFichier getSauvegarde() {
        return sauvegarde;
    }
}
