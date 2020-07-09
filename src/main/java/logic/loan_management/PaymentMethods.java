package logic.loan_management;

import logic.company_members.Employee;
import logic.exceptions.LoanAlreadyPaidException;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Loan;
import logic.loan_classes.PaymentManager;
import logic.pdf.InformationPack;
import logic.pdf.Receipt;

import java.util.Date;

/**
 * This class is the "interface" where the employee pays the loans of a client.
 */
public class PaymentMethods {
    private static PaymentManager paymentManager;

    /**
     * It pays the first unpaid installment with a custom date of payment.
     * @param EMPLOYEE The employee who helped with the payment.
     * @param LOAN_ID Within the given client, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the installment was paid.
     * @return The receipt of the payment.
     */
    public static Receipt payInstallment(final Employee EMPLOYEE, final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        if (loan.isPaid())
            throw new LoanAlreadyPaidException();
        paymentManager = new PaymentManager(loan, DATE_OF_PAYMENT);
        paymentManager.pay();

        InformationPack pack = new InformationPack(loan, DATE_OF_PAYMENT, EMPLOYEE, paymentManager.getMoneyToCapital(),
                paymentManager.getMoneyToInterests());

        return new Receipt(pack);
    }

    /**
     * Pays all the installments. You'll be changed 5% interest.<br/>
     * @param EMPLOYEE The employee who helped with the payment.
     * @param LOAN_ID Within the given client, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the loan was paid. Every installment will be paid in that date.
     * @return The receipt of the payment.
     */
    public static Receipt payLoan(final Employee EMPLOYEE, final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        if (loan.isPaid())
            throw new LoanAlreadyPaidException();
        paymentManager = new PaymentManager(loan, DATE_OF_PAYMENT);
        paymentManager.payAll();

        InformationPack pack = new InformationPack(loan, DATE_OF_PAYMENT, EMPLOYEE, paymentManager.getMoneyToCapital(),
                paymentManager.getMoneyToInterests());

        return new Receipt(pack);
    }
}
