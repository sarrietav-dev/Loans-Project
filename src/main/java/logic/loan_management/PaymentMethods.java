package logic.loan_management;

import logic.company_members.Employee;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Loan;
import logic.loan_classes.PaymentManager;
import logic.pdf.InformationPack;
import logic.pdf.Receipt;

import java.util.Date;

public class PaymentMethods {
    private static PaymentManager paymentManager;

    /**
     * It pays the first unpaid installment with a custom date of payment.
     * @param LOAN_ID Within the given client, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the installment was paid.
     */
    public static Receipt payInstallment(final Employee employee, final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        paymentManager = new PaymentManager(loan, DATE_OF_PAYMENT);
        paymentManager.pay();

        InformationPack pack = new InformationPack(loan, DATE_OF_PAYMENT, employee, paymentManager.getMoneyToCapital(),
                paymentManager.getMoneyToInterests());

        return new Receipt(pack);
    }

    /**
     * Pays all the installments. You'll be changed 5% interest.<br/>
     * @param LOAN_ID Within the given client, looks for a loan with that ID.
     * @param DATE_OF_PAYMENT The date when the loan was paid. Every installment will be paid in that date.
     */
    public static Receipt payLoan(final Employee employee, final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        Loan loan = ReadLoan.getLoan(LOAN_ID);
        paymentManager = new PaymentManager(loan, DATE_OF_PAYMENT);
        paymentManager.payAll();

        InformationPack pack = new InformationPack(loan, DATE_OF_PAYMENT, employee, paymentManager.getMoneyToCapital(),
                paymentManager.getMoneyToInterests());

        return new Receipt(pack);
    }
}
