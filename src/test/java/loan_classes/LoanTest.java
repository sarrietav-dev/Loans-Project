package loan_classes;

import logic.loan_classes.Client;
import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    @Test
    void amountIllegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Loan(-1, new Client(), "2020-06-22"));
    }

    @Test
    void incorrectDateOrderTest() {
        assertThrows(IllegalArgumentException.class, () -> new Loan(-1, new Client(), "22-06-2020"));
    }
}