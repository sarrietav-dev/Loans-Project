package logic.file_management.loan_crud;

import logic.file_management.CRUD;
import logic.file_management.borrower_crud.UpdateBorrower;
import logic.loan_classes.Loan;

import java.io.IOException;
import java.util.Random;

public class CreateLoan extends CRUD {
    public static void create(Loan loan) throws IOException {
        loan.setLoanNumber(setID());
        UpdateBorrower.addLoan(loan);
    }

    public static void create(Loan loan, final int BORROWER_ID) {
        loan.setLoanNumber(setID());
        UpdateBorrower.addLoan(loan, BORROWER_ID);
    }

    public static void create(Loan... loans) throws IOException {
        for (Loan loan : loans) {
            loan.setLoanNumber(setID());
            UpdateBorrower.addLoan(loan);
        }
    }

    private static int setID() {
        Random random = new Random();
        int ID = Math.abs(random.nextInt() + 1);
        if (ReadLoan.doesLoanExist(ID))
            setID();
        return ID;
    }
}
