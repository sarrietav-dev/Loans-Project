package logic.file_management.client_crud;

import logic.exceptions.ClientAlreadyExistsException;
import logic.exceptions.CannotAddMoreLoansException;
import logic.file_management.CRUD;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import static logic.file_management.client_crud.ReadClient.hasAnyLoanDelayed;

public class UpdateClient extends CRUD {
    public static void addLoan(Loan loan) {
        try {
            CreateClient.create(loan.getClient());
        } catch (ClientAlreadyExistsException ignored) {

        }
        addLoan(loan, loan.getClient().getId());
    }

    public static void addLoan(Loan loan, final int CLIENT_ID) {
        HashMap<Client, ArrayList<Loan>> data = CLIENT_DATABASE.getData();
        Set<Entry<Client, ArrayList<Loan>>> entrySet = data.entrySet();

        List<Entry<Client, ArrayList<Loan>>> entryList = entrySet.stream()
                .filter(entry -> CLIENT_ID == entry.getKey().getId())
                .collect(Collectors.toList());

        entryList.forEach(entry -> {
            if (hasAnyLoanDelayed(entry.getKey())) {
                throw new CannotAddMoreLoansException("Client " + entry.getKey().getName() +
                        " has a delayed installment.");
            } else if (ReadClient.totalAmountBorrowed(entry.getKey()) > CLIENT_DATABASE.getMaximumToLendPerClient()) {
                throw new CannotAddMoreLoansException("Client " + entry.getKey().getName() +
                        " has exceeded the limit of borrowed money");
            } else if (loan.getAmount() > CLIENT_DATABASE.getMaximumToLendPerClient()) {
                throw new CannotAddMoreLoansException("The loan surpasses the limit specified limit ("
                        + CLIENT_DATABASE.getMaximumToLendPerClient() + ").");
            } // TODO: 6/07/20 If all money is borrowed.
            else {
                setAddLoanOperation(loan, entry);
                CLIENT_DATABASE.updateDataList(data);
            }
        });
    }

    private static void setAddLoanOperation(Loan loan, Entry<Client, ArrayList<Loan>> entry) {
        loan.setClient(entry.getKey());
        entry.getValue().add(loan);
    }
}
