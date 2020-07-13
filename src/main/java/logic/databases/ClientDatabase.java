package logic.databases;

import logic.exceptions.DateOutOfLimitException;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class manages all the persistence client data between the hard disk and the running program.
 */
@SuppressWarnings("unchecked")
public class ClientDatabase {
    private static ClientDatabase clientDatabase;
    private static HashMap<Client, ArrayList<Loan>> data;
    private static double maximumAmountToLend = 999999999;
    private static double maximumToLendPerClient = 100000;
    private static int limitOfAuthDate = 20;
    private static final File PATH = new File("data" + File.separator + "client-data.dat");

    private ClientDatabase() {
        load();
    }

    public static ClientDatabase getInstance() {
        if (clientDatabase == null)
            clientDatabase = new ClientDatabase();
        return clientDatabase;
    }

    @SuppressWarnings("unchecked")
    private void load() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            data = (HashMap<Client, ArrayList<Loan>>) objectInputStream.readObject();
            maximumAmountToLend = objectInputStream.readDouble();
            maximumToLendPerClient = objectInputStream.readDouble();
            limitOfAuthDate = objectInputStream.readInt();

            objectInputStream.close();
            fileInputStream.close();
        } catch (EOFException e) {
            updateDataList(new HashMap<>());
            load();
        } catch (InvalidClassException e) {
            try {
                PrintWriter printWriter = new PrintWriter(PATH);
                printWriter.print("");
                printWriter.close();
                System.out.println("Classes incompatible. File erased. Run the test again.");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the data of the database.
     *
     * @return a copy the clients data.
     */
    public HashMap<Client, ArrayList<Loan>> getData() {
        return new HashMap<>(data);
    }

    public double getMaximumAmountToLend() {
        return maximumAmountToLend;
    }

    public void setMaximumAmountToLend(double maximumAmountToLend) {
        ClientDatabase.maximumAmountToLend = maximumAmountToLend;
        uploadDataToTheDataBase();
    }

    public double getMaximumToLendPerClient() {
        return maximumToLendPerClient;
    }

    public void setMaximumToLendPerClient(double maximumToLendPerClient) {
        ClientDatabase.maximumToLendPerClient = maximumToLendPerClient;
        uploadDataToTheDataBase();
    }

    /**
     * Takes a hashmap with all the new data and updates the file where the old data was stored.
     *
     * @param data The new data of clients.
     */
    public void updateDataList(HashMap<Client, ArrayList<Loan>> data) {
        ClientDatabase.data = data;
        uploadDataToTheDataBase();
    }

    /**
     * Takes an int and sets the limit day to set an auth day
     *
     * @param limitOfAuthDate
     * @throws DateOutOfLimitException if the date is out of the first 20 days of the month.
     * @throws NumberFormatException   if the number is negative.
     */
    public void updateLimitDate(int limitOfAuthDate) {
        if (limitOfAuthDate <= 0)
            throw new NumberFormatException("Only positive numbers");
        else if (limitOfAuthDate > 20)
            throw new DateOutOfLimitException("Date out of limits! Only between the first 20 days of the month!");
        else
            ClientDatabase.limitOfAuthDate = limitOfAuthDate;
        uploadDataToTheDataBase();
    }

    public int getLimitOfAuthDate() {
        return limitOfAuthDate;
    }

    private void uploadDataToTheDataBase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(data);
            objectOutputStream.writeDouble(maximumAmountToLend);
            objectOutputStream.writeDouble(maximumToLendPerClient);
            objectOutputStream.writeInt(limitOfAuthDate);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
