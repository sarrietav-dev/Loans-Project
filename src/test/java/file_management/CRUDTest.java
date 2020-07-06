package file_management;

import logic.file_management.CRUD;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

class CRUDTest {
    @Test
    void getAllDataTest() {
        for (Map.Entry<Client, ArrayList<Loan>> entry : CRUD.getAllData()) {
            System.out.println(entry.getKey());
            for (Loan loan : entry.getValue())
                System.out.println(loan);
        }
    }
}