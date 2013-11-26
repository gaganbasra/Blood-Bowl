/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.gui.map;

import bloodbowl.engine.Game;
import bloodbowl.model.map.DugoutCell;
import bloodbowl.model.players.Player;
import bloodbowl.model.teams.Team;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

/**This class implements the Player Transfer handler class
 *
 * It overrides the getSourceActions, importData and canImport methods
 *
 * @author Philip
 */
public class PlayersTransferHandler extends TransferHandler {

    private JTable source;
    private PitchGrid target;
    private Team team;
    private int row;
    private int column;
    private Player player;
    private Game gameEngine;

    /**
     * 
     * @param source: The dugout grid where the player is dragged from
     * @param team: The team the transfer players belong to
     */
    PlayersTransferHandler(JTable source, Team team, Game engine) {
        super();

        this.source = source;
        this.team = team;

        gameEngine = engine;

    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    /**
     * This method implements the drag and drop player
     *
     * @param info
     * @return Boolean
     */
    @Override
    public boolean importData(TransferSupport info) {

        row = source.getSelectedRow();
        column = source.getSelectedColumn();
        target = (PitchGrid) info.getComponent();

        JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();

        if (team.getID() == 1) {
            //get the player selected in the dugout
            player = gameEngine.getPlayerAtDugoutA(row, column);
            //set the selected player in the game
            gameEngine.setSelectedPlayer(player);
            //drag player selected to the pitch
            if (gameEngine.placePlayerAtPitchCell(dl.getRow(), dl.getColumn())) {
                //remove player from the dugout
                DugoutCell dcell = (DugoutCell) source.getValueAt(row, column);
                dcell.resetPlayer();
            }

        } else {
            //get the player selected in the dugout
            player = gameEngine.getPlayerAtDugoutB(row, column);
            //set the selected player in the game
            gameEngine.setSelectedPlayer(player);
            //drag player selected to the pitch
            if (gameEngine.placePlayerAtPitchCell(dl.getRow(), dl.getColumn())) {
                //remove player from the dugout
                DugoutCell dcell = (DugoutCell) source.getValueAt(row, column);
                dcell.resetPlayer();
            }
        }

        //refresh the dugout and the pitch grids
        source.repaint();
        target.repaint();
         
        return super.importData(info);
    }

    /**
     * Test if a player exist in the selected dugout cell and
     * can be drag and drop
     * @param support
     * @return Boolean
     */
    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {

        row = source.getSelectedRow();
        column = source.getSelectedColumn();
        Player p = getPlayerAt(row, column);

        if (p == null) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * This method get the player at the specified location 
     *
     * @param row player location row
     * @param column player location column
     * @return Player
     */
    public Player getPlayerAt(int row, int column) {
        for (Player p : team.getPlayers()) {
            if (p.getLocation().getRow() == row && p.getLocation().getColumn() == column) {
                player = p;
                break;
            }
        }
        return player;
    }
}
