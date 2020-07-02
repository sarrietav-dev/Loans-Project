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

    private DataBase() {
        load();
    }

    public static DataBase getInstance() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    private void load() {
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
        catch (InvalidClassException e) {
            try {
                PrintWriter printWriter = new PrintWriter(PATH);
                printWriter.print("");
                printWriter.close();
                System.out.println("Classes incompatible. File erased. Run the test again.");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public HashMap<Borrower, ArrayList<Loan>> getData() {
        return data;
    }

    public void updateData(HashMap<Borrower, ArrayList<Loan>> data) {
        DataBase.data = data;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
