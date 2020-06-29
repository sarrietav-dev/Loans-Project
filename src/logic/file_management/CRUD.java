package logic.file_management;

import logic.DataBase;
import logic.loan_classes.Borrower;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public abstract class CRUD {
    protected static DataBase dataBase;

    static {
            dataBase = DataBase.getInstance();
    }

    public static Set<Map.Entry<Borrower, ArrayList<Loan>>> getAllData() {
        return dataBase.getData().entrySet();
    }
}
