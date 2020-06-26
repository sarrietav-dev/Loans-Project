package logic.file_management;

import logic.loan_classes.Loan;

import java.io.IOException;

public interface FilteredSearch {
    boolean LOAN = false;
    boolean BORROWER = true;

    Loan[] search(int ID, boolean filter) throws IOException;
}
