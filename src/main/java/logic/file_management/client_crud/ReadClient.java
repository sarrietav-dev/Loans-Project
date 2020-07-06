package logic.file_management.client_crud;

import logic.file_management.CRUD;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.Set;
import java.util.stream.Collectors;

public class ReadClient extends CRUD {
    public static Set<Client> getAllClients() {
        return dataBase.getData().keySet();
    }

    public static boolean doesClientExist(Client client) {
        return dataBase.getData().keySet().stream()
                .anyMatch(client::equals);
    }

    public static double totalAmountBorrowed(Client client) {
        double sum = 0.0;
        for (Loan loan : dataBase.getData().get(client)) {
            double amount = loan.getAmount();
            if (!loan.isPaid())
                sum += amount;
        }
        return sum;
    }

    public static boolean hasAnyLoanDelayed(Client client) {
        return dataBase.getData().get(client).stream()
                .anyMatch(ReadLoan::isLoanDelayed);
    }

    public static Set<Client> getDelayedClients() {
        return getAllClients().stream()
                .filter(ReadClient::hasAnyLoanDelayed)
                .collect(Collectors.toSet());
    }
}
