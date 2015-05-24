/*
 * Menu.java				22 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.calculatrice.navigation;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import iut.info1.projetS2.calculatrice.Calculatrice;
import iut.info1.projetS2.tableur.Tableur;
import iut.info1.projetS2.tableur.action.AProposAction;
import iut.info1.projetS2.tableur.action.AideAction;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/** 
 * Permet la création d'un menu de navigation pour la calculatrice
 * @author 20-20
 * @version 0.1
 */

@SuppressWarnings("serial")
public class Navigation extends JMenuBar{
	
	/** Menu s'affichant lors du clic droit */
    private JPopupMenu popup;
    
    /** Fenetre principale de notre application */
    private Calculatrice fenetre;
    
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
    */
	public Navigation(Calculatrice calculatrice) {
		super();

		buildMenu();
		
		/**
		 * Tu as oublié de récupérer l'instance de la JFrame de ta calculatrice
	     * et de même dans tes classes couper/copier/coller
		 */
		this.fenetre = fenetre;

		//actionSouris();
	}
	
	 /** 
     * Permet d'initialiser la barre de menu et d'y ajouter tous les menus 
     * à l'intérieur
     */
    public void buildMenu()  {
        
        // Initialisation du menu fichier
        buildMenuFichier();
        
        // Initialisation du menu editer
        buildMenuEditer();
             
        // Initialisation du menu autre
        buildMenuAutre();                               
        
        // Initialisation du menu pop-up
        buildMenuPopUp();
        
        // Ajout du menu fichier dans la barre de menu
        this.add(menuFichier);
        
        // Ajout du menu éditer dans la barre de menu
        this.add(menuEditer);
        
        // Ajout du menu autre dans la barre de menu
        this.add(menuAutre);
        
    }
	
    /** 
     * Permet d'initialiser le menu pop-up et ses sous-menu
     */
    private void buildMenuPopUp() {
        
        // Création du menu pop-up
        popup = new JPopupMenu();
        
        // Ajout du sous-menu copier
        item = new JMenuItem(new Copier(fenetre,"Copier"));
        item.setIcon(new ImageIcon("copy.gif"));
        item.setAccelerator(KeyStroke.getKeyStroke('C',
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        popup.add(item);
        
        // Ajout du sous-menu couper
        item = new JMenuItem(new Couper(fenetre,"Couper"));
        item.setIcon(new ImageIcon("cut.jpg"));
        item.setAccelerator(KeyStroke.getKeyStroke('X',
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        popup.add(item);
        
        // Ajout du sous-menu coller
        item = new JMenuItem(new Coller(fenetre,"Coller"));
        item.setIcon(new ImageIcon("coller.gif"));
        item.setAccelerator(KeyStroke.getKeyStroke('V',
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));       
        popup.add(item);
        
    }
    
    /** 
     * Permet d'initialiser le menu autre et tout ses sous-menus
     */
    private void buildMenuAutre() {
        
        // Création du menu autre
        menuAutre = new JMenu("?");
        
//        // Ajout du sous-menu aide
//        item = new JMenuItem(new ActionAide(fenetre,"Aide"));
//        item.setIcon(new ImageIcon("aide.png"));
//        menuAutre.add(item);
        
        // Ajout du sous-menu à propos
        item = new JMenuItem(new APropos(fenetre,"A propos"));
        item.setIcon(new ImageIcon("aPropos.png"));
        menuAutre.add(item);
    }

    /** 
     * Permet d'initialiser le menu éditer et tout ses sous-menus
     */
    private void buildMenuEditer() {
       
        // Création du menu Editer
        menuEditer = new JMenu("Editer");
        
        // Ajout du sous-menu copier
        item = new JMenuItem(new Copier(fenetre,"Copier"));
        item.setIcon(new ImageIcon("copy.gif"));
        item.setAccelerator(KeyStroke.getKeyStroke('C',
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        menuEditer.add(item);

        // Ajout du sous-menu couper
        item = new JMenuItem(new Couper(fenetre,"Couper"));
        item.setIcon(new ImageIcon("cut.jpg"));
        item.setAccelerator(KeyStroke.getKeyStroke('X',
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        menuEditer.add(item);

        // Ajout du sous-menu coller
        item = new JMenuItem(new Coller(fenetre,"Coller"));
        item.setIcon(new ImageIcon("coller.gif"));
        item.setAccelerator(KeyStroke.getKeyStroke('V',
                  Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
        menuEditer.add(item);       
    }

    /** 
     * Permet d'initialiser le menu fichier et tout ses sous-menus
     */
    private void buildMenuFichier() {
        
        // Création du menu fichier
        menuFichier = new JMenu("Fichier");
        
        // Ajout du sous-menu accueil
        item = new JMenuItem(new Accueil(fenetre, "Accueil"));
        item.setIcon(new ImageIcon("accueil.png"));
        menuFichier.add(item);   
        
        // Ajout du sous-menu quitter
        item = new JMenuItem(new Accueil(fenetre, "Quitter"));
        item.setIcon(new ImageIcon("quitter.gif"));
        menuFichier.add(item);
    }
    
//    /**
//     * Permet de récupérer les actions de notre périphérique souris
//     */
//    public void actionSouris() {
//    	fenetre.get().addMouseListener(new MouseAdapter() {
//
//    		/**
//    		 * Gère les actions de la souris
//    		 */
//    		public void mouseClicked(MouseEvent e) {
//
//    			// si on appui sur le clic droit
//    			if (SwingUtilities.isRightMouseButton(e)) {
//
//    				// On affiche le pop-up là où est notre pointeur
//    					//popup.show(fenetre.getContainer(), e.getX(), e.getY());
//    			}
//
//    		}
//    	});
//    }

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
