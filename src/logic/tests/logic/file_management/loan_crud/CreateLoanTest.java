package logic.file_management.loan_crud;

import logic.exceptions.CannotAddMoreLoansException;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CreateLoanTest {
    @Test
    void createTest() {
        Loan loan = new Loan(20000, new Borrower(4949494, "Jess", "696969696",
                "420", "home"), "2000-02-12");
        Loan loan1 = new Loan(2000, "2020-12-10");

        try {
            CreateLoan.create(loan);
            CreateLoan.create(loan1, 1002244364);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotAddMoreLoansException e) {
            System.out.println(e.getMessage());
        }
    }
}