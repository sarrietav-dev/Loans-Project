package logic.file_management.loan_crud;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.CRUD;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.Map;

public class ReadLoan extends CRUD {
    public static boolean doesLoanExist(final int ID) {
        return getAllData().stream()
                .flatMap(entry -> entry.getValue().stream())
                .anyMatch(loan -> loan.getLoanNumber() == ID);
    }

    public static Loan getLoan(final int ID) {
        for(Map.Entry<Client, ArrayList<Loan>> entry : getAllData())
            for (Loan loan : entry.getValue())
                if (loan.getLoanNumber() == ID)
                    return loan;
        throw new ObjectNotFoundException("There's no client with that Loan");
    }

    public static boolean isLoanDelayed(final Loan loan) {
        return loan.isDelayed();
    }
}
