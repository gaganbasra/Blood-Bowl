/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbowl.gui.map;

import bloodbowl.model.players.Player;
import bloodbowl.model.teams.TeamType;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 * This is a utility class that provides a static method
 * imageScale, getThumbnailPlayerResource , getImagePlayerResource
 *
 *@author Philip
 *
 */
public class Utility {

    /**
     * scales the icon image on the specified size
     * 
     * @param src
     * @param w
     * @param h
     * @return
     */
    public static BufferedImage imageScale(ImageIcon src, int w, int h) {
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        // Fill background for scale to fit.
        g2.setBackground(UIManager.getColor("Panel.background"));
        g2.clearRect(0, 0, w, h);
        double xScale = (double) w / src.getIconWidth();
        double yScale = (double) h / src.getIconHeight();
        // Scaling options:
        // Scale to fit - image just fits in label.
        double scale = Math.max(xScale, yScale);
        // Scale to fill - image just fills label.
        //double scale = Math.max(xScale, yScale);
        int width = (int) (scale * src.getIconWidth());
        int height = (int) (scale * src.getIconHeight());
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        g2.drawImage(src.getImage(), x, y, width, height, null);
        g2.dispose();
        return dst;
    }

    /**
     * Gets the thumbnamil image of the player parameter
     *
     * @param player
     * @return String Image player path
     */
    public static String getThumbnailPlayerResource(Player player) {

        final int teamA = 1;
        final int teamB = 2;

        String imageResource = "";

        if (player != null) {
            switch (player.getTeamID()) {
                case teamA:
                    switch (player.getType()) {
                        case BLACKORC:
                            imageResource = "/bloodbowl/resources/TeamA/blackorc.gif";
                            break;
                        case BLITZER:
                            imageResource = "/bloodbowl/resources/TeamA/blitzer.gif";
                            break;
                        case CATCHER:
                            imageResource = "/bloodbowl/resources/TeamA/catcher.gif";
                            break;
                        case GOBLIN:
                            imageResource = "/bloodbowl/resources/TeamA/goblin.gif";
                            break;
                        case LINEMAN:
                            imageResource = "/bloodbowl/resources/TeamA/lineman.gif";
                            break;
                        case OGRE:
                            imageResource = "/bloodbowl/resources/TeamA/ogre.gif";
                            break;
                        case THROWER:
                            imageResource = "/bloodbowl/resources/TeamA/thrower.gif";
                            break;
                        case TROLL:
                            imageResource = "/bloodbowl/resources/TeamA/troll.gif";
                            break;
                    }
                    break;
                case teamB:
                    switch (player.getType()) {
                        case BLACKORC:
                            imageResource = "/bloodbowl/resources/TeamB/blackorc.gif";
                            break;
                        case BLITZER:
                            imageResource = "/bloodbowl/resources/TeamB/blitzer.gif";
                            break;
                        case CATCHER:
                            imageResource = "/bloodbowl/resources/TeamB/catcher.gif";
                            break;
                        case GOBLIN:
                            imageResource = "/bloodbowl/resources/TeamB/goblin.gif";
                            break;
                        case LINEMAN:
                            imageResource = "/bloodbowl/resources/TeamB/lineman.gif";
                            break;
                        case OGRE:
                            imageResource = "/bloodbowl/resources/TeamB/ogre.gif";
                            break;
                        case THROWER:
                            imageResource = "/bloodbowl/resources/TeamB/thrower.gif";
                            break;
                        case TROLL:
                            imageResource = "/bloodbowl/resources/TeamB/troll.gif";
                            break;
                    }
                    break;

            }
        }

        return imageResource;
    }

    /**
     * Gets the image of the player parameter
     *
     * @param player
     * @return String Image player path
     */
    public static String getImagePlayerResource(Player player, TeamType playerTeamType) {

        String imageResource = "";

        if (player != null) {

            switch (playerTeamType) {
                case ELF_TYPE:
                    switch (player.getType()) {
                        case BLITZER:
                            imageResource = "/bloodbowl/resources/elves/eBlitzer.gif";
                            break;
                        case CATCHER:
                            imageResource = "/bloodbowl/resources/elves/eCatcher.gif";
                            break;
                        case LINEMAN:
                            imageResource = "/bloodbowl/resources/elves/eLineman.gif";
                            break;
                        case THROWER:
                            imageResource = "/bloodbowl/resources/elves/eThrower.gif";
                            break;
                    }
                    break;
                case HUMAN_TYPE:
                    switch (player.getType()) {
                        case BLITZER:
                            imageResource = "/bloodbowl/resources/humans/hBlitzer.gif";
                            break;
                        case CATCHER:
                            imageResource = "/bloodbowl/resources/humans/hCatcher.gif";
                            break;
                        case LINEMAN:
                            imageResource = "/bloodbowl/resources/humans/hLineman.gif";
                            break;
                        case OGRE:
                            imageResource = "/bloodbowl/resources/humans/hOgre.gif";
                            break;
                        case THROWER:
                            imageResource = "/bloodbowl/resources/humans/hThrower.gif";
                            break;
                    }
                    break;

                case ORC_TYPE:
                    switch (player.getType()) {
                        case BLACKORC:
                            imageResource = "/bloodbowl/resources/Orcs/oblack.gif";
                            break;
                        case BLITZER:
                            imageResource = "/bloodbowl/resources/Orcs/oBlitzer.gif";
                            break;
                        case GOBLIN:
                            imageResource = "/bloodbowl/resources/Orcs/oGob.gif";
                            break;
                        case LINEMAN:
                            imageResource = "/bloodbowl/resources/Orcs/oLineman.gif";
                            break;
                        case THROWER:
                            imageResource = "/bloodbowl/resources/Orcs/oThrower.gif";
                            break;
                        case TROLL:
                            imageResource = "/bloodbowl/resources/Orcs/oTroll.gif";
                            break;
                    }
                    break;

            }
        }
        return imageResource;
    }

    /**
     * Method to get the ball image resources
     * @return String
     */
    public static String getGameBallResource() {
        String imageResource = "/bloodbowl/resources/ball.gif";

        return imageResource;
    }

    /**
     * Method to get the ball possessor image resources on the pitch
     *
     * @return
     */
    public static String getBallPossessor() {
        String imageResource = "/bloodbowl/resources/possessor.gif";

        return imageResource;
    }


     /**
     * Method to get the image resource of the stunned player
     *
     * @return
     */
    public static String getStunnedResource() {
        String imageResource = "/bloodbowl/resources/stunned.gif";

        return imageResource;
    }

    
    /**
     * helper method to get custom game font
     *
     * @throws FontFormatException
     * @throws IOException
     */
    public static void getBloodBowlFont() throws FontFormatException, IOException {

        Utility u = new Utility();

        Font ttfLucida = u.bloodBowlFont();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(ttfLucida);

    }

    /**
     * Helper method to create game font
     *
     * @return Font
     * @throws FontFormatException
     * @throws IOException
     */
    private Font bloodBowlFont() throws FontFormatException, IOException {
        String fontFileName = "/bloodbowl/resources/jblack.ttf";
        return Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(fontFileName));
    }
}
