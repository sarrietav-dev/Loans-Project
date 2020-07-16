package logic.file_management;

import logic.databases.ClientDatabase;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class ClientLoanCRUD {
    protected static final ClientDatabase CLIENT_DATABASE = ClientDatabase.getInstance();
    protected static final HashMap<Client, ArrayList<Loan>> data = CLIENT_DATABASE.getData();


    public static Set<Map.Entry<Client, ArrayList<Loan>>> getAllData() {
        return data.entrySet();
    }
}
