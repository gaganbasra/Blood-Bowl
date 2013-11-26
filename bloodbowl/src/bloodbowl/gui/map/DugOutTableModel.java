/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.gui.map;

import bloodbowl.model.map.Cell;
import bloodbowl.model.map.DugoutGrid;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *  This class extends the AbstractTableModel class.
 *  It's used by the DugOutGrid
 * 
 *  @see DugOutGrid
 *
 * @author Philip
 */
public class DugOutTableModel extends AbstractTableModel {

    private static final int ROWS = 12;
    private static final int COLS = 4;
    private static final String[] columns = new String[COLS];
    private DugoutGrid dugoutGrid = null;

    //arryalist of players in each category of the dugout
    public DugOutTableModel() {
        dugoutGrid = new DugoutGrid();
    }

    /**
     * Method to get the total number of columns
     * @return
     */
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Method to set the cell editable
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    /**
     * Method to set the value of the cell
     *
     * @param cellplayer
     * @param row
     * @param col
     */
    @Override
    public void setValueAt(Object player, int row, int col) {
        //cells[row][col].setPlayer((Player) player);
        fireTableCellUpdated(row, col);
        fireTableDataChanged();
    }

    /**
     * Method to get the column name
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        return String.valueOf(column + 1);
    }

    /**
     * Method to get the total number of rows
     * @return
     */
    public int getRowCount() {
        return ROWS;
    }

    /**
     * Method to get the column class
     *
     * @param column
     * @return
     */
    @Override
    public Class<?> getColumnClass(int column) {
        return Icon.class;
    }

    /**
     * Method to get the value of the cell at the row and column specified
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public Cell getValueAt(int rowIndex, int columnIndex) {
        return dugoutGrid.getCell(rowIndex, columnIndex);
    }
}
