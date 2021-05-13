/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author hoang
 */
public class Grant_JFrame extends javax.swing.JFrame {
    private DefaultComboBoxModel cbb_model;
    Connection conn;
    String selectedCbb;
    
    public Grant_JFrame() throws SQLException {
        this.conn = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","U_QLBV_QUANTRIVIEN","1234");
        String sql = "ALTER SESSION SET \"_ORACLE_SCRIPT\" = TRUE";
        // Tạo đối tượng thực thi câu lệnh Select
        Statement st = (Statement) conn.createStatement();
        // Thực thi
        st.executeQuery(sql);
        this.loadComboBox_User_Role();
        initComponents();
    }
    
    public void loadComboBox_User_Role() throws SQLException{
        ResultSet rs1, rs2 = null;
        // Câu lệnh xem dữ liệu
        String sql01 = "select distinct USERNAME from dba_users WHERE USERNAME LIKE ('%QLBV%')";
        // Tạo đối tượng thực thi câu lệnh Select
        Statement st = (Statement) conn.createStatement();
        // Thực thi
        rs1 = st.executeQuery(sql01);
        Vector data = new Vector();
        //cbb_model.setRowCount(0);
        // Nếu sách không tồn tại
        if (rs1.isBeforeFirst() == false) {
            JOptionPane.showMessageDialog(this, "Chưa có thông tin obj privs!");
            return;
        }
        // Trong khi chưa hết dữ liệu
        while (rs1.next()) {
            // Dữ liệu cột user/rolename
            data.add(rs1.getString("USERNAME"));
            System.out.println(data);
            // Thêm một dòng vào table model
        }

        //--------------------LOAD DATA LEN tblModel_ObjectPrivs01 OBJECT PRIVS (MUC TABLE)-------------------   
        String sql02 = "select distinct role from dba_roles WHERE role LIKE ('%QLBV%')";
        // Tạo đối tượng thực thi câu lệnh Select
        // st = (Statement) conn.createStatement();
        // Thực thi
        rs2 = st.executeQuery(sql02);
        //data = null;
        while (rs2.next()) {
            // Dữ liệu cột user/rolename
            data.add(rs2.getString("ROLE"));
            System.out.println(data);
            // Thêm một dòng vào table model
        }
        cbb_model = new DefaultComboBoxModel(data);    
    }
    
    public void Grant_PR_to_UR(String username) throws SQLException{
        String pr_name = txtPri_Role.getText();
        String sql = "{call sp_GRANT_PR_TO_UR(?, ?)}";   
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, pr_name);
            cstm.setString(2, username);
            // Executes the Procedure statement 
           cstm.executeQuery();
        }
        catch (SQLException ex) {
                Logger.getLogger(Grant_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton_Grant = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        txtPri_Role = new javax.swing.JTextField();
        jComboBox_UR = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton_jumpToRevoke = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jButton_Grant.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Grant.setText("GRANT");
        jButton_Grant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GrantActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("PRIVILEGE/ROLE:");

        jComboBox_UR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox_UR.setModel(cbb_model);
        jComboBox_UR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_URActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("USER/ROLE:");

        jButton_jumpToRevoke.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_jumpToRevoke.setText("toREVOKE");
        jButton_jumpToRevoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_jumpToRevokeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtPri_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_jumpToRevoke)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_Grant, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_UR, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPri_Role, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Grant)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox_UR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addComponent(jButton_jumpToRevoke)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_jumpToRevokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_jumpToRevokeActionPerformed
        // JUMP TO REVOKE FRAME
        this.dispose();
        try {
            Revoke_JFrame revoke= new Revoke_JFrame();
            revoke.show();
        } catch (SQLException ex) {
            Logger.getLogger(Grant_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_jumpToRevokeActionPerformed

    private void jButton_GrantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GrantActionPerformed
        try {
            Grant_PR_to_UR(this.selectedCbb);
            JOptionPane.showMessageDialog(this, "Granted successfully");
        } catch (SQLException ex) {
            Logger.getLogger(Grant_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_GrantActionPerformed

    private void jComboBox_URActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_URActionPerformed
        selectedCbb= (String) jComboBox_UR.getSelectedItem();
       // System.out.println(selectedCbb);
    }//GEN-LAST:event_jComboBox_URActionPerformed

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
            java.util.logging.Logger.getLogger(Grant_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grant_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grant_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grant_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Grant_JFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Grant_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Grant;
    private javax.swing.JButton jButton_jumpToRevoke;
    private javax.swing.JComboBox<String> jComboBox_UR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtPri_Role;
    // End of variables declaration//GEN-END:variables
}
