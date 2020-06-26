package logic.file_management.writers;

import logic.file_management.WriteCSV;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class LoanWriterTest {
    Loan testLoan = new Loan(2000, new Borrower(1002244364, "Sebastian", "25252525",
            "69696969", "Cartagena"), "2020-02-10");

    Loan testLoan2 = new Loan(4000, new Borrower(2004488475, "Jess", "092400",
            "30011200300", "Since"), "2020-09-20");
    @Test
    void writesToCSVTest() throws IOException {
        WriteCSV loanWriter = new LoanWriter(testLoan, testLoan2);
        loanWriter.write();
    }

}