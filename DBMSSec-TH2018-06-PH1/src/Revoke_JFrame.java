
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

/*
 *
 * @author VOCONGHUAN
 */
public class Revoke_JFrame extends javax.swing.JFrame {
    private String header1[] = {"User/Rolename", "Privileges"};
    private DefaultTableModel tblModel_Sys_Privs = new DefaultTableModel(header1, 0);
    private String header2[] = {"User/Rolename", "Objectname", "Column", "Privileges"};
    private DefaultTableModel tblModel_ObjectPrivs = new DefaultTableModel(header2, 0);
    private String header3[] = {"User/Rolename", "granted_role"};
    private DefaultTableModel tblModel_Role = new DefaultTableModel(header3, 0);
    private DefaultComboBoxModel cbb_model;
    Connection conn;

        //load data len table 1
    public void loadIntotblModel_Sys_Privs() throws SQLException{
            //---------------LOAD DATA LEN tblModel_Sys_Privs OBJECT PRIVS (MUC COT)-------------------
        ResultSet rs1 = null;
         // Câu lệnh xem dữ liệu
         String sql01 = "select GRANTEE, privilege from DBA_SYS_PRIVS where grantee like '%QLBV%'";
         // Tạo đối tượng thực thi câu lệnh Select
         Statement st = (Statement) conn.createStatement();
          // Thực thi
         rs1 = st.executeQuery(sql01);
         Vector data = null;
         tblModel_Sys_Privs.setRowCount(0);
         // Nếu sách không tồn tại
         if (rs1.isBeforeFirst() == false) {
           JOptionPane.showMessageDialog(this, "No data on system privilege");
           return;
         }
         // Trong khi chưa hết dữ liệu
         while (rs1.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs1.getString("GRANTEE"));
           // Dữ liệu cột object name
           data.add(rs1.getString("PRIVILEGE"));
           // Thêm một dòng vào table model
            tblModel_Sys_Privs.addRow(data);
         }
      }
        //load data len table 2
    public void loadIntotblModel_ObjectPrivs() throws SQLException{
            //---------------LOAD DATA LEN tblModel_Sys_Privs OBJECT PRIVS (MUC COT)-------------------
        ResultSet rs1, rs2 = null;
         // Câu lệnh xem dữ liệu
         String sql01 = "select Grantee, TABLE_NAME, COLUMN_NAME, PRIVILEGE from DBA_COL_PRIVS where Grantee LIKE '%QLBV%'";
         // Tạo đối tượng thực thi câu lệnh Select
         Statement st = (Statement) conn.createStatement();
          // Thực thi
         rs1 = st.executeQuery(sql01);
         Vector data = null;
         tblModel_ObjectPrivs.setRowCount(0);
         // Nếu sách không tồn tại
         if (rs1.isBeforeFirst() == false) {
           JOptionPane.showMessageDialog(this, "No data obj privs!");
           return;
         }
         // Trong khi chưa hết dữ liệu
         while (rs1.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs1.getString("GRANTEE"));
           // Dữ liệu cột object name
           data.add(rs1.getString("TABLE_NAME"));
           // Dữ liệu cột column
              data.add(rs1.getString("COLUMN_NAME"));
           // Dữ liệu cột privs
           data.add(rs1.getString("PRIVILEGE"));
           // Thêm một dòng vào table model
            tblModel_ObjectPrivs.addRow(data);
         }
         
         //--------------------LOAD DATA LEN tblModel_Sys_Privs OBJECT PRIVS (MUC TABLE)-------------------   
         String sql02 = "select Grantee, TABLE_NAME, PRIVILEGE from DBA_TAB_PRIVS where Grantee LIKE '%QLBV%'";
        // Tạo đối tượng thực thi câu lệnh Select
         // st = (Statement) conn.createStatement();
          // Thực thi
         rs2 = st.executeQuery(sql02);
         //data = null;
         while (rs2.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs2.getString("GRANTEE"));
           // Dữ liệu cột object name
           data.add(rs2.getString("TABLE_NAME"));
           // Dữ liệu cột column
              data.add(null);
           // Dữ liệu cột privs
           data.add(rs2.getString("PRIVILEGE"));
           // Thêm một dòng vào table model
            tblModel_ObjectPrivs.addRow(data);
         }
         // Hiển thị dữ liệu lên table (minh se goi lenh nay trong method initComponents()
         //jTable_Obj_Privs.setModel(tblModel_Sys_Privs);
        }  
        //load data len table 3
    public void loadIntotblModel_Role() throws SQLException{
        //-----------LOAD DATA LEN tblModel_Role role
        ResultSet rs1 = null;

         // Câu lệnh xem dữ liệu
         String sql01 = "select GRANTEE, GRANTED_ROLE from DBA_ROLE_PRIVS where GRANTEE LIKE '%QLBV%'";
         // Tạo đối tượng thực thi câu lệnh Select
         Statement st = (Statement) conn.createStatement();
          // Thực thi
         rs1 = st.executeQuery(sql01);
         Vector data = null;
         tblModel_Role.setRowCount(0);
         // Nếu sách không tồn tại
         if (rs1.isBeforeFirst() == false) {
           JOptionPane.showMessageDialog(this, "Chưa có thông tin role!");
           return;
         }
         // Trong khi chưa hết dữ liệu
         while (rs1.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs1.getString("GRANTEE"));
           // Dữ liệu cột GRANTED_ROLE
           data.add(rs1.getString("GRANTED_ROLE"));
           // Thêm một dòng vào table model
            tblModel_Role.addRow(data);
         }
}
    
        //load data len combobox 1
    public void loadIntoComboBox() throws SQLException{
     //---------------LOAD DATA LEN tblModel_Sys_Privs OBJECT PRIVS (MUC COT)-------------------
        ResultSet rs1, rs2 = null;
         // Câu lệnh xem dữ liệu
         String sql01 = "select distinct USERNAME from dba_users WHERE USERNAME LIKE ('%QLBV%')";
         // Tạo đối tượng thực thi câu lệnh Select
         Statement st = (Statement) conn.createStatement();
          // Thực thi
         rs1 = st.executeQuery(sql01);
         Vector data= new Vector();
         //cbb_model.setRowCount(0);
         // Nếu sách không tồn tại
         if (rs1.isBeforeFirst() == false) {
           JOptionPane.showMessageDialog(this, "No User/Role");
           return;
         }
         // Trong khi chưa hết dữ liệu
         while (rs1.next()) {
           // Dữ liệu cột user/rolename
           data.add(rs1.getString("USERNAME"));
           System.out.println(data);
         }
         
         String sql02 = "select distinct role from dba_roles WHERE role LIKE ('%QLBV%')";
          // Thực thi
         rs2 = st.executeQuery(sql02);
         //data = null;
         while (rs2.next()) {
           // Dữ liệu cột user/rolename
            data.add(rs2.getString("ROLE"));
            System.out.println(data);
         }
        cbb_model= new DefaultComboBoxModel(data);
}
    
    //loc  bang 3 theo cbb3
     public void loadIntotblModel_with_Cbb01(String userRoleSelected) throws SQLException{
        //-----------LOAD DATA LEN tblModel_Role role
        ResultSet rs1 = null;
         // Câu lệnh xem dữ liệu
         String sql01 = "select GRANTEE, PRIVILEGE"
                 + " from DBA_SYS_PRIVS where GRANTEE = '" + userRoleSelected + "'";
        System.out.println(sql01);

          //Tạo đối tượng thực thi câu lệnh Select
         Statement st = (Statement) conn.createStatement();
          // Thực thi
         rs1 = st.executeQuery(sql01);
         Vector data;
         tblModel_Sys_Privs.setRowCount(0);
         // Nếu sách không tồn tại
         if (rs1.isBeforeFirst() == false) {
           //JOptionPane.showMessageDialog(this, "No role granted to "+ userRoleSelected);
           return;
         }
         // Trong khi chưa hết dữ liệu
         while (rs1.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs1.getString("GRANTEE"));
           // Dữ liệu cột GRANTED_ROLE
           data.add(rs1.getString("privilege"));
           // Thêm một dòng vào table model
            tblModel_Sys_Privs.addRow(data);
         }
}
    
        //loc  bang 2 theo cbb
     public void loadIntotblModel_with_Cbb02(String userRoleSelected) throws SQLException{
           //---------------LOAD DATA LEN tblModel_Sys_Privs OBJECT PRIVS (MUC COT)-------------------
        ResultSet rs1, rs2 = null;
         // Câu lệnh xem dữ liệu
         String sql01 = "select Grantee, TABLE_NAME, COLUMN_NAME, PRIVILEGE from DBA_COL_PRIVS where Grantee = '" + userRoleSelected + "'";
         // Tạo đối tượng thực thi câu lệnh Select
         Statement st = (Statement) conn.createStatement();
          // Thực thi
         rs1 = st.executeQuery(sql01);
         Vector data = null;
         tblModel_ObjectPrivs.setRowCount(0);
         // Nếu sách không tồn tại
         if (rs1.isBeforeFirst() == false) {
           return;
         }
         // Trong khi chưa hết dữ liệu
         while (rs1.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs1.getString("GRANTEE"));
           // Dữ liệu cột object name
           data.add(rs1.getString("TABLE_NAME"));
           // Dữ liệu cột column
              data.add(rs1.getString("COLUMN_NAME"));
           // Dữ liệu cột privs
           data.add(rs1.getString("PRIVILEGE"));
           // Thêm một dòng vào table model
            tblModel_ObjectPrivs.addRow(data);
         }
         
         //--------------------LOAD DATA LEN tblModel_Sys_Privs OBJECT PRIVS (MUC TABLE)-------------------   
         String sql02 = "select Grantee, TABLE_NAME, PRIVILEGE from DBA_TAB_PRIVS where Grantee = '" + userRoleSelected + "'";
          // Thực thi
         rs2 = st.executeQuery(sql02);
         //data = null;
         while (rs2.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs2.getString("GRANTEE"));
           // Dữ liệu cột object name
           data.add(rs2.getString("TABLE_NAME"));
           // Dữ liệu cột column
              data.add(null);
           // Dữ liệu cột privs
           data.add(rs2.getString("PRIVILEGE"));
           // Thêm một dòng vào table model
            tblModel_ObjectPrivs.addRow(data);
         }
}
    
    //loc  bang 3 theo cbb3
     public void loadIntotblModel_with_Cbb03(String userRoleSelected) throws SQLException{
        //-----------LOAD DATA LEN tblModel_Role role
        ResultSet rs1 = null;
         // Câu lệnh xem dữ liệu
         String sql01 = "select GRANTEE, GRANTED_ROLE from DBA_ROLE_PRIVS where GRANTEE = '" + userRoleSelected + "'";
        System.out.println(sql01);

          //Tạo đối tượng thực thi câu lệnh Select
         Statement st = (Statement) conn.createStatement();
          // Thực thi
         rs1 = st.executeQuery(sql01);
         Vector data;
         tblModel_Role.setRowCount(0);
         // Nếu sách không tồn tại
         if (rs1.isBeforeFirst() == false) {
          // JOptionPane.showMessageDialog(this, "No role granted to "+ userRoleSelected);
           return;
         }
         // Trong khi chưa hết dữ liệu
         while (rs1.next()) {
           data = new Vector();
           // Dữ liệu cột user/rolename
           data.add(rs1.getString("GRANTEE"));
           // Dữ liệu cột GRANTED_ROLE
           data.add(rs1.getString("GRANTED_ROLE"));
           // Thêm một dòng vào table model
            tblModel_Role.addRow(data);
         }
}
    
    public void revoke1(int selectRow){
        String user_role= (String) jTable_Sys_Privs.getModel().getValueAt(selectRow, 0);
        String sys_privs_name= (String) jTable_Sys_Privs.getModel().getValueAt(selectRow, 1);
        
        String sql = "{call sp_REVOKE_SYS_PRIVS(?, ?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, user_role);
            cstm.setString(2, sys_privs_name);
            System.out.println("REVOKE " + sys_privs_name + " FROM " + user_role); 
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
            }        
    } 
    public void revoke2(int selectRow){
        String user_role= (String) jTable_Obj_Privs.getModel().getValueAt(selectRow, 0);
        String objectname= (String) jTable_Obj_Privs.getModel().getValueAt(selectRow, 1);
        String column= (String) jTable_Obj_Privs.getModel().getValueAt(selectRow, 2);
        String privs= (String) jTable_Obj_Privs.getModel().getValueAt(selectRow, 3);
        
        String sql = "{call sp_REVOKE_OBJECT_PRIVS(?, ?, ?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, user_role);
            cstm.setString(2, objectname);
            cstm.setString(3, privs);
            //cstm.setString(4, privs);
            //System.out.println(user_role+ " "+objectname+" "+ column+" "+ privs);
            System.out.println("REVOKE " + privs + " ON U_QLBV_QUANTRIVIEN."+ objectname+ " FROM "+ user_role); 
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }    
    public void revoke3(int selectRow){
        String user_role= (String) jTable_role.getModel().getValueAt(selectRow, 0);
        String role_name= (String) jTable_role.getModel().getValueAt(selectRow, 1);
        
        String sql = "{call sp_REVOKE_ROLE_PRIVS(?, ?)}";
        try (
            CallableStatement cstm = conn.prepareCall(sql);
        ) {
            // Set parameter values
            cstm.setString(1, user_role);
            cstm.setString(2, role_name);
            //cstm.setString(4, privs);
            //System.out.println(user_role+ " "+objectname+" "+ column+" "+ privs);
            System.out.println("REVOKE " + role_name + " FROM "+ user_role); 
            // Executes the Procedure statement 
           cstm.executeQuery();
        }   catch (SQLException ex) {
                Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }    
    
    public Revoke_JFrame() throws SQLException {        
        this.conn = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","U_QLBV_QUANTRIVIEN","1234");
        String sql = "ALTER SESSION SET \"_ORACLE_SCRIPT\" = TRUE";
        // Tạo đối tượng thực thi câu lệnh Select
        Statement st = (Statement) conn.createStatement();
        // Thực thi
        st.executeQuery(sql);
        loadIntotblModel_Sys_Privs();
        loadIntotblModel_ObjectPrivs();
        loadIntotblModel_Role();
        loadIntoComboBox();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_RevokeHome = new javax.swing.JPanel();
        jTabbedPaneRevokeHome = new javax.swing.JTabbedPane();
        jPanel_SysPrivs = new javax.swing.JPanel();
        jScrollPane_SysPrivs = new javax.swing.JScrollPane();
        jTable_Sys_Privs = new javax.swing.JTable();
        jButton_Revoke_SysPrivs = new javax.swing.JButton();
        jButton_Cancel_SysPrivs = new javax.swing.JButton();
        jComboBox_Sys_Privs = new javax.swing.JComboBox<>();
        jPanel_Obj_Privs = new javax.swing.JPanel();
        jScrollPane_Obj_Privs = new javax.swing.JScrollPane();
        jTable_Obj_Privs = new javax.swing.JTable();
        jButton_Revoke_Object_Privs = new javax.swing.JButton();
        jButton_Cancel_Object_Privs = new javax.swing.JButton();
        jComboBox_Obj_Privs = new javax.swing.JComboBox<>();
        jPanel_Role = new javax.swing.JPanel();
        jScrollPane_Role = new javax.swing.JScrollPane();
        jTable_role = new javax.swing.JTable();
        jButton_Revoke_Role = new javax.swing.JButton();
        jButton_Cancel_Role = new javax.swing.JButton();
        jComboBox_Role = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Revoke privileges");
        setName("JFrame_RevokeHome"); // NOI18N

        jPanel_RevokeHome.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_RevokeHome.setPreferredSize(new java.awt.Dimension(800, 400));

        jPanel_SysPrivs.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_SysPrivs.setPreferredSize(new java.awt.Dimension(800, 350));

        jTable_Sys_Privs.setModel(tblModel_Sys_Privs);
        jScrollPane_SysPrivs.setViewportView(jTable_Sys_Privs);

        jButton_Revoke_SysPrivs.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Revoke_SysPrivs.setLabel("Revoke");
        jButton_Revoke_SysPrivs.setPreferredSize(new java.awt.Dimension(150, 30));
        jButton_Revoke_SysPrivs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Revoke_SysPrivsActionPerformed(evt);
            }
        });

        jButton_Cancel_SysPrivs.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Cancel_SysPrivs.setText("Cancel");
        jButton_Cancel_SysPrivs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cancel_SysPrivsActionPerformed(evt);
            }
        });

        jComboBox_Sys_Privs.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox_Sys_Privs.setModel(cbb_model);
        jComboBox_Sys_Privs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Sys_PrivsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_SysPrivsLayout = new javax.swing.GroupLayout(jPanel_SysPrivs);
        jPanel_SysPrivs.setLayout(jPanel_SysPrivsLayout);
        jPanel_SysPrivsLayout.setHorizontalGroup(
            jPanel_SysPrivsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_SysPrivs)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SysPrivsLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jComboBox_Sys_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jButton_Revoke_SysPrivs, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jButton_Cancel_SysPrivs, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
        );

        jPanel_SysPrivsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Cancel_SysPrivs, jButton_Revoke_SysPrivs});

        jPanel_SysPrivsLayout.setVerticalGroup(
            jPanel_SysPrivsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SysPrivsLayout.createSequentialGroup()
                .addComponent(jScrollPane_SysPrivs, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SysPrivsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Revoke_SysPrivs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cancel_SysPrivs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Sys_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel_SysPrivsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_Cancel_SysPrivs, jButton_Revoke_SysPrivs});

        jTabbedPaneRevokeHome.addTab("System Privileges", jPanel_SysPrivs);

        jPanel_Obj_Privs.setBackground(new java.awt.Color(204, 255, 255));
        jPanel_Obj_Privs.setPreferredSize(new java.awt.Dimension(800, 350));

        jTable_Obj_Privs.setModel(tblModel_ObjectPrivs);
        jScrollPane_Obj_Privs.setViewportView(jTable_Obj_Privs);

        jButton_Revoke_Object_Privs.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Revoke_Object_Privs.setLabel("Revoke");
        jButton_Revoke_Object_Privs.setPreferredSize(new java.awt.Dimension(150, 30));
        jButton_Revoke_Object_Privs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Revoke_Object_PrivsActionPerformed(evt);
            }
        });

        jButton_Cancel_Object_Privs.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Cancel_Object_Privs.setLabel("Cancel");
        jButton_Cancel_Object_Privs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cancel_Object_PrivsActionPerformed(evt);
            }
        });

        jComboBox_Obj_Privs.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox_Obj_Privs.setModel(cbb_model);
        jComboBox_Obj_Privs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Obj_PrivsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Obj_PrivsLayout = new javax.swing.GroupLayout(jPanel_Obj_Privs);
        jPanel_Obj_Privs.setLayout(jPanel_Obj_PrivsLayout);
        jPanel_Obj_PrivsLayout.setHorizontalGroup(
            jPanel_Obj_PrivsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Obj_PrivsLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jComboBox_Obj_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jButton_Revoke_Object_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jButton_Cancel_Object_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
            .addComponent(jScrollPane_Obj_Privs)
        );
        jPanel_Obj_PrivsLayout.setVerticalGroup(
            jPanel_Obj_PrivsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Obj_PrivsLayout.createSequentialGroup()
                .addComponent(jScrollPane_Obj_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Obj_PrivsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Revoke_Object_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cancel_Object_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Obj_Privs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPaneRevokeHome.addTab("Object Privileges", jPanel_Obj_Privs);

        jPanel_Role.setBackground(new java.awt.Color(204, 255, 255));

        jTable_role.setModel(tblModel_Role);
        jScrollPane_Role.setViewportView(jTable_role);

        jButton_Revoke_Role.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Revoke_Role.setLabel("Revoke");
        jButton_Revoke_Role.setPreferredSize(new java.awt.Dimension(150, 30));
        jButton_Revoke_Role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Revoke_RoleActionPerformed(evt);
            }
        });

        jButton_Cancel_Role.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Cancel_Role.setLabel("Cancel");
        jButton_Cancel_Role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cancel_RoleActionPerformed(evt);
            }
        });

        jComboBox_Role.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox_Role.setModel(cbb_model);
        jComboBox_Role.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_RoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_RoleLayout = new javax.swing.GroupLayout(jPanel_Role);
        jPanel_Role.setLayout(jPanel_RoleLayout);
        jPanel_RoleLayout.setHorizontalGroup(
            jPanel_RoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_RoleLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jComboBox_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jButton_Revoke_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jButton_Cancel_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
            .addGroup(jPanel_RoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane_Role, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE))
        );
        jPanel_RoleLayout.setVerticalGroup(
            jPanel_RoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_RoleLayout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addGroup(jPanel_RoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Revoke_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Cancel_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(jPanel_RoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_RoleLayout.createSequentialGroup()
                    .addComponent(jScrollPane_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 67, Short.MAX_VALUE)))
        );

        jTabbedPaneRevokeHome.addTab("Role", jPanel_Role);

        javax.swing.GroupLayout jPanel_RevokeHomeLayout = new javax.swing.GroupLayout(jPanel_RevokeHome);
        jPanel_RevokeHome.setLayout(jPanel_RevokeHomeLayout);
        jPanel_RevokeHomeLayout.setHorizontalGroup(
            jPanel_RevokeHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneRevokeHome, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel_RevokeHomeLayout.setVerticalGroup(
            jPanel_RevokeHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneRevokeHome)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_RevokeHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_RevokeHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_Revoke_SysPrivsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Revoke_SysPrivsActionPerformed
        int selectRow= jTable_Sys_Privs.getSelectedRow();
        this.revoke1(selectRow);
        try {
            this.loadIntotblModel_Sys_Privs();
            this.jTable_Sys_Privs.setModel(tblModel_Sys_Privs);
        } catch (SQLException ex) {
            Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        JOptionPane.showMessageDialog(this, "Revoke successfully");  

    }//GEN-LAST:event_jButton_Revoke_SysPrivsActionPerformed

    private void jButton_Cancel_SysPrivsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cancel_SysPrivsActionPerformed
        this.dispose();
        Frm_User_Menu crud_ur = null;
        try {
            crud_ur = new Frm_User_Menu();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        crud_ur.show();
    }//GEN-LAST:event_jButton_Cancel_SysPrivsActionPerformed

    private void jButton_Revoke_Object_PrivsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Revoke_Object_PrivsActionPerformed
        int selectRow= jTable_Obj_Privs.getSelectedRow();
        this.revoke2(selectRow);
        try {
            this.loadIntotblModel_ObjectPrivs();
            this.jTable_Obj_Privs.setModel(tblModel_ObjectPrivs);
        } catch (SQLException ex) {
            Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        JOptionPane.showMessageDialog(this, "Revoke successfully");        
    }//GEN-LAST:event_jButton_Revoke_Object_PrivsActionPerformed

    private void jButton_Cancel_Object_PrivsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cancel_Object_PrivsActionPerformed
    this.dispose();
        Frm_User_Menu crud_ur = null;
        try {
            crud_ur = new Frm_User_Menu();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        crud_ur.show();
    }//GEN-LAST:event_jButton_Cancel_Object_PrivsActionPerformed

    private void jButton_Revoke_RoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Revoke_RoleActionPerformed
        int selectRow= jTable_role.getSelectedRow();
        this.revoke3(selectRow);
        try {
            this.loadIntotblModel_Role();
            this.jTable_role.setModel(tblModel_Role);
        } catch (SQLException ex) {
            Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        JOptionPane.showMessageDialog(this, "Revoke successfully");  
    }//GEN-LAST:event_jButton_Revoke_RoleActionPerformed

    private void jButton_Cancel_RoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cancel_RoleActionPerformed
        this.dispose();
        Frm_User_Menu crud_ur = null;
        try {
            crud_ur = new Frm_User_Menu();
        } catch (SQLException ex) {
            Logger.getLogger(Frm_User_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        crud_ur.show();
    }//GEN-LAST:event_jButton_Cancel_RoleActionPerformed

    private void jComboBox_RoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_RoleActionPerformed
        String userRoleSelected= (String)jComboBox_Role.getSelectedItem();
        System.out.println("filter with " + userRoleSelected);
        try {
            loadIntotblModel_with_Cbb03(userRoleSelected);
        } catch (SQLException ex) {
            Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable_role.setModel(tblModel_Role);
    }//GEN-LAST:event_jComboBox_RoleActionPerformed

    private void jComboBox_Obj_PrivsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Obj_PrivsActionPerformed
        String userRoleSelected= (String)jComboBox_Obj_Privs.getSelectedItem();
        System.out.println("filter with " + userRoleSelected);
        try {
            loadIntotblModel_with_Cbb02(userRoleSelected);
        } catch (SQLException ex) {
            Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable_Obj_Privs.setModel(tblModel_ObjectPrivs); 
    }//GEN-LAST:event_jComboBox_Obj_PrivsActionPerformed

    private void jComboBox_Sys_PrivsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Sys_PrivsActionPerformed
        String userRoleSelected= (String)jComboBox_Sys_Privs.getSelectedItem();
        System.out.println("filter with " + userRoleSelected);
        try {
            loadIntotblModel_with_Cbb01(userRoleSelected);
        } catch (SQLException ex) {
            Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable_Sys_Privs.setModel(tblModel_Sys_Privs); 
    }//GEN-LAST:event_jComboBox_Sys_PrivsActionPerformed

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
            java.util.logging.Logger.getLogger(Revoke_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Revoke_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Revoke_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Revoke_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Revoke_JFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Revoke_JFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancel_Object_Privs;
    private javax.swing.JButton jButton_Cancel_Role;
    private javax.swing.JButton jButton_Cancel_SysPrivs;
    private javax.swing.JButton jButton_Revoke_Object_Privs;
    private javax.swing.JButton jButton_Revoke_Role;
    private javax.swing.JButton jButton_Revoke_SysPrivs;
    private javax.swing.JComboBox<String> jComboBox_Obj_Privs;
    private javax.swing.JComboBox<String> jComboBox_Role;
    private javax.swing.JComboBox<String> jComboBox_Sys_Privs;
    private javax.swing.JPanel jPanel_Obj_Privs;
    private javax.swing.JPanel jPanel_RevokeHome;
    private javax.swing.JPanel jPanel_Role;
    private javax.swing.JPanel jPanel_SysPrivs;
    private javax.swing.JScrollPane jScrollPane_Obj_Privs;
    private javax.swing.JScrollPane jScrollPane_Role;
    private javax.swing.JScrollPane jScrollPane_SysPrivs;
    private javax.swing.JTabbedPane jTabbedPaneRevokeHome;
    private javax.swing.JTable jTable_Obj_Privs;
    private javax.swing.JTable jTable_Sys_Privs;
    private javax.swing.JTable jTable_role;
    // End of variables declaration//GEN-END:variables
}
