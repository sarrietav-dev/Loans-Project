package logic.loan_management;

import logic.company_members.Employee;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.DateFormatter;
import logic.loan_classes.Loan;
import logic.pdf.Receipt;
import org.junit.jupiter.api.Test;

import java.util.Date;

class PaymentMethodsTest {
    private final int LOAN_ID = 2064682115;
    private final Loan loan = ReadLoan.getLoan(LOAN_ID);
    private final Employee employee = new Employee();

    @Test
    void payTest() {
        System.out.println(loan.getDates());
        Receipt receipt = PaymentMethods.payInstallment(employee, LOAN_ID, new Date());
        System.out.println(loan.getDates());
        receipt.generateReceipt();
    }

    @Test
    void payCustomDateTest() {
        System.out.println(loan.getDates());
        Receipt receipt = PaymentMethods.payInstallment(employee, LOAN_ID, DateFormatter.format("2021-05-10"));
        System.out.println(loan.getDates());
        receipt.generateReceipt();
    }

    @Test
    void payAllTest() {
        System.out.println(loan.getDates());
        Receipt receipt = PaymentMethods.payLoan(employee, LOAN_ID, new Date());
        System.out.println(loan.getDates());
        receipt.generateReceipt();
    }

    @Test
    void payAllCustomDateTest() {
        System.out.println(loan.getDates());
        Receipt receipt = PaymentMethods.payLoan(employee, LOAN_ID, DateFormatter.format("2022-01-01"));
        System.out.println(loan.getDates());
        receipt.generateReceipt();
    }
}