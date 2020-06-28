package logic.file_management.borrower_crud;

import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static logic.file_management.borrower_crud.ReadBorrower.doesBorrowerExists;

public class CreateBorrower extends CRUD {
    public static void create(Borrower borrower) throws IOException, ClassNotFoundException {
        if (!doesBorrowerExists(borrower))
            createBorrower(borrower);
    }

    private static void createBorrower(Borrower borrower) throws IOException {
        HashMap<Borrower, ArrayList<Loan>> data = dataBase.getData();
        data.put(borrower, new ArrayList<>());
        dataBase.updateData(data);
    }
}
