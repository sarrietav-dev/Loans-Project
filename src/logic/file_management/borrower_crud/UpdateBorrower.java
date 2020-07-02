package logic.file_management.borrower_crud;

import logic.exceptions.CannotAddMoreLoansException;
import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import static logic.file_management.borrower_crud.ReadBorrower.isAnyLoanDelayed;

public class UpdateBorrower extends CRUD {
    public static void addLoan(Loan loan) {
        CreateBorrower.create(loan.getBorrower());
        addLoan(loan, loan.getBorrower().getId());
    }

    public static void addLoan(Loan loan, final int BORROWER_ID) {
        HashMap<Borrower, ArrayList<Loan>> data = dataBase.getData();
        Set<Entry<Borrower, ArrayList<Loan>>> entrySet = data.entrySet();

        List<Entry<Borrower, ArrayList<Loan>>> entryList = entrySet.stream()
                .filter(entry -> BORROWER_ID == entry.getKey().getId())
                .collect(Collectors.toList());

        entryList.forEach(entry -> {
            if (isAnyLoanDelayed(entry.getKey())) {
                throw new CannotAddMoreLoansException("Borrower " + entry.getKey().getName() +
                        " has a delayed installment.");
            } else {
                setAddLoanOperation(loan, entry);
                dataBase.updateData(data);
            }
        });
    }

    private static void setAddLoanOperation(Loan loan, Entry<Borrower, ArrayList<Loan>> entry) {
        loan.setBorrower(entry.getKey());
        entry.getValue().add(loan);
    }
}
