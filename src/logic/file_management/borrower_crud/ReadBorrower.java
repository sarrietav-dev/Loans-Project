package logic.file_management.borrower_crud;

import logic.file_management.CRUD;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.Set;

public class ReadBorrower extends CRUD {
    public static Set<Borrower> getAllBorrowers() {
        return dataBase.getData().keySet();
    }

    public static boolean doesBorrowerExist(Borrower borrower) {
        return dataBase.getData().keySet().stream()
                .anyMatch(borrower::equals);
    }

    public static boolean isAnyLoanDelayed(Borrower borrower) {
        return dataBase.getData().get(borrower).stream()
                .anyMatch(loan -> loan.getDates().isDelayed());
    }

    public static double totalAmountBorrowed(Borrower borrower) {
        return dataBase.getData().get(borrower).stream()
                .mapToDouble(Loan::getAmount).sum();
    }
}
