package logic.file_management;

import java.io.IOException;
import java.util.Random;

public interface IdentificationManagerCSV {
    int writeID() throws IOException;

    static int getRandomID() {
        return Math.abs(new Random().nextInt() + 1);
    }
}
