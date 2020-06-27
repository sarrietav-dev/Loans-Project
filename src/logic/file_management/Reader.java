package logic.file_management;

import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;

public class Reader {
    FileInputStream fileInputStream;
    ObjectInputStream objectInputStream;

    public Reader() throws IOException {
        fileInputStream = new FileInputStream(new File("data/data.txt"));
        objectInputStream = new ObjectInputStream(fileInputStream);
    }

    public ArrayList<Loan> read() throws IOException, ClassNotFoundException {
        ArrayList<Loan> loanArrayList = (ArrayList<Loan>) objectInputStream.readObject();
        close();
        return loanArrayList;
    }

    public void close() throws IOException {
        objectInputStream.close();
        fileInputStream.close();
    }
}
