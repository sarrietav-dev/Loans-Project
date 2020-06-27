package logic.file_management;

import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Writer {
    Loan[] loans;
    ArrayList<Loan> storage;

    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;

    public Writer(Loan... loans) throws IOException, ClassNotFoundException {
        fileOutputStream = new FileOutputStream(new File("data/data.txt"));
        objectOutputStream = new ObjectOutputStream(fileOutputStream);

        storage = new Reader().read();

        this.loans = loans;
    }

    public void write() throws IOException {
        storage.addAll(Arrays.asList(loans));
        objectOutputStream.writeObject(storage);
        objectOutputStream.close();
    }

}
