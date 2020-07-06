package logic;

public class Admin {
    private static final DataBase dataBase = DataBase.getInstance();

    public static void changeMaximumAmountToLend(double amount) {
            dataBase.setMaximumAmountToLend(amount);
    }

    public static void changeMaximumToLendPerClient(double amount) {
        dataBase.setMaximumToLendPerClient(amount);
    }
}
