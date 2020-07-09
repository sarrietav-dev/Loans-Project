package logic.file_management.loan_crud;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.CRUD;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.Map;

public class ReadLoan extends CRUD {

    /**
     * Looks in the database if a loan ID exists.
     * @param ID The ID that will be searched.
     * @return True if it does exist, otherwise it will return false.
     */
    public static boolean doesLoanExist(final int ID) {
        return getAllData().stream()
                .flatMap(entry -> entry.getValue().stream())
                .anyMatch(loan -> loan.getLoanNumber() == ID);
    }

    /**
     * Returns a loan that's on the database.
     * @param ID The ID of the loan that wants to be retrieved.
     * @return The loan that matches that ID.
     * @throws ObjectNotFoundException if the loan doesn't exist.
     */
    public static Loan getLoan(final int ID) {
        for(Map.Entry<Client, ArrayList<Loan>> entry : getAllData())
            for (Loan loan : entry.getValue())
                if (loan.getLoanNumber() == ID)
                    return loan;
        throw new ObjectNotFoundException("There's no client with that Loan");
    }

    /**
     * Look if the loan has a delayed installment.
     * @param loan The loan that will be consulted.
     * @return True if it as a delayed installment, otherwise returns false.
     */
    public static boolean isLoanDelayed(final Loan loan) {
        return loan.isDelayed();
    }
}
