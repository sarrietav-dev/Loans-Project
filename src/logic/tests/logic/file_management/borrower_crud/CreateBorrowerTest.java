package logic.file_management.borrower_crud;

import logic.loan_classes.Borrower;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CreateBorrowerTest {
    @Test
    void createBorrower() throws IOException, ClassNotFoundException {
        CreateBorrower createBorrower = new BorrowerCRUD();

        Borrower borrower = new Borrower("arrietismo@gmail.com", "password",1002244364,
                "Sebastian", "1000", "6464", "Home");
        Borrower borrower1 = new Borrower("jatenciap@gmail.com", "sebasbb", 24092001,
                "Jess", "9898989", "01010101", "Since");
        Borrower borrower2 = new Borrower("jarrieta@gmail.com", "Abcde1237$", 9876554,
                "John", "797979", "93939393", "Cartagena");

        createBorrower.create(borrower);
        createBorrower.create(borrower1);
        createBorrower.create(borrower2);

        ReadBorrower readBorrower = (ReadBorrower) createBorrower;

        for (Borrower borrowerKey : readBorrower.getAllBorrowers())
            System.out.println(borrowerKey);
    }
}