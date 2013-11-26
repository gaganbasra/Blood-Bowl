/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PlayerEditor.java
 *
 * Created on Jan 24, 2011, 1:19:36 AM
 */
package bloodbowl.gui.dialog;

import bloodbowl.model.managers.DiceManager;
import bloodbowl.model.managers.TeamManager;
import bloodbowl.model.players.Player;
import bloodbowl.model.players.PlayerTitle;
import bloodbowl.model.players.PlayerType;
import bloodbowl.model.players.Skills;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 * Class for the Player Editor Dialog
 * @author Rishinder
 */
public class PlayerEditor extends javax.swing.JDialog {

    TeamManager teamManager;
    TeamEditor teamEditor;
    boolean editMode;
    int numberOfImprovementChances=0;

    boolean editPlayerMode;
    Player player;

    /** Creates new form PlayerEditor */
    public PlayerEditor() {

        initComponents();

        this.setLocationRelativeTo(getRootPane());
    }

    /**
     * Constructor for the player editor
     * @param teamEditorDialog Instance of Team Editor
     */
    public PlayerEditor(TeamEditor teamEditorDialog) {
        initComponents();

        this.setLocationRelativeTo(getRootPane());

        this.teamEditor = teamEditorDialog;
        teamManager = teamEditorDialog.getManager();
        editMode = true;
        editPlayerMode=false;
        improvementPanel.setVisible(false);
    }

    /**
     * Constructor in case where the player is being edited
     * @param player Player being edited
     * @param teamEditorDialog Team Editor Dialog
     */
    public PlayerEditor(Player player, TeamEditor teamEditorDialog) {
        initComponents();

        this.setLocationRelativeTo(getRootPane());

        this.player=player;
        this.teamEditor = teamEditorDialog;
        editMode = true;
        editPlayerMode=true;
        if(player.isUpgradable())
        {
            improvementPanel.setVisible(true);
            switch(player.getPlayerTitle())
            {
                case EXPERIENCED: numberOfImprovementChances=1; break;
                case VETERAN: numberOfImprovementChances=2; break;
                case EMERGING_STAR: numberOfImprovementChances=3; break;
                case STAR: numberOfImprovementChances=4; break;
                case SUPER_STAR: numberOfImprovementChances=5; break;
                case LEGEND: numberOfImprovementChances=6; break;
            }
        }
        else
            improvementPanel.setVisible(false);
    }

    /**
     * Populate the Player Editor dialog with some values
     */
    public void refreshEditor() {
        DefaultComboBoxModel newCBModel = new DefaultComboBoxModel((teamManager.getTeam()).getAvailablePlayerOptions());
        playerType.setModel(newCBModel);

        //Filling the stats for the selected player in the list. Lineman is first in all lists
        maTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanMA()));
        stTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanST()));
        agTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanAG()));
        avTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanAV()));
        jLabelInvalidAddition.setVisible(false);
    }

    /**
     * Populate the Player Editor dialog with some values
     */
    public void refreshEditorWithPlayer() {
        playerType.setVisible(false);
        addButton.setVisible(false);
        jLabel2.setVisible(false);
        jLabelInvalidAddition.setVisible(false);

        //Filling the stats for the selected player in the list. Lineman is first in all lists
        maTextF.setText(String.valueOf(player.getMa()));
        stTextF.setText(String.valueOf(player.getSt()));
        agTextF.setText(String.valueOf(player.getAg()));
        avTextF.setText(String.valueOf(player.getAv()));

        pointsText.setText(String.valueOf(player.getPoints()));
        titleText.setText(player.getPlayerTitle().toString());
        refreshSkillsCheckBoxes(player.getSkillsList());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        maTextF = new javax.swing.JTextField();
        stTextF = new javax.swing.JTextField();
        agTextF = new javax.swing.JTextField();
        avTextF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pointsText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        titleText = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        playerType = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        accurateSkill = new javax.swing.JCheckBox();
        bigHandSkill = new javax.swing.JCheckBox();
        blockSkill = new javax.swing.JCheckBox();
        sureHandsSkill = new javax.swing.JCheckBox();
        catchSkill = new javax.swing.JCheckBox();
        dodgeSkill = new javax.swing.JCheckBox();
        lonerSkill = new javax.swing.JCheckBox();
        passSkill = new javax.swing.JCheckBox();
        nosSkill = new javax.swing.JCheckBox();
        boneHeadSkill = new javax.swing.JCheckBox();
        mightyBlowSkill = new javax.swing.JCheckBox();
        tickSkullSkill = new javax.swing.JCheckBox();
        sideStepSkill = new javax.swing.JCheckBox();
        rightStuffSkill = new javax.swing.JCheckBox();
        stuntySkill = new javax.swing.JCheckBox();
        reallyStupidSkill = new javax.swing.JCheckBox();
        regenerationSkill = new javax.swing.JCheckBox();
        throwMateSkill = new javax.swing.JCheckBox();
        jLabelInvalidAddition = new javax.swing.JLabel();
        improvementPanel = new javax.swing.JPanel();
        improvementRollBt = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        increaseMA = new javax.swing.JRadioButton();
        increaseST = new javax.swing.JRadioButton();
        increaseAG = new javax.swing.JRadioButton();
        addNewSkill = new javax.swing.JRadioButton();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Player Editor");

        jLabel2.setText("Type:");
        jLabel2.setName("jLabel2"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Characteristics"));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel5.setText("MA:");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("ST:");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("AG:");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText("AV:");
        jLabel8.setName("jLabel8"); // NOI18N

        maTextF.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        maTextF.setEnabled(false);
        maTextF.setName("maTextF"); // NOI18N

        stTextF.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        stTextF.setName("stTextF"); // NOI18N

        agTextF.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        agTextF.setEnabled(false);
        agTextF.setName("agTextF"); // NOI18N

        avTextF.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        avTextF.setEnabled(false);
        avTextF.setName("avTextF"); // NOI18N

        jLabel1.setText("Points:");
        jLabel1.setName("jLabel1"); // NOI18N

        pointsText.setText("0");
        pointsText.setEnabled(false);
        pointsText.setName("pointsText"); // NOI18N

        jLabel3.setText("Level:");
        jLabel3.setName("jLabel3"); // NOI18N

        titleText.setText("ROOKIE");
        titleText.setEnabled(false);
        titleText.setName("titleText"); // NOI18N
        titleText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTextActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(maTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(agTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(avTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(stTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pointsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 47, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(titleText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {agTextF, avTextF, maTextF, stTextF}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(maTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(jLabel1)
                    .add(pointsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(agTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7)
                    .add(jLabel3)
                    .add(titleText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(avTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel6)
                    .add(stTextF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        addButton.setText("Add");
        addButton.setName("addButton"); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        doneButton.setText("Done");
        doneButton.setName("doneButton"); // NOI18N
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        playerType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        playerType.setName("playerType"); // NOI18N
        playerType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerTypeActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Skills"));
        jPanel3.setName("jPanel3"); // NOI18N

        accurateSkill.setText("Accurate");
        accurateSkill.setEnabled(false);
        accurateSkill.setName("Accurate"); // NOI18N
        accurateSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accurateSkillActionPerformed(evt);
            }
        });

        bigHandSkill.setText("Big Hands");
        bigHandSkill.setEnabled(false);
        bigHandSkill.setName("bigHandSkill"); // NOI18N
        bigHandSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bigHandSkillActionPerformed(evt);
            }
        });

        blockSkill.setText("Block");
        blockSkill.setEnabled(false);
        blockSkill.setName("blockSkill"); // NOI18N
        blockSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockSkillActionPerformed(evt);
            }
        });

        sureHandsSkill.setText("Sure Hands");
        sureHandsSkill.setEnabled(false);
        sureHandsSkill.setName("sureHandsSkill"); // NOI18N
        sureHandsSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sureHandsSkillActionPerformed(evt);
            }
        });

        catchSkill.setText("Catch");
        catchSkill.setEnabled(false);
        catchSkill.setName("catchSkill"); // NOI18N
        catchSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catchSkillActionPerformed(evt);
            }
        });

        dodgeSkill.setText("Dodge");
        dodgeSkill.setEnabled(false);
        dodgeSkill.setName("dodgeSkill"); // NOI18N
        dodgeSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodgeSkillActionPerformed(evt);
            }
        });

        lonerSkill.setText("Loner");
        lonerSkill.setEnabled(false);
        lonerSkill.setName("lonerSkill"); // NOI18N
        lonerSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lonerSkillActionPerformed(evt);
            }
        });

        passSkill.setText("Pass");
        passSkill.setEnabled(false);
        passSkill.setName("passSkill"); // NOI18N
        passSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passSkillActionPerformed(evt);
            }
        });

        nosSkill.setText("Nerves of Steel");
        nosSkill.setEnabled(false);
        nosSkill.setName("nosSkill"); // NOI18N
        nosSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nosSkillActionPerformed(evt);
            }
        });

        boneHeadSkill.setText("Bone Head");
        boneHeadSkill.setEnabled(false);
        boneHeadSkill.setName("boneHeadSkill"); // NOI18N
        boneHeadSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boneHeadSkillActionPerformed(evt);
            }
        });

        mightyBlowSkill.setText("Mighty Blow");
        mightyBlowSkill.setEnabled(false);
        mightyBlowSkill.setName("mightyBlowSkill"); // NOI18N
        mightyBlowSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mightyBlowSkillActionPerformed(evt);
            }
        });

        tickSkullSkill.setText("Tick Skull");
        tickSkullSkill.setEnabled(false);
        tickSkullSkill.setName("tickSkullSkill"); // NOI18N
        tickSkullSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tickSkullSkillActionPerformed(evt);
            }
        });

        sideStepSkill.setText("Side Step");
        sideStepSkill.setEnabled(false);
        sideStepSkill.setName("sideStepSkill"); // NOI18N
        sideStepSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sideStepSkillActionPerformed(evt);
            }
        });

        rightStuffSkill.setText("Right Stuff");
        rightStuffSkill.setEnabled(false);
        rightStuffSkill.setName("rightStuffSkill"); // NOI18N
        rightStuffSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightStuffSkillActionPerformed(evt);
            }
        });

        stuntySkill.setText("Stunty");
        stuntySkill.setEnabled(false);
        stuntySkill.setName("stuntySkill"); // NOI18N
        stuntySkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stuntySkillActionPerformed(evt);
            }
        });

        reallyStupidSkill.setText("Really Stupid");
        reallyStupidSkill.setEnabled(false);
        reallyStupidSkill.setName("reallyStupidSkill"); // NOI18N
        reallyStupidSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reallyStupidSkillActionPerformed(evt);
            }
        });

        regenerationSkill.setText("Regeneration");
        regenerationSkill.setEnabled(false);
        regenerationSkill.setName("regenerationSkill"); // NOI18N
        regenerationSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regenerationSkillActionPerformed(evt);
            }
        });

        throwMateSkill.setText("Throw Team Mate");
        throwMateSkill.setEnabled(false);
        throwMateSkill.setName("throwMateSkill"); // NOI18N
        throwMateSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                throwMateSkillActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(accurateSkill)
                            .add(bigHandSkill)
                            .add(blockSkill))
                        .add(38, 38, 38)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(sureHandsSkill)
                            .add(catchSkill)
                            .add(dodgeSkill)))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(boneHeadSkill)
                            .add(sideStepSkill)
                            .add(reallyStupidSkill))
                        .add(24, 24, 24)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(mightyBlowSkill)
                            .add(rightStuffSkill)
                            .add(regenerationSkill))))
                .add(18, 18, 18)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(throwMateSkill)
                    .add(stuntySkill)
                    .add(tickSkullSkill)
                    .add(passSkill)
                    .add(nosSkill)
                    .add(lonerSkill))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(accurateSkill)
                    .add(sureHandsSkill)
                    .add(lonerSkill))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(catchSkill)
                            .add(nosSkill))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(dodgeSkill)
                            .add(passSkill)))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(bigHandSkill)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(blockSkill)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(boneHeadSkill)
                            .add(mightyBlowSkill))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(sideStepSkill)
                            .add(rightStuffSkill))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(reallyStupidSkill)
                            .add(regenerationSkill)))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(tickSkullSkill)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(stuntySkill)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(throwMateSkill)))
                .addContainerGap())
        );

        jLabelInvalidAddition.setForeground(new java.awt.Color(255, 0, 0));
        jLabelInvalidAddition.setText("Invalid player addition!");
        jLabelInvalidAddition.setName("jLabelInvalidAddition"); // NOI18N

        improvementPanel.setName("improvementPanel"); // NOI18N

        improvementRollBt.setText("Perform Improvement Roll");
        improvementRollBt.setName("improvementRollBt"); // NOI18N
        improvementRollBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                improvementRollBtActionPerformed(evt);
            }
        });

        jLabel4.setText("Make a choice:");
        jLabel4.setName("jLabel4"); // NOI18N

        increaseMA.setText("Increase MA");
        increaseMA.setEnabled(false);
        increaseMA.setName("increaseMA"); // NOI18N
        increaseMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseMAActionPerformed(evt);
            }
        });

        increaseST.setText("Increase ST");
        increaseST.setEnabled(false);
        increaseST.setName("increaseST"); // NOI18N
        increaseST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseSTActionPerformed(evt);
            }
        });

        increaseAG.setText("Increase AG");
        increaseAG.setEnabled(false);
        increaseAG.setName("increaseAG"); // NOI18N
        increaseAG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAGActionPerformed(evt);
            }
        });

        addNewSkill.setText("Add a new Skill");
        addNewSkill.setEnabled(false);
        addNewSkill.setName("addNewSkill"); // NOI18N
        addNewSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewSkillActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout improvementPanelLayout = new org.jdesktop.layout.GroupLayout(improvementPanel);
        improvementPanel.setLayout(improvementPanelLayout);
        improvementPanelLayout.setHorizontalGroup(
            improvementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(improvementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(improvementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(increaseAG)
                    .add(increaseST)
                    .add(improvementRollBt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .add(jLabel4)
                    .add(increaseMA)
                    .add(addNewSkill))
                .addContainerGap())
        );
        improvementPanelLayout.setVerticalGroup(
            improvementPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(improvementPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(improvementRollBt)
                .add(11, 11, 11)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(increaseMA)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(increaseST)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(increaseAG)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(addNewSkill)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(jLabel2)
                        .add(17, 17, 17)
                        .add(playerType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(jLabelInvalidAddition)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 244, Short.MAX_VALUE)
                                .add(addButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(doneButton))
                            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(improvementPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(playerType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel2))
                        .add(18, 18, 18)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, improvementPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(doneButton)
                    .add(addButton)
                    .add(jLabelInvalidAddition))
                .add(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method to be invoked when the player editor opens in editPlayerMode.
     * Used to save the edited player.
     */
    public void saveEditedPlayer()
    {
        int ma = Integer.parseInt(maTextF.getText());
        int st = Integer.parseInt(stTextF.getText());
        int ag = Integer.parseInt(agTextF.getText());
        int av = Integer.parseInt(avTextF.getText());

        ArrayList<Skills> pSkills = new ArrayList();
        if (accurateSkill.isSelected()) {
            pSkills.add(Skills.ACCURATE);
        }
        if (passSkill.isSelected()) {
            pSkills.add(Skills.PASS);
        }
        if (bigHandSkill.isSelected()) {
            pSkills.add(Skills.BIGHANDS);
        }
        if (blockSkill.isSelected()) {
            pSkills.add(Skills.BLOCK);
        }
        if (boneHeadSkill.isSelected()) {
            pSkills.add(Skills.BONEHEAD);
        }
        if (catchSkill.isSelected()) {
            pSkills.add(Skills.CATCH);
        }
        if (dodgeSkill.isSelected()) {
            pSkills.add(Skills.DODGE);
        }
        if (lonerSkill.isSelected()) {
            pSkills.add(Skills.LONER);
        }
        if (mightyBlowSkill.isSelected()) {
            pSkills.add(Skills.MIGHTYBLOW);
        }
        if (nosSkill.isSelected()) {
            pSkills.add(Skills.NERVESOFSTEEL);
        }
        if (reallyStupidSkill.isSelected()) {
            pSkills.add(Skills.REALLYSTUPID);
        }
        if (regenerationSkill.isSelected()) {
            pSkills.add(Skills.REGENERATION);
        }
        if (rightStuffSkill.isSelected()) {
            pSkills.add(Skills.RIGHTSTUFF);
        }
        if (sideStepSkill.isSelected()) {
            pSkills.add(Skills.SIDESTEP);
        }
        if (stuntySkill.isSelected()) {
            pSkills.add(Skills.STUNTY);
        }
        if (sureHandsSkill.isSelected()) {
            pSkills.add(Skills.SUREHANDS);
        }
        if (throwMateSkill.isSelected()) {
            pSkills.add(Skills.THROWTEAMMATE);
        }
        if (tickSkullSkill.isSelected()) {
            pSkills.add(Skills.TICKSKULL);
        }

        player.setMa(ma);
        player.setSt(st);
        player.setAg(ag);
        player.setAv(av);
        player.setSkillsList(pSkills);
    }

    /**
     * Method for Button to add players in the team and reflect the changes in the team editor as well.
     * @param evt
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        boolean validAddition = false;
        if (playerType.getSelectedItem() == "Lineman") {
            validAddition = teamManager.createPlayer(PlayerType.LINEMAN);
        } else if (playerType.getSelectedItem() == "Blitzer") {
            validAddition = teamManager.createPlayer(PlayerType.BLITZER);
        } else if (playerType.getSelectedItem() == "Black Orc") {
            validAddition = teamManager.createPlayer(PlayerType.BLACKORC);
        } else if (playerType.getSelectedItem() == "Catcher") {
            validAddition = teamManager.createPlayer(PlayerType.CATCHER);
        } else if (playerType.getSelectedItem() == "Goblin") {
            validAddition = teamManager.createPlayer(PlayerType.GOBLIN);
        } else if (playerType.getSelectedItem() == "Ogre") {
            validAddition = teamManager.createPlayer(PlayerType.OGRE);
        } else if (playerType.getSelectedItem() == "Thrower") {
            validAddition = teamManager.createPlayer(PlayerType.THROWER);
        } else if (playerType.getSelectedItem() == "Troll") {
            validAddition = teamManager.createPlayer(PlayerType.TROLL);
        }


        if (validAddition) {
            jLabelInvalidAddition.setVisible(false);
            if (editMode) {
                int ma = Integer.parseInt(maTextF.getText());
                int st = Integer.parseInt(stTextF.getText());
                int ag = Integer.parseInt(agTextF.getText());
                int av = Integer.parseInt(avTextF.getText());

                ArrayList<Skills> pSkills = new ArrayList();
                if (accurateSkill.isSelected()) {
                    pSkills.add(Skills.ACCURATE);
                }
                if (passSkill.isSelected()) {
                    pSkills.add(Skills.PASS);
                }
                if (bigHandSkill.isSelected()) {
                    pSkills.add(Skills.BIGHANDS);
                }
                if (blockSkill.isSelected()) {
                    pSkills.add(Skills.BLOCK);
                }
                if (boneHeadSkill.isSelected()) {
                    pSkills.add(Skills.BONEHEAD);
                }
                if (catchSkill.isSelected()) {
                    pSkills.add(Skills.CATCH);
                }
                if (dodgeSkill.isSelected()) {
                    pSkills.add(Skills.DODGE);
                }
                if (lonerSkill.isSelected()) {
                    pSkills.add(Skills.LONER);
                }
                if (mightyBlowSkill.isSelected()) {
                    pSkills.add(Skills.MIGHTYBLOW);
                }
                if (nosSkill.isSelected()) {
                    pSkills.add(Skills.NERVESOFSTEEL);
                }
                if (reallyStupidSkill.isSelected()) {
                    pSkills.add(Skills.REALLYSTUPID);
                }
                if (regenerationSkill.isSelected()) {
                    pSkills.add(Skills.REGENERATION);
                }
                if (rightStuffSkill.isSelected()) {
                    pSkills.add(Skills.RIGHTSTUFF);
                }
                if (sideStepSkill.isSelected()) {
                    pSkills.add(Skills.SIDESTEP);
                }
                if (stuntySkill.isSelected()) {
                    pSkills.add(Skills.STUNTY);
                }
                if (sureHandsSkill.isSelected()) {
                    pSkills.add(Skills.SUREHANDS);
                }
                if (throwMateSkill.isSelected()) {
                    pSkills.add(Skills.THROWTEAMMATE);
                }
                if (tickSkullSkill.isSelected()) {
                    pSkills.add(Skills.TICKSKULL);
                }

                (teamManager.getTeam().getPlayer(teamManager.getTeam().getTotalPlayers() - 1)).setAg(ag);
                (teamManager.getTeam().getPlayer(teamManager.getTeam().getTotalPlayers() - 1)).setAv(av);
                (teamManager.getTeam().getPlayer(teamManager.getTeam().getTotalPlayers() - 1)).setMa(ma);
                (teamManager.getTeam().getPlayer(teamManager.getTeam().getTotalPlayers() - 1)).setSt(st);
                (teamManager.getTeam().getPlayer(teamManager.getTeam().getTotalPlayers() - 1)).setSkillsList(pSkills);

            }
            teamEditor.addPlayerToEditor();

        } else {
            jLabelInvalidAddition.setVisible(true);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        if(editPlayerMode)
        {
            saveEditedPlayer();
            teamEditor.refreshExistingTeamData();
        }
        this.setVisible(false);
    }//GEN-LAST:event_doneButtonActionPerformed

    /**
     * Combobox to choose the player type and fill the player details on selection
     * @param evt
     */
    private void playerTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerTypeActionPerformed
        if (playerType.getSelectedItem() == "Lineman") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getLinemanAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getLinemanSkills());
        } else if (playerType.getSelectedItem() == "Blitzer") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getBlitzerMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getBlitzerST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getBlitzerAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getBlitzerAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getBlitzerSkills());
        } else if (playerType.getSelectedItem() == "Black Orc") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getBlackorcMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getBlackorcST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getBlackorcAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getBlackorcAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getBlackorcSkills());
        } else if (playerType.getSelectedItem() == "Catcher") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getCatcherMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getCatcherST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getCatcherAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getCatcherAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getCatcherSkills());
        } else if (playerType.getSelectedItem() == "Goblin") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getGoblinMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getGoblinST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getGoblinAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getGoblinAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getGoblinSkills());
        } else if (playerType.getSelectedItem() == "Ogre") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getOgreMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getOgreST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getOgreAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getOgreAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getOgreSkills());
        } else if (playerType.getSelectedItem() == "Thrower") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getThrowerMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getThrowerST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getThrowerAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getThrowerAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getThrowerSkills());
        } else if (playerType.getSelectedItem() == "Troll") {
            maTextF.setText(String.valueOf((teamManager.getTeam()).getTrollMA()));
            stTextF.setText(String.valueOf((teamManager.getTeam()).getTrollST()));
            agTextF.setText(String.valueOf((teamManager.getTeam()).getTrollAG()));
            avTextF.setText(String.valueOf((teamManager.getTeam()).getTrollAV()));
            this.refreshSkillsCheckBoxes((teamManager.getTeam()).getTrollSkills());
        }
    }//GEN-LAST:event_playerTypeActionPerformed

    /**
     * Refresh the player skills checkboxes for each selection type
     * @param skills Player SKills
     */
    private void refreshSkillsCheckBoxes(ArrayList<Skills> skills) {
        accurateSkill.setSelected(false);
        bigHandSkill.setSelected(false);
        blockSkill.setSelected(false);
        sureHandsSkill.setSelected(false);
        catchSkill.setSelected(false);
        dodgeSkill.setSelected(false);
        lonerSkill.setSelected(false);
        passSkill.setSelected(false);
        nosSkill.setSelected(false);
        boneHeadSkill.setSelected(false);
        mightyBlowSkill.setSelected(false);
        tickSkullSkill.setSelected(false);
        sideStepSkill.setSelected(false);
        rightStuffSkill.setSelected(false);
        stuntySkill.setSelected(false);
        reallyStupidSkill.setSelected(false);
        regenerationSkill.setSelected(false);
        throwMateSkill.setSelected(false);

        if (skills.contains(Skills.ACCURATE)) {
            accurateSkill.setSelected(true);
        }
        if (skills.contains(Skills.BIGHANDS)) {
            bigHandSkill.setSelected(true);
        }
        if (skills.contains(Skills.BLOCK)) {
            blockSkill.setSelected(true);
        }
        if (skills.contains(Skills.BONEHEAD)) {
            boneHeadSkill.setSelected(true);
        }
        if (skills.contains(Skills.CATCH)) {
            catchSkill.setSelected(true);
        }
        if (skills.contains(Skills.DODGE)) {
            dodgeSkill.setSelected(true);
        }
        if (skills.contains(Skills.LONER)) {
            lonerSkill.setSelected(true);
        }
        if (skills.contains(Skills.MIGHTYBLOW)) {
            mightyBlowSkill.setSelected(true);
        }
        if (skills.contains(Skills.NERVESOFSTEEL)) {
            nosSkill.setSelected(true);
        }
        if (skills.contains(Skills.PASS)) {
            passSkill.setSelected(true);
        }
        if (skills.contains(Skills.REALLYSTUPID)) {
            reallyStupidSkill.setSelected(true);
        }
        if (skills.contains(Skills.REGENERATION)) {
            regenerationSkill.setSelected(true);
        }
        if (skills.contains(Skills.RIGHTSTUFF)) {
            rightStuffSkill.setSelected(true);
        }
        if (skills.contains(Skills.SIDESTEP)) {
            sideStepSkill.setSelected(true);
        }
        if (skills.contains(Skills.STUNTY)) {
            stuntySkill.setSelected(true);
        }
        if (skills.contains(Skills.SUREHANDS)) {
            sureHandsSkill.setSelected(true);
        }
        if (skills.contains(Skills.THROWTEAMMATE)) {
            throwMateSkill.setSelected(true);
        }
        if (skills.contains(Skills.TICKSKULL)) {
            tickSkullSkill.setSelected(true);
        }
    }
    private void sureHandsSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sureHandsSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_sureHandsSkillActionPerformed

    private void titleTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleTextActionPerformed

    private void improvementRollBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_improvementRollBtActionPerformed
        improvementRollBt.setEnabled(false);
        int diceVal=DiceManager.roll2D6();
        if(diceVal>=2&& diceVal<=9)
        {
            addNewSkill.setEnabled(true);
        }
        else if(diceVal==10)
        {
            increaseMA.setEnabled(true);
        }
        else if(diceVal==11)
        {
            increaseAG.setEnabled(true);
        }
        else if(diceVal==12)
        {
            increaseST.setEnabled(true);
        }
    }//GEN-LAST:event_improvementRollBtActionPerformed

    private void increaseMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseMAActionPerformed
        int newMa=Integer.parseInt(maTextF.getText());
        newMa++;
        maTextF.setText(Integer.toString(newMa));
        disableAllImprovements();
        numberOfImprovementChances--;
        if(numberOfImprovementChances>0)
        {
            improvementRollBt.setVisible(true);
            improvementRollBt.setEnabled(true);
        }
    }//GEN-LAST:event_increaseMAActionPerformed

    private void increaseSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseSTActionPerformed
        int newSt=Integer.parseInt(stTextF.getText());
        newSt++;
        stTextF.setText(Integer.toString(newSt));
        disableAllImprovements();
        numberOfImprovementChances--;
        if(numberOfImprovementChances>0)
        {
            improvementRollBt.setVisible(true);
            improvementRollBt.setEnabled(true);
        }
    }//GEN-LAST:event_increaseSTActionPerformed

    private void increaseAGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseAGActionPerformed
        int newAg=Integer.parseInt(agTextF.getText());
        newAg++;
        agTextF.setText(Integer.toString(newAg));
        disableAllImprovements();
        numberOfImprovementChances--;
        if(numberOfImprovementChances>0)
        {
            improvementRollBt.setVisible(true);
            improvementRollBt.setEnabled(true);
        }
    }//GEN-LAST:event_increaseAGActionPerformed

    private void addNewSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewSkillActionPerformed
        enableSkills();
        disableAllImprovements();
        numberOfImprovementChances--;
        if(numberOfImprovementChances>0)
        {
            improvementRollBt.setVisible(true);
            improvementRollBt.setEnabled(true);
        }
    }//GEN-LAST:event_addNewSkillActionPerformed

    private void accurateSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accurateSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_accurateSkillActionPerformed

    private void lonerSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lonerSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_lonerSkillActionPerformed

    private void bigHandSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bigHandSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_bigHandSkillActionPerformed

    private void catchSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catchSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_catchSkillActionPerformed

    private void nosSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nosSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_nosSkillActionPerformed

    private void blockSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_blockSkillActionPerformed

    private void dodgeSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodgeSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_dodgeSkillActionPerformed

    private void passSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_passSkillActionPerformed

    private void boneHeadSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boneHeadSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_boneHeadSkillActionPerformed

    private void mightyBlowSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mightyBlowSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_mightyBlowSkillActionPerformed

    private void tickSkullSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tickSkullSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_tickSkullSkillActionPerformed

    private void sideStepSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sideStepSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_sideStepSkillActionPerformed

    private void rightStuffSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightStuffSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_rightStuffSkillActionPerformed

    private void stuntySkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stuntySkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_stuntySkillActionPerformed

    private void reallyStupidSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reallyStupidSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_reallyStupidSkillActionPerformed

    private void regenerationSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regenerationSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_regenerationSkillActionPerformed

    private void throwMateSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_throwMateSkillActionPerformed
        disableSkills();
    }//GEN-LAST:event_throwMateSkillActionPerformed

    public void disableAllImprovements()
    {
        player.upgradeDone();
        increaseMA.setEnabled(false);
        increaseAG.setEnabled(false);
        increaseST.setEnabled(false);
        addNewSkill.setEnabled(false);
    }
    /**
     * Method to enable all skills check boxes
     */
    public void enableSkills()
    {
        accurateSkill.setEnabled(true);
        bigHandSkill.setEnabled(true);
        blockSkill.setEnabled(true);
        sureHandsSkill.setEnabled(true);
        catchSkill.setEnabled(true);
        dodgeSkill.setEnabled(true);
        lonerSkill.setEnabled(true);
        passSkill.setEnabled(true);
        nosSkill.setEnabled(true);
        boneHeadSkill.setEnabled(true);
        mightyBlowSkill.setEnabled(true);
        tickSkullSkill.setEnabled(true);
        sideStepSkill.setEnabled(true);
        rightStuffSkill.setEnabled(true);
        stuntySkill.setEnabled(true);
        reallyStupidSkill.setEnabled(true);
        regenerationSkill.setEnabled(true);
        throwMateSkill.setEnabled(true);
    }

    /**
     * Method to disable all skills checkboxes
     */
    public void disableSkills()
    {
        accurateSkill.setEnabled(false);
        bigHandSkill.setEnabled(false);
        blockSkill.setEnabled(false);
        sureHandsSkill.setEnabled(false);
        catchSkill.setEnabled(false);
        dodgeSkill.setEnabled(false);
        lonerSkill.setEnabled(false);
        passSkill.setEnabled(false);
        nosSkill.setEnabled(false);
        boneHeadSkill.setEnabled(false);
        mightyBlowSkill.setEnabled(false);
        tickSkullSkill.setEnabled(false);
        sideStepSkill.setEnabled(false);
        rightStuffSkill.setEnabled(false);
        stuntySkill.setEnabled(false);
        reallyStupidSkill.setEnabled(false);
        regenerationSkill.setEnabled(false);
        throwMateSkill.setEnabled(false);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PlayerEditor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox accurateSkill;
    private javax.swing.JButton addButton;
    private javax.swing.JRadioButton addNewSkill;
    private javax.swing.JTextField agTextF;
    private javax.swing.JTextField avTextF;
    private javax.swing.JCheckBox bigHandSkill;
    private javax.swing.JCheckBox blockSkill;
    private javax.swing.JCheckBox boneHeadSkill;
    private javax.swing.JCheckBox catchSkill;
    private javax.swing.JCheckBox dodgeSkill;
    private javax.swing.JButton doneButton;
    private javax.swing.JPanel improvementPanel;
    private javax.swing.JButton improvementRollBt;
    private javax.swing.JRadioButton increaseAG;
    private javax.swing.JRadioButton increaseMA;
    private javax.swing.JRadioButton increaseST;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelInvalidAddition;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox lonerSkill;
    private javax.swing.JTextField maTextF;
    private javax.swing.JCheckBox mightyBlowSkill;
    private javax.swing.JCheckBox nosSkill;
    private javax.swing.JCheckBox passSkill;
    private javax.swing.JComboBox playerType;
    private javax.swing.JTextField pointsText;
    private javax.swing.JCheckBox reallyStupidSkill;
    private javax.swing.JCheckBox regenerationSkill;
    private javax.swing.JCheckBox rightStuffSkill;
    private javax.swing.JCheckBox sideStepSkill;
    private javax.swing.JTextField stTextF;
    private javax.swing.JCheckBox stuntySkill;
    private javax.swing.JCheckBox sureHandsSkill;
    private javax.swing.JCheckBox throwMateSkill;
    private javax.swing.JCheckBox tickSkullSkill;
    private javax.swing.JTextField titleText;
    // End of variables declaration//GEN-END:variables
}
