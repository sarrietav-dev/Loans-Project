package logic.file_management;

import logic.loan_classes.Loan;

import java.io.IOException;

/**
 * Interface implemented by {@link logic.file_management.readers.LoanReader}, it filters results.
 */
public interface Filter {
    boolean LOAN = false;
    boolean BORROWER = true;

    Loan[] searchByBorrower(int ID) throws IOException;
}
