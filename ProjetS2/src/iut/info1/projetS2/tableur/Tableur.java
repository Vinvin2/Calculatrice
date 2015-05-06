/*
 * Tableur.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import iut.info1.projetS2.tableur.action.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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
    
    /** Barre de menu de notre application */
    private JMenuBar menuBar;
    
    /** Menu fichier de notre application */
    private JMenu menuFichier;
    
    /** Menu editer de notre application */
    private JMenu menuEditer;
    
    /** Menu autre de notre application */
    private JMenu menuAutre;
    
    /** Sous-menu de notre application */
    private JMenuItem item;
    
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
        
        // On inititalise notre menu
        buildMenu();
        
        // On initialise notre talbeur
        buildTableur();
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
        modele = new modeleDeTable();
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
        scroll.setPreferredSize(new Dimension(700, 500));
        
        // Ajout des en-têtes de ligne à notre tableur
        scroll.setRowHeaderView(enteteLigne);
        
        // Ajout  de notre tableau dans le JPanel principal
        container.add(scroll, BorderLayout.CENTER);
        

    }

    /** 
     * Permet d'initialiser la barre de menu et d'y ajouter tous les menus 
     * à l'intérieur
     */
    private void buildMenu() {
        
        // Création de la barre de menu
        menuBar = new JMenuBar();
        
        // Initialisation du menu fichier
        buildMenuFichier();
        
        // Initialisation du menu editer
        buildMenuEditer();
             
        // Initialisation du menu autre
        buildMenuAutre();                               
        
        // Ajout du menu fichier dans la barre de menu
        menuBar.add(menuFichier);
        
        // Ajout du menu éditer dans la barre de menu
        menuBar.add(menuEditer);
        
        // Ajout du menu autre dans la barre de menu
        menuBar.add(menuAutre);
        
        // Affichage de la barre de menu
        setJMenuBar(menuBar);
    }

    /** 
     * Permet d'initialiser le menu autre et tout ses sous-menus
     */
    private void buildMenuAutre() {
        
        // Création du menu autre
        menuAutre = new JMenu("?");
        
        // Ajout du sous-menu aide
        item = new JMenuItem(new AideAction("Aide"));
        menuAutre.add(item);
        
        // Ajout du sous-menu à propos
        item = new JMenuItem(new AProposAction(this,"A propos"));
        menuAutre.add(item);
    }

    /** 
     * Permet d'initialiser le menu éditer et tout ses sous-menus
     */
    private void buildMenuEditer() {
       
        // Création du menu Editer
        menuEditer = new JMenu("Editer");
        
        // Ajout du sous-menu copier
        item = new JMenuItem(new CopierAction("Copier"));
        item.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit()
        .getMenuShortcutKeyMask(), false));
        menuEditer.add(item);
        
        // Ajout du sous-menu couper
        item = new JMenuItem(new CouperAction("Couper"));
        item.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit()
        .getMenuShortcutKeyMask(), false));
        menuEditer.add(item);
        
        // Ajout du sous-menu coller
        item = new JMenuItem(new CollerAction("Coller"));
        item.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit()
        .getMenuShortcutKeyMask(), false));
        menuEditer.add(item);       
    }

    /** 
     * Permet d'initialiser le menu fichier et tout ses sous-menus
     */
    private void buildMenuFichier() {
        
        // Création du menu fichier
        menuFichier = new JMenu("Fichier");
        
        // Ajout du sous-menu nouveau
        item = new JMenuItem(new NouveauAction("Nouveau"));
        menuFichier.add(item);
        
        // Ajout d'une barre de séparation
        menuFichier.insertSeparator(1);
        
        // Ajout du sous-menu ouvrir
        item = new JMenuItem(new OuvrirAction("Ouvrir"));
        menuFichier.add(item);
        
        // Ajout du sous-menu sauver
        item = new JMenuItem(new SauverAction("Sauver"));
        menuFichier.add(item);
        
        // Ajout du sous-menu quitter
        item = new JMenuItem(new QuitterAction(this, "Quitter"));
        menuFichier.add(item);       
    }
    
    

    /** TODO 
     * Permet de créer et d'initialiser toutes nos JPaneln notre JButton et 
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

    /** TODO commenter le rôle de la méthode
     * 
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
     * @return the menuFichier
     */
    public JMenu getMenuFichier() {
        return menuFichier;
    }

    /**
     * @return the menuEditer
     */
    public JMenu getMenuEditer() {
        return menuEditer;
    }

    /**
     * @return the menuAutre
     */
    public JMenu getMenuAutre() {
        return menuAutre;
    }

    /**
     * @return the item
     */
    public JMenuItem getItem() {
        return item;
    }

    /**
     * @return the lm
     */
    public ListModel<?> getLm() {
        return lm;
    }

}
