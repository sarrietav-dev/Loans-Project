package logic.company_members;

import logic.databases.ClientDatabase;

public class Admin {
    private static final ClientDatabase CLIENT_DATABASE = ClientDatabase.getInstance();

    public static void changeMaximumAmountToLend(double amount) {
            CLIENT_DATABASE.setMaximumAmountToLend(amount);
    }

    public static void changeMaximumToLendPerClient(double amount) {
        CLIENT_DATABASE.setMaximumToLendPerClient(amount);
    }
}
