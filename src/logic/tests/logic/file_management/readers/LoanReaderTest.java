package logic.file_management.readers;

import logic.file_management.FilteredSearch;
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
        String[][] strings = loanReader.getAllData();
        for (String[] dataPack : strings) {
            for (String data : dataPack)
                System.out.print(data + ", ");
            System.out.println();
        }
        loanReader.close();
    }

    @Test
    void getFilteredLoans() throws IOException {
        FilteredSearch search = new LoanReader();
        for (Loan loan : search.search(1002244364, FilteredSearch.BORROWER))
            System.out.println(loan);
    }

    @Test
    void getAllIDsTest() throws IOException {
        ArrayList<Integer> ids = loanReader.getIDs();
        int[] idsArray = new int[] {
                ids.get(0),
                ids.get(1)
        };
        assertArrayEquals(new int[] {2014759998, 810571556}, idsArray);
        loanReader.close();
    }

    @Test
    void returnsLoanTest() throws IOException {
        System.out.println(loanReader.get(655294301));
        loanReader.close();
    }
}