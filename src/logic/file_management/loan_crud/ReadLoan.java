package logic.file_management.loan_crud;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ReadLoan extends CRUD {
    public static boolean doesLoanExist(final int ID) {
        Set<Map.Entry<Borrower, ArrayList<Loan>>> entrySet = dataBase.getData().entrySet();
        for (Map.Entry<Borrower, ArrayList<Loan>> entry : entrySet)
            for (Loan loan : entry.getValue())
                if (loan.getLoanNumber() == ID)
                    return true;
        return false;
    }

    public static Loan getLoan(final int ID) {
        for (Borrower borrower : dataBase.getData().keySet())
            for (Loan loan : dataBase.getData().get(borrower))
                if (loan.getLoanNumber() == ID)
                    return loan;
        throw new ObjectNotFoundException("There's no borrower with that Loan");
    }
}
