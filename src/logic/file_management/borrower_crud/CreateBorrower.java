package logic.file_management.borrower_crud;

import logic.exceptions.BorrowerAlreadyExistsException;
import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashMap;

import static logic.file_management.borrower_crud.ReadBorrower.doesBorrowerExists;

public class CreateBorrower extends CRUD {
    public static void create(Borrower borrower) {
        if (!doesBorrowerExists(borrower))
            createBorrower(borrower);
    }

    public static void create(Borrower... borrowers) {
        for (Borrower borrower : borrowers)
            if (doesBorrowerExists(borrower))
                throw new BorrowerAlreadyExistsException("That ID is Taken!");
            else
                createBorrower(borrower);
    }

    private static void createBorrower(Borrower borrower) {
        HashMap<Borrower, ArrayList<Loan>> data = dataBase.getData();
        data.put(borrower, new ArrayList<>());
        dataBase.updateData(data);
    }
}
