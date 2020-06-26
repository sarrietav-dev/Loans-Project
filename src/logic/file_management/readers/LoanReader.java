package logic.file_management.readers;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.Maker;
import logic.file_management.ReadCSV;
import logic.file_management.Searchable;
import logic.file_management.makers.LoanMaker;

import java.io.*;
import java.util.ArrayList;

public class LoanReader extends ReadCSV implements Searchable {
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
    public ArrayList<String[]> getAllData() throws IOException {
        String line;
        ArrayList<String[]> data = new ArrayList<>();

        while((line = bufferedReader.readLine()) != null)
            data.add(line.split(","));

        return data;
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
    public Object get(int id) throws IOException {
        Maker loanMaker = new LoanMaker(getLoanInfo(id));
        return loanMaker.make();
    }

    // TODO: Add functionality to this.
    @Override
    public void search(int ID, boolean filter) {

    }
}
