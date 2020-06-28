package logic.file_management;

import logic.file_management.borrower_crud.BorrowerCRUD;
import logic.file_management.borrower_crud.ReadBorrower;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Writer implements Serializable {
    Loan[] loans;
    Borrower[] borrowers;
    HashMap<Borrower, ArrayList<Loan>> storage;

    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;

    public Writer() throws IOException {
        fileOutputStream = new FileOutputStream(new File("data/data.dat"));
        objectOutputStream = new ObjectOutputStream(fileOutputStream);

        storage = new HashMap<>();

        storage.put(new Borrower("yo", "oy", 1010101, "seb", "1", "2", "home"), new ArrayList<>());

        objectOutputStream.writeObject(storage);

        objectOutputStream.close();
        fileOutputStream.close();
    }

    public Writer(Loan... loans) throws IOException, ClassNotFoundException {
        fileOutputStream = new FileOutputStream(new File("data/data.dat"));
        objectOutputStream = new ObjectOutputStream(fileOutputStream);

        HashMap<Borrower, ArrayList<Loan>> hashMap = new Reader().getData();
        storage.putAll(hashMap);

        this.loans = loans;
    }

    public Writer(Borrower... borrowers) throws IOException, ClassNotFoundException {
        fileOutputStream = new FileOutputStream(new File("data/data.dat"));
        objectOutputStream = new ObjectOutputStream(fileOutputStream);

        HashMap<Borrower, ArrayList<Loan>> hashMap = new Reader().getData();
        storage.putAll(hashMap);

        this.borrowers = borrowers;
    }

    public void write() throws IOException, ClassNotFoundException {
        if (loans != null)
            addLoans();
        else if (borrowers != null)
            addBorrower();

        objectOutputStream.writeObject(storage);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private void addLoans() throws IOException, ClassNotFoundException {
        ReadBorrower reader = new BorrowerCRUD();

        for (Loan loan : loans) {
            if (reader.doesBorrowerExists(loan.getBorrower()))
                addNewEntry(loan);
            else
                addLoanToExistingBorrower(loan);
        }
    }

    private void addNewEntry(Loan loan) {
        storage.put(loan.getBorrower(), new ArrayList<>() {{add(loan);}});
    }

    private void addLoanToExistingBorrower(Loan loan) {
        for (Borrower borrower : storage.keySet())
            if (borrower.equals(loan.getBorrower())) {
                storage.get(borrower).add(loan);
                break;
            }
    }

    private void addBorrower() {
        for (Borrower borrower : borrowers)
            storage.put(borrower, new ArrayList<>());
    }
}
