/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpage;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.print.DocFlavor;
import javax.swing.JOptionPane;

/**
 *
 * @author fahol1
 */
public class issueReturn extends javax.swing.JFrame {

    Connection con;
    Statement stmt;
    Statement pst;

    ResultSet rs;
    int curRow = 0;
    public int bookissuedtoMemberId;
    public int bookissuedId;
    public static int fineupd;

    public issueReturn() {
        initComponents();
        setSize(700, 400);
        setTitle("issue or retunred books");
        setResizable(false);
        DBConnect();

        levelmi.setVisible(false);
        mitxt.setVisible(false);
        misrch.setVisible(false);
        saps.setVisible(false);
        levelbk.setVisible(false);
        levelbknm.setVisible(false);
        levelcapa.setVisible(false);
        levelcapacity.setVisible(false);
        rtnrcv.setVisible(false);
        mmid.setVisible(false);
        idlevel.setVisible(false);
        mmnm.setVisible(false);
        levelnm.setVisible(false);
        issue.setVisible(false);
        levelbn.setVisible(false);
        bntxt.setVisible(false);
        bnsrch.setVisible(false);
        rtnmisrch.setVisible(false);
        bnsrchrtn.setVisible(false);

    }

    public void DBConnect() {
        try {
            String host = "jdbc:mysql://localhost:3306/booksdb";

            String db_uname = "root";
            String db_pass = "";
            con = DriverManager.getConnection(host, db_uname, db_pass);

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }
    }

    public void fineadd() {

        try {

            String query = "select * from members where userid = ? ";
            PreparedStatement st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String searchid = Integer.toString(bookissuedtoMemberId);

            st.setString(1, searchid);
            rs = st.executeQuery();

            if (rs.next()) {
                if (fineupd == 1) {
                    rs.updateInt("userid", bookissuedtoMemberId);
                    rs.updateString("fine", "100 tk");
                    rs.updateRow();
                } else if (fineupd == 2) {
                    rs.updateInt("userid", bookissuedtoMemberId);
                    rs.updateString("fine", "200 tk");
                    rs.updateRow();
                } else if (fineupd == 3) {
                    rs.updateInt("userid", bookissuedtoMemberId);
                    rs.updateString("fine", "300 tk");
                    rs.updateRow();
                } else if (fineupd == 4) {
                    rs.updateInt("userid", bookissuedtoMemberId);
                    rs.updateString("fine", "400 tk");
                    rs.updateRow();
                } else if (fineupd == 5) {
                    rs.updateInt("userid", bookissuedtoMemberId);
                    rs.updateString("fine", "500 tk");
                    rs.updateRow();
                }
            }

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }

    }

    public static void checkfine(java.util.Date returnset, java.util.Date trReturned) {
        JOptionPane.showMessageDialog(null, "cheking fines");
        Calendar c = Calendar.getInstance();
        c.setTime(returnset);
        c.add(Calendar.DATE, 7);
        java.util.Date weekOne = c.getTime();
        //JOptionPane.showMessageDialog(null,"weekOne : " + weekOne);

        c.setTime(weekOne);
        c.add(Calendar.DATE, 7);
        java.util.Date weekTwo = c.getTime();
        //JOptionPane.showMessageDialog(null,"week two : " + weekTwo);

        c.setTime(weekTwo);
        c.add(Calendar.DATE, 7);
        java.util.Date weekThree = c.getTime();
        //JOptionPane.showMessageDialog(null,"week three : " + weekThree);

        c.setTime(weekThree);
        c.add(Calendar.DATE, 7);
        java.util.Date weekFour = c.getTime();
        //JOptionPane.showMessageDialog(null,"week four : " + weekFour);

        if (trReturned.compareTo(weekOne) < 0) {
            JOptionPane.showMessageDialog(null, "week one finned 100tk ");
            fineupd = 1;

        } else if (trReturned.compareTo(weekOne) == 0) {
            JOptionPane.showMessageDialog(null, "week one same finned 100tk ");
            fineupd = 1;
        } else if (trReturned.compareTo(weekOne) > 0) {
            //JOptionPane.showMessageDialog(null, "weektwo check ");
            if (trReturned.compareTo(weekTwo) < 0) {
                JOptionPane.showMessageDialog(null, "week two finned 200tk ");
                fineupd = 2;
            } else if (trReturned.compareTo(weekTwo) == 0) {
                JOptionPane.showMessageDialog(null, "week Two same finned 200tk ");
                fineupd = 2;
            } else if (trReturned.compareTo(weekTwo) > 0) {
                //  JOptionPane.showMessageDialog(null, "weekthree check ");
                if (trReturned.compareTo(weekThree) < 0) {
                    JOptionPane.showMessageDialog(null, "week Three finned 300tk ");
                    fineupd=3;
                } else if (trReturned.compareTo(weekThree) == 0) {
                    JOptionPane.showMessageDialog(null, "week Three same finned 300tk ");
                    fineupd=3;

                } else if (trReturned.compareTo(weekThree) > 0) {
                    //JOptionPane.showMessageDialog(null, "week four check ");

                    if (trReturned.compareTo(weekFour) < 0) {
                        JOptionPane.showMessageDialog(null, "week four finned 400tk ");
                        fineupd=4;

                    } else if (trReturned.compareTo(weekFour) == 0) {
                        JOptionPane.showMessageDialog(null, "week four same finned 400tk ");
                        fineupd=4;

                    } else if (trReturned.compareTo(weekFour) > 0) {
                        JOptionPane.showMessageDialog(null, "more than four weeks fine 500tk ");
                        fineupd=5;
                    }
                }
            }

        }
    }

    public void borrowUdate(int bookId, int MemberId) {
        try {

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM borrow";
            rs = stmt.executeQuery(sql);
            //adding date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date currentdate = new java.util.Date();
            String issuedate = sdf.format(currentdate).toString();

            Calendar c = Calendar.getInstance();
            c.setTime(currentdate);
            c.add(Calendar.DATE, 7);
            java.util.Date returndate = c.getTime();
            String returnDate = sdf.format(returndate).toString();
            rs.moveToInsertRow();
            rs.updateInt("memid", MemberId);
            rs.updateInt("bookid", bookId);
            rs.updateString("issuedate", issuedate);
            rs.updateString("returndate", returnDate);
            rs.insertRow();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }
    }

    public void returnUpdate(int bookId, int MemberId) {
        try {

            String query = "select * from borrow where memid = ? ";
            PreparedStatement st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String searchid = Integer.toString(MemberId);

            st.setString(1, searchid);
            rs = st.executeQuery();

            if (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String retrndate = rs.getString("returndate");
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date rdate = null;
                try {
                    rdate = sdf.parse(retrndate);
                    JOptionPane.showMessageDialog(null, rdate);
                } catch (ParseException e) {
                }
                java.util.Date returneddate = new java.util.Date();
                sdf.format(returneddate);
                JOptionPane.showMessageDialog(null, returneddate);
            //rdate actual return date 
                //returneddate actually returned date
                JOptionPane.showMessageDialog(null, " should return  :" + rdate);
                JOptionPane.showMessageDialog(null, "and returned :" + returneddate);
                //Date returned = c.getTime();
                if (rdate.compareTo(returneddate) > 0) {
                    JOptionPane.showMessageDialog(null, "returned before due date");
                    //returned before due date
                } else if (rdate.compareTo(returneddate) < 0) {
                    //JOptionPane.showMessageDialog(null, "jorimana");
                    checkfine(rdate, returneddate);
                    //returned after due date
                } else if (rdate.compareTo(returneddate) == 0) {
                    JOptionPane.showMessageDialog(null, "returned on date ");
                    //on date
                }
                rs.deleteRow();

                if (fineupd > 0) {
                    fineadd();
                }

            }

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        mitxt = new javax.swing.JTextField();
        bntxt = new javax.swing.JTextField();
        bnsrch = new javax.swing.JButton();
        misrch = new javax.swing.JButton();
        levelbn = new javax.swing.JLabel();
        levelmi = new javax.swing.JLabel();
        bnsrchrtn = new javax.swing.JButton();
        rtnmisrch = new javax.swing.JButton();
        saps = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        levelbk = new javax.swing.JLabel();
        levelcapa = new javax.swing.JLabel();
        levelbknm = new javax.swing.JLabel();
        levelcapacity = new javax.swing.JLabel();
        mmid = new javax.swing.JLabel();
        mmnm = new javax.swing.JLabel();
        idlevel = new javax.swing.JLabel();
        levelnm = new javax.swing.JLabel();
        issue = new javax.swing.JButton();
        rtnrcv = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 255));

        jButton1.setText("return");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("issue");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainpage/back.png"))); // NOI18N
        back.setText("Go Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(back, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(550, 70, 140, 250);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(10, 60, 700, 2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainpage/issuebook.png"))); // NOI18N
        jLabel1.setText("Issue Return");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(500, 10, 160, 40);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(540, 70, 10, 250);

        bnsrch.setBackground(new java.awt.Color(153, 153, 255));
        bnsrch.setText("search");
        bnsrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnsrchActionPerformed(evt);
            }
        });

        misrch.setBackground(new java.awt.Color(153, 153, 255));
        misrch.setText("search");
        misrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                misrchActionPerformed(evt);
            }
        });

        levelbn.setText("Enter Book Name :");

        levelmi.setText("Enter Member ID ");

        bnsrchrtn.setBackground(new java.awt.Color(153, 153, 255));
        bnsrchrtn.setText("search");
        bnsrchrtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnsrchrtnActionPerformed(evt);
            }
        });

        rtnmisrch.setBackground(new java.awt.Color(153, 153, 255));
        rtnmisrch.setText("search");
        rtnmisrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtnmisrchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mitxt)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(levelbn)
                            .addComponent(levelmi, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(bntxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bnsrchrtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(bnsrch))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rtnmisrch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(misrch)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(levelbn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnsrch)
                    .addComponent(bnsrchrtn))
                .addGap(22, 22, 22)
                .addComponent(levelmi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mitxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(misrch)
                    .addComponent(rtnmisrch))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(340, 70, 190, 270);

        saps.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(saps);
        saps.setBounds(330, 70, 10, 250);

        levelbk.setText("Book :");

        levelcapa.setText("Capacity:");

        levelbknm.setText("notfound");

        mmid.setText("Id :");

        mmnm.setText("Name :");

        idlevel.setText("not found");

        issue.setBackground(new java.awt.Color(153, 153, 255));
        issue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainpage/tick.png"))); // NOI18N
        issue.setText("issue");
        issue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueActionPerformed(evt);
            }
        });

        rtnrcv.setBackground(new java.awt.Color(153, 153, 255));
        rtnrcv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainpage/tick.png"))); // NOI18N
        rtnrcv.setText("recieved");
        rtnrcv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtnrcvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(levelbk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(levelcapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(levelcapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(levelbknm, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(mmid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mmnm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(levelnm, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(idlevel, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rtnrcv, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(issue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(levelbk)
                    .addComponent(levelbknm, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(levelcapa)
                    .addComponent(levelcapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mmid)
                    .addComponent(idlevel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mmnm)
                    .addComponent(levelnm, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(issue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rtnrcv)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(20, 70, 300, 260);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        levelmi.setVisible(false);
        mitxt.setVisible(false);
        misrch.setVisible(false);
        saps.setVisible(false);
        levelbk.setVisible(false);
        levelbknm.setVisible(false);
        levelcapa.setVisible(false);
        levelcapacity.setVisible(false);
        rtnrcv.setVisible(false);
        mmid.setVisible(false);
        idlevel.setVisible(false);
        mmnm.setVisible(false);
        levelnm.setVisible(false);
        issue.setVisible(false);
        rtnmisrch.setVisible(false);
        bnsrchrtn.setVisible(false);

        levelbn.setVisible(true);
        bntxt.setVisible(true);
        bnsrch.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bnsrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnsrchActionPerformed
        saps.setVisible(true);
        levelbk.setVisible(true);
        levelbknm.setVisible(true);
        levelcapa.setVisible(true);
        levelcapacity.setVisible(true);
        levelmi.setVisible(true);
        mitxt.setVisible(true);
        misrch.setVisible(true);

        //search 
        try {

            String query = "select * from books where Title = ? ";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bntxt.getText());
            rs = pst.executeQuery();
            if (rs.next()) {

                String name = rs.getString("Title");
                int capa = rs.getInt("Capacity");
                String capacity = Integer.toString(capa);
                levelbknm.setText(name);
                levelcapacity.setText(capacity);
                bookissuedId = rs.getInt("ID");

                pst.close();
            }

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }
    }//GEN-LAST:event_bnsrchActionPerformed

    private void misrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_misrchActionPerformed
        mmid.setVisible(true);
        idlevel.setVisible(true);
        mmnm.setVisible(true);
        levelnm.setVisible(true);
        issue.setVisible(true);

        levelmi.setVisible(false);
        mitxt.setVisible(false);
        misrch.setVisible(false);
        levelbn.setVisible(false);
        bntxt.setVisible(false);
        bnsrch.setVisible(false);

        //serch member 
        try {

            int capacity;
            String query = "select * from members where userid = ? ";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, mitxt.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("Name");
                int Id = rs.getInt("userid");
                String mem_id = Integer.toString(Id);
                levelnm.setText(name);
                idlevel.setText(mem_id);
                bookissuedtoMemberId = Id;

                pst.close();
                rs.close();
            }

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }

    }//GEN-LAST:event_misrchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        levelmi.setVisible(false);
        mitxt.setVisible(false);
        misrch.setVisible(false);
        saps.setVisible(false);
        levelbk.setVisible(false);
        levelbknm.setVisible(false);
        levelcapa.setVisible(false);
        levelcapacity.setVisible(false);
        rtnrcv.setVisible(false);
        mmid.setVisible(false);
        idlevel.setVisible(false);
        mmnm.setVisible(false);
        levelnm.setVisible(false);
        issue.setVisible(false);
        levelbn.setVisible(true);
        bntxt.setVisible(true);
        bnsrch.setVisible(false);
        bnsrchrtn.setVisible(true);
        //rtnmisrch.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void bnsrchrtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnsrchrtnActionPerformed
        saps.setVisible(true);
        levelbk.setVisible(true);
        levelbknm.setVisible(true);
        levelcapa.setVisible(true);
        levelcapacity.setVisible(true);
        levelmi.setVisible(true);
        mitxt.setVisible(true);
        rtnmisrch.setVisible(true);

        //here
        try {

            String query = "select * from books where Title = ? ";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, bntxt.getText());
            rs = pst.executeQuery();
            if (rs.next()) {

                String name = rs.getString("Title");
                int capa = rs.getInt("Capacity");
                String capacity = Integer.toString(capa);
                levelbknm.setText(name);
                levelcapacity.setText(capacity);
                bookissuedId = rs.getInt("ID");

                pst.close();
            }

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }
    }//GEN-LAST:event_bnsrchrtnActionPerformed

    private void rtnmisrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtnmisrchActionPerformed
        mmid.setVisible(true);
        idlevel.setVisible(true);
        mmnm.setVisible(true);
        levelnm.setVisible(true);
        rtnrcv.setVisible(true);

        levelmi.setVisible(false);
        mitxt.setVisible(false);
        misrch.setVisible(false);
        levelbn.setVisible(false);
        bntxt.setVisible(false);
        bnsrch.setVisible(false);
        rtnmisrch.setVisible(false);
        bnsrchrtn.setVisible(false);
        try {

            //int capacity ;
            String query = "select * from members where userid = ? ";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, mitxt.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("Name");
                int Id = rs.getInt("userid");
                String mem_id = Integer.toString(Id);
                levelnm.setText(name);
                idlevel.setText(mem_id);
                bookissuedtoMemberId = Id;

                pst.close();
                rs.close();
            }

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }
    }//GEN-LAST:event_rtnmisrchActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        AdminPanel rtn = new AdminPanel();
        close();
        rtn.setVisible(true);

    }//GEN-LAST:event_backActionPerformed

    private void issueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueActionPerformed

        try {

            String query = "select * from books where ID = ? ";
            PreparedStatement st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String searchid = Integer.toString(bookissuedId);

            st.setString(1, searchid);
            rs = st.executeQuery();
            int capacity = 0;
            if (rs.next()) {
                capacity = rs.getInt("Capacity");
                capacity = capacity - 1;

                rs.updateInt("ID", bookissuedId);
                rs.updateInt("Capacity", capacity);
                rs.updateRow();

            }
            borrowUdate(bookissuedId, bookissuedtoMemberId);
            JOptionPane.showMessageDialog(issueReturn.this, bookissuedId + " is issued to " + bookissuedtoMemberId + "\nCapacity :" + capacity);

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }

    }//GEN-LAST:event_issueActionPerformed

    private void rtnrcvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtnrcvActionPerformed
        // TODO add your handling code here:
        try {

            String query = "select * from books where ID = ? ";
            PreparedStatement st = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String searchid = Integer.toString(bookissuedId);

            st.setString(1, searchid);
            rs = st.executeQuery();
            int capacity = 0;
            if (rs.next()) {
                capacity = rs.getInt("Capacity");
                capacity = capacity + 1;

                rs.updateInt("ID", bookissuedId);
                rs.updateInt("Capacity", capacity);
                rs.updateRow();

            }

            returnUpdate(bookissuedId, bookissuedtoMemberId);
            JOptionPane.showMessageDialog(issueReturn.this, bookissuedId + " is recieved from " + bookissuedtoMemberId + "\nCapacity :" + capacity);

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(issueReturn.this, err.getMessage());
        }

    }//GEN-LAST:event_rtnrcvActionPerformed
    public void close() {

        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

    }

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
            java.util.logging.Logger.getLogger(issueReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issueReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issueReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issueReturn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issueReturn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton bnsrch;
    private javax.swing.JButton bnsrchrtn;
    private javax.swing.JTextField bntxt;
    private javax.swing.JLabel idlevel;
    private javax.swing.JButton issue;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel levelbk;
    private javax.swing.JLabel levelbknm;
    private javax.swing.JLabel levelbn;
    private javax.swing.JLabel levelcapa;
    private javax.swing.JLabel levelcapacity;
    private javax.swing.JLabel levelmi;
    private javax.swing.JLabel levelnm;
    private javax.swing.JButton misrch;
    private javax.swing.JTextField mitxt;
    private javax.swing.JLabel mmid;
    private javax.swing.JLabel mmnm;
    private javax.swing.JButton rtnmisrch;
    private javax.swing.JButton rtnrcv;
    private javax.swing.JSeparator saps;
    // End of variables declaration//GEN-END:variables
}
