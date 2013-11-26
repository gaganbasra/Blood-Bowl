/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.gui.map;

import bloodbowl.engine.Game;
import bloodbowl.model.map.PitchCell;
import bloodbowl.model.objects.Ball;
import bloodbowl.model.players.Player;
import bloodbowl.model.players.PlayerStates;
import java.awt.Color;
import java.awt.Component;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**This class extends the JTable to provide the custom display for the game pitch
 * It overrides the preparedRenderrer method
 *
 * @author Philip
 */
public class PitchGrid extends JTable implements DragGestureListener, Observer {

    private JLabel lbl;
    private Player player;
    private PitchCell pitchCell;
    private Game gameEngine = null;
    private String toolTip;

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        if (toolTip != null) {
            return toolTip;
        }
        return super.getToolTipText(event);
    }

    public PitchGrid() {

        super.setBorder(BorderFactory.createLineBorder(Color.blue, 4));

        gameEngine = Game.getInstance();
    }

    public PitchGrid(Game engine) {
        super.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
        this.gameEngine = engine;
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

        lbl = new JLabel();

        if ((column > 0 && column < 25)) {
            if (row < 4) {
                cell.setBackground(new Color(45, 79, 5));
            } else if (row > 10) {
                cell.setBackground(new Color(45, 79, 5));
            } else {
                cell.setBackground(new Color(100, 177, 9));
            }
        } else {
            cell.setBackground(new Color(100, 177, 9));
        }

        if ((column >= 12 && column <= 13)) {
            if (row >= 4 && row <= 10) {
                cell.setBackground(new Color(219, 239, 194));
            }
        }

        if ((column == 0 || column == 25)) {
            cell.setBackground(new Color(159, 223, 83));
        }


        pitchCell = (PitchCell) this.getValueAt(row, column);
        player = gameEngine.getPlayerAtPitch(row, column);

        //player with ball
        if (pitchCell.hasPlayer() && pitchCell.hasBall()) {            
            lbl = (JLabel) this.getCellRenderer(row, column);
            lbl.setIcon(new ImageIcon(getClass().getResource(Utility.getBallPossessor())));
            return lbl;

            //pitch cell has player and player is stunned
        } else if (pitchCell.hasPlayer()  && player != null && player.getState() == PlayerStates.STUNNED) {            
            lbl = (JLabel) this.getCellRenderer(row, column);
            lbl.setIcon(new ImageIcon(getClass().getResource(Utility.getStunnedResource())));
            return lbl;
            //pitch cell has player
        } else if (pitchCell.hasPlayer() && player!=null && player.getState() != PlayerStates.STUNNED) {
            player = gameEngine.getPlayerAtPitch(row, column);

            lbl = (JLabel) this.getCellRenderer(row, column);
            lbl.setIcon(new ImageIcon(getClass().getResource(Utility.getThumbnailPlayerResource(player))));
            return lbl;
            //pitch cell has ball 
        } else if (pitchCell.hasBall() && !gameEngine.isTouchdownFlag()) {
            lbl = (JLabel) this.getCellRenderer(pitchCell.getRow(), pitchCell.getColumn());
            lbl.setIcon(new ImageIcon(getClass().getResource(Utility.getGameBallResource())));

            return lbl;
        } else {
            return cell;
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

            if (player.getActive() == true) {

                if (player.getLocation() instanceof PitchCell) {
                    PitchCell cell = (PitchCell) player.getLocation();

                    pitchCell = (PitchCell) this.getValueAt(cell.getRow(), cell.getColumn());

                    pitchCell.setPlayer();
                }

            }
        } else if ((o instanceof Ball)) {

            int row = gameEngine.getPitch().pitch.length;
            int column = gameEngine.getPitch().pitch[row - 1].length;
            PitchCell ballCell;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    ballCell = (PitchCell) this.getValueAt(i, j);

                    if (ballCell.hasBall()) {
                        ballCell.resetBall();
                        this.repaint();
                    }
                }
            }

            Ball ball = (Ball) o;
            PitchCell cell = (PitchCell) ball.getLocation();

            pitchCell = (PitchCell) this.getValueAt(cell.getRow(), cell.getColumn());
            pitchCell.setBall();

            if (gameEngine.isTouchdownFlag()) {
                pitchCell.resetBall();
            }

        }

        this.repaint();
    }

    /**
     * 
     * @param dge
     */
    public void dragGestureRecognized(DragGestureEvent dge) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
