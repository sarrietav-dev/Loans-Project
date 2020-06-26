package logic.file_management.readers;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.Maker;
import logic.file_management.ReadCSV;
import logic.file_management.makers.BorrowerMaker;

import java.io.*;
import java.util.ArrayList;

public class BorrowerReader extends ReadCSV {
    BufferedReader bufferedReader;

    public BorrowerReader() throws FileNotFoundException {
        File PATH = new File(PARENT_FOLDER_PATH.getPath() + File.separator + "borrowers.csv");
        bufferedReader = new BufferedReader(new FileReader(PATH));
    }

    @Override
    public boolean doesIDExist(int id) throws IOException {
        return getIDs().contains(id);
    }

    private ArrayList<Integer> getIDs() throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        final int ID = 0;
        for (String[] data : getAllData())
            ids.add(Integer.parseInt(data[ID]));
        return ids;
    }

    @Override
    public Object get(int id) throws IOException {
        Maker borrowerMaker = new BorrowerMaker(getBorrowerInfo(id));
        return borrowerMaker.make();
    }

    private String[] getBorrowerInfo(int id) throws IOException {
        for (String[] info : getAllData())
            if (Integer.parseInt(info[0]) == id)
                return info;
        throw new ObjectNotFoundException("Borrower's ID not found");
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
    public void close() throws IOException {
        bufferedReader.close();
    }

}
