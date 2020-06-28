package logic;

import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    private static DataBase dataBase;
    private static HashMap<Borrower, ArrayList<Loan>> data;
    private static final File PATH = new File("data" + File.separator +"data.dat");

    private DataBase() throws IOException, ClassNotFoundException {
        load();
    }

    public static DataBase getInstance() throws IOException, ClassNotFoundException {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    private void load() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            data = (HashMap<Borrower, ArrayList<Loan>>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        }
        catch (EOFException e) {
            updateData(new HashMap<>());
            load();
        }

    }

    public HashMap<Borrower, ArrayList<Loan>> getData() {
        return data;
    }

    public void updateData(HashMap<Borrower, ArrayList<Loan>> data) throws IOException {
        DataBase.data = data;

        FileOutputStream fileOutputStream = new FileOutputStream(PATH);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(data);

        objectOutputStream.close();
        fileOutputStream.close();
    }
}
