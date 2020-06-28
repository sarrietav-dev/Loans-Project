package logic.file_management.borrower_crud;

import logic.file_management.Reader;
import logic.file_management.Writer;
import logic.loan_classes.Borrower;

import java.io.IOException;
import java.util.Set;

public class BorrowerCRUD implements CreateBorrower, ReadBorrower, UpdateBorrower, DeleteBorrower {

    public BorrowerCRUD() throws IOException {
    }

    @Override
    public void create(Borrower borrower) throws IOException, ClassNotFoundException {
        if (!doesBorrowerExists(borrower)) {
            Writer writer = new Writer(borrower);
            writer.write();
        }
    }

    @Override
    public boolean doesBorrowerExists(Borrower borrower) throws IOException, ClassNotFoundException {
        for (Borrower borrowerKey : new Reader().getData().keySet())
            if (borrower.equals(borrowerKey))
                return true;
        return false;
    }

    @Override
    public Set<Borrower> getAllBorrowers() throws IOException, ClassNotFoundException {
        return new Reader().getData().keySet();
    }
}
