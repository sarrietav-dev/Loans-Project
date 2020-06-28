package logic.file_management.borrower_crud;

import logic.loan_classes.Borrower;

import java.io.IOException;
import java.util.Set;

public interface ReadBorrower {
    Set<Borrower> getAllBorrowers() throws IOException, ClassNotFoundException;
    boolean doesBorrowerExists(Borrower borrower) throws IOException, ClassNotFoundException;
}
