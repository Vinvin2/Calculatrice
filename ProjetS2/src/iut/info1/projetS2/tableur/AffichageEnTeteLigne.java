/*
 * AffichageEnTeteLigne.java               5 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

/** 
 * Permet l'ajout d'en-t�tes pour les lignes
 * @author Micka�l
 * @version 0.1
 */

@SuppressWarnings({ "serial", "rawtypes" })
class AffichageEnTeteLigne extends JLabel implements ListCellRenderer {

    /** 
     * Initilisation de nos en-t�tes
     * @param table notre tableur
     */
    AffichageEnTeteLigne(JTable table) {
        JTableHeader header = table.getTableHeader();
        
        // On rend la case opaque
        setOpaque(true);
        
        // Choix de la bordure de nos en-t�tes
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        
        // On centre nos nombres
        setHorizontalAlignment(CENTER);
        
        // Choix de la couleur d'�criture
        setForeground(header.getForeground());
        
        // Choix de la couleur de fond
        setBackground(header.getBackground());
        
        // D�claration d'une police et d'une taille
//        Font f = new Font("Serif", Font.PLAIN, 20);
        
        // Choix de la police d'en-t�tes
        setFont(header.getFont());
    }

    /**
     * Retourne le Component qui a �t� configur� pour afficher la valeur
     * sp�cifi�
     * @param list la JList � dessiner
     * @param value La valeur retourn�e par list.getModel().getElementAt(index)
     * @param index l'index de cellule
     * @param isSelected vrai si la cellule voulu est s�lectionn�e
     * @param cellHasFocus vrai si notre curseur est sur la cellule
     * @return 0
     */
    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }