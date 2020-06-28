package logic.file_management;

import logic.DataBase;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public abstract class CRUD {
    protected static DataBase dataBase;

    static {
        try {
            dataBase = DataBase.getInstance();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Set<Map.Entry<Borrower, ArrayList<Loan>>> getAllData() {
        return dataBase.getData().entrySet();
    }
}
