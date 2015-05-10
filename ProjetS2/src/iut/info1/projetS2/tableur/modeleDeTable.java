/*
 * modeleDeTable.java				6 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/** TODO commenter la responsabilité de cette classe
 * @author Mickaël
 * @version 0.1
 */
public class modeleDeTable extends AbstractTableModel {
    
    /** Liste de toutes nos en-têtes */
    private String[] nomColonnes = {"A","B","C","D","E","F","G","H","I","J","K",
                                    "L","M","N","O","P","Q","R","S","T","U","V",
                                    "X","Y","Z"};
    /** Tableau stockant nos données */
    public Object[][] donnees = new Object[20][26];

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
        
        return nomColonnes.length;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        
        return donnees.length;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int ligne, int colonne) {

        return donnees[ligne][colonne];
    }
    
    public boolean isCellEditable(int ligne, int colonne) {
        
        return true;
    }
    /** TODO commenter le rôle de la méthode
     * @param value
     * @param row
     * @param col
     */
    public void setValueAt(Object value, int row, int col) {
        donnees[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    /**
     * @return the nomColonnes
     */
    public String[] getNomColonnes() {
        return nomColonnes;
    }

    /**
     * @return the donnees
     */
    public Object getDonnees() {
        return donnees;
    }
 
    
}
