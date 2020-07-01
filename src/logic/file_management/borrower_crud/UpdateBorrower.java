package logic.file_management.borrower_crud;

import logic.exceptions.CannotAddMoreLoansException;
import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UpdateBorrower extends CRUD {
    public static void addLoan(Loan loan) {
        CreateBorrower.create(loan.getBorrower());
        addLoan(loan, loan.getBorrower().getId());
    }

    public static void addLoan(Loan loan, final int BORROWER_ID) {
        HashMap<Borrower, ArrayList<Loan>> data = dataBase.getData();
        Set<Map.Entry<Borrower, ArrayList<Loan>>> entrySet = data.entrySet();
        for (Map.Entry<Borrower, ArrayList<Loan>> entry : entrySet) {
            if (BORROWER_ID == entry.getKey().getId()) {
                if (ReadBorrower.isAnyLoanDelayed(entry.getKey())) {
                    throw new CannotAddMoreLoansException("Borrower " + entry.getKey().getName() + " has a delayed installment.");
                } else {
                    loan.setBorrower(entry.getKey());
                    entry.getValue().add(loan);
                    dataBase.updateData(data);
                }
            }
        }
    }
}
