import custom_classes.FrameUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Kai Jie
 */
public class ScoreFrame extends javax.swing.JFrame {
    final private MainLauncher mainLauncher; 

    /**
     * Creates new form ScoreFrame
     * @param mainLauncher
     */
    public ScoreFrame(MainLauncher mainLauncher) {
        initComponents();
        
        this.mainLauncher = mainLauncher;
        loadScores(); // Load scores when the frame is initialized
    }
    
    // Method to load scores from the database
    public final void loadScores() {
        loadLevelScores(jTable1, "1");
        loadLevelScores(jTable2, "2");
        loadLevelScores(jTable3, "3");
    }

    // Method to load scores for a specific level
    public void loadLevelScores(javax.swing.JTable table, String level) {
        // SQL query with one parameter for level
        String query = "SELECT u.first_name, u.last_name, s.level, s.moves, s.time_seconds, s.timestamp FROM score s JOIN user u ON s.user_id = u.id WHERE s.level = ? ORDER BY s.moves ASC";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        try (Connection con = FrameUtils.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            // Correct parameter index is 1 because we have only one placeholder (?)
            pstmt.setString(1, level);

            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hasData = false; // Flag to check if we have data

                while (rs.next()) {
                    // If we have data, set the flag to true
                    hasData = true;

                    // Populate row data
                    Object[] row = {
                        model.getRowCount() + 1, // Automatically number rows
                        rs.getString("first_name") + " " + rs.getString("last_name"),
                        rs.getString("time_seconds"),
                        rs.getInt("moves"),
                        rs.getString("timestamp")
                    };
                    model.addRow(row);
                }

                // If no data was retrieved, add a single row with "No data" message
                if (!hasData) {
                    Object[] noDataRow = {"", "No data available for this level", "", "", ""};
                    model.addRow(noDataRow);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "An error occurred while loading scores: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btn_back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 102), new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        lbl_title.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 0));
        lbl_title.setText("Score Board");
        jPanel1.add(lbl_title);
        lbl_title.setBounds(60, 0, 272, 64);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Player", "Time", "Moves", "Time Stamp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(25);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable1.getColumnModel().getColumn(2).setMinWidth(50);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(3).setMinWidth(40);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(45);
        }

        jTabbedPane1.addTab("Level 1", jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Player", "Time", "Moves", "Time Stamp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setEnabled(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(25);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable2.getColumnModel().getColumn(2).setMinWidth(50);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable2.getColumnModel().getColumn(3).setMinWidth(40);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(45);
        }

        jTabbedPane1.addTab("Level 2", jScrollPane2);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Player", "Time", "Moves", "Time Stamp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setEnabled(false);
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setMinWidth(25);
            jTable3.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable3.getColumnModel().getColumn(2).setMinWidth(50);
            jTable3.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable3.getColumnModel().getColumn(3).setMinWidth(40);
            jTable3.getColumnModel().getColumn(3).setMaxWidth(45);
        }

        jTabbedPane1.addTab("Level 3", jScrollPane3);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(10, 60, 410, 200);

        btn_back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(51, 0, 0));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        jPanel1.add(btn_back);
        btn_back.setBounds(160, 270, 115, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/win.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(320, 0, 90, 90);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 430, 310));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/main_bg.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, -1));

        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/menu.png"))); // NOI18N
        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close.png"))); // NOI18N
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        FrameUtils.goToFrame(this, mainLauncher);
    }//GEN-LAST:event_btn_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        MainLauncher mainLauncher = new MainLauncher();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ScoreFrame(mainLauncher).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbl_title;
    // End of variables declaration//GEN-END:variables
}
