package logic.file_management;

import logic.loan_classes.Borrower;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {
    @Test
    void readTest() {

        try {
            Reader reader = new Reader();
            for (Borrower borrower : reader.getData().keySet())
                System.out.println(borrower);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}