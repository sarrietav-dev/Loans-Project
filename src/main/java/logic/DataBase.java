package logic;

import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    private static DataBase dataBase;
    private static HashMap<Borrower, ArrayList<Loan>> data;
    private static double maximumAmountToLend = 999999999;
    private static double maximumToLendPerBorrower = 100000;
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
            maximumAmountToLend = objectInputStream.readDouble();
            maximumToLendPerBorrower = objectInputStream.readDouble();

            objectInputStream.close();
            fileInputStream.close();
        }
        catch (EOFException e) {
            updateDataList(new HashMap<>());
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

    public double getMaximumAmountToLend() {
        return maximumAmountToLend;
    }

    public void setMaximumAmountToLend(double maximumAmountToLend) {
        DataBase.maximumAmountToLend = maximumAmountToLend;
        uploadDataToTheDataBase();
    }

    public double getMaximumToLendPerBorrower() {
        return maximumToLendPerBorrower;
    }

    public void setMaximumToLendPerBorrower(double maximumToLendPerBorrower) {
        DataBase.maximumToLendPerBorrower = maximumToLendPerBorrower;
        uploadDataToTheDataBase();
    }

    public void updateDataList(HashMap<Borrower, ArrayList<Loan>> data) {
        DataBase.data = data;
        uploadDataToTheDataBase();
    }

    private void uploadDataToTheDataBase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(data);
            objectOutputStream.writeDouble(maximumAmountToLend);
            objectOutputStream.writeDouble(maximumToLendPerBorrower);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
