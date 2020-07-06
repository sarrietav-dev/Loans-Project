package logic.file_management.loan_crud;

import logic.file_management.CRUD;
import logic.file_management.client_crud.UpdateClient;
import logic.loan_classes.Loan;

import java.util.Arrays;
import java.util.Random;

public class CreateLoan extends CRUD {
    public static void create(Loan loan) {
        loan.setLoanNumber(setID());
        UpdateClient.addLoan(loan);
    }

    public static void create(Loan loan, final int CLIENT_ID) {
        loan.setLoanNumber(setID());
        UpdateClient.addLoan(loan, CLIENT_ID);
    }

    public static void create(Loan... loans) {
        Arrays.stream(loans).forEach(loan -> {
            loan.setLoanNumber(setID());
            UpdateClient.addLoan(loan);
        });
    }

    private static int setID() {
        Random random = new Random();
        int ID = Math.abs(random.nextInt() + 1);
        if (ReadLoan.doesLoanExist(ID))
            setID();
        return ID;
    }
}
