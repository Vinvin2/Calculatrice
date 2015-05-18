/*
 * Menu.java				13 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import static iut.info1.projetS2.tableur.Tableur.*;

import iut.info1.projetS2.tableur.action.AProposAction;
import iut.info1.projetS2.tableur.action.AideAction;
import iut.info1.projetS2.tableur.action.CollerAction;
import iut.info1.projetS2.tableur.action.CopierAction;
import iut.info1.projetS2.tableur.action.CouperAction;
import iut.info1.projetS2.tableur.action.NouveauAction;
import iut.info1.projetS2.tableur.action.OuvrirAction;
import iut.info1.projetS2.tableur.action.QuitterAction;
import iut.info1.projetS2.tableur.action.SauverAction;

import java.awt.Toolkit;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/** 
 * Permet la création du menu de notre tableur
 * @author Mickaël
 * @version 0.1
 */
public class Menu {
    
    /** Barre de menu de notre application */
    private static JMenuBar menuBar;
    
    /** Menu fichier de notre application */
    private static JMenu menuFichier;
    
    /** Menu editer de notre application */
    private static JMenu menuEditer;
    
    /** Menu autre de notre application */
    private static JMenu menuAutre;
    
    /** Sous-menu de notre application */
    private static JMenuItem item;
    /** 
     * Permet d'initialiser la barre de menu et d'y ajouter tous les menus 
     * à l'intérieur
     */
    public static void buildMenu() {
        
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
        
    }

    /** 
     * Permet d'initialiser le menu autre et tout ses sous-menus
     */
    private static void buildMenuAutre() {
        
        // Création du menu autre
        menuAutre = new JMenu("?");
        
        // Ajout du sous-menu aide
        item = new JMenuItem(new AideAction("Aide"));
        menuAutre.add(item);
        
        // Ajout du sous-menu à propos
        item = new JMenuItem(new AProposAction(getFenetre(),"A propos"));
        menuAutre.add(item);
    }

    /** 
     * Permet d'initialiser le menu éditer et tout ses sous-menus
     */
    private static void buildMenuEditer() {
       
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
    private static void buildMenuFichier() {
        
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
        item = new JMenuItem(new QuitterAction(getFenetre(), "Quitter"));
        menuFichier.add(item);       
    }
    
    /**
     * @return the menuBar
     */
    public static JMenuBar getMenuBar() {
        return menuBar;
    }
    
    /**
     * @return the menuFichier
     */
    public static JMenu getMenuFichier() {
        return menuFichier;
    }

    /**
     * @return the menuEditer
     */
    public static JMenu getMenuEditer() {
        return menuEditer;
    }

    /**
     * @return the menuAutre
     */
    public static JMenu getMenuAutre() {
        return menuAutre;
    }

    /**
     * @return the item
     */
    public static JMenuItem getItem() {
        return item;
    }
}
