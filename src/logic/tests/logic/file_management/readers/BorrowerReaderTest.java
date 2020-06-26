package logic.file_management.readers;

import logic.file_management.ReadCSV;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


class BorrowerReaderTest {
    @Test
    void getAllDataTest() throws IOException {
        ReadCSV borrowerReader = new BorrowerReader();
        ArrayList<String[]> strings = borrowerReader.getAllData();
        for (String[] dataPack : strings) {
            for (String data : dataPack)
                System.out.print(data + ", ");
            System.out.println();
        }
    }
}