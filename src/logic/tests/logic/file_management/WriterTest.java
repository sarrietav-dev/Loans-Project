package logic.file_management;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {
    @Test @Disabled
    void startFileWriterTest() throws IOException {
        Writer writer = new Writer();
        writer.write();
    }
}