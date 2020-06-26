package logic.file_management.write_classes;

import logic.FetchInformation;
import logic.file_management.ReadCSV;
import logic.file_management.WriteCSV;
import logic.file_management.read_classes.BorrowerReader;
import logic.loan_classes.Borrower;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BorrowerWriter extends WriteCSV {
    FileWriter writer;

    public BorrowerWriter(Borrower... borrowers) throws IOException {
        super(borrowers);

        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "borrowers.csv");
        writer = new FileWriter(PATH, APPEND);
    }

    @Override
    public void write() throws IOException {
        ReadCSV borrowerReader = new BorrowerReader();
        for (FetchInformation borrower : objects)
            if (!(borrowerReader.doesIDExist(borrower.getID())))
                writer.write(getCSVRow(borrower));
        writer.close();
    }

    @Override
    protected String getCSVRow(FetchInformation borrower) {
        return getString((String[]) borrower.getInfo());
    }

}
