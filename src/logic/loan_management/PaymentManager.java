package logic.loan_management;

import logic.DataBase;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Loan;

import java.util.Calendar;

public class PaymentManager {
    DataBase dataBase = DataBase.getInstance();

    /**
     * It pays the first unpaid installment. It takes the system date.
     * @param BORROWER_ID Looks a borrower with that ID.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     */
    public static void payInstallment(final int LOAN_ID) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        // TODO: 28/06/20 Call PaymentMethods pay()
    }

    /**
     * It pays the first unpaid installment with a custom date of payment.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the installment was paid.
     */
    public static void payInstallment(final int LOAN_ID, final Calendar DATE_OF_PAYMENT) {
        // TODO: 28/06/20 Call PaymentMethods pay(Calendar)
    }

    /**
     * Pays all the installments. You will be charged 5% interest.
     * @param LOAN_ID Within the given borrower, looks for a loan with that ID.
     */
    public static void payLoan(final int LOAN_ID) {
        // TODO: 28/06/20 Call PaymentMethods payAll()
    }
}
