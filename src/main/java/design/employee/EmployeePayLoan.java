/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.employee;

import com.toedter.calendar.JTextFieldDateEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import logic.exceptions.LoanAlreadyPaidException;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Loan;
import logic.loan_classes.PaymentManager;
import logic.pdf.InformationPack;
import logic.pdf.Receipt;

/**
 *
 * @author Administrador
 */
public class EmployeePayLoan extends javax.swing.JFrame {

    /**
     * Creates new form EmployeePayLoan
     */
    
    private static Loan loanChecked;
    private static Date paymentDate;
    
    public EmployeePayLoan() {
        initComponents();
        JTextFieldDateEditor editor = (JTextFieldDateEditor) insertPayment.getDateEditor();
        editor.setEditable(false);
        loanChecked = ReadLoan.getLoan(EmployeeConsultClient.selectedLoanId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        labelCustom1 = new org.edisoncor.gui.label.LabelCustom();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        textPaymentDate = new javax.swing.JLabel();
        insertPayment = new com.toedter.calendar.JDateChooser();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        buttonPayInstallment = new org.edisoncor.gui.button.ButtonAction();
        buttonPayLoan = new org.edisoncor.gui.button.ButtonAction();
        buttonBack2 = new org.edisoncor.gui.button.ButtonIpod();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setColorPrimario(new java.awt.Color(153, 153, 255));
        panel1.setColorSecundario(new java.awt.Color(255, 255, 255));

        labelCustom1.setBackground(new java.awt.Color(102, 102, 102));
        labelCustom1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelCustom1.setText("Pay a Loan");
        labelCustom1.setColorDeSegundoBorde(new java.awt.Color(0, 0, 0));

        panelImage1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        textPaymentDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textPaymentDate.setText("Payment Date:");

        insertPayment.setDateFormatString("yyyy/MM/dd");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(textPaymentDate, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(insertPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(insertPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        panelImage2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonPayInstallment.setText("Pay one Installment");
        buttonPayInstallment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayInstallmentActionPerformed(evt);
            }
        });

        buttonPayLoan.setText("Pay all loan");
        buttonPayLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPayLoanActionPerformed(evt);
            }
        });

        buttonBack2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.jpg"))); // NOI18N
        buttonBack2.setText("Back");
        buttonBack2.setAnimacion(false);
        buttonBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBack2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonPayInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPayLoan, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelImage2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(buttonPayInstallment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonPayLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCustom1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPayInstallmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayInstallmentActionPerformed
        // TODO add your handling code here:
        
        int cont=0;
        
        JTextFieldDateEditor editor = (JTextFieldDateEditor) insertPayment.getDateEditor();
        
        if ("".equals(String.valueOf(editor.getText())))
        {
            JOptionPane.showMessageDialog(null, "- You must fill the Date field!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        
        else {
        
            try{
        paymentDate = insertPayment.getDate();
        EmployeeUsser.getUsser().payAnInstallment(loanChecked.getLoanNumber(), paymentDate);
            }
        catch (LoanAlreadyPaidException e){
                cont++;
                JOptionPane.showMessageDialog(null, "- Loan already paid!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
            
            if (cont==0)
            {
                paymentDate = insertPayment.getDate();
                EmployeeUsser.getUsser().payAnInstallment(loanChecked.getLoanNumber(), paymentDate);
                JOptionPane.showMessageDialog(null, "Installment successfully repaid!", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }//GEN-LAST:event_buttonPayInstallmentActionPerformed

    private void buttonPayLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPayLoanActionPerformed
        // TODO add your handling code here:
        JTextFieldDateEditor editor = (JTextFieldDateEditor) insertPayment.getDateEditor();
        
        int cont = 0;
        
        if ("".equals(String.valueOf(editor.getText())))
        {
            JOptionPane.showMessageDialog(null, "- You must fill the Date field!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            cont++;
        }
        
        else
        {
            
            try{
        paymentDate = insertPayment.getDate();
        PaymentManager PaymentManager1 = new PaymentManager(loanChecked,paymentDate);
        PaymentManager1.payAll();
        
            }
            catch (LoanAlreadyPaidException e){
                cont++;
                JOptionPane.showMessageDialog(null, "Loan already paid!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
            
            if (cont == 0)
            {
                paymentDate = insertPayment.getDate();
                PaymentManager PaymentManager1 = new PaymentManager(loanChecked,paymentDate);
                PaymentManager1.payAll();
                InformationPack informationPack1 = new InformationPack(loanChecked, paymentDate, EmployeeUsser.getUsser(), PaymentManager1.getMoneyToCapital(), PaymentManager1.getMoneyToInterests());
                Receipt Receipt1 = new Receipt(informationPack1);
                Receipt1.generateReceipt();
                JOptionPane.showMessageDialog(null, "Loan successfully repaid! ", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE); 
            }
          
        
        
        
        }
    }//GEN-LAST:event_buttonPayLoanActionPerformed

    private void buttonBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBack2ActionPerformed
        // TODO add your handling code here:
        EmployeeClientCheckLoan EmployeeClientCheckLoan1 = new EmployeeClientCheckLoan();
        this.setVisible(false);
        EmployeeClientCheckLoan1.setVisible(true);
    }//GEN-LAST:event_buttonBack2ActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeePayLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeePayLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeePayLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeePayLoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeePayLoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonIpod buttonBack2;
    private org.edisoncor.gui.button.ButtonAction buttonPayInstallment;
    private org.edisoncor.gui.button.ButtonAction buttonPayLoan;
    private com.toedter.calendar.JDateChooser insertPayment;
    private org.edisoncor.gui.label.LabelCustom labelCustom1;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private javax.swing.JLabel textPaymentDate;
    // End of variables declaration//GEN-END:variables
}
