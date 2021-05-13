/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Vector;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hoang
 */
public class Frm_User_Menu extends javax.swing.JFrame {
    private Connection conn;
    private String header1[]= {"ID", "USERNAME"};
    private DefaultTableModel tblmodel1= new DefaultTableModel(header1, 0);
    private String header2[]= {"ID", "ROLENAME"};
    private DefaultTableModel tblmodel2= new DefaultTableModel(header2, 0);
    
    public Frm_User_Menu() throws SQLException {
        this.conn = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","U_QLBV_QUANTRIVIEN","1234");
        String sql = "ALTER SESSION SET \"_ORACLE_SCRIPT\" = TRUE";
        // Tạo đối tượng thực thi câu lệnh Select
        Statement st = (Statement) conn.createStatement();
        // Thực thi
        st.executeQuery(sql);
        loadTable_USERS();
        loadTable_ROLES();
        initComponents();
    }
    
    // LOAD BANG USERS

    

            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        Users = new javax.swing.JPanel();
        jLabel_Password = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        Delete_User_Btn = new javax.swing.JButton();
        Create_User_Btn = new javax.swing.JButton();
        jLabel_Username = new javax.swing.JLabel();
        jScrollPanel = new javax.swing.JScrollPane();
        jTable_User = new javax.swing.JTable();
        Update_User_Btn = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        Grant_Btn = new javax.swing.JButton();
        Roles = new javax.swing.JPanel();
        jLabel_Password1 = new javax.swing.JLabel();
        txtPassword1 = new javax.swing.JTextField();
        Delete_Role_Btn1 = new javax.swing.JButton();
        Create_Role_Btn1 = new javax.swing.JButton();
        jLabel_Rolename = new javax.swing.JLabel();
        jScrollPanel1 = new javax.swing.JScrollPane();
        jTable_Role = new javax.swing.JTable();
        Update_Role_Btn1 = new javax.swing.JButton();
        txtRolename = new javax.swing.JTextField();
        Grant_Btn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create user/role");
        setBackground(new java.awt.Color(204, 255, 255));
        setSize(new java.awt.Dimension(800, 400));

        Users.setBackground(new java.awt.Color(204, 255, 255));

        jLabel_Password.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Password.setText("Password:");

        Delete_User_Btn.setBackground(new java.awt.Color(204, 204, 204));
        Delete_User_Btn.setText("DELETE");
        Delete_User_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_User_BtnActionPerformed(evt);
            }
        });

        Create_User_Btn.setBackground(new java.awt.Color(204, 204, 204));
        Create_User_Btn.setText("INSERT");
        Create_User_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_User_BtnActionPerformed(evt);
            }
        });

        jLabel_Username.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Username.setText("Username:");

        jTable_User.setModel(tblmodel1);
        jTable_User.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_UserMouseClicked(evt);
            }
        });
        jScrollPanel.setViewportView(jTable_User);

        Update_User_Btn.setBackground(new java.awt.Color(204, 204, 204));
        Update_User_Btn.setText("UPDATE");
        Update_User_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_User_BtnActionPerformed(evt);
            }
        });

        Grant_Btn.setBackground(new java.awt.Color(204, 204, 204));
        Grant_Btn.setText("toGRANT");
        Grant_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Grant_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UsersLayout = new javax.swing.GroupLayout(Users);
        Users.setLayout(UsersLayout);
        UsersLayout.setHorizontalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanel)
            .addGroup(UsersLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Password)
                    .addComponent(jLabel_Username))
                .addGap(10, 10, 10)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UsersLayout.createSequentialGroup()
                        .addComponent(Create_User_Btn)
                        .addGap(18, 18, 18)
                        .addComponent(Update_User_Btn))
                    .addGroup(UsersLayout.createSequentialGroup()
                        .addComponent(Delete_User_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Grant_Btn)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        UsersLayout.setVerticalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLayout.createSequentialGroup()
                .addComponent(jScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Username)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Create_User_Btn)
                    .addComponent(Update_User_Btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Password)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete_User_Btn)
                    .addComponent(Grant_Btn))
                .addGap(15, 15, 15))
        );

        jTabbedPane2.addTab("Users", Users);

        Roles.setBackground(new java.awt.Color(204, 255, 255));

        jLabel_Password1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Password1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Password1.setText("Password:");

        Delete_Role_Btn1.setBackground(new java.awt.Color(204, 204, 204));
        Delete_Role_Btn1.setText("DELETE");
        Delete_Role_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_Role_Btn1ActionPerformed(evt);
            }
        });

        Create_Role_Btn1.setBackground(new java.awt.Color(204, 204, 204));
        Create_Role_Btn1.setText("INSERT");
        Create_Role_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_Role_Btn1ActionPerformed(evt);
            }
        });

        jLabel_Rolename.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Rolename.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel_Rolename.setText("Rolename:");

        jTable_Role.setModel(tblmodel2);
        jTable_Role.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_RoleMouseClicked(evt);
            }
        });
        jScrollPanel1.setViewportView(jTable_Role);

        Update_Role_Btn1.setBackground(new java.awt.Color(204, 204, 204));
        Update_Role_Btn1.setText("UPDATE");
        Update_Role_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_Role_Btn1ActionPerformed(evt);
            }
        });

        Grant_Btn1.setBackground(new java.awt.Color(204, 204, 204));
        Grant_Btn1.setText("toGRANT");
        Grant_Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Grant_Btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RolesLayout = new javax.swing.GroupLayout(Roles);
        Roles.setLayout(RolesLayout);
        RolesLayout.setHorizontalGroup(
            RolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanel1)
            .addGroup(RolesLayout.createSequentialGroup()
                .addGroup(RolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RolesLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel_Password1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RolesLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel_Rolename)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRolename, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Create_Role_Btn1)
                    .addComponent(Delete_Role_Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(RolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Grant_Btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Update_Role_Btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        RolesLayout.setVerticalGroup(
            RolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RolesLayout.createSequentialGroup()
                .addComponent(jScrollPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(RolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Rolename)
                    .addComponent(txtRolename, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Create_Role_Btn1)
                    .addComponent(Update_Role_Btn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Password1)
                    .addComponent(txtPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete_Role_Btn1)
                    .addComponent(Grant_Btn1))
                .addGap(15, 15, 15))
        );

        jTabbedPane2.addTab("Roles", Roles);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//////////////////////////////////////USER--------------------------------------
    private void loadTable_USERS(){
        String sql = "SELECT user_id, username FROM dba_users where username like '%QLBV%'";
        Statement st;
        try{            
            tblmodel1.setRowCount(0);
            st = (Statement)conn.createStatement();
           ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Vector data = new Vector();
                data.add(rs.getInt("user_id"));
                data.add(rs.getString("username"));
                tblmodel1.addRow(data);
            }
        }
        catch(Exception e){
            return;
        }
    }
    public void dropuser(int selectRow){
        String username= (String) jTable_User.getModel().getValueAt(selectRow, 1);
        String sql = "{call sp_DROP_USER(?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, username);
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }        
        this.loadTable_USERS();
        jTable_User.setModel(tblmodel1);
    }  
    public void updateUser(){
        //String username= (String) jTable_User.getModel().getValueAt(selectRow, 1);
        //txtUsername.setText(username);
        String username= (String)txtUsername.getText();
        String newPassword= (String)txtPassword.getText();
        String sql = "{call sp_update_USER(?, ?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, username);
            cstm.setString(2, newPassword);
            
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }        
        this.loadTable_USERS();
        jTable_User.setModel(tblmodel1);
    }
    
    
    //////////////////////////////////////ROLE--------------------------------------
    private void loadTable_ROLES(){
        String sql = "SELECT role_id, role FROM dba_roles where role like '%QLBV%'";
        Statement st;
        try{            
            tblmodel2.setRowCount(0);
            st = (Statement)conn.createStatement();
           ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Vector data = new Vector();
                data.add(rs.getInt("role_id"));
                data.add(rs.getString("role"));
                tblmodel2.addRow(data);
            }
        }
        catch(Exception e){
            return;
        }
    }
    public void droprole(int selectRow){
        String username= (String) jTable_Role.getModel().getValueAt(selectRow, 1);
        String sql = "{call sp_DROP_ROLE(?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, username);
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }        
        this.loadTable_ROLES();
        jTable_Role.setModel(tblmodel2);
    }  
    public void updateRole(){
        //String username= (String) jTable_User.getModel().getValueAt(selectRow, 1);
        //txtUsername.setText(username);
        String username= (String)txtRolename.getText();
        String newPassword= (String)txtPassword1.getText();
        String sql = "{call sp_update_ROLE(?, ?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, username);
            cstm.setString(2, newPassword);
            
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }        
        this.loadTable_USERS();
        jTable_Role.setModel(tblmodel2);
    }
    
    
    
    private void Create_User_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_User_BtnActionPerformed
        String username= (String)txtUsername.getText();
        String password = (String)txtPassword.getText();
        if(username.equals("") || password.equals("")){
            JOptionPane.showMessageDialog(this, "username or password is empty");
            return;
        }
        String sql = "{call sp_CREATE_USER(?, ?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, username);
            cstm.setString(2, password);
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }        
        this.loadTable_USERS();
        jTable_User.setModel(tblmodel1);
    }//GEN-LAST:event_Create_User_BtnActionPerformed

    private void Update_User_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_User_BtnActionPerformed
        //int selectRow= jTable_User.getSelectedRow();
        this.updateUser();
        this.loadTable_USERS();
        jTable_User.setModel(tblmodel1);
    }//GEN-LAST:event_Update_User_BtnActionPerformed

    private void jTable_UserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_UserMouseClicked
        String username= (String) jTable_User.getModel().getValueAt(jTable_User.getSelectedRow(), 1);
        txtUsername.setText(username);
    }//GEN-LAST:event_jTable_UserMouseClicked

    private void Grant_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Grant_BtnActionPerformed
        this.dispose();
        Grant_JFrame grant = null;
        try {
            grant = new Grant_JFrame();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        grant.show();
    }//GEN-LAST:event_Grant_BtnActionPerformed

    private void Delete_User_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_User_BtnActionPerformed
        int selectRow= jTable_User.getSelectedRow();
        this.dropuser(selectRow);
        JOptionPane.showMessageDialog(this,"Drop successfully");
    }//GEN-LAST:event_Delete_User_BtnActionPerformed

    private void Delete_Role_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_Role_Btn1ActionPerformed
        int selectRow= jTable_Role.getSelectedRow();
        this.droprole(selectRow);
        JOptionPane.showMessageDialog(this,"Drop successfully");
    }//GEN-LAST:event_Delete_Role_Btn1ActionPerformed

    private void Create_Role_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_Role_Btn1ActionPerformed
        String username= (String)txtRolename.getText();
        String password = (String)txtPassword1.getText();
        if(username.equals("")){
            JOptionPane.showMessageDialog(this, "rolename is empty");
            return;
        }
        if(password.equals("")){
            String sql = "{call sp_CREATE_ROLE_NO_PASS(?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, username);
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else{
        String sql = "{call sp_CREATE_ROLE(?, ?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, username);
            cstm.setString(2, password);
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.loadTable_ROLES();
        jTable_Role.setModel(tblmodel2);
        
    }//GEN-LAST:event_Create_Role_Btn1ActionPerformed

    private void jTable_RoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_RoleMouseClicked
        String username= (String) jTable_Role.getModel().getValueAt(jTable_Role.getSelectedRow(), 1);
        txtRolename.setText(username);
    }//GEN-LAST:event_jTable_RoleMouseClicked

    private void Update_Role_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_Role_Btn1ActionPerformed
        //int selectRow= jTable_User.getSelectedRow();
        this.updateRole();
        this.loadTable_ROLES();
        jTable_Role.setModel(tblmodel2);
    }//GEN-LAST:event_Update_Role_Btn1ActionPerformed

    private void Grant_Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Grant_Btn1ActionPerformed
        this.dispose();
        Grant_JFrame grant = null;
        try {
            grant = new Grant_JFrame();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        grant.show();
    }//GEN-LAST:event_Grant_Btn1ActionPerformed
                        
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
            java.util.logging.Logger.getLogger(Frm_User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_User_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Frm_User_Menu().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Create_Role_Btn1;
    private javax.swing.JButton Create_User_Btn;
    private javax.swing.JButton Delete_Role_Btn1;
    private javax.swing.JButton Delete_User_Btn;
    private javax.swing.JButton Grant_Btn;
    private javax.swing.JButton Grant_Btn1;
    private javax.swing.JPanel Roles;
    private javax.swing.JButton Update_Role_Btn1;
    private javax.swing.JButton Update_User_Btn;
    private javax.swing.JPanel Users;
    private javax.swing.JLabel jLabel_Password;
    private javax.swing.JLabel jLabel_Password1;
    private javax.swing.JLabel jLabel_Rolename;
    private javax.swing.JLabel jLabel_Username;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JScrollPane jScrollPanel1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable_Role;
    private javax.swing.JTable jTable_User;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPassword1;
    private javax.swing.JTextField txtRolename;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
