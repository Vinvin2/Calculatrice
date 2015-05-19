/*
 * modeleDeTable.java               6 mai 2015
 * IUT INFO1 2014-2015 
 */
package iut.info1.projetS2.tableur;

import javax.swing.table.AbstractTableModel;

/** TODO commenter la responsabilité de cette classe
 * @author Mickaël
 * @version 0.1
 */
@SuppressWarnings("serial")
public class ModeleDeTable extends AbstractTableModel {
    
    /** Liste de toutes nos en-têtes */
    private String[] nomColonnes = {"A","B","C","D","E","F","G","H","I","J","K",
                                    "L","M","N","O","P","Q","R","S","T","U","V",
                                    "X","Y","Z"};
    /** Tableau stockant nos données */
    public static Object[][] donnees = new Object[20][26];

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
    public static Object[][] getDonnees() {
        return donnees;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#fireTableDataChanged()
     */
    @Override
    public void fireTableDataChanged() {
        // TODO Auto-generated method stub
        super.fireTableDataChanged();
    }

    /**
     * @param donnees the donnees to set
     */
    public static void setDonnees(Object[][] donnees) {
        ModeleDeTable.donnees = donnees;
/*        fireTableDataChanged();
        for (int i = 0; i < donnees.length; i++) {
            for (int j = 0; j < donnees[i].length; j++) {
                setValueAt(donnees, i, j);
            }
        }*/
    }    
}
