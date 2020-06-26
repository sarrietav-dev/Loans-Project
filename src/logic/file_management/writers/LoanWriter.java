package logic.file_management.writers;

import logic.FetchInformation;
import logic.file_management.IdentificationManagerCSV;
import logic.file_management.ReadCSV;
import logic.file_management.WriteCSV;
import logic.file_management.readers.LoanReader;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoanWriter extends WriteCSV implements IdentificationManagerCSV {
    FileWriter writer;
    private static final int LOAN_INFO = 0;
    private static final int BORROWER = 1;

    public LoanWriter(Loan... loans) throws IOException {
        super(loans);

        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "loans.csv");
        writer = new FileWriter(PATH, APPEND);

        for (Loan loanItem : loans)
            loanItem.setID(writeID());
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
        for (FetchInformation loans : objects)
            writer.write(getCSVRow(loans));
        writer.close();
    }

    @Override
    protected String getCSVRow(FetchInformation loan) {
        Object[] objects = loan.getInfo();
        String[] info = (String[]) objects[LOAN_INFO];

        StringBuilder csvRow = new StringBuilder();
        for (String data : info)
            csvRow.append(data).append(",");
        csvRow.substring(0, csvRow.length()-1);
        csvRow.append("\n");

        return csvRow.toString();
    }

    private void writeBorrower() throws IOException {
        for (FetchInformation loan : objects) {
            WriteCSV borrowerWriter = new BorrowerWriter((Borrower) loan.getInfo()[BORROWER]);
            borrowerWriter.write();
        }
    }
}
