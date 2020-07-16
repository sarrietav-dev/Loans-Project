package logic.file_management.loan_crud;

import logic.file_management.ClientLoanCRUD;
import logic.file_management.client_crud.UpdateClient;
import logic.loan_classes.Loan;

import java.util.Arrays;
import java.util.Random;

public class CreateLoan extends ClientLoanCRUD {

    /**
     * Creates a loan and adds it to the database. Also, it sets an unique ID for the loan.
     * @param loan The loan that will be added.
     * @throws logic.exceptions.CannotAddMoreLoansException if some requirements are met. <br>
     *     See: {@link UpdateClient#addLoan(Loan, int)} for more information.
     */
    public static void create(Loan loan) {
        loan.setLoanNumber(setID());
        UpdateClient.addLoan(loan);
    }

    /**
     * Creates a loan and adds it to the database in a specified client. Also, it sets an unique ID for the loan.
     * @param loan The loan that will be added.
     * @param CLIENT_ID The client ID where the loan will be stored.
     * @throws logic.exceptions.CannotAddMoreLoansException if some requirements are met. <br>
     *     See: {@link UpdateClient#addLoan(Loan, int)} for more information.
     */
    public static void create(Loan loan, final int CLIENT_ID) {
        loan.setLoanNumber(setID());
        UpdateClient.addLoan(loan, CLIENT_ID);
    }

    /**
     * Creates and adds a series of loans to the database.
     * @param loans The loans that will be added.
     */
    public static void create(Loan... loans) {
        Arrays.stream(loans)
            .forEach(loan -> create(loan));
    }

    private static int setID() {
        Random random = new Random();
        int ID = Math.abs(random.nextInt() + 1);
        if (ReadLoan.doesLoanExist(ID))
            setID();
        return ID;
    }
}
