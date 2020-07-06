package logic.file_management.loan_crud;

import logic.file_management.CRUD;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateLoan extends CRUD {
    public static void update(Loan loan) {
        HashMap<Client, ArrayList<Loan>> data = CLIENT_DATABASE.getData();
        ArrayList<Loan> loans = data.get(loan.getClient());
        loans.removeIf(loanFromData -> loanFromData.getLoanNumber() == loan.getLoanNumber());
        loans.add(loan);
        CLIENT_DATABASE.updateDataList(data);
    }
}
