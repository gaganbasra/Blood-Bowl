/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoadGame.java
 *
 * Created on Mar 4, 2011, 2:47:34 PM
 */
package bloodbowl.gui.dialog;

import bloodbowl.engine.BloodBowlException;
import bloodbowl.engine.Game;
import bloodbowl.gui.map.BloodBowl;
import bloodbowl.model.managers.GameManager;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * The dialog class for loading a saved game.
 * @author Rishinder
 */
public class LoadGame extends javax.swing.JFrame {

    private GameManager gameManager;
    private BloodBowl gameMap;
    private Game gameEngine;

    /** 
     * Creates new Dialog
     */
    public LoadGame() {
        initComponents();
        gameManager = GameManager.getInstance();
        populateSavedGames();

        this.setLocationRelativeTo(getRootPane());
    }

    public final void populateSavedGames() {
        DefaultComboBoxModel newCBModel = new DefaultComboBoxModel((gameManager.getSavedGames()).toArray());
        loadGameCombo.setModel(newCBModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadGameCombo = new javax.swing.JComboBox();
        loadGameButton = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loadGameCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        loadGameButton.setText("Load Game");
        loadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGameButtonActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(loadGameCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(loadGameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loadGameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadGameButton)
                    .addComponent(Cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CancelActionPerformed

    private void loadGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameButtonActionPerformed
        try {
            if (loadGameCombo.getSelectedItem() != null) {
                gameEngine = gameManager.restoreGame(loadGameCombo.getSelectedItem().toString());

                gameMap = new BloodBowl(gameEngine);
                gameMap.setVisible(true);

                this.setVisible(false);
            }
        } catch (IOException ex) {
             JOptionPane.showConfirmDialog(this, ex.getMessage(), "BloodBowl", JOptionPane.OK_OPTION);
        } catch (FontFormatException ex) {
            JOptionPane.showConfirmDialog(this, ex.getMessage(), "BloodBowl", JOptionPane.OK_OPTION);
        } catch (BloodBowlException ex) {
            JOptionPane.showConfirmDialog(this, ex.getMessage(), "BloodBowl", JOptionPane.OK_OPTION);
        }



//        Simulator sim = new Simulator(gameEngine);
//        sim.drawMap();
    }//GEN-LAST:event_loadGameButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LoadGame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton loadGameButton;
    private javax.swing.JComboBox loadGameCombo;
    // End of variables declaration//GEN-END:variables
}