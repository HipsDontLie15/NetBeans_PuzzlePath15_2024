
import custom_classes.FrameUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Kai Jie
 */
public class LoginFrame extends javax.swing.JFrame {
    final private MainLauncher mainLauncher; 
    private String fname, lname, email, password;
    private char[] passwordChars;
    
    /**
     * Creates new form SecondFrame
     * @param mainLauncher
     */
    public LoginFrame(MainLauncher mainLauncher) {
        initComponents();
        
        // Use the utility method to set title and icon
        FrameUtils.setFrameTitleAndIcon(this, "PuzzlePath 15", "/resources/15.png");
        
        this.mainLauncher = mainLauncher;        

        jPanel1.setOpaque( false );
        jPanel2.setOpaque( false );
        FrameUtils.addHoverEffect(btn_submit);
    }
    
    public final void clearTextLogin() {
        txt_email.setText("");
        txt_password.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbl_title = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_submit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        txt_email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(115, 54, 15));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 204, 102), new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(null);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/login_sign.png"))); // NOI18N
        jPanel3.add(jLabel5);
        jLabel5.setBounds(30, 10, 100, 100);

        lbl_title.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 0));
        lbl_title.setText("Login");
        jPanel3.add(lbl_title);
        lbl_title.setBounds(10, 110, 140, 67);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 150, 190));

        btn_back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(51, 0, 0));
        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 115, 30));

        btn_submit.setBackground(new java.awt.Color(255, 153, 51));
        btn_submit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_submit.setForeground(new java.awt.Color(51, 0, 0));
        btn_submit.setText("Submit");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });
        getContentPane().add(btn_submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 190, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_password)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 176, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_password, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 260, 80));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Email");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(170, 211, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_email)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 70, 260, -1));

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

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        clearTextLogin();
        FrameUtils.goToFrame(this, mainLauncher);
    }//GEN-LAST:event_btn_backActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrameUtils.goToFrame(this, mainLauncher);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        // Retrieve input values
        email = txt_email.getText();
        passwordChars = txt_password.getPassword(); // Get the password as a char array
        password = new String(passwordChars); // Convert char array to String

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill up both email and password fields.", 
                "Login Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection con = FrameUtils.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, email);  //VALUES (1, ?)
            pstmt.setString(2, password);  //VALUES (1, 2)

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Successfully logged in
                    JOptionPane.showMessageDialog(null,
                            "Login successful!",
                            "Login Success",   
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    fname = rs.getString("first_name");
                    lname = rs.getString("last_name");
                    
                    mainLauncher.setUserName(fname, lname);                    
                    mainLauncher.setUserEmail(email);

                    clearTextLogin();
                    FrameUtils.goToFrame(this, mainLauncher);
                    
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Invalid email or password.", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "An error occurred: " + e.getMessage(), 
                "Login Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_submitActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        MainLauncher mainLauncher = new MainLauncher();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame(mainLauncher).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_submit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_password;
    // End of variables declaration//GEN-END:variables
}
