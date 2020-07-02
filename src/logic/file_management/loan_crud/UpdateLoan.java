package logic.file_management.loan_crud;

import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateLoan extends CRUD {
    public static void update(Loan loan) {
        HashMap<Borrower, ArrayList<Loan>> data = dataBase.getData();
        ArrayList<Loan> loans = data.get(loan.getBorrower());
        loans.removeIf(loanFromData -> loanFromData.getLoanNumber() == loan.getLoanNumber());
        loans.add(loan);
        dataBase.updateData(data);
    }
}
