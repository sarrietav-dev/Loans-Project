package logic.file_management.readers;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.Maker;
import logic.file_management.ReadCSV;
import logic.file_management.makers.BorrowerMaker;
import logic.loan_classes.Borrower;

import java.io.*;
import java.util.ArrayList;

/**
 * This class reads through a CSV file and retrieves information.
 */
public class BorrowerReader extends ReadCSV {
    BufferedReader bufferedReader;

    public BorrowerReader() throws FileNotFoundException {
        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "borrowers.csv");
        bufferedReader = new BufferedReader(new FileReader(PATH));
    }

    /**
     * Searches a borrower in the CSV.
     * @param id ID to be searched.
     * @return true if the ID exists, this the borrower exists. Either way returns false.
     */
    @Override
    public boolean doesIDExist(int id) throws IOException {
        return getIDs().contains(id);
    }

    private ArrayList<Integer> getIDs() throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        final int ID = 0;
        for (String[] data : getAllRawData())
            ids.add(Integer.parseInt(data[ID]));
        return ids;
    }

    /**
     * Searches for a {@link logic.loan_classes.Borrower} object and returns it.
     * @param id The id of the borrower you want to retrieve.
     * @return The wanted {@link Borrower} object.
     */
    @Override
    public Borrower get(int id) throws IOException {
        Maker borrowerMaker = new BorrowerMaker(getBorrowerInfo(id));
        return (Borrower) borrowerMaker.make();
    }


    private String[] getBorrowerInfo(int id) throws IOException {
        for (String[] info : getAllRawData())
            if (Integer.parseInt(info[0]) == id)
                return info;
        throw new ObjectNotFoundException("Borrower's ID not found");
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

}
