/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.gui.map;

import bloodbowl.model.map.Cell;
import bloodbowl.model.map.DugoutCell;
import bloodbowl.model.players.Player;
import bloodbowl.model.teams.Team;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**This class extends the JTable to provide the custom display for the game dugout
 * It overrides the preparedRenderrer method
 *
 * @author Philip
 */
public class DugOutGrid extends JTable implements DragGestureListener, Observer {

    private ImageIcon icon;
    private DugoutCell dugoutCell;
    private Player player;
    private Team team;
    private JLabel lbl;

    public DugOutGrid(Team team) {
        super.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));

        this.setDragEnabled(true);

        this.team = team;
    }

    /**
     * This overridden method provides a custom display of the rows and columns in the JTable class
     * @param renderer
     * @param row
     * @param column
     * @return
     */
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component cell = super.prepareRenderer(renderer, row, column);

        if (row < 4) {
            cell.setBackground(Color.red);
        } else if (row >= 4 && row < 7) {
            cell.setBackground(new Color(65, 44, 239));
        } else if (row >= 8) {
            cell.setBackground(new Color(237, 239, 44));
        }

        DugoutCell playerDugoutCell = (DugoutCell) this.getValueAt(row, column);

        player = team.getDugoutPlayerAt(playerDugoutCell);

        if (player != null && playerDugoutCell.hasPlayer()) {
            lbl = (JLabel) this.getCellRenderer(row, column);
            lbl.setIcon(new ImageIcon(getClass().getResource(Utility.getThumbnailPlayerResource(player))));          
            return lbl;
        } else {
            return cell;
        }
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        Cursor cursor = null;
        int row = this.getSelectedRow();
        int column = this.getSelectedColumn();
        Cell cell = (Cell) this.getModel().getValueAt(row, column);

        if (dge.getDragAction() == DnDConstants.ACTION_MOVE) {
            cursor = DragSource.DefaultMoveDrop;
        }
    }

    /**
     * This method is used by the Observer to update the cell
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {

        if (o instanceof Player) {
            player = (Player) o;
            if (player.getActive() == false) {
                if (player.getLocation() instanceof DugoutCell) {
                    DugoutCell cell = (DugoutCell) player.getLocation();

                    dugoutCell = (DugoutCell) this.getValueAt(cell.getRow(), cell.getColumn());
                    dugoutCell.setPlayer();                                        
                }
            }
        }
        this.repaint();
    }
}
