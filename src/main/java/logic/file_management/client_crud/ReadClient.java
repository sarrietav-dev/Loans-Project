package logic.file_management.client_crud;

import logic.company_members.Employee;
import logic.file_management.CRUD;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.Set;
import java.util.stream.Collectors;

public class ReadClient extends CRUD {
    public static Set<Client> getAllClients() {
        return CLIENT_DATABASE.getData().keySet();
    }

    public static boolean doesClientExist(Client client) {
        return CLIENT_DATABASE.getData().keySet().stream()
                .anyMatch(client::equals);
    }

    public static double totalAmountBorrowed(Client client) {
        double sum = 0.0;
        for (Loan loan : CLIENT_DATABASE.getData().get(client)) {
            double amount = loan.getAmount();
            if (!loan.isPaid())
                sum += amount;
        }
        return sum;
    }

    public static boolean hasAnyLoanDelayed(Client client) {
        return CLIENT_DATABASE.getData().get(client).stream()
                .anyMatch(ReadLoan::isLoanDelayed);
    }

    public static double getAllBorrowedMoney() {
        return CLIENT_DATABASE.getData().keySet().stream()
                .mapToDouble(ReadClient::totalAmountBorrowed).sum();
    }

    public static Set<Client> getDelayedClients() {
        return getAllClients().stream()
                .filter(ReadClient::hasAnyLoanDelayed)
                .collect(Collectors.toSet());
    }
}
