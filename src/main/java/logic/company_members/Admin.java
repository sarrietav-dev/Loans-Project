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

    public static void changeAuthDate(int date) {
	    CLIENT_DATABASE.updateLimitDate(date);
    }

    public static double getMaximumAmountToLend() {
        return CLIENT_DATABASE.getMaximumAmountToLend();
    }

    public static double getMaximumToLendPerClient() {
        return CLIENT_DATABASE.getMaximumToLendPerClient();
    }

    public static int getAuthDate() {
        return CLIENT_DATABASE.getLimitOfAuthDate();
    }
}
