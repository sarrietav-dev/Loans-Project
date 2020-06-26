package logic.file_management.readers;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.Maker;
import logic.file_management.ReadCSV;
import logic.file_management.FilteredSearch;
import logic.file_management.makers.LoanMaker;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;

public class LoanReader extends ReadCSV implements FilteredSearch {
    private final BufferedReader bufferedReader;

    public LoanReader() throws FileNotFoundException {
        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "loans.csv");
        bufferedReader = new BufferedReader(new FileReader(PATH));
    }

    public ArrayList<Integer> getIDs() throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        final int ID = 0;
        for (String[] data : getAllData())
            ids.add(Integer.parseInt(data[ID]));

        return ids;
    }

    @Override
    public String[][] getAllData() throws IOException {
        String line;
        ArrayList<String[]> data = new ArrayList<>();

        while((line = bufferedReader.readLine()) != null)
            data.add(line.split(","));

        return ArrayConverter.convert(data);
    }

    @Override
    public boolean doesIDExist(int id) throws IOException {
        return getIDs().contains(id);
    }

    private String[] getLoanInfo(int id) throws IOException {
        for (String[] info : getAllData())
            if (Integer.parseInt(info[0]) == id)
                return info;
        throw new ObjectNotFoundException("Loan's ID not found");
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }

    @Override
    public Loan get(int id) throws IOException {
        Maker loanMaker = new LoanMaker(getLoanInfo(id));
        return (Loan) loanMaker.make();
    }

    // TODO: Add functionality to this.
    @Override
    public Loan[] search(final int ID, boolean filter) throws IOException {
        if (filter == FilteredSearch.LOAN) {
            return new Loan[] {
                    get(ID)
            };
        } else if (filter == FilteredSearch.BORROWER) {
            return getFilteredLoans(ID);
        }

        throw new ObjectNotFoundException("There's no Loan or Borrower with that ID");
    }

    private Loan[] getFilteredLoans(final int ID) throws IOException {
        final int BORROWER_ID = 2;
        ArrayList<String[]> loans = new ArrayList<>();

        for (String[] data : getAllData())
            if (Integer.parseInt(data[BORROWER_ID]) == ID)
                loans.add(data);

        return new LoanMaker(ArrayConverter.convert(loans)).makeMultiple();
    }
}
