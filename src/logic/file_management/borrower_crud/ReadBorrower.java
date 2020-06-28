package logic.file_management.borrower_crud;

import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ReadBorrower extends CRUD {
    public static Set<Borrower> getAllBorrowers() {
        return dataBase.getData().keySet();
    }

    public static boolean doesBorrowerExists(Borrower borrower) {
        for (Borrower borrowerKey : dataBase.getData().keySet())
            if (borrower.equals(borrowerKey))
                return true;
        return false;
    }

    public static boolean hasThisLoan(final int ID) {
        Set<Map.Entry<Borrower, ArrayList<Loan>>> entrySet = dataBase.getData().entrySet();
        for (Map.Entry<Borrower, ArrayList<Loan>> entry : entrySet)
            for (Loan loan : entry.getValue())
                if (loan.getLoanNumber() == ID)
                    return true;
        return false;
    }
}
