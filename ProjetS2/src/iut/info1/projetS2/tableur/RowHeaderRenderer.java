/*
 * RowHeaderRenderer.java				5 mai 2015
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
class RowHeaderRenderer extends JLabel implements ListCellRenderer {

    /** 
     * Initilisation de nos en-t�tes
     * @param table notre tableur
     */
    RowHeaderRenderer(JTable table) {
        JTableHeader header = table.getTableHeader();
        
        // On rend la case opaque
        setOpaque(true);
        
        // 
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        
        // On centre nos nombres
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }

    /** TODO commenter le r�le de la m�thode
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return 0
     */
    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }