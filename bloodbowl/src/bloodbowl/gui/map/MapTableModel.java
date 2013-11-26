/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.gui.map;

import bloodbowl.model.map.Cell;
import bloodbowl.model.map.PitchGrid;
import bloodbowl.model.players.Player;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *  This class extends the AbstractTableModel class.
 *  It's used by the PitchGrid
 *
 *  @see PitchGrid
 *
 * @author Philip
 */
public class MapTableModel extends AbstractTableModel {

    private static final int ROWS = 15;
    private static final int COLS = 26;

    private static final String[] columns = new String[COLS];    
    private PitchGrid pitch=null;
    private JTable table;

    public MapTableModel(JTable table) {
        this.table = table;

        pitch=new PitchGrid();
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
    public void setValueAt(Object cellplayer, int row, int col) {
        Player player=(Player)cellplayer;
        pitch.getCell(row, col).setPlayer();
        fireTableCellUpdated(row, col);
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
        return pitch.getCell(rowIndex,columnIndex);
    }
}
