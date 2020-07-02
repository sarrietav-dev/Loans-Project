package logic.file_management.readers;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.Maker;
import logic.file_management.ReadCSV;
import logic.file_management.Filter;
import logic.file_management.makers.LoanMaker;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;

public class LoanReader extends ReadCSV implements Filter {
    private final BufferedReader bufferedReader;

    public LoanReader() throws FileNotFoundException {
        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "loans.csv");
        bufferedReader = new BufferedReader(new FileReader(PATH));
    }

    /**
     * Searches a loan in the CSV.
     * @param id ID to be searched.
     * @return true if the ID exists, this the loan exists. Either way returns false.
     */
    @Override
    public boolean doesIDExist(int id) throws IOException {
        return getIDs().contains(id);
    }

    // TODO: Private when test are deleted.
    public ArrayList<Integer> getIDs() throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        final int ID = 0;
        for (String[] data : getAllRawData())
            ids.add(Integer.parseInt(data[ID]));

        return ids;
    }

    /**
     * Searches for a {@link Loan} object and returns it.
     * @param id The id of the borrower you want to retrieve.
     * @return The wanted {@link Loan} object.
     */
    @Override
    public Loan get(int id) throws IOException {
        Maker loanMaker = new LoanMaker(getLoanInfo(id));
        return (Loan) loanMaker.make();
    }

    private String[] getLoanInfo(int id) throws IOException {
        for (String[] info : getAllRawData())
            if (Integer.parseInt(info[0]) == id)
                return info;
        throw new ObjectNotFoundException("Loan's ID not found");
    }

    /**
     * Takes every piece of data from the CSV and returns it in String form.
     * @return And Array of String arrays containing all the CSV data.
     */
    @Override
    public String[][] getAllRawData() throws IOException {
        String line;
        ArrayList<String[]> data = new ArrayList<>();

        while((line = bufferedReader.readLine()) != null)
            data.add(line.split(","));

        return ArrayConverter.convert(data);
    }

    /**
     * Closes the buffer. Use this every time you finish your queries.
     */
    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }

    /**
     * Gets all the loans that match the same borrower.
     * @param ID The ID of the Borrower.
     * @return An Array of Loans that match the same borrower.
     * @throws ObjectNotFoundException if there isn't any Loans that matches the given ID.
     */
    @Override
    public Loan[] searchByBorrower(final int ID) throws IOException {
        final int BORROWER_ID = 2;
        ArrayList<String[]> loans = new ArrayList<>();

        for (String[] data : getAllRawData())
            if (Integer.parseInt(data[BORROWER_ID]) == ID)
                loans.add(data);

        if (loans.isEmpty())
            throw new ObjectNotFoundException("There's no Loan with that Borrower'sID");

        return new LoanMaker(ArrayConverter.convert(loans)).makeMultiple();
    }

}
