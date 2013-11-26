/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TeamEditor.java
 *
 * Created on Jan 24, 2011, 1:07:15 AM
 */

package bloodbowl.gui.dialog;


import bloodbowl.model.managers.TeamManager;
import bloodbowl.model.players.Actions;
import bloodbowl.model.players.Player;
import bloodbowl.model.teams.Team;
import bloodbowl.model.teams.TeamType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * Class to Edit/Create a team.
 * Uses Team Manager form the model.managers package to do all the work
 * @author Rishinder
 */
public class TeamEditor extends javax.swing.JDialog {
    PlayerEditor playerEditor;
    Team currentTeam;
    DefaultTableModel newModel;
    NewGame newGameDialog;
    TeamManager teamManager=new TeamManager();
    boolean editTeamMode;
    /** Creates new form TeamEditor */
    public TeamEditor() {
        
        initComponents();

        this.setLocationRelativeTo(getRootPane());
    }

    /**
     * Constructor to pass the team which has to be edited and the New Game Dialog reference
     * @param teamFromNewGameDialog Team to be edited
     * @param newGameDialog New Game Dialog reference
     */
    public TeamEditor(Team teamFromNewGameDialog, NewGame newGameDialog) {

        initComponents();
        this.setLocationRelativeTo(getRootPane());

        this.newGameDialog=newGameDialog;
        currentTeam=teamFromNewGameDialog;
        teamManager=newGameDialog.teamManager;
        mvpBt.setVisible(false);

        newModel=new DefaultTableModel();
        newModel.addColumn("Type");
        newModel.addColumn("MA");
        newModel.addColumn("ST");
        newModel.addColumn("AG");
        newModel.addColumn("AV");
        newModel.addColumn("PTS");
    }

    /**
     * Constructor to be invoked when an existing team has to be edited.
     * @param someExistingTeam Some team which already exists
     */
    public TeamEditor(Team someExistingTeam) {

        initComponents();
        this.setLocationRelativeTo(getRootPane());
        editTeamMode=true;
        currentTeam=someExistingTeam;
        teamManager.setTeam(currentTeam);
        refreshExistingTeamData();
        cancelButton.setVisible(false);
        doneButton.setVisible(false);
        mvpBt.setVisible(true);
    }

    public void refreshExistingTeamData()
    {
        newModel=new DefaultTableModel();
        newModel.addColumn("Type");
        newModel.addColumn("MA");
        newModel.addColumn("ST");
        newModel.addColumn("AG");
        newModel.addColumn("AV");
        newModel.addColumn("PTS");

        Object newData[]={" ",0,0,0,0,0};

        for(Player p:currentTeam.getPlayers())
        {
            newData[0]=p.getType();
            newData[1]=p.getMa();
            newData[2]=p.getSt();
            newData[3]=p.getAg();
            newData[4]=p.getAv();
            newData[5]=p.getPoints();
            newModel.addRow(newData);
        }

        jTable1.setModel(newModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addPlayerButton = new javax.swing.JButton();
        deletePlayerButton = new javax.swing.JButton();
        editBt = new javax.swing.JButton();
        mvpBt = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Team Editor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Team"));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText("Name:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel6.setText("Team Name");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel2.setText("Type:");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel7.setText("Team Type");
        jLabel7.setName("jLabel7"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(jLabel1)
                .add(18, 46, Short.MAX_VALUE)
                .add(jLabel6)
                .add(57, 57, 57)
                .add(jLabel2)
                .add(18, 63, Short.MAX_VALUE)
                .add(jLabel7)
                .add(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel6)
                    .add(jLabel2)
                    .add(jLabel7))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Statistics"));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText("Wins");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Ties");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Losses");
        jLabel5.setName("jLabel5"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setText("0");
        jTextField1.setAlignmentX(2);
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setText("0");
        jTextField2.setAlignmentX(2);
        jTextField2.setName("jTextField2"); // NOI18N

        jTextField3.setEditable(false);
        jTextField3.setText("0");
        jTextField3.setAlignmentX(2);
        jTextField3.setName("jTextField3"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(28, 28, 28)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Statistics"));
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        addPlayerButton.setText("Add");
        addPlayerButton.setName("addPlayerButton"); // NOI18N
        addPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerButtonActionPerformed(evt);
            }
        });

        deletePlayerButton.setText("Delete");
        deletePlayerButton.setName("deletePlayerButton"); // NOI18N
        deletePlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePlayerButtonActionPerformed(evt);
            }
        });

        editBt.setText("Edit");
        editBt.setName("editBt"); // NOI18N
        editBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtActionPerformed(evt);
            }
        });

        mvpBt.setText("MVP");
        mvpBt.setName("mvpBt"); // NOI18N
        mvpBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mvpBtActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                        .add(mvpBt)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 113, Short.MAX_VALUE)
                        .add(editBt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(addPlayerButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deletePlayerButton)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(new java.awt.Component[] {addPlayerButton, deletePlayerButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(deletePlayerButton)
                    .add(addPlayerButton)
                    .add(editBt)
                    .add(mvpBt))
                .addContainerGap())
        );

        doneButton.setText("Done");
        doneButton.setName("doneButton"); // NOI18N
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.setName("saveButton"); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(saveButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 191, Short.MAX_VALUE)
                        .add(doneButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cancelButton)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {jPanel1, jPanel2, jPanel3}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.linkSize(new java.awt.Component[] {cancelButton, doneButton, saveButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(doneButton)
                    .add(cancelButton)
                    .add(saveButton))
                .add(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    /**
     * Handler for Add button for adding players to a team
     * @param evt
     */
    private void addPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayerButtonActionPerformed
        playerEditor=new PlayerEditor(this);
        playerEditor.refreshEditor();
        playerEditor.setModal(true);
        playerEditor.setVisible(true);
    }//GEN-LAST:event_addPlayerButtonActionPerformed

    /**
     * Handler for Done button for committing a team
     * @param evt
     */
    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        
        if(currentTeam.getID()==1)
        {
            newGameDialog.commitTeamA();
        }
        else
        {
            newGameDialog.commitTeamB();
        }
        this.setVisible(false);
    }//GEN-LAST:event_doneButtonActionPerformed

    /**
     * Handler for Delete button for deleting players of a team
     * @param evt
     */
    private void deletePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePlayerButtonActionPerformed
        if(jTable1.getRowCount()>0 && (currentTeam.getPlayers()).indexOf(currentTeam.getPlayer(jTable1.getSelectedRow()))>0)
        {
            currentTeam.removePlayer(jTable1.getSelectedRow());
            newModel.removeRow(jTable1.getSelectedRow());
        }
    }//GEN-LAST:event_deletePlayerButtonActionPerformed

    /**
     * Handler for Cancel button to cancel team edition
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Handler for Save button for saving a team to a file
     * @param evt
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            teamManager.saveTeam();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TeamEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TeamEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(editTeamMode)
        {
            this.setVisible(false);
        }
        else
        {
            saveButton.setEnabled(false);
            saveButton.setText("Team Saved");
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void editBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtActionPerformed
        if(jTable1.getSelectedRow()>=0)
        {
            playerEditor=new PlayerEditor(currentTeam.getPlayer(jTable1.getSelectedRow()), this);
            playerEditor.refreshEditorWithPlayer();
            playerEditor.setModal(true);
            playerEditor.setVisible(true);
        }
    }//GEN-LAST:event_editBtActionPerformed

    private void mvpBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mvpBtActionPerformed
        if(jTable1.getSelectedRow()>=0)
        {
            currentTeam.getPlayer(jTable1.getSelectedRow()).selectedMostValued();
            mvpBt.setVisible(false);
            refreshExistingTeamData();
        }
    }//GEN-LAST:event_mvpBtActionPerformed

    /**
     * Method to populate the Editor with values depending upon the selected team to be created
     */
    public void refreshEditor()
    {
        jLabel6.setText(currentTeam.getName());
        jTextField1.setText(String.valueOf(currentTeam.getWins()));
        jTextField2.setText(String.valueOf(currentTeam.getTies()));
        jTextField3.setText(String.valueOf(currentTeam.getLooses()));
        if(currentTeam.getType()==TeamType.HUMAN_TYPE)
        {
            jLabel7.setText("Humans");
        }
        else if(currentTeam.getType()==TeamType.ELF_TYPE)
        {
            jLabel7.setText("Elves");
        }
        else{
            jLabel7.setText("Orcs");
        }

        jTable1.setModel(newModel);
    }

    /**
     * Method to add player to the team's table of players
     */
    public void addPlayerToEditor()
    {
        String type=(currentTeam.getPlayer(currentTeam.getTotalPlayers()-1)).getType().toString();
        int ma=(currentTeam.getPlayer(currentTeam.getTotalPlayers()-1)).getMa();
        int st=(currentTeam.getPlayer(currentTeam.getTotalPlayers()-1)).getSt();
        int ag=(currentTeam.getPlayer(currentTeam.getTotalPlayers()-1)).getAg();
        int av=(currentTeam.getPlayer(currentTeam.getTotalPlayers()-1)).getAv();
        int pts=(currentTeam.getPlayer(currentTeam.getTotalPlayers()-1)).getPoints();
        
        Object newData[]={" ",0,0,0,0,0};
        newData[0]=type;
        newData[1]=ma;
        newData[2]=st;
        newData[3]=ag;
        newData[4]=av;
        newData[5]=pts;
        newModel.addRow(newData);
        //this.refreshEditor();
    }

    public Team getTeam()
    {
        return currentTeam;
    }

    public TeamManager getManager()
    {
        return teamManager;
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeamEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPlayerButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deletePlayerButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JButton editBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton mvpBt;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

}
