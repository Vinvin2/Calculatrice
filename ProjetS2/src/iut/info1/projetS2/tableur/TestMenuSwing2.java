/*
 * TestMenuSwing2.java				20 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.xml.soap.Text;

/**
 * TODO commenter la responsabilité de cette classe
 * @author Mickaël
 * @version 0.1
 */
public class TestMenuSwing2 extends JMenuBar {

    /** Menu s'affichant lors du clic droit */
    public static JPopupMenu popup;

    /** Sous-menu de notre pop-up */
    private static JMenuItem copier;

    /** Sous-menu de notre pop-up */
    private static JMenuItem couper;
    
    /** Sous-menu de notre pop-up */
    private static JMenuItem coller;
    
    /**
     * Méthode principale
     */
    public TestMenuSwing2() {

        // Listener générique qui affiche l'action du menu utilisé
        ActionListener afficherMenuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Elément de menu ["
                        + event.getActionCommand() + "] utilisé.");
            }
        };
        
        
        ActionListener copierMenuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Elément de menu ["
                        + event.getActionCommand() + "] utilisé.");
                
                }
        };
        
        ActionListener collerMenuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("Elément de menu ["
                        + event.getActionCommand() + "] utilisé.");
            }
        };

        // On ajoute tous les sous-menu à notre menu popup et on leur 
        // assigne des actions
        popup = new JPopupMenu();
        copier = new JMenuItem("Copier");
        copier.addActionListener(copierMenuListener);
        popup.add(copier);
        couper = new JMenuItem("Couper");
        couper.addActionListener(afficherMenuListener);
        popup.add(couper);
        coller = new JMenuItem("Coller");
        coller.addActionListener(collerMenuListener);
        popup.add(coller);
    }

    public void processMouseEvent(MouseEvent e) {

    }

    /**
     * TODO commenter le rôle de la méthode
     * @param s
     */
    public static void main(String s[]) {

        
        final JFrame frame = new JFrame("Test de menu divers");

        final JTextField texte = new JTextField();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final TestMenuSwing2 tms = new TestMenuSwing2();

        frame.add(texte);

        texte.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {

                    if (getSelectedText() == null) {
                        copier.setEnabled(false);
                        couper.setEnabled(false);
                    } else {
                        copier.setEnabled(true);
                        couper.setEnabled(true);
                    }
                    
//                    Clipboard c = getToolkit().getSystemClipboard();
//
//                    Transferable t = c.getContents(this);
//
//                    if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//
//                        String s;
//
//                        try {
//                            s = (String) t.getTransferData(DataFlavor.stringFlavor);
//                            if (s.equals("")) {
//                                coller.setEnabled(false);
//                            } else {
//                                coller.setEnabled(true);
//                            }
//                        } catch (UnsupportedFlavorException | IOException ex) {
//                            Logger.getLogger(TextInputField.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                    } else {
//                        coller.setEnabled(false);
//                    }

                    popup.setLocation(e.getXOnScreen(), e.getYOnScreen());
                    popup.setVisible(true);
                } else {
                    Object obj = e.getSource();
                    if (obj instanceof JMenuItem) {
                        JMenuItem menuItem = (JMenuItem) obj;
                        if (coller == menuItem) {
                            coller();    
                        } else if (couper == menuItem) {
                            couper();
                        } else if (copier == menuItem) {
                            copier();
                        }
                    }

                    afficherPopup(e);
                }
            }

            private void copier() {
                // TODO Auto-generated method stub
                
            }

            private void couper() {
                // TODO Auto-generated method stub
                
            }

            private void coller() {
                // TODO Auto-generated method stub
                
            }

            private Object getText() {
                // TODO Auto-generated method stub
                return null;
            }

            private Object getSelectedText() {
                // TODO Auto-generated method stub
                return null;
            }

            /**
             * On affiche notre menu pop-up
             * @param e evenement
             */
            private void afficherPopup(MouseEvent e) {

                // Si on a bien l'action d'afficher un pop-up
                if (e.isPopupTrigger()) {

                    // On affiche le pop-up là où est notre pointeur
                    TestMenuSwing2.popup.show(texte, e.getX(), e.getY());

                }

            }

        });

        frame.setMinimumSize(new Dimension(250, 200));

        frame.pack();

        frame.setVisible(true);

    }

}
