package logic.loan_management;

import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.DateFormatter;
import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

class PaymentManagerTest {
    private final int LOAN_ID = 784128717 ;
    private Loan loan = ReadLoan.getLoan(LOAN_ID);

    @Test
    void payTest() {
        System.out.println(loan.getDates());
        PaymentManager.payInstallment(LOAN_ID);
        System.out.println(loan.getDates());
    }

    @Test
    void payCustomDateTest() {
        System.out.println(loan.getDates());
        PaymentManager.payInstallment(LOAN_ID, DateFormatter.format("2021-05-10"));
        System.out.println(loan.getDates());
    }

    @Test
    void payAllTest() {
        System.out.println(loan.getDates());
        PaymentManager.payLoan(LOAN_ID);
        System.out.println(loan.getDates());
    }

    @Test
    void payAllCustomDateTest() {
        System.out.println(loan.getDates());
        PaymentManager.payLoan(LOAN_ID, DateFormatter.format("2022-01-01"));
        System.out.println(loan.getDates());
    }
}