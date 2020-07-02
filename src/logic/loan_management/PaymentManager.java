package logic.loan_management;

import logic.file_management.loan_crud.ReadLoan;
import logic.file_management.loan_crud.UpdateLoan;
import logic.loan_classes.Loan;
import logic.loan_classes.PaymentMethods;

import java.util.Date;

public class PaymentManager {
    // TODO: 30/06/20 Add PDF
    private static PaymentMethods loanDates;

    /**
     * It pays the first unpaid installment. It takes the system date.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     */
    public static void payInstallment(final int LOAN_ID) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        loanDates = loan.getDates();
        loanDates.pay();
        UpdateLoan.update(loan);
    }

    /**
     * It pays the first unpaid installment with a custom date of payment.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the installment was paid.
     */
    public static void payInstallment(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        // TODO: 28/06/20 Call PaymentMethods pay(Calendar)
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        loanDates = loan.getDates();
        loanDates.pay(DATE_OF_PAYMENT);
        UpdateLoan.update(loan);
    }

    /**
     * Pays all the installments. You will be charged 5% interest.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     */
    public static void payLoan(final int LOAN_ID) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        loanDates = loan.getDates();
        loanDates.payAll();
        UpdateLoan.update(loan);
    }

    /**
     * Pays all the installments. You'll be changed 5% interest.<br/>
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the loan was paid. Every installment will be paid in that date.
     */
    public static void payLoan(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        loanDates = loan.getDates();
        loanDates.payAll(DATE_OF_PAYMENT);
        UpdateLoan.update(loan);
    }
}
