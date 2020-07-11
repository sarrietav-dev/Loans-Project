package logic.file_management.client_crud;

import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateClientTest {
    @Test
    void clientDoesNotExistTest() {
        UpdateClient.addLoan(new Loan(2000, "2020-10-12"), 1);
    }
}