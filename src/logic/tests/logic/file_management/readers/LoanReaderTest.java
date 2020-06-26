package logic.file_management.readers;

import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoanReaderTest {
    LoanReader loanReader = new LoanReader();
    
    LoanReaderTest() throws FileNotFoundException {
    }

    @Test
    void getAllDataTest() throws IOException {
        ArrayList<String[]> strings = loanReader.getAllData();
        for (String[] dataPack : strings) {
            for (String data : dataPack)
                System.out.print(data + ", ");
            System.out.println();
        }
    }

    @Test
    void getAllIDsTest() throws IOException {
        ArrayList<Integer> ids = loanReader.getIDs();
        int[] idsArray = new int[] {
                ids.get(0),
                ids.get(1)
        };
        assertArrayEquals(new int[] {2014759998, 810571556}, idsArray);
    }

    @Test
    void returnsLoanTest() throws IOException {
        Loan testLoan = new Loan(2000, new Borrower(1002244364, "Sebastian", "25252525",
                "69696969", "Cartagena"), "2020-02-10");
        System.out.println(loanReader.get(655294301));
    }
}