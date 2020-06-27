package logic.file_management.makers;

import logic.file_management.Maker;
import logic.file_management.MultipleMaker;
import logic.file_management.ReadCSV;
import logic.file_management.readers.BorrowerReader;
import logic.loan_classes.Borrower;
import logic.loan_classes.Dates;
import logic.loan_classes.Loan;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates a {@link Loan} object or multiple {@link Loan} objects from an String array of data.
 */
public class LoanMaker extends Maker implements MultipleMaker {
    private final String[][] data;

    private final static int ID = 0;
    private final static int AMOUNT = 1;
    private final static int BORROWER_ID = 2;
    private final static int DATES = 3;
    private final static int IS_PAID = 4;

    private LoanMaker(String[] info) {
        super(info);
        data = null;
    }

    /**
     *
     * @param data Loans to make.
     */
    public LoanMaker(String[]... data) {
        super(null);
        if (data.length == 1)
            info = data[0];
        this.data = data;
    }

    /**
     * Makes the data to {@link Loan} objects.
     * @return An array of {@link Loan} objects made from the given data.
     * @throws IOException If the file is deleted.
     */
    @Override
    public Loan[] makeMultiple() throws IOException {
        if (data == null)
            throw new NullPointerException("Didn't provide the correct object!");

        ArrayList<Loan> loans = new ArrayList<>();

        for (String[] info : data) {
            this.info = info;
            loans.add(make());
        }

        return convert(loans);
    }

    /**
     * Useful if you only provide a single {@link Loan} <br>
     * WARNING: IF YOU PROVIDED MULTIPLE LOAN DATA, THIS FUNCTION WILL ONLY MAKE THE FIRST OF ALL OF THEM.
     * @return A single {@link Loan} object from the given data.
     */
    @Override
    public Loan make() throws IOException {
        return new Loan(getID(), getAmount(), getBorrower(), getDates(), getIsPaid());
    }

    private int getID() {
        return Integer.parseInt(info[ID]);
    }

    private double getAmount() {
        return Double.parseDouble(info[AMOUNT]);
    }

    private Borrower getBorrower() throws IOException {
        ReadCSV borrowerReader = new BorrowerReader();
        return (Borrower) borrowerReader.get(Integer.parseInt(info[BORROWER_ID]));
    }

    private Dates getDates() {
        return new DatesMaker(info[DATES]).make();
    }

    private boolean getIsPaid() {
        return Boolean.parseBoolean(info[IS_PAID]);
    }

    private Loan[] convert(ArrayList<Loan> loans) {

        Loan[] returnArray = new Loan[loans.size()];

        for(int i = 0; i < loans.size(); i++)
            returnArray[i] = loans.get(i);

        return returnArray;
    }
}
