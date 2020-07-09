package logic.databases;

import logic.company_members.Employee;

import java.io.*;
import java.util.ArrayList;

/**
 * This class manages all the persistence client data between the hard disk and the running program.
 */
public class EmployeeDatabase {
    private static EmployeeDatabase employeeDatabase;

    private static ArrayList<Employee> employees;

    private static final File PATH = new File("data" + File.separator + "employee-data.dat");

    private EmployeeDatabase() {
        load();
    }

    public static EmployeeDatabase getInstance() {
        if (employeeDatabase == null)
            employeeDatabase = new EmployeeDatabase();
        return employeeDatabase;
    }

    @SuppressWarnings("unchecked")
    private void load() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            employees = (ArrayList<Employee>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (EOFException | WriteAbortedException e) {
            updateDataList(new ArrayList<>());
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
     * Takes an arraylist with all the new data and updates the file where the old data was stored.
     * @param employees A new arraylist that will be stored in the file.
     */
    public void updateDataList(ArrayList<Employee> employees) {
        EmployeeDatabase.employees = employees;
        uploadDataToTheDatabase();
    }

    /**
     * Gets the database of employees.
     * @return Returns a copy of all the available employees of the company.
     */
    public ArrayList<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    private void uploadDataToTheDatabase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(employees);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
