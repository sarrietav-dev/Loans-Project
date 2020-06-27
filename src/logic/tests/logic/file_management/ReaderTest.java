package logic.file_management;

import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {
    @Test
    void readArrayListSize() throws IOException, ClassNotFoundException {
        ArrayList<Loan> loans = new Reader().read();
        assertEquals(0, loans.size());
    }
}