package logic.file_management.write_classes;

import logic.file_management.ReadCSV;
import logic.file_management.WriteCSV;
import logic.file_management.read_classes.BorrowerReader;
import logic.loan_classes.Borrower;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BorrowerWriter extends WriteCSV {
    FileWriter writer;

    public BorrowerWriter(Borrower borrower) throws IOException {
        super(borrower);

        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "borrowers.csv");
        writer = new FileWriter(PATH, APPEND);
    }

    @Override
    public void write() throws IOException {
        ReadCSV borrowerReader = new BorrowerReader();
        if (!(borrowerReader.doesIDExist(object.getID())))
            writer.write(getCSVRow());
        writer.close();
    }

    @Override
    protected String getCSVRow() {
        return getString((String[]) object.getInfo());
    }

}
