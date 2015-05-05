/*
 * Lanceur.java				2 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import javax.swing.SwingUtilities;

/**
 * @author Mickaël
 * @version 0.1
 */
public class Lanceur {
    
    /**
     * lance le tableur
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                //On crée une nouvelle instance de notre JDialog
                Tableur fenetre = new Tableur();
               
                
            }
        });
    }
}
