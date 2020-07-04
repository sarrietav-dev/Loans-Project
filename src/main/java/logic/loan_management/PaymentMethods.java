package logic.loan_management;

import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Loan;
import logic.loan_classes.PaymentManager;

import java.util.Date;

public class PaymentMethods {
    // TODO: 30/06/20 Add PDF
    private static PaymentManager paymentManager;

    /**
     * It pays the first unpaid installment. It takes the system date.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     */
    public static void payInstallment(final int LOAN_ID) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        paymentManager = new PaymentManager(loan, new Date());
        paymentManager.pay();
        // TODO: 4/07/20 Add Receipt
    }

    /**
     * It pays the first unpaid installment with a custom date of payment.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the installment was paid.
     */
    public static void payInstallment(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        paymentManager = new PaymentManager(loan, DATE_OF_PAYMENT);
        paymentManager.pay();
        // TODO: 4/07/20 Add Receipt
    }

    /**
     * Pays all the installments. You will be charged 5% interest.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     */
    public static void payLoan(final int LOAN_ID) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        paymentManager = new PaymentManager(loan, new Date());
        paymentManager.payAll();
        // TODO: 4/07/20 Add Receipt
    }

    /**
     * Pays all the installments. You'll be changed 5% interest.<br/>
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the loan was paid. Every installment will be paid in that date.
     */
    public static void payLoan(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        paymentManager = new PaymentManager(loan, DATE_OF_PAYMENT);
        paymentManager.payAll();
        // TODO: 4/07/20 Add Receipt
    }
}
