package logic.file_management.borrower_crud;

import logic.exceptions.BorrowerAlreadyExistsException;
import logic.loan_classes.Borrower;
import org.junit.jupiter.api.Test;

class CreateBorrowerTest {
    @Test
    void createBorrower() {
        Borrower tempBorrower = new Borrower(1002244364, "Sebastian", "696969696",
                "420", "home");
        Borrower tempBorrower2 = new Borrower(1002244364, "Sebastian", "696969696",
                "420", "home");
        Borrower tempBorrower3 = new Borrower(20023023, "Sebastian", "696969696",
                "420", "home");
        Borrower tempBorrower4 = new Borrower(4949494, "Jess", "696969696",
                "420", "home");
        try {
            CreateBorrower.create(tempBorrower, tempBorrower2, tempBorrower3, tempBorrower4);
            for (Borrower borrower : ReadBorrower.getAllBorrowers())
                System.out.println(borrower);
        } catch (BorrowerAlreadyExistsException e) {
            System.out.println("Borrower already exists!");
        }
    }
}