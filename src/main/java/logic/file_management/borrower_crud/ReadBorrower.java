package logic.file_management.borrower_crud;

import logic.file_management.CRUD;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReadBorrower extends CRUD {
    public static Set<Borrower> getAllBorrowers() {
        return dataBase.getData().keySet();
    }

    public static boolean doesBorrowerExist(Borrower borrower) {
        return dataBase.getData().keySet().stream()
                .anyMatch(borrower::equals);
    }

    public static double totalAmountBorrowed(Borrower borrower) {
        return dataBase.getData().get(borrower).stream()
                .mapToDouble(Loan::getAmount).sum();
    }

    public static boolean hasAnyLoanDelayed(Borrower borrower) {
        return dataBase.getData().get(borrower).stream()
                .anyMatch(ReadLoan::isLoanDelayed);
    }

    public static Set<Borrower> getDelayedBorrowers() {
        return getAllBorrowers().stream()
                .filter(ReadBorrower::hasAnyLoanDelayed)
                .collect(Collectors.toSet());
    }
}
