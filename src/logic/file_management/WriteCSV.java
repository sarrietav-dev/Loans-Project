package logic.file_management;

import logic.FetchInformation;

import java.io.File;
import java.io.IOException;

public abstract class WriteCSV {
    protected FetchInformation object;
    protected File PARENT_FOLDER_PATH = new File("data" + File.separator);
    protected final boolean APPEND = true;

    public WriteCSV(FetchInformation object) {
        this.object = object;
    }

    public abstract void write() throws IOException;

    protected abstract String getCSVRow();

    protected static String getString(String[] info2) {
        StringBuilder csvRow = new StringBuilder();
        for (String data : info2)
            csvRow.append(data).append(",");
        csvRow.substring(0, csvRow.length() - 1);
        csvRow.append("\n");

        return csvRow.toString();
    }
}
