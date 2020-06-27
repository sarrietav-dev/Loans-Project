package logic.file_management;

import java.io.IOException;
import java.util.Random;

/**
 * Interface implemented by {@link logic.file_management.writers.LoanWriter} to add an unique ID to an object.
 */
public interface IdentificationManagerCSV {
    int writeID() throws IOException;

    static int getRandomID() {
        return Math.abs(new Random().nextInt() + 1);
    }
}
