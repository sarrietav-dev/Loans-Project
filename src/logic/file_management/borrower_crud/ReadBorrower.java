package logic.file_management.borrower_crud;

import logic.file_management.CRUD;
import logic.loan_classes.Borrower;

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
}
