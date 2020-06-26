package logic.file_management.makers;

import logic.file_management.Maker;
import logic.file_management.ReadCSV;
import logic.file_management.readers.BorrowerReader;
import logic.loan_classes.Borrower;
import logic.loan_classes.Dates;
import logic.loan_classes.Loan;

import java.io.IOException;

public class LoanMaker implements Maker {
    private final String[] info;
    private final static int ID = 0;
    private final static int AMOUNT = 1;
    private final static int BORROWER_ID = 2;
    private final static int DATES = 3;
    private final static int IS_PAID = 4;

    public LoanMaker(String[] info) {
        this.info = info;
    }

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
}
