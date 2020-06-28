package logic.file_management.borrower_crud;

import logic.loan_classes.Borrower;

import java.io.IOException;

public interface CreateBorrower {
    void create(Borrower borrower) throws IOException, ClassNotFoundException;
}
