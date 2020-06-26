package logic.file_management;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ReadCSV {
    protected File PARENT_FOLDER_PATH = new File("data");

    public abstract ArrayList<String[]> getAllData() throws IOException;

    public abstract boolean doesIDExist(int id) throws IOException;

    public abstract void close() throws IOException;
    public abstract void reset() throws IOException;

    public abstract Object get(int id) throws IOException;
}
