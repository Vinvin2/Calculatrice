/*
 * Tableur.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import iut.info1.projetS2.tableur.Menu;
import iut.info1.projetS2.tableur.action.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
 * Création de l'interface graphique
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class Tableur extends JFrame{
    
    /** JFrame de notre classe */
    private Tableur fenetre;
    
    /** Container principal de l'application */
    private Container container;
    
    /** JPanel contenant la console */
    private Container panelConsole;
    
    /** Champs de texte de l'application */
    private JTextField console;
    
    /** Modele de table par défaut */
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
    
    /** Dollar à afficher */
    private JLabel dollar;
    
    /** Texte à afficher */
    private JLabel label;

    /** contient les commandes liées à cette fenêtre */
    private Commandes actions;
    
    /**
     * Création d'un objet tableur
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
        
        //On donne un titre à l'application
        setTitle("Tableur projet S2"); 
        
        //On donne une taille à notre fenêtre
        setSize(900,700);             
        
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
        
        // On initialise notre talbeur
        buildTableur();
        
        // Affichage de la barre de menu
        setJMenuBar(new Menu(this));
       
        
        /*
         *  On lie le tableur avec une classe Commandes car l'execution de
         *  commandes est liée à un tableur précis
         *  commandes est liee a un tableur precis
         */
        actions = new Commandes(this);
    }

    /**
     * Permet de raffraichir le tableur après une modification
     * @param fenetre de notre application
     */
    public static void refresh(Tableur fenetre) {
        fenetre.tableur.setModel(fenetre.modele);
        fenetre.modele.fireTableDataChanged();
    }
    
    /**
     * Permet d'implanter une ListModel grâce à la classe AbstractListModel
     */
    @SuppressWarnings("rawtypes")
    ListModel lm = new AbstractListModel() {
        String headers[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9","10"
                            ,"11","12","13","14","15","16","17","18","19","20"};

        // Retourne le nombre d'éléments présents dans la liste
        public int getSize() {
            return headers.length;
        }

        public Object getElementAt(int index) {
          return headers[index];
        }
    };
        

    /**
     * Permet de créer et d'initialiser notre table de 20 lignes et 26 colonnes
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void buildTableur() {
        
        // Création d'un modèle de table
        modele = new DefaultTableModel(lm.getSize(), 26); 
        
        // Création d'un modèle de table
        modele = new ModeleDeTable();
        
        // Création du tableur
        tableur = new JTable(modele);
        
        // On empèche le repositionnement automatique
        tableur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // On met la hauteur de chaque ligne à 40px
        tableur.setRowHeight(40);
        
        // On met la marge à 0 entre chaque ligne
        tableur.setRowMargin(0);
        
        // Déclaration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 20);
        
        // Ajout de la police à notre tableur
        tableur.setFont(f);
        
        // Création d'un JList qui affiche les données contenues dans lm
        enteteLigne = new JList(lm);
        
        // On met la longueur de chaque en-têtes à 50px
        enteteLigne.setFixedCellWidth(50);

        // On aligne la hauteur de nos en-têtes avec les autres lignes du tableur
        enteteLigne.setFixedCellHeight(tableur.getRowHeight()
            + tableur.getRowMargin());
        
        // Permet de modifier l'affichage des éléments de la liste d'en-têtes
        enteteLigne.setCellRenderer(new AffichageEnTeteLigne(tableur));

        // Ajout du tableur dans une JSrcollPane
        scroll = new JScrollPane(tableur);
        
        // Taille du tableur
        scroll.setPreferredSize(new Dimension(740, 478));
        
        // Ajout des en-têtes de ligne à notre tableur
        scroll.setRowHeaderView(enteteLigne);
        
        // Ajout  de notre tableau dans le JPanel principal
        container.add(scroll, BorderLayout.CENTER);
        

    }   

    /**
     * Permet de créer et d'initialiser toutes nos JPanel, notre JButton et 
     * notre JTextField
     * @return notre panel
     */
    private JPanel buildContentPane() {
        
        // Création de notre JPanel principal
        container = new Container(900,700);
        
        // Création de notre JPanel contenant la console et son bouton
        panelConsole = new Container(900,100);
        
        // On utilise le layout par défaut
        container.setLayout(new FlowLayout());
        
        // On met le fond en vert
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
        
        // Déclaration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 32);
        
        // Ajout de la police à notre console
        dollar.setFont(f);
        
    }

    /** 
     * création et initialisation du bouton valider pour la console
     */
    private void buildJButton() {

        // Création du bouton valider
        valider = new JButton(new GetAction(this, "Valider"));
        
        // On gère la taille des boutons
        valider.setPreferredSize(new Dimension(100, 60));

        // Déclaration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 20);
        
        // Ajout de la police à notre console
        valider.setFont(f);
        
        // On définit la couleur des boutons
        valider.setBackground(new Color(250,128,114));
        
        // valider devient le bouton par défaut (c-à-d : activation avec entrée)
        this.getRootPane().setDefaultButton(valider);
        
    }

    /** 
     * Initialisation d'un champs de texte qui va servir de console
     */
    private void buildJTextField() {
        // Création de notre champs de texte
        console = new JTextField();
        
        // Taille de notre console
        console.setPreferredSize(new Dimension(500, 60));
        
        // Déclaration d'une police et d'une taille
        Font f = new Font("Serif", Font.PLAIN, 32);
        
        // Ajout de la police à notre console
        console.setFont(f);
        
        // On peut écrire à l'intérieur
        console.setEditable(true);

    }

    /**
     * @return the fenetre
     */
    public Tableur getFenetre() {
        return fenetre;
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
}
