package logic.file_management.write_classes;

import logic.file_management.IdentificationManagerCSV;
import logic.file_management.ReadCSV;
import logic.file_management.WriteCSV;
import logic.file_management.read_classes.LoanReader;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoanWriter extends WriteCSV implements IdentificationManagerCSV {
    FileWriter writer;
    private static final int BORROWER = 1;

    public LoanWriter(Loan loan) throws IOException {
        super(loan);

        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "loans.csv");
        writer = new FileWriter(PATH, APPEND);

        loan.setID(writeID());
    }

    @Override
    public int writeID() throws IOException {
        ReadCSV reader = new LoanReader();

        int randomID = IdentificationManagerCSV.getRandomID();

        if (reader.doesIDExist(randomID))
            return writeID();
        else
            return randomID;
    }

    @Override
    public void write() throws IOException {
        writeBorrower();
        writeLoan();
    }

    private void writeLoan() throws IOException {
        writer.write(getCSVRow());
        writer.close();
    }

    @Override
    protected String getCSVRow() {
        Object[] objects = object.getInfo();
        String[] info = (String[]) objects[0];

        StringBuilder csvRow = new StringBuilder();
        for (String data : info)
            csvRow.append(data).append(",");
        csvRow.substring(0, csvRow.length()-1);
        csvRow.append("\n");

        return csvRow.toString();
    }

    private void writeBorrower() throws IOException {
        WriteCSV borrowerWriter = new BorrowerWriter((Borrower) object.getInfo()[BORROWER]);
        borrowerWriter.write();
    }
}
