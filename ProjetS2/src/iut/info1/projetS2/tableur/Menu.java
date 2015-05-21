/*
 * Menu.java				13 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import iut.info1.projetS2.tableur.action.*;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/** 
 * Permet la cr�ation du menu de notre tableur
 * @author Micka�l
 * @version 0.1
 */
public class Menu extends JMenuBar{
    
    /** Fenetre principale de notre application */
    private Tableur fenetre;
    
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
     * Constructeur de notre classe
     * @param fenetre � r�cup�rer
     */
    public Menu(Tableur fenetre) {
        super();
        
        this.fenetre = fenetre;
        
        buildMenu();
    }
    /** 
     * Permet d'initialiser la barre de menu et d'y ajouter tous les menus 
     * � l'int�rieur
     */
    public void buildMenu()  {
        
        // Cr�ation de la barre de menu
//        menuBar = new JMenuBar();
        
        // Initialisation du menu fichier
        buildMenuFichier();
        
        // Initialisation du menu editer
        buildMenuEditer();
             
        // Initialisation du menu autre
        buildMenuAutre();                               
        
        // Ajout du menu fichier dans la barre de menu
        this.add(menuFichier);
        
        // Ajout du menu �diter dans la barre de menu
        this.add(menuEditer);
        
        // Ajout du menu autre dans la barre de menu
        this.add(menuAutre);
        
    }

    /** 
     * Permet d'initialiser le menu autre et tout ses sous-menus
     */
    private void buildMenuAutre() {
        
        // Cr�ation du menu autre
        menuAutre = new JMenu("?");
        
        // Ajout du sous-menu aide
        item = new JMenuItem(new AideAction(fenetre,"Aide"));
        menuAutre.add(item);
        
        // Ajout du sous-menu � propos
        item = new JMenuItem(new AProposAction(fenetre,"A propos"));
        menuAutre.add(item);
    }

    /** 
     * Permet d'initialiser le menu �diter et tout ses sous-menus
     */
    private void buildMenuEditer() {
       
        // Cr�ation du menu Editer
        menuEditer = new JMenu("Editer");
        
        // Ajout du sous-menu copier
        item = new JMenuItem(new CopierAction(fenetre,"Copier"));
        item.setIcon(new ImageIcon("cut.jpg"));
        item.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C,
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        menuEditer.add(item);
        
        // Ajout du sous-menu couper
        item = new JMenuItem(new CouperAction(fenetre,"Couper"));
        item.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        menuEditer.add(item);
        
        // Ajout du sous-menu coller
        item = new JMenuItem(new CollerAction(fenetre,"Coller"));
        item.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V,
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        menuEditer.add(item);       
    }

    /** 
     * Permet d'initialiser le menu fichier et tout ses sous-menus
     */
    private void buildMenuFichier() {
        
        // Cr�ation du menu fichier
        menuFichier = new JMenu("Fichier");
        
        // Ajout du sous-menu nouveau
        item = new JMenuItem(new NouveauAction(fenetre,"Nouveau"));
        menuFichier.add(item);
        
        // Ajout d'une barre de s�paration
        menuFichier.insertSeparator(1);
        
        // Ajout du sous-menu ouvrir
        item = new JMenuItem(new ChargerAction(fenetre,"Charger"));
        menuFichier.add(item);
        
        // Ajout du sous-menu sauver
        item = new JMenuItem(new SauverAction(fenetre,"Sauver"));
        menuFichier.add(item);
        
        // Ajout du sous-menu quitter
        item = new JMenuItem(new AccueilAction(fenetre, "Accueil"));
        menuFichier.add(item);       
    }
    
    /**
     * @return the menuBar
     */
    public JMenuBar getMenuBar() {
        return menuBar;
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

}
