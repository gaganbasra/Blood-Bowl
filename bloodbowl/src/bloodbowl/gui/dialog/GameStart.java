/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GameStart.java
 *
 * Created on Jan 23, 2011, 9:18:01 PM
 */
package bloodbowl.gui.dialog;

import java.awt.event.WindowEvent;

/**
 * Class for the main Dialog when the game starts
 * @author Rishinder
 */
public class GameStart extends javax.swing.JFrame {

    private NewGame newGameEditor;
    private LoadGame loadGame;

    /** Creates new form GameStart */
    public GameStart() {

        initComponents();
        this.setLocationRelativeTo(getRootPane());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        exitButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        newButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loadButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 254, 254));
        setForeground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        exitButton.setBounds(340, 360, 120, 23);
        jLayeredPane1.add(exitButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bloodbowl/resources/Warrior-03-june.gif"))); // NOI18N
        jLabel4.setBounds(320, 80, 120, 140);
        jLayeredPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        newButton.setText("New Game");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        newButton.setBounds(340, 330, 120, 23);
        jLayeredPane1.add(newButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bloodbowl/resources/IntroLogo.gif"))); // NOI18N
        jLabel1.setBounds(190, 10, 410, 70);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setText("SOEN 6441 - Team 7");
        jLabel3.setBounds(340, 230, 150, 14);
        jLayeredPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        loadButton.setText("Load Game");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        loadButton.setBounds(340, 300, 120, 23);
        jLayeredPane1.add(loadButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bloodbowl/resources/mainPageImage1.jpg"))); // NOI18N
        jLabel2.setBounds(-6, 4, 770, 430);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method to launch the New Game Dialog
     */
    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed

        newGameEditor = new NewGame();
        newGameEditor.setModal(true);
        newGameEditor.setVisible(true);
        if (newGameEditor.isStartNewGame()) {
            this.setVisible(false);
        }
    }//GEN-LAST:event_newButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * Load saved game
     * @param evt
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        loadGame = new LoadGame();
        loadGame.setVisible(true);

        this.setVisible(false);
    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
//                try {
//
//                    System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
//                    UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
//                } catch (UnsupportedLookAndFeelException ex) {
//                    Logger.getLogger(GameStart.class.getName()).log(Level.SEVERE, null, ex);
//                }
                new GameStart().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newButton;
    // End of variables declaration//GEN-END:variables
}
