package logic.file_management;

import logic.loan_classes.Borrower;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WriterTest {
    @Test
    void startFileWriterTest() throws IOException {
        new Writer();
        try {
            Reader reader = new Reader();
            for (Borrower borrower : reader.getData().keySet())
                System.out.println(borrower);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}