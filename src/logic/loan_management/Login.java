package logic.loan_management;

import logic.exceptions.InvalidUserException;
import logic.loan_classes.Borrower;

public class Login extends SystemManagement {
    public static LoanManager login(String username, String password) {
        for (Borrower borrower : data.keySet()) {
            String user = borrower.getDataAccess()[0];
            String pass = borrower.getDataAccess()[1];
            if (user.equals(username) && pass.equals(password))
                return new LoanManager();
        }
        throw new InvalidUserException("Incorrect User or Password");
    }
}
