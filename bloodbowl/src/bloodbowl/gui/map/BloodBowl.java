/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BloodBowl.java
 *
 * Created on Jan 4, 2011, 7:17:47 PM
 */
package bloodbowl.gui.map;

import bloodbowl.engine.Game;
import bloodbowl.gui.dialog.GameStart;
import bloodbowl.gui.dialog.SaveGame;
import bloodbowl.gui.dialog.TeamEditor;
import bloodbowl.model.managers.ActionManager;
import bloodbowl.model.managers.BlockDiceFaces;
import bloodbowl.model.managers.EventLogger;
import bloodbowl.model.managers.GameReferee;
import bloodbowl.model.map.PitchCell;
import bloodbowl.model.objects.Ball;
import bloodbowl.model.objects.Coin;
import bloodbowl.model.players.Player;
import bloodbowl.model.players.Skills;
import bloodbowl.model.teams.Team;
import bloodbowl.model.teams.TeamType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ToolTipManager;
import javax.swing.table.TableColumn;

/**
 * Main game layout
 *
 * @author Philip
 */
public class BloodBowl extends javax.swing.JFrame {

    private Team team1;
    private Team team2;
    private Game gameEngine;
    private GameStart splashScreen;
    private boolean tossCoinsDone;
    private Ball ball;
    private GameReferee referee;
    private ActionManager actionManager;
    private boolean isValidTeamAPlacement = false;
    private boolean isValidTeamBPlacement = false;
    private boolean isValidationPassed = false;
    private boolean isResumedGame = false;
    private boolean isGameStart = false;
    private boolean isTouchedDown = false;
    private String message;

    /**
     * Creates new form BloodBowl
     * initialise all the necessary game components
     * 
     */
    public BloodBowl() throws FontFormatException, IOException {

        initComponents();
        //tool tip to display on pitch
        ToolTipManager.sharedInstance().setInitialDelay(0);

        gameEngine = Game.getInstance();
        referee = new GameReferee();
        actionManager = new ActionManager(gameEngine);
        ball = gameEngine.getGameBall();

        setUpMap();

        setUpTeam1DugOut();
        setUpTeam2DugOut();

        initialiseTeams();

        startGamePlay(false);

        this.setLocationRelativeTo(getRootPane());

        EventLogger.setObserver((Observer) jTxtAreaLog);

        jTxtAreaLog.setCaretPosition(jTxtAreaLog.getText().length());
        //applyGameFont();
    }

    /**
     * Creates new form BloodBowl
     * initialise all the necessary game components
     *
     */
    public BloodBowl(Game gameEngine) throws FontFormatException, IOException {

        initComponents();

        this.gameEngine = gameEngine;
        referee = new GameReferee(gameEngine);
        actionManager = new ActionManager(gameEngine);
        ball = gameEngine.getGameBall();

        ToolTipManager.sharedInstance().setInitialDelay(0);

        setUpAndRestoreMap();

        setUpTeam1DugOut();
        setUpTeam2DugOut();

        restoreTeams();

        this.setLocationRelativeTo(getRootPane());
        //applyGameFont();

        startGamePlay(true);
        isResumedGame = true;
        setCoinTossMenu(true);
        gameEngine.setSelectedPlayer(null);

        ball.addObserver(tblGamePitch);
        ball.setLocation(ball.getLocation().getRow(), ball.getLocation().getColumn());
        ball.deleteObservers();


        EventLogger.setObserver((Observer) jTxtAreaLog);
        jTxtAreaLog.setCaretPosition(jTxtAreaLog.getText().length());
        showCurrentTeam();
    }

    /**
     * apply JSL Blackletter font for windows OS
     *
     * @throws IOException
     * @throws FontFormatException
     */
    private void applyGameFont() throws IOException, FontFormatException {
        Utility.getBloodBowlFont();
        this.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblSkills.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jlblActions.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblCurrentTeam.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblReferee.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblPlayerStats.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblCommentaries.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblCurrent.setFont(new Font("JSL Blackletter", Font.BOLD, 18));

        jLblTeam1.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblTeamATurnCount.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblPointsTeamA.setFont(new Font("JSL Blackletter", Font.BOLD, 18));

        jLblTeam2.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblTeamBTurnCount.setFont(new Font("JSL Blackletter", Font.BOLD, 18));
        jLblPointsTeamB.setFont(new Font("JSL Blackletter", Font.BOLD, 18));

    }

    /**
     * helper method to enable menu options
     *
     * @param flag
     */
    private void setCoinTossMenu(boolean flag) {
        if (flag) {
            tossCoinsDone = true;
            jMenuTossCoin.setEnabled(false);
        } else {
            tossCoinsDone = false;
            jMenuTossCoin.setEnabled(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPnlBackground = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLblTeam1 = new javax.swing.JLabel();
        jLblPointsTeamA = new javax.swing.JLabel();
        jLblTeamATurnCount = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLblPointsTeamB = new javax.swing.JLabel();
        jLblTeam2 = new javax.swing.JLabel();
        jLblTeamBTurnCount = new javax.swing.JLabel();
        jPnlLog = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLblReferee = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtAreaLog = new JEventLog();
        jLblCommentaries = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstActions = new javax.swing.JList();
        jPanel9 = new javax.swing.JPanel();
        jBtnEndTurn = new javax.swing.JButton();
        jLblCurrent = new javax.swing.JLabel();
        jLblCurrentTeam = new javax.swing.JLabel();
        jBtnValidateTeam = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLblIsActive = new javax.swing.JLabel();
        jLblMA = new javax.swing.JLabel();
        jLblAG = new javax.swing.JLabel();
        jLblAV = new javax.swing.JLabel();
        jLblST = new javax.swing.JLabel();
        jLblSkills = new javax.swing.JLabel();
        jLblPlayerImage = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLstPlayerSkills = new javax.swing.JList();
        jlblActions = new javax.swing.JLabel();
        jLblPlayerStats = new javax.swing.JLabel();
        jPnlDugOutTeamA = new javax.swing.JPanel();
        pnlBoard = new javax.swing.JPanel();
        jPnlDugOutTeamB = new javax.swing.JPanel();
        gameMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveGameMenuItem = new javax.swing.JMenuItem();
        quitGameMenuItem = new javax.swing.JMenuItem();
        editTeamA = new javax.swing.JMenuItem();
        editTeamB = new javax.swing.JMenuItem();
        jMenuTossCoin = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPnlBackground.setBackground(new java.awt.Color(102, 102, 102));
        jPnlBackground.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 0, 0)));
        jPnlBackground.setName("jPnlBackground"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setName("jPanel4"); // NOI18N

        jPanel3.setBackground(java.awt.Color.lightGray);
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, java.awt.Color.orange));
        jPanel3.setName("jPanel3"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setName("jPanel2"); // NOI18N

        jLblTeam1.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblTeam1.setText("Team :");
        jLblTeam1.setName("jLblTeam1"); // NOI18N

        jLblPointsTeamA.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblPointsTeamA.setText("Points: O");
        jLblPointsTeamA.setName("jLblPointsTeamA"); // NOI18N

        jLblTeamATurnCount.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblTeamATurnCount.setText("Turn: 8/8");
        jLblTeamATurnCount.setName("jLblTeamATurnCount"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLblTeam1)
                    .add(jLblTeamATurnCount)
                    .add(jLblPointsTeamA))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(50, 50, 50)
                .add(jLblTeam1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLblPointsTeamA)
                .add(12, 12, 12)
                .add(jLblTeamATurnCount)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setName("jPanel5"); // NOI18N

        jLblPointsTeamB.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblPointsTeamB.setText("Points: O");
        jLblPointsTeamB.setName("jLblPointsTeamB"); // NOI18N

        jLblTeam2.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblTeam2.setText("Team :");
        jLblTeam2.setName("jLblTeam2"); // NOI18N

        jLblTeamBTurnCount.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblTeamBTurnCount.setText("Turn : 8/8");
        jLblTeamBTurnCount.setName("jLblTeamBTurnCount"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLblTeam2)
                    .add(jLblTeamBTurnCount)
                    .add(jLblPointsTeamB))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(56, 56, 56)
                .add(jLblTeam2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLblPointsTeamB)
                .add(12, 12, 12)
                .add(jLblTeamBTurnCount)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0))
        );

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(0, 0, 0))
        );

        jPnlLog.setBackground(new java.awt.Color(0, 0, 255));
        jPnlLog.setBorder(new javax.swing.border.MatteBorder(null));
        jPnlLog.setName("jPnlLog"); // NOI18N
        jPnlLog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(0, 0, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setFocusTraversalPolicyProvider(true);
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(200, 200));

        jLblReferee.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblReferee.setForeground(new java.awt.Color(255, 255, 255));
        jLblReferee.setText("Referee");
        jLblReferee.setName("jLblReferee"); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bloodbowl/resources/referee.png"))); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLblReferee)
                    .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(28, 28, 28)
                .add(jLblReferee)
                .add(31, 31, 31)
                .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        jPnlLog.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 142, 298));

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTxtAreaLog.setColumns(20);
        jTxtAreaLog.setEditable(false);
        jTxtAreaLog.setLineWrap(true);
        jTxtAreaLog.setRows(5);
        jTxtAreaLog.setWrapStyleWord(true);
        jTxtAreaLog.setName("jTxtAreaLog"); // NOI18N
        jScrollPane1.setViewportView(jTxtAreaLog);

        jLblCommentaries.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblCommentaries.setForeground(new java.awt.Color(255, 255, 255));
        jLblCommentaries.setText("Commentaries");
        jLblCommentaries.setName("jLblCommentaries"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(63, 63, 63)
                        .add(jLblCommentaries))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLblCommentaries)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
        );

        jPnlLog.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 250, 300));

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setName("jPanel8"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jLstActions.setFont(new java.awt.Font("Lucida Blackletter", 1, 14));
        jLstActions.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Move", "Pass", "Throw", "Catch", "Block" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jLstActions.setName("jLstActions"); // NOI18N
        jLstActions.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jLstActionsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jLstActions);

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

        jPnlLog.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 52, 170, 250));

        jPanel9.setBackground(new java.awt.Color(0, 0, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setName("jPanel9"); // NOI18N

        jBtnEndTurn.setText("End Turn");
        jBtnEndTurn.setName("jBtnEndTurn"); // NOI18N
        jBtnEndTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEndTurnActionPerformed(evt);
            }
        });

        jLblCurrent.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblCurrent.setForeground(new java.awt.Color(255, 255, 255));
        jLblCurrent.setText("Current Team");
        jLblCurrent.setName("jLblCurrent"); // NOI18N

        jLblCurrentTeam.setFont(new java.awt.Font("Lucida Grande", 0, 14));
        jLblCurrentTeam.setForeground(new java.awt.Color(255, 255, 255));
        jLblCurrentTeam.setText("--");
        jLblCurrentTeam.setName("jLblCurrentTeam"); // NOI18N

        jBtnValidateTeam.setText("Validate Team");
        jBtnValidateTeam.setName("jBtnValidateTeam"); // NOI18N
        jBtnValidateTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnValidateTeamActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jBtnEndTurn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                    .add(jPanel9Layout.createSequentialGroup()
                        .add(28, 28, 28)
                        .add(jLblCurrent))
                    .add(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLblCurrentTeam))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(59, Short.MAX_VALUE)
                        .add(jBtnValidateTeam)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(72, 72, 72)
                .add(jBtnEndTurn)
                .add(18, 18, 18)
                .add(jLblCurrent)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLblCurrentTeam)
                .add(47, 47, 47)
                .add(jBtnValidateTeam)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPnlLog.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 170, 300));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setName("jPanel6"); // NOI18N

        jLblIsActive.setFont(new java.awt.Font("Lucida Blackletter", 0, 14));
        jLblIsActive.setText("Is Active:");
        jLblIsActive.setName("jLblIsActive"); // NOI18N

        jLblMA.setFont(new java.awt.Font("Lucida Blackletter", 0, 14));
        jLblMA.setText("MA:");
        jLblMA.setName("jLblMA"); // NOI18N

        jLblAG.setFont(new java.awt.Font("Lucida Blackletter", 0, 14));
        jLblAG.setText("AG:");
        jLblAG.setName("jLblAG"); // NOI18N

        jLblAV.setFont(new java.awt.Font("Lucida Blackletter", 0, 14));
        jLblAV.setText("AV:");
        jLblAV.setName("jLblAV"); // NOI18N

        jLblST.setFont(new java.awt.Font("Lucida Blackletter", 0, 14));
        jLblST.setText("ST:");
        jLblST.setName("jLblST"); // NOI18N

        jLblSkills.setFont(new java.awt.Font("Lucida Blackletter", 1, 14));
        jLblSkills.setText("Player Skills");
        jLblSkills.setName("jLblSkills"); // NOI18N

        jLblPlayerImage.setName("jLblPlayerImage"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jLstPlayerSkills.setFont(new java.awt.Font("Lucida Grande", 1, 12));
        jLstPlayerSkills.setName("jLstPlayerSkills"); // NOI18N
        jScrollPane3.setViewportView(jLstPlayerSkills);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLblAV)
                            .add(jLblMA)
                            .add(jLblAG)
                            .add(jLblST)))
                    .add(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLblIsActive)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 62, Short.MAX_VALUE)
                .add(jLblPlayerImage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(290, Short.MAX_VALUE)
                .add(jLblSkills)
                .add(56, 56, 56))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLblSkills)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jLblIsActive)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 23, Short.MAX_VALUE)
                        .add(jLblMA)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLblAG)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLblAV)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLblST)
                        .add(78, 78, 78))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLblPlayerImage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 171, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 197, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        jPnlLog.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 430, 250));

        jlblActions.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jlblActions.setForeground(new java.awt.Color(255, 255, 255));
        jlblActions.setText("Select Action");
        jlblActions.setName("jlblActions"); // NOI18N
        jPnlLog.add(jlblActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, 16));

        jLblPlayerStats.setFont(new java.awt.Font("Lucida Blackletter", 0, 18));
        jLblPlayerStats.setForeground(new java.awt.Color(255, 255, 255));
        jLblPlayerStats.setText("Player Statistics");
        jLblPlayerStats.setName("jLblPlayerStats"); // NOI18N
        jPnlLog.add(jLblPlayerStats, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, -1, -1));

        jPnlDugOutTeamA.setBackground(java.awt.Color.lightGray);
        jPnlDugOutTeamA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPnlDugOutTeamA.setName("jPnlDugOutTeamA"); // NOI18N
        jPnlDugOutTeamA.setOpaque(false);

        org.jdesktop.layout.GroupLayout jPnlDugOutTeamALayout = new org.jdesktop.layout.GroupLayout(jPnlDugOutTeamA);
        jPnlDugOutTeamA.setLayout(jPnlDugOutTeamALayout);
        jPnlDugOutTeamALayout.setHorizontalGroup(
            jPnlDugOutTeamALayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 114, Short.MAX_VALUE)
        );
        jPnlDugOutTeamALayout.setVerticalGroup(
            jPnlDugOutTeamALayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 339, Short.MAX_VALUE)
        );

        pnlBoard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlBoard.setName("pnlBoard"); // NOI18N

        org.jdesktop.layout.GroupLayout pnlBoardLayout = new org.jdesktop.layout.GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
            pnlBoardLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 730, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
            pnlBoardLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 372, Short.MAX_VALUE)
        );

        jPnlDugOutTeamB.setBackground(java.awt.Color.lightGray);
        jPnlDugOutTeamB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPnlDugOutTeamB.setName("jPnlDugOutTeamB"); // NOI18N
        jPnlDugOutTeamB.setOpaque(false);

        org.jdesktop.layout.GroupLayout jPnlDugOutTeamBLayout = new org.jdesktop.layout.GroupLayout(jPnlDugOutTeamB);
        jPnlDugOutTeamB.setLayout(jPnlDugOutTeamBLayout);
        jPnlDugOutTeamBLayout.setHorizontalGroup(
            jPnlDugOutTeamBLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 100, Short.MAX_VALUE)
        );
        jPnlDugOutTeamBLayout.setVerticalGroup(
            jPnlDugOutTeamBLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 327, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPnlBackgroundLayout = new org.jdesktop.layout.GroupLayout(jPnlBackground);
        jPnlBackground.setLayout(jPnlBackgroundLayout);
        jPnlBackgroundLayout.setHorizontalGroup(
            jPnlBackgroundLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlBackgroundLayout.createSequentialGroup()
                .add(jPnlBackgroundLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPnlBackgroundLayout.createSequentialGroup()
                        .add(jPnlDugOutTeamA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(pnlBoard, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jPnlDugOutTeamB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPnlLog, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, 0))
        );
        jPnlBackgroundLayout.setVerticalGroup(
            jPnlBackgroundLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlBackgroundLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jPnlDugOutTeamB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPnlBackgroundLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jPnlBackgroundLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPnlDugOutTeamA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pnlBoard, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPnlLog, 0, 0, Short.MAX_VALUE))
            .add(jPnlBackgroundLayout.createSequentialGroup()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(322, Short.MAX_VALUE))
        );

        fileMenu.setText("Game");

        saveGameMenuItem.setText("Save Game...");
        saveGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveGameMenuItem);

        quitGameMenuItem.setText("Quit");
        quitGameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitGameMenuItem);

        editTeamA.setText("Edit Team on Left");
        editTeamA.setName("editTeamA"); // NOI18N
        editTeamA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openTeamEditorForA(evt);
            }
        });
        fileMenu.add(editTeamA);

        editTeamB.setText("Edit Team on Right");
        editTeamB.setName("editTeamB"); // NOI18N
        editTeamB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openTeamEditorForB(evt);
            }
        });
        fileMenu.add(editTeamB);

        gameMenuBar.add(fileMenu);

        jMenuTossCoin.setText("Start");
        jMenuTossCoin.setName("jMenuTossCoin"); // NOI18N
        jMenuTossCoin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuTossCoinMouseClicked(evt);
            }
        });
        jMenuTossCoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTossCoinActionPerformed(evt);
            }
        });
        gameMenuBar.add(jMenuTossCoin);

        editMenu.setText("Moves");

        jMenuItem1.setText("Undo");
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        editMenu.add(jMenuItem1);

        gameMenuBar.add(editMenu);

        helpMenu.setText("Help");

        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        gameMenuBar.add(helpMenu);

        setJMenuBar(gameMenuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlBackground, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlBackground, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method to Quit the current game
     * @param evt
     */
    private void quitGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitGameMenuItemActionPerformed

        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit the game?", "BloodBowl", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            splashScreen = new GameStart();
            splashScreen.setVisible(true);

            this.setVisible(false);
        }
    }//GEN-LAST:event_quitGameMenuItemActionPerformed

    /**
     * helper method to enable save game
     *
     * @param evt
     */
    private void saveGameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGameMenuItemActionPerformed
        if ((isValidTeamAPlacement && isValidTeamBPlacement) || isResumedGame) {
            saveGameDialog = new SaveGame(gameEngine);
            saveGameDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Referee says: You cannot save a game with teams that is not validated", "BloodBowl", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_saveGameMenuItemActionPerformed

    private void jMenuTossCoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuTossCoinActionPerformed
    }//GEN-LAST:event_jMenuTossCoinActionPerformed

    /**
     * Method to toss coins on game start
     *
     * @param evt
     */
    private void jMenuTossCoinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuTossCoinMouseClicked
        String[] choices = {"Head", "Tail"};
        String[] options = {"Kick Ball", "Recieve Ball"};

        if (tossCoinsDone == false) {

            int response = JOptionPane.showOptionDialog(this, gameEngine.getTeamA().getName() + " Team Choose", "Toss Coin", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "BloodBowl");

            boolean outcome;

            //head selected
            if (response == 0) {
                outcome = referee.performToss(gameEngine.getTeamA(), gameEngine.getTeamB(), Coin.HEADS);
                //if head
                message = String.format("%s team performed toss, chose head", gameEngine.getTeamA().getName());
                EventLogger.log(message);
                if (outcome) {
                    EventLogger.log(referee.getTossMessage());
                    response = JOptionPane.showOptionDialog(this, referee.getTossMessage(), gameEngine.getTeamA().getName() + " Team Choose", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, "BloodBowl");
                    if (response == 0) {
                        gameEngine.setCurrentTeam(gameEngine.getTeamA().getID());
                        message = String.format("%s team kicks ball and team %s recieves ball", gameEngine.getTeamA().getName(), gameEngine.getTeamB().getName());
                    } else {
                        gameEngine.setCurrentTeam(gameEngine.getTeamB().getID());
                    }
                } else {
                    EventLogger.log(referee.getTossMessage());
                    response = JOptionPane.showOptionDialog(this, referee.getTossMessage(), gameEngine.getTeamB().getName() + " Team Choose", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, "BloodBowl");
                    if (response == 0) {
                        gameEngine.setCurrentTeam(gameEngine.getTeamB().getID());
                        message = String.format("%s team kicks ball and team %s recieves ball", gameEngine.getTeamB().getName(), gameEngine.getTeamA().getName());
                    } else {
                        //TeamOptions.RecieveBall;
                        gameEngine.setCurrentTeam(gameEngine.getTeamA().getID());
                    }
                }
            } //tail selected
            else {
                outcome = referee.performToss(gameEngine.getTeamA(), gameEngine.getTeamB(), Coin.TAILS);
                message = String.format("%s team performed toss, chose tail", gameEngine.getTeamA().getName());
                EventLogger.log(message);

                if (outcome) {
                    EventLogger.log(referee.getTossMessage());
                    response = JOptionPane.showOptionDialog(this, referee.getTossMessage(), gameEngine.getTeamA().getName() + " Team Choose", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, "BloodBowl");
                    if (response == 0) {
                        //TeamOptions.KickBall;
                        gameEngine.setCurrentTeam(gameEngine.getTeamA().getID());
                        message = String.format("%s team kicks ball and team %s recieves ball", gameEngine.getTeamA().getName(), gameEngine.getTeamB().getName());
                    } else {
                        //TeamOptions.RecieveBall;
                        gameEngine.setCurrentTeam(gameEngine.getTeamB().getID());
                    }
                } else {
                    EventLogger.log(referee.getTossMessage());
                    response = JOptionPane.showOptionDialog(this, referee.getTossMessage(), gameEngine.getTeamB().getName() + " Team Choose", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, "BloodBowl");
                    if (response == 0) {
                        //TeamOptions.KickBall;
                        gameEngine.setCurrentTeam(gameEngine.getTeamB().getID());
                        message = String.format("%s team kicks ball and team %s recieves ball", gameEngine.getTeamB().getName(), gameEngine.getTeamA().getName());
                    } else {
                        //TeamOptions.RecieveBall;
                        gameEngine.setCurrentTeam(gameEngine.getTeamA().getID());
                    }
                }

            }
            EventLogger.log(message);
            setCoinTossMenu(true);
            showCurrentTeam();

            isGameStart = true;

        }
    }//GEN-LAST:event_jMenuTossCoinMouseClicked

    /**
     * Method to end the current team turn
     * 
     * @param evt
     */
    private void jBtnEndTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEndTurnActionPerformed

        referee.swapTurn();

        showCurrentTeam();

        jLblTeamATurnCount.setText(String.format("Turn : %s/8", referee.getTeamATurnCount()));
        jLblTeamBTurnCount.setText(String.format("Turn : %s/8", referee.getTeamBTurnCount()));

        EventLogger.log(String.format("%s Turn left: %s/8", gameEngine.getTeamA().getName(), referee.getTeamATurnCount()));
        EventLogger.log(String.format("%s Turn left: %s/8", gameEngine.getTeamB().getName(), referee.getTeamBTurnCount()));
    }

    /**
     * Method to show the current team
     * 
     */
    private void showCurrentTeam() {
        if (gameEngine.getTeamA().getID() == gameEngine.getCurrentTeam()) {
            jLblCurrentTeam.setText(gameEngine.getTeamA().getName());

        } else {
            jLblCurrentTeam.setText(gameEngine.getTeamB().getName());
        }
    }//GEN-LAST:event_jBtnEndTurnActionPerformed

    /**
     * validate players placement on pitch
     * 
     * @param evt
     */
    private void jBtnValidateTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnValidateTeamActionPerformed

        if (gameEngine.getTeamA().getID() == gameEngine.getCurrentTeam()) {
            isValidTeamAPlacement = referee.validatePlacement(gameEngine.getTeamA());

            if (isValidTeamAPlacement) {
                isValidationPassed = true;
                message = String.format("Referee says: Team %s validation passed!", gameEngine.getTeamA().getName());
                gameEngine.setCurrentTeam(gameEngine.getTeamB().getID());
                JOptionPane.showMessageDialog(this, message);

                EventLogger.log(message);
            } else {
                message = String.format("Referee says: Invalid team %s formation\n%s", gameEngine.getTeamA().getName(), referee.getErrorMsg());
                EventLogger.log(message);
                JOptionPane.showMessageDialog(this, message);
            }
        } else {
            isValidTeamBPlacement = referee.validatePlacement(gameEngine.getTeamB());

            if (isValidTeamBPlacement) {
                isValidationPassed = true;
                message = String.format("Referee says: Team %s validation passed!", gameEngine.getTeamB().getName());
                gameEngine.setCurrentTeam(gameEngine.getTeamA().getID());
                EventLogger.log(message);
                JOptionPane.showMessageDialog(this, message);
            } else {
                message = String.format("Referee says: Invalid team %s formation\n%s", gameEngine.getTeamB().getName(), referee.getErrorMsg());
                EventLogger.log(message);
                JOptionPane.showMessageDialog(this, message);
            }
        }

        if (isValidTeamAPlacement && isValidTeamBPlacement) {

            ball.addObserver(tblGamePitch);
            tblGamePitch.setToolTip("Place Ball");
            message = "Referee says: Place ball on pitch";
            EventLogger.log(message);
            JOptionPane.showMessageDialog(this, message);
            startGamePlay(true);

            gameEngine.resetTouchDownFlag();
        }

        showCurrentTeam();
    }//GEN-LAST:event_jBtnValidateTeamActionPerformed

    private void jLstActionsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jLstActionsValueChanged
    }//GEN-LAST:event_jLstActionsValueChanged

    /**
     * Open team A for editing
     * @param evt
     */
    private void openTeamEditorForA(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openTeamEditorForA

        TeamEditor teamEditorA = new TeamEditor(gameEngine.getTeamA());
        //teamEditorA.setModal(true);
        teamEditorA.refreshEditor();
        teamEditorA.setVisible(true);
    }//GEN-LAST:event_openTeamEditorForA

    /**
     * Open team B for editing
     * @param evt
     */
    private void openTeamEditorForB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openTeamEditorForB
        TeamEditor teamEditorA = new TeamEditor(gameEngine.getTeamB());
        //teamEditorA.setModal(true);
        teamEditorA.refreshEditor();
        teamEditorA.setVisible(true);
    }//GEN-LAST:event_openTeamEditorForB

    /**
     * Method to start the game play
     * 
     * @param flag
     */
    private void startGamePlay(boolean flag) {
        if (flag) {
            jBtnEndTurn.setEnabled(true);
            jMenuTossCoin.setEnabled(false);
            jBtnValidateTeam.setEnabled(false);
            message = "Game starting...";
            EventLogger.log(message);
        } else {
            jBtnEndTurn.setEnabled(false);
            jMenuTossCoin.setEnabled(true);
            jBtnValidateTeam.setEnabled(true);
        }

    }

    /**
     * Method to setup the pitch
     */
    private void setUpMap() {
        tblGamePitch = new PitchGrid();
        tableModel = new MapTableModel(tblGamePitch);

        tblGamePitch.setModel(tableModel);

        tblGamePitch.setRowHeight(25);
        TableColumn col;

        for (int i = 0; i < tblGamePitch.getColumnCount(); i++) {
            col = tblGamePitch.getColumnModel().getColumn(i);
            col.setMaxWidth(25);
        }

        tblGamePitch.setCellSelectionEnabled(true);
        tblGamePitch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pnlBoard.setLayout(new BorderLayout());
        pnlBoard.add(tblGamePitch, BorderLayout.CENTER);

        tblGamePitch.setSelectionBackground(Color.BLUE);
        tblGamePitch.setDropMode(DropMode.ON_OR_INSERT);

        tblGamePitch.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int rowSelected = tblGamePitch.rowAtPoint(e.getPoint());
                int columnSelected = tblGamePitch.columnAtPoint(e.getPoint());

                playerMovement(rowSelected, columnSelected);
            }
        });
    }

    /**
     * Method to coordinates player movement
     * 
     * @param rowSelected
     * @param columnSelected
     */
    private void playerMovement(int rowSelected, int columnSelected) {
        Player player = gameEngine.getPlayerAtPitch(rowSelected, columnSelected);

        PitchCell pitchCell = gameEngine.getPitch().getPitchCell(rowSelected, columnSelected);

        if (player == null) {
            if (gameEngine.getSelectedPlayer() != null) {               
                if (ball.getPossessor() == gameEngine.getSelectedPlayer()) {
                    if (!gameEngine.movePlayer(pitchCell)) {
                        if (gameEngine.isWaitingForBlockResolution()) {
                            gameEngine.getActionManager().performBlock(diceOption());
                            gameEngine.setWaitingForBlockResolution(false);
                        }
                    }

                    ball.addObserver(tblGamePitch);
                    gameEngine.getGameBall().setLocation(gameEngine.getSelectedPlayer().getLocation().getRow(), gameEngine.getSelectedPlayer().getLocation().getColumn());
                    ball.deleteObservers();

                }
                if (!gameEngine.movePlayer(pitchCell)) {
                    if (gameEngine.isWaitingForBlockResolution()) {
                        gameEngine.getActionManager().performBlock(diceOption());
                        gameEngine.setWaitingForBlockResolution(false);
                    }
                }

                if (pitchCell.hasBall() && ball.getPossessor() == null) {
                    ball.addObserver(tblGamePitch);
                    actionManager.pickUpBall();
                    ball.deleteObservers();
                    jTxtAreaLog.append(actionManager.getPickUpMessage().concat("\n\n"));
                }

                showPlayersSkills(gameEngine.getSelectedPlayer());
            }
        } else {
            if (ball.getPossessor() != null && ball.getPossessor() == gameEngine.getSelectedPlayer() && gameEngine.getSelectedPlayer() != player) {
                ball.addObserver(tblGamePitch);
                actionManager.performPass(player);
                ball.deleteObservers();
                jTxtAreaLog.append(actionManager.getPassMessage().concat("\n\n"));
            }
            gameEngine.setSelectedPlayer(player);

            setPlayerStatistics(player);
            showPlayersSkills(player);
        }

        if (!actionManager.IsKickedOff() && isValidTeamAPlacement && isValidTeamBPlacement) {
            JOptionPane.showMessageDialog(null, "Referee says: Game Kick off");
            ball.setLocation(rowSelected, columnSelected);
            actionManager.kickOff();
            tblGamePitch.setToolTip("");
            ball.deleteObservers();

        } else if (ball.countObservers() > 0) {
            ball.setLocation(rowSelected, columnSelected);
            tblGamePitch.setToolTip("");
            ball.deleteObservers();
        }

        if (gameEngine.isTouchdownFlag()) {
            JOptionPane.showMessageDialog(null, "Referee says: Touchdown scored!\nPlace players on pitch");
            EventLogger.log("Touchdown scored");

            afterTouchDown();

        }

    }

    /**
     * Method to show the dice option when player is tackle
     */
    private BlockDiceFaces diceOption() {
        List<String> dices = new ArrayList<String>();
        List<BlockDiceFaces> blockDiceOptions=new ArrayList<BlockDiceFaces>();
        blockDiceOptions.addAll(gameEngine.getActionManager().getDiceOptions());
        int response = 0;
        if (blockDiceOptions.size() > 0) {

            for (BlockDiceFaces dice : blockDiceOptions) {
               
                dices.add(dice.name());
            }
            response = JOptionPane.showOptionDialog(this, "Choose ?", "Dice Option(s)", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, dices.toArray(), "BloodBowl");
        }
        return blockDiceOptions.get(response);
    }

    /**
     * Method to award point after a touch down
     */
    private void afterTouchDown() {
        jLblPointsTeamA.setText(String.format("Points :%s", gameEngine.getTeamA().getScores()));
        jLblPointsTeamB.setText(String.format("Points :%s", gameEngine.getTeamB().getScores()));

        isValidTeamAPlacement = false;
        isValidTeamBPlacement = false;

        jBtnEndTurn.setEnabled(false);
        jBtnValidateTeam.setEnabled(true);
    }

    /**
     * Set up team1 dugout
     */
    private void setUpTeam1DugOut() {

        tblTeam1Dugout = new DugOutGrid(gameEngine.getTeamA());

        tableModelTeam1 = new DugOutTableModel();

        tblTeam1Dugout.setModel(tableModelTeam1);
        tblTeam1Dugout.setRowHeight(30);

        TableColumn col;

        for (int i = 0; i < tblTeam1Dugout.getColumnCount(); i++) {
            col = tblTeam1Dugout.getColumnModel().getColumn(i);
            col.setMaxWidth(30);
        }

        tblTeam1Dugout.setRowSelectionAllowed(false);
        tblTeam1Dugout.setCellSelectionEnabled(true);

        tblTeam1Dugout.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPnlDugOutTeamA.setLayout(new BorderLayout());
        jPnlDugOutTeamA.add(tblTeam1Dugout, BorderLayout.CENTER);

        tblTeam1Dugout.setDragEnabled(true);

        tblTeam1Dugout.repaint();

        tblTeam1Dugout.addMouseListener(new MouseAdapter() {

            //overides the mouse click events for dugout
            @Override
            public void mouseClicked(MouseEvent e) {

                int rowSelected = tblTeam1Dugout.rowAtPoint(e.getPoint());
                int columnSelected = tblTeam1Dugout.columnAtPoint(e.getPoint());

                Player player = gameEngine.getPlayerAtDugoutA(rowSelected, columnSelected);

                if (player != null) {
                    gameEngine.setSelectedPlayer(player);
                }

                setPlayerStatistics(player);
                showPlayersSkills(player);

            }

            //overides the mouse pressed events for dugout and sets the players transfer handler
            @Override
            public void mousePressed(MouseEvent e) {

                int rowSelected = tblTeam1Dugout.rowAtPoint(e.getPoint());
                int columnSelected = tblTeam1Dugout.columnAtPoint(e.getPoint());

                Player player = gameEngine.getPlayerAtDugoutA(rowSelected, columnSelected);

                if (player != null) {
                    gameEngine.setSelectedPlayer(player);

                    if (isGameStart || isResumedGame) {
                        tblGamePitch.setTransferHandler(new PlayersTransferHandler(tblTeam1Dugout, team1, gameEngine));
                    } else {
                        JOptionPane.showMessageDialog(null, "Referee says: Click start game!", "Bloodbowl", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * Set up team2 dugout
     */
    private void setUpTeam2DugOut() {

        tblTeam2Dugout = new DugOutGrid(gameEngine.getTeamB());

        tableModelTeam2 = new DugOutTableModel();

        tblTeam2Dugout.setModel(tableModelTeam2);
        tblTeam2Dugout.setRowHeight(30);

        TableColumn col;

        for (int i = 0; i
                < tblTeam2Dugout.getColumnCount(); i++) {
            col = tblTeam2Dugout.getColumnModel().getColumn(i);
            col.setMaxWidth(30);
        }

        tblTeam2Dugout.setRowSelectionAllowed(false);
        tblTeam2Dugout.setCellSelectionEnabled(true);

        tblTeam2Dugout.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPnlDugOutTeamB.setLayout(new BorderLayout());
        jPnlDugOutTeamB.add(tblTeam2Dugout, BorderLayout.CENTER);

        tblTeam2Dugout.setDragEnabled(true);
        tblTeam2Dugout.repaint();

        tblTeam2Dugout.addMouseListener(new MouseAdapter() {

            //overides the mouse click events for dugout
            @Override
            public void mouseClicked(MouseEvent e) {

                int rowSelected = tblTeam2Dugout.rowAtPoint(e.getPoint());
                int columnSelected = tblTeam2Dugout.columnAtPoint(e.getPoint());

                Player player = gameEngine.getPlayerAtDugoutB(rowSelected, columnSelected);

                if (player != null) {
                    gameEngine.setSelectedPlayer(player);
                }

                setPlayerStatistics(player);
                showPlayersSkills(player);

            }

            //overides the mouse pressed events for dugout and sets the players transfer handler
            @Override
            public void mousePressed(MouseEvent e) {

                int rowSelected = tblTeam2Dugout.rowAtPoint(e.getPoint());
                int columnSelected = tblTeam2Dugout.columnAtPoint(e.getPoint());

                Player player = gameEngine.getPlayerAtDugoutB(rowSelected, columnSelected);

                if (player != null) {
                    gameEngine.setSelectedPlayer(player);

                    if (isGameStart || isResumedGame) {
                        tblGamePitch.setTransferHandler(new PlayersTransferHandler(tblTeam2Dugout, team2, gameEngine));
                    } else {
                        JOptionPane.showMessageDialog(null, "Referee says: Click start game!", "Bloodbowl", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * Method to setup the map from saved game
     */
    private void setUpAndRestoreMap() {

        tblGamePitch = new PitchGrid(gameEngine);
        tableModel = new MapTableModel(tblGamePitch);

        tblGamePitch.setModel(tableModel);

        tblGamePitch.setRowHeight(25);
        TableColumn col;

        for (int i = 0; i < tblGamePitch.getColumnCount(); i++) {
            col = tblGamePitch.getColumnModel().getColumn(i);
            col.setMaxWidth(25);
        }

        tblGamePitch.setCellSelectionEnabled(true);
        tblGamePitch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pnlBoard.setLayout(new BorderLayout());
        pnlBoard.add(tblGamePitch, BorderLayout.CENTER);

        tblGamePitch.setSelectionBackground(Color.BLUE);
        tblGamePitch.setDropMode(DropMode.ON_OR_INSERT);

        tblGamePitch.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int rowSelected = tblGamePitch.rowAtPoint(e.getPoint());
                int columnSelected = tblGamePitch.columnAtPoint(e.getPoint());

                playerMovement(rowSelected, columnSelected);
            }
        });
    }

    /**
     * This methods displays the selected player statistics
     * 
     * @param player
     */
    private void setPlayerStatistics(Player player) {

        if (player == null) {
            jLblSkills.setText("Skills: -");
            jLblMA.setText("MA: -");
            jLblAG.setText("AG: -");
            jLblAV.setText("AV: -");
            jLblST.setText("ST: -");
            jLblIsActive.setText("Is Active: -");
            jLblPlayerImage.setIcon(null);
        } else {
            jLblMA.setText("MA: " + player.getMa());
            jLblAG.setText("AG: " + player.getAg());
            jLblAV.setText("AV: " + player.getAv());
            jLblST.setText("ST: " + player.getSt());
            jLblIsActive.setText("Is Active: " + player.getActive());

            ///StringBuilder skillBuilder = new StringBuilder("Skills: ");
            //JList list = new JList(new Vector(arrayList));

            DefaultListModel lstSkillModel = new DefaultListModel();
            for (Skills skill : player.getSkillsList()) {
                lstSkillModel.addElement(skill);
            }

            jLstPlayerSkills.setModel(lstSkillModel);

            TeamType playerTeamType;
            if (player.getTeamID() == gameEngine.getTeamA().getID()) {
                playerTeamType = gameEngine.getTeamA().getType();
            } else {
                playerTeamType = gameEngine.getTeamB().getType();
            }

            jLblPlayerImage.setIcon(new ImageIcon(getClass().getResource(Utility.getImagePlayerResource(player, playerTeamType))));
        }

    }

    /**
     * Display selected player statistics
     * @param player
     */
    private void showPlayersSkills(Player player) {
        StringBuilder skillBuilder = new StringBuilder("Skills: ");

        if (player != null) {
            if (player.getTeamID() == gameEngine.getTeamA().getID()) {
                for (Skills skill : player.getSkillsList()) {
                    skillBuilder.append("\n").append(skill);
                }
            } else {

                if (player != null) {
                    if (player.getTeamID() == gameEngine.getTeamB().getID()) {
                        for (Skills skill : player.getSkillsList()) {
                            skillBuilder.append("\n").append(skill);
                        }
                    }
                }
            }
        }
    }

    /**
     * Initialise both teams, place team in the dugout
     * Add the dugout and the pitch to the player observer
     */
    private void initialiseTeams() {

        team1 = gameEngine.getTeamA();
        team2 = gameEngine.getTeamB();

        for (int i = 0; i < team1.getPlayers().size(); i++) {
            Player p1 = team1.getPlayer(i);

            //adds team1 pitch and dugout to the list of observers
            p1.addObserver(tblGamePitch);
            p1.addObserver(tblTeam1Dugout);


        }

        for (int i = 0; i < team2.getPlayers().size(); i++) {
            Player p2 = team2.getPlayer(i);

            //adds team2 pitch and dugout to the list of observers
            p2.addObserver(tblGamePitch);
            p2.addObserver(tblTeam2Dugout);

        }

        gameEngine.placeTeamsInReserves();

        jLblTeam1.setText("Team: " + gameEngine.getTeamA().getName());
        jLblTeam2.setText("Team: " + gameEngine.getTeamB().getName());

    }

    /**
     * Restore the teams from saved game
     */
    private void restoreTeams() {

        team1 = gameEngine.getTeamA();
        team2 = gameEngine.getTeamB();

        for (int i = 0; i < team1.getPlayers().size(); i++) {
            Player p1 = team1.getPlayer(i);

            //adds team1 pitch and dugout to the list of observers

            p1.addObserver(tblGamePitch);
            p1.addObserver(tblTeam1Dugout);

            p1.setLocation(p1.getLocation());
        }

        for (int i = 0; i < team2.getPlayers().size(); i++) {
            Player p2 = team2.getPlayer(i);

            //adds team2 pitch and dugout to the list of observers
            p2.addObserver(tblGamePitch);
            p2.addObserver(tblTeam2Dugout);

            p2.setLocation(p2.getLocation());
        }

        jLblTeam1.setText("Team: " + gameEngine.getTeamA().getName());
        jLblTeam2.setText("Team: " + gameEngine.getTeamB().getName());

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new BloodBowl().setVisible(true);
                } catch (FontFormatException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem editTeamA;
    private javax.swing.JMenuItem editTeamB;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar gameMenuBar;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jBtnEndTurn;
    private javax.swing.JButton jBtnValidateTeam;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLblAG;
    private javax.swing.JLabel jLblAV;
    private javax.swing.JLabel jLblCommentaries;
    private javax.swing.JLabel jLblCurrent;
    private javax.swing.JLabel jLblCurrentTeam;
    private javax.swing.JLabel jLblIsActive;
    private javax.swing.JLabel jLblMA;
    private javax.swing.JLabel jLblPlayerImage;
    private javax.swing.JLabel jLblPlayerStats;
    private javax.swing.JLabel jLblPointsTeamA;
    private javax.swing.JLabel jLblPointsTeamB;
    private javax.swing.JLabel jLblReferee;
    private javax.swing.JLabel jLblST;
    private javax.swing.JLabel jLblSkills;
    private javax.swing.JLabel jLblTeam1;
    private javax.swing.JLabel jLblTeam2;
    private javax.swing.JLabel jLblTeamATurnCount;
    private javax.swing.JLabel jLblTeamBTurnCount;
    private javax.swing.JList jLstActions;
    private javax.swing.JList jLstPlayerSkills;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jMenuTossCoin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnlBackground;
    private javax.swing.JPanel jPnlDugOutTeamA;
    private javax.swing.JPanel jPnlDugOutTeamB;
    private javax.swing.JPanel jPnlLog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTxtAreaLog;
    private javax.swing.JLabel jlblActions;
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JMenuItem quitGameMenuItem;
    private javax.swing.JMenuItem saveGameMenuItem;
    // End of variables declaration//GEN-END:variables
    private MapTableModel tableModel;
    private DugOutTableModel tableModelTeam1;
    private DugOutTableModel tableModelTeam2;
    private PitchGrid tblGamePitch;
    private JButton btn;
    private TeamEditor teamEditor;
    private DugOutGrid tblTeam1Dugout;
    private DugOutGrid tblTeam2Dugout;
    private SaveGame saveGameDialog;
}
