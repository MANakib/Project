/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpage;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MemberAccount extends javax.swing.JFrame {

    MemberMainpage usercol;
    public String usercoll;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int curRow = 0;

    public MemberAccount(MemberMainpage u) {

        initComponents();
        setSize(700, 400);
        setResizable(false);
        setTitle("account");
        usercol = u;
        usercoll = usercol.usertransfer();
        MemberData(usercoll);

    }

    private void MemberData(String usercoll) {

        DBConnect();
        try {
            rs.first();

            String query = "select * from members where userid = ? ";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, usercoll);
            rs = pst.executeQuery();
            if (rs.next()) {

                String Name = rs.getString("Name");
                int phone = rs.getInt("Phone");
                String PhoneSet = Integer.toString(phone);
                memberName.setText(Name);
                memberPhone.setText(PhoneSet);

            } else {
                JOptionPane.showMessageDialog(MemberAccount.this, "member not found");

            }

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(MemberAccount.this, err.getMessage());
        }
    }
    
     public void DBConnect() {
        try {
            String host = "jdbc:mysql://localhost:3306/booksdb";

            String db_uname = "root";
            String db_pass = "";
            con = DriverManager.getConnection(host, db_uname, db_pass);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM Members";
            rs = stmt.executeQuery(sql);
            rs.next();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(MemberAccount.this, err.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        memberPhone = new javax.swing.JLabel();
        memberName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        accountEdit = new javax.swing.JButton();
        accountMessage = new javax.swing.JButton();
        accountSearch = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        borrowedBookone = new javax.swing.JLabel();
        borrowedBookthree = new javax.swing.JLabel();
        borrowedBooktwo = new javax.swing.JLabel();
        borrowedBookfour = new javax.swing.JLabel();
        borrowedBookfive = new javax.swing.JLabel();
        borrowedBookSeparatorOne = new javax.swing.JSeparator();
        borrowedBookSeparatortwo = new javax.swing.JSeparator();
        borrowedBookSeparatorthree = new javax.swing.JSeparator();
        borrowedBookSeparatorfour = new javax.swing.JSeparator();
        back = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 500, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("  Account");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 30, 150, 30));

        memberPhone.setText("jLabel2");
        getContentPane().add(memberPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 140, 20));

        memberName.setText("jLabel2");
        getContentPane().add(memberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 140, 20));

        jLabel4.setText("Phone :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 50, 20));

        jLabel5.setText("Name :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 50, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 260, 10));

        accountEdit.setBackground(new java.awt.Color(153, 153, 255));
        accountEdit.setText("Edit");
        getContentPane().add(accountEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 80, -1));

        accountMessage.setBackground(new java.awt.Color(153, 153, 255));
        accountMessage.setText("Message");
        getContentPane().add(accountMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 80, -1));

        accountSearch.setBackground(new java.awt.Color(153, 153, 255));
        accountSearch.setText("Search");
        accountSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountSearchActionPerformed(evt);
            }
        });
        getContentPane().add(accountSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 80, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 260, 10));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Actions ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 140, 30));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 10, 240));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Recently Borrowed");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 120, 30));

        borrowedBookone.setText("Book One");
        getContentPane().add(borrowedBookone, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 121, 24));

        borrowedBookthree.setText("Book Three");
        getContentPane().add(borrowedBookthree, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 121, 24));

        borrowedBooktwo.setText("Book Two");
        getContentPane().add(borrowedBooktwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 121, 24));

        borrowedBookfour.setText("Book Four");
        getContentPane().add(borrowedBookfour, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 121, 24));

        borrowedBookfive.setText("Book Five");
        getContentPane().add(borrowedBookfive, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 121, 24));
        getContentPane().add(borrowedBookSeparatorOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 173, 10));
        getContentPane().add(borrowedBookSeparatortwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 173, 10));
        getContentPane().add(borrowedBookSeparatorthree, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 173, 10));
        getContentPane().add(borrowedBookSeparatorfour, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 173, 10));

        back.setBackground(new java.awt.Color(153, 153, 255));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainpage/back.png"))); // NOI18N
        back.setText("Go Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, -1));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 282, 110, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void accountSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountSearchActionPerformed
        MemberSearch accsearch = new MemberSearch();
        accsearch.setVisible(true);


    }//GEN-LAST:event_accountSearchActionPerformed
 public void close() {

        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

    }
    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        MemberMainpage rtn = new MemberMainpage(null);
        close();
        rtn.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(MemberAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberAccount(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountEdit;
    private javax.swing.JButton accountMessage;
    private javax.swing.JButton accountSearch;
    private javax.swing.JButton back;
    private javax.swing.JSeparator borrowedBookSeparatorOne;
    private javax.swing.JSeparator borrowedBookSeparatorfour;
    private javax.swing.JSeparator borrowedBookSeparatorthree;
    private javax.swing.JSeparator borrowedBookSeparatortwo;
    private javax.swing.JLabel borrowedBookfive;
    private javax.swing.JLabel borrowedBookfour;
    private javax.swing.JLabel borrowedBookone;
    private javax.swing.JLabel borrowedBookthree;
    private javax.swing.JLabel borrowedBooktwo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel memberName;
    private javax.swing.JLabel memberPhone;
    // End of variables declaration//GEN-END:variables
}
