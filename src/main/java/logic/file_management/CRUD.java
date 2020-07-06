package logic.file_management;

import logic.databases.ClientDatabase;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public abstract class CRUD {
    protected static final ClientDatabase CLIENT_DATABASE = ClientDatabase.getInstance();


    public static Set<Map.Entry<Client, ArrayList<Loan>>> getAllData() {
        return CLIENT_DATABASE.getData().entrySet();
    }
}
