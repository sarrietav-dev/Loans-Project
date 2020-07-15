package logic.file_management.client_crud;

import logic.exceptions.ObjectNotFoundException;
import logic.file_management.CRUD;
import logic.file_management.loan_crud.ReadLoan;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReadClient extends CRUD {
    public static Set<Client> getAllClients() {
        return data.keySet();
    }

    public static boolean doesClientExist(Client client) {
        return data.keySet().stream()
            .anyMatch(client::equals);
    }

    public static double totalAmountBorrowed(Client client) {
        double sum = 0.0;
        for (Loan loan : data.get(client)) {
            double amount = loan.getAmount();
            if (!loan.isPaid())
                sum += amount;
        }
        return sum;
    }

    public static double getInterestsCollected() {
        return getAllClients().stream()
                .flatMap(client -> data.get(client).stream())
                .mapToDouble(Loan::getInterestsCollected).sum();
    }

    public static boolean hasAnyLoanDelayed(Client client) {
        return data.get(client).stream()
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

    public static ArrayList<Loan> getLoans(Client client) {
        return data.get(client);
    }

    public static ArrayList<Loan> getLoans(final int CLIENT_ID) {
        for (Client client : getAllClients())
            if (client.getId() == CLIENT_ID)
                return data.get(client);
        throw new ObjectNotFoundException("Client not found!");
    }
}
