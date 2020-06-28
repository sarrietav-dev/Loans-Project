package logic.file_management;

import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Reader {
    private HashMap<Borrower, ArrayList<Loan>> data = new HashMap<>();

    public Reader() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("data/data.dat"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


        // TODO: THIS SHIT BREAKS ITSELF.
        HashMap<Borrower, ArrayList<Loan>> hashMap = (HashMap<Borrower, ArrayList<Loan>>) objectInputStream.readObject();
        data.putAll(hashMap);


        objectInputStream.close();
        fileInputStream.close();
    }

    public HashMap<Borrower, ArrayList<Loan>> getData() {
        return data;
    }
}
