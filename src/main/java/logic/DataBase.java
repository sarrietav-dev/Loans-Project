package logic;

import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class DataBase {
    private static DataBase dataBase;
    private static HashMap<Client, ArrayList<Loan>> data;
    private static double maximumAmountToLend = 999999999;
    private static double maximumToLendPerClient = 100000;
    private static final File PATH = new File("data" + File.separator +"data.dat");

    private DataBase() {
        load();
    }

    public static DataBase getInstance() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    @SuppressWarnings("unchecked")
    private void load() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            data = (HashMap<Client, ArrayList<Loan>>) objectInputStream.readObject();
            maximumAmountToLend = objectInputStream.readDouble();
            maximumToLendPerClient = objectInputStream.readDouble();

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

    public HashMap<Client, ArrayList<Loan>> getData() {
        return data;
    }

    public double getMaximumAmountToLend() {
        return maximumAmountToLend;
    }

    public void setMaximumAmountToLend(double maximumAmountToLend) {
        DataBase.maximumAmountToLend = maximumAmountToLend;
        uploadDataToTheDataBase();
    }

    public double getMaximumToLendPerClient() {
        return maximumToLendPerClient;
    }

    public void setMaximumToLendPerClient(double maximumToLendPerClient) {
        DataBase.maximumToLendPerClient = maximumToLendPerClient;
        uploadDataToTheDataBase();
    }

    public void updateDataList(HashMap<Client, ArrayList<Loan>> data) {
        DataBase.data = data;
        uploadDataToTheDataBase();
    }

    private void uploadDataToTheDataBase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(data);
            objectOutputStream.writeDouble(maximumAmountToLend);
            objectOutputStream.writeDouble(maximumToLendPerClient);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
