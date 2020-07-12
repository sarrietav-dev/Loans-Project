/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import design.admin.AdminInterface;
import design.employee.EmployeeInterface;

import javax.swing.JOptionPane;

import logic.company_members.Employee;
import logic.company_members.employee_crud.ReadEmployee;
import logic.databases.EmployeeDatabase;
import logic.exceptions.LoginIncorrectException;

/**
 * @author Administrador
 */
public class LoginMenu extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public LoginMenu() {
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

        panel1 = new org.edisoncor.gui.panel.Panel();
        panelCurves4 = new org.edisoncor.gui.panel.PanelCurves();
        checkAuthors3 = new org.edisoncor.gui.button.ButtonIpod();
        textLogin3 = new javax.swing.JLabel();
        textUsser3 = new javax.swing.JLabel();
        insertUsser3 = new javax.swing.JTextField();
        textPassword3 = new javax.swing.JLabel();
        insertPassword3 = new javax.swing.JPasswordField();
        buttonLogin3 = new org.edisoncor.gui.button.ButtonIpod();
        buttonExit3 = new org.edisoncor.gui.button.ButtonIpod();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setColorPrimario(new java.awt.Color(102, 102, 255));
        panel1.setColorSecundario(new java.awt.Color(255, 255, 255));

        checkAuthors3.setText("Authors");
        checkAuthors3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAuthors3ActionPerformed(evt);
            }
        });

        textLogin3.setFont(new java.awt.Font("SimSun", 1, 24)); // NOI18N
        textLogin3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textLogin3.setText("Welcome to the ISETEX'S DEN");

        textUsser3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textUsser3.setText("Usser:");

        textPassword3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textPassword3.setText("Password:");

        buttonLogin3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        buttonLogin3.setText("Login");
        buttonLogin3.setAnimacion(false);
        buttonLogin3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogin3ActionPerformed(evt);
            }
        });

        buttonExit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        buttonExit3.setText("Exit");
        buttonExit3.setAnimacion(false);
        buttonExit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExit3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCurves4Layout = new javax.swing.GroupLayout(panelCurves4);
        panelCurves4.setLayout(panelCurves4Layout);
        panelCurves4Layout.setHorizontalGroup(
                panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                .addComponent(checkAuthors3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                                                .addGap(53, 53, 53)
                                                                .addComponent(textUsser3)
                                                                .addGap(34, 34, 34)
                                                                .addComponent(insertUsser3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(textLogin3))
                                                .addContainerGap(85, Short.MAX_VALUE))
                                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                                                .addComponent(buttonLogin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(70, 70, 70)
                                                                .addComponent(buttonExit3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                                                .addComponent(textPassword3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(insertPassword3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelCurves4Layout.setVerticalGroup(
                panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCurves4Layout.createSequentialGroup()
                                .addComponent(checkAuthors3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(textLogin3)
                                .addGap(32, 32, 32)
                                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(insertUsser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textUsser3))
                                .addGap(18, 18, 18)
                                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(insertPassword3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textPassword3))
                                .addGap(27, 27, 27)
                                .addGroup(panelCurves4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonExit3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonLogin3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCurves4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCurves4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLogin3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogin3ActionPerformed
        // TODO add your handling code here:

        boolean proceed = true;


        if ("Admin".equals(insertUsser3.getText()) && "1234".equals(insertPassword3.getText())) {
            AdminInterface AdminInterface1 = new AdminInterface();
            this.setVisible(false);
            AdminInterface1.setVisible(true);
        } else if ("".equals(insertUsser3.getText()) || "".equals(insertPassword3.getText())) {
            proceed = false;
            JOptionPane.showMessageDialog(null, "You must fill all the fields!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                Employee employee = ReadEmployee.login(insertUsser3.getText(), insertPassword3.getText());
                System.out.println(employee);
            } catch (LoginIncorrectException e) {
                proceed = false;
                System.out.print("Usser or password incorrect, throwing exception... ");
                JOptionPane.showMessageDialog(null, "User or password incorrect!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (proceed == true) {
            EmployeeInterface EmployeeInterface1 = new EmployeeInterface();
            this.setVisible(false);
            EmployeeInterface1.setVisible(true);
        }

    }//GEN-LAST:event_buttonLogin3ActionPerformed

    private void checkAuthors3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAuthors3ActionPerformed
        // TODO add your handling code here:
        Authors Authors1 = new Authors();
        this.setVisible(false);
        Authors1.setVisible(true);
    }//GEN-LAST:event_checkAuthors3ActionPerformed

    private void buttonExit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExit3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_buttonExit3ActionPerformed

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
            java.util.logging.Logger.getLogger(LoginMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonIpod buttonExit;
    private org.edisoncor.gui.button.ButtonIpod buttonExit1;
    private org.edisoncor.gui.button.ButtonIpod buttonExit2;
    private org.edisoncor.gui.button.ButtonIpod buttonExit3;
    private org.edisoncor.gui.button.ButtonIpod buttonLogin;
    private org.edisoncor.gui.button.ButtonIpod buttonLogin1;
    private org.edisoncor.gui.button.ButtonIpod buttonLogin2;
    private org.edisoncor.gui.button.ButtonIpod buttonLogin3;
    private org.edisoncor.gui.button.ButtonIpod checkAuthors;
    private org.edisoncor.gui.button.ButtonIpod checkAuthors1;
    private org.edisoncor.gui.button.ButtonIpod checkAuthors2;
    private org.edisoncor.gui.button.ButtonIpod checkAuthors3;
    private javax.swing.JPasswordField insertPassword;
    private javax.swing.JPasswordField insertPassword1;
    private javax.swing.JPasswordField insertPassword2;
    private javax.swing.JPasswordField insertPassword3;
    private javax.swing.JTextField insertUsser;
    private javax.swing.JTextField insertUsser1;
    private javax.swing.JTextField insertUsser2;
    private javax.swing.JTextField insertUsser3;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves2;
    private org.edisoncor.gui.panel.PanelCurves panelCurves3;
    private org.edisoncor.gui.panel.PanelCurves panelCurves4;
    private javax.swing.JLabel textLogin;
    private javax.swing.JLabel textLogin1;
    private javax.swing.JLabel textLogin2;
    private javax.swing.JLabel textLogin3;
    private javax.swing.JLabel textPassword;
    private javax.swing.JLabel textPassword1;
    private javax.swing.JLabel textPassword2;
    private javax.swing.JLabel textPassword3;
    private javax.swing.JLabel textUsser;
    private javax.swing.JLabel textUsser1;
    private javax.swing.JLabel textUsser2;
    private javax.swing.JLabel textUsser3;
    // End of variables declaration//GEN-END:variables
}
