/*
 * actionTableur.java				29 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur.action;

import iut.info1.projetS2.tableur.Tableur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** 
 * Effectue toutes les actions li�es au tableur, aussi bien avec la souris
 * qu'avec les touches du clavier
 * @author Micka�l
 * @version 0.1
 */
public class ActionTableur {

    /** fenetre de notre tableur */
    private Tableur fenetre;

    /** Texte � l'int�rieur de notre console */
    private String textConsole = "";

    /** num�ro de la ligne du tableur */
    private int ligne = 0;

    /** num�ro de la colonne du tableur */
    private int colonne = 0;

    /**
     * Constructeur principal de notre classe
     * @param fenetre de notre tableurs
     */
    public ActionTableur(Tableur fenetre) {
        this.fenetre = fenetre;

        // On appelle les actions li�es � la souris
        actionsSouris();

        // On appelle les actions li�es aux touches du clavier
        actionsTouches();
    }
    
    /**
     * Permet de g�rer toutes les actions reli�es � la souris
     */
    private void actionsSouris() {
        fenetre.getTableur().addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // non utilis�e
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // On sauvegarge notre cellule si on clique autre part
                fenetre.getActions().actionValider();
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // non utilis�e
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // non utilis�e
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                
                // on r�cup�re le num�ro de ligne
                ligne = fenetre.getTableur().getSelectedRow();
                
                // on r�cup�re le num�ro de colonne
                colonne = fenetre.getTableur().getSelectedColumn();
                
                // on r�cup�re le tableau � deux dimensions contenant
                // toutes les donn�es de la table
                String[][] stringTab = fenetre.getActions().getEntrees();
                
                // si la case est vide
                if (stringTab[ligne][colonne] == "") {
                    // on converti notre nombre en lettre
                    char lettre = (char) (colonne + 65);
                    
                    textConsole = lettre + String.valueOf(ligne+1) + " ";
                    fenetre.getConsole().setText(textConsole);

                } else {

                    // on affiche les donn�es correspondant � la cellule s�lection�e
                    fenetre.getConsole().setText(stringTab[ligne][colonne]);
                }
            }
        });
    }

    /**
     * Permet de g�rer toutes les actions reli�es aux touches de la souris
     */
    public void actionsTouches() {

        fenetre.getTableur().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent arg0) {               
                // non utilis�e
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                // non utilis�e
            }

            @Override
            public void keyPressed(KeyEvent arg0) {

                // On r�initialise notre console si on change de ligne ou 
                // de colonne et on met � jour les valeurs de nos variables
                if (ligne != fenetre.getTableur().getSelectedRow() + 1
                   || colonne != fenetre.getTableur().getSelectedColumn() + 1) {
                    
                    // on r�cup�re le num�ro de ligne
                    ligne = fenetre.getTableur().getSelectedRow() + 1;
                    
                    // on r�cup�re le num�ro de colonne
                    colonne = fenetre.getTableur().getSelectedColumn() + 1;
                    
                    // on r�initialise notre texte
                    textConsole = "";

                }

                // on converti notre nombre en lettre
                char lettre = (char) (colonne + 64);

                // si la console est vide on lui rajoute le num�ro de la 
                // ligne et la lettre de la colonne
                if (textConsole == "") {
                    textConsole = lettre + String.valueOf(ligne) + " ";
                }

                // si la touche appuy�e est diff�rente des fl�ches et du 
                // retour chariot
                if (toucheCorrecte(arg0)) {

                    // A chaque fois que l'on appui sur une touche on ajoute le
                    // caract�re entr� dans notre console
                    textConsole += String.valueOf(arg0.getKeyChar());
                    fenetre.getConsole().setText(textConsole);

                }

                // si la touche appuy� est celle du retour chariot
                if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    textConsole = textConsole.substring(0,
                                                        textConsole.length()-1);
                    fenetre.getConsole().setText(textConsole);
                }

                // si entr�e ou tab sont rentr�es
                if (actionPerformed(arg0)) {
                    
                    // on lance le calcul si possible
                    fenetre.getActions().actionValider();

                    // on vide la console
                    fenetre.getConsole().setText("");
                }

            }  
        });
    }

    /** 
     * Permet de choisir quelles touches permettent d'�crire dans le tableur
     * @param arg0
     * @return vrai si la touche est correcte sinon faux
     */
    protected static boolean toucheCorrecte(KeyEvent arg0) {

        boolean bon = false;
        
        // code de notre touche
        int source = arg0.getKeyCode();

        if (source != KeyEvent.VK_UP 
                && source != KeyEvent.VK_DOWN
                && source != KeyEvent.VK_LEFT 
                && source != KeyEvent.VK_RIGHT
                && source != KeyEvent.VK_BACK_SPACE
                && source != KeyEvent.VK_ALT
                && source != KeyEvent.VK_CANCEL
                && source != KeyEvent.VK_CAPS_LOCK
                && source != KeyEvent.VK_CLEAR
                && source != KeyEvent.VK_CONTEXT_MENU
                && source != KeyEvent.VK_CONTROL
                && source != KeyEvent.VK_COPY
                && source != KeyEvent.VK_CUT
                && source != KeyEvent.VK_DELETE
                && source != KeyEvent.VK_ESCAPE
                && source != KeyEvent.VK_AGAIN
                && source != KeyEvent.VK_ALL_CANDIDATES
                && source != KeyEvent.VK_ALPHANUMERIC
                && source != KeyEvent.VK_ALT_GRAPH
                && source != KeyEvent.VK_AMPERSAND
                && source != KeyEvent.VK_BEGIN
                && source != KeyEvent.VK_BRACELEFT
                && source != KeyEvent.VK_BRACERIGHT
                && source != KeyEvent.VK_CLOSE_BRACKET
                && source != KeyEvent.VK_CODE_INPUT
                && source != KeyEvent.VK_COMPOSE
                && source != KeyEvent.VK_CONVERT
                && source != KeyEvent.VK_F1
                && source != KeyEvent.VK_F2
                && source != KeyEvent.VK_F3
                && source != KeyEvent.VK_F4
                && source != KeyEvent.VK_F5
                && source != KeyEvent.VK_F6
                && source != KeyEvent.VK_F7
                && source != KeyEvent.VK_F8
                && source != KeyEvent.VK_F9
                && source != KeyEvent.VK_F10
                && source != KeyEvent.VK_F11
                && source != KeyEvent.VK_F12
                && source != KeyEvent.VK_FINAL
                && source != KeyEvent.VK_FIND
                && source != KeyEvent.VK_FULL_WIDTH
                && source != KeyEvent.VK_HELP
                && source != KeyEvent.VK_HIRAGANA
                && source != KeyEvent.VK_HOME
                && source != KeyEvent.VK_NUM_LOCK
                && source != KeyEvent.VK_PAGE_DOWN
                && source != KeyEvent.VK_PAGE_UP
                && source != KeyEvent.VK_PASTE
                && source != KeyEvent.VK_PAUSE
                && source != KeyEvent.VK_PREVIOUS_CANDIDATE
                && source != KeyEvent.VK_PRINTSCREEN
                && source != KeyEvent.VK_ROMAN_CHARACTERS
                && source != KeyEvent.VK_SCROLL_LOCK
                && source != KeyEvent.VK_SHIFT
                && source != KeyEvent.VK_STOP
                && source != KeyEvent.VK_TAB
                && source != KeyEvent.VK_UNDO
                && source != KeyEvent.VK_WINDOWS
                ) {
            bon = true;
        }
        return bon;
    }

    /** 
     * Permet de savoir quand la touche espace ou la touche tab sont press�es
     * @param arg0 touche press�e
     * @return  vrai si entr�e ou tab sinon false
     */
    @SuppressWarnings("static-method")
    public boolean actionPerformed(KeyEvent arg0) {
        
        boolean ok = false; 
        
        // code de notre touche
        int source = arg0.getKeyCode();

        if (source == KeyEvent.VK_ENTER || source == KeyEvent.VK_TAB
                || source == KeyEvent.VK_UP || source == KeyEvent.VK_DOWN
                || source == KeyEvent.VK_LEFT || source == KeyEvent.VK_RIGHT) {
            ok = true;
        }

        return ok;
    }
}
