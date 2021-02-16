
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sean
 */
public class UserFrame extends javax.swing.JFrame {

    /**
     * Creates new form UserFrame
     */
    public UserFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_Name = new javax.swing.JTextField();
        txt_Student_number = new javax.swing.JTextField();
        txt_Class = new javax.swing.JTextField();
        txt_Major = new javax.swing.JTextField();
        btn_addToStudent = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel2.setText("Student_Number");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 75, -1, -1));

        jLabel3.setText("Class");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 145, -1, -1));

        jLabel4.setText("Major");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));
        getContentPane().add(txt_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 35, 140, -1));
        getContentPane().add(txt_Student_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 73, 140, -1));
        getContentPane().add(txt_Class, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 145, 140, -1));
        getContentPane().add(txt_Major, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 233, 200, -1));

        btn_addToStudent.setText("Add to Student");
        btn_addToStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addToStudentActionPerformed(evt);
            }
        });
        getContentPane().add(btn_addToStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        pack();
    }// </editor-fold>                        

    private void btn_addToStudentActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        int ID=Integer.parseInt(txt_Name.getText());
 String firstName = txt_Student_number.getText();
 String lastName = txt_Class.getText();
 String email= txt_Major.getText();
 Connection conn=null;
 PreparedStatement pstmt=null;
 try{
 Class.forName("com.mysql.jdbc.Driver");
 //demo is the name of the database
 conn=DriverManager.getConnection("jdbc:mysql:///demo", "root",
"");//replace XXXXXX with you password
 //employees is the name of the table
 //4 questions marks for 4 fields in the table that i want to insert into
 pstmt=conn.prepareStatement("insert into student values(?,?,?,?)");
 pstmt.setInt(1,ID);
 pstmt.setString(2, firstName);
 pstmt.setString(3, lastName);
 pstmt.setString(4, email);
 int i=pstmt.executeUpdate();//will update database
 if (i>0) {
 JOptionPane.showMessageDialog(null, "Data is saved");
 } else {

 JOptionPane.showMessageDialog(null, "Data not saved");
 }
 }
 catch(Exception e){
 JOptionPane.showMessageDialog(null, e);
 }

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
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btn_addToStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txt_Class;
    private javax.swing.JTextField txt_Major;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JTextField txt_Student_number;
    // End of variables declaration                   
}
