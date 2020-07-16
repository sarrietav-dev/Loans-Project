package logic.file_management.client_crud;

import logic.exceptions.CannotAddMoreLoansException;
import logic.exceptions.ClientAlreadyExistsException;
import logic.exceptions.ObjectNotFoundException;
import logic.file_management.ClientLoanCRUD;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import static logic.file_management.client_crud.CreateClient.create;
import static logic.file_management.client_crud.ReadClient.*;

public class UpdateClient extends ClientLoanCRUD {

	/**
	 * Add a loan to the database, if the client that it has it's not found on the database, it's create.
	 *
	 * @param loan The loan that will be added.
	 */
	public static void addLoan(Loan loan) {
		try {
			create(loan.getClient());
		} catch (ClientAlreadyExistsException ignored) {
		}
		addLoan(loan, loan.getClient().getId());
	}

	/**
	 * Adds a loan to an specified client.
	 *
	 * @param loan      The loan that will be stored.
	 * @param CLIENT_ID The ID of the client that owns the loan.
	 * @throws ObjectNotFoundException     If the ID doesn't match any existing client.
	 * @throws CannotAddMoreLoansException If:
	 *                                     <ul>
	 *                                         <li>The client is has a delayed loan.</li>
	 *                                         <li>The client exceeds the maximum amount of money to borrow per client</li>
	 *                                         <li>The loan amount exceeds the maximum amount of money to borrow per client</li>
	 *                                         <li>The total amount of money borrowed of all clients exceeds the company's total amount
	 *                                         of available money to borrow.</li>
	 *                                     </ul>
	 */
	public static void addLoan(Loan loan, final int CLIENT_ID) {
		Set<Entry<Client, ArrayList<Loan>>> entrySet = data.entrySet();

		List<Entry<Client, ArrayList<Loan>>> entryList = entrySet.stream()
			.filter(clientArrayListEntry -> CLIENT_ID == clientArrayListEntry.getKey().getId())
			.collect(Collectors.toList());

		if (entryList.isEmpty())
			throw new ObjectNotFoundException("The client with the ID: " + CLIENT_ID + " wasn't found.");

		entryList.forEach(entry -> {
			if (hasAnyLoanDelayed(entry.getKey()))
				throw new CannotAddMoreLoansException("Client " + entry.getKey().getName() +
						" has a delayed installment.");
			else if (totalAmountBorrowed(entry.getKey()) > CLIENT_DATABASE.getMaximumToLendPerClient())
				throw new CannotAddMoreLoansException("Client " + entry.getKey().getName() +
						" has exceeded the limit of borrowed money");
			else if (loan.getAmount() > CLIENT_DATABASE.getMaximumToLendPerClient())
				throw new CannotAddMoreLoansException("The loan surpasses the limit specified limit ("
						+ CLIENT_DATABASE.getMaximumToLendPerClient() + ").");
			else if (getAllBorrowedMoney() > CLIENT_DATABASE.getMaximumAmountToLend())
				throw new CannotAddMoreLoansException("All clients have borrowed all money");

			setAddLoanOperation(loan, entry);
			CLIENT_DATABASE.updateDataList(data);
		});
	}

	private static void setAddLoanOperation(Loan loan, Entry<Client, ArrayList<Loan>> entry) {
		loan.setClient(entry.getKey());
		entry.getValue().add(loan);
	}

	/**
	 * Takes a client and updates its values.
	 *
	 * @param client The client that will be changed.
	 */
	public static void update(Client client) {
		Set<Entry<Client, ArrayList<Loan>>> entrySet = data.entrySet();

		for (Entry<Client, ArrayList<Loan>> entry : entrySet) {
			if (client.equals(entry.getKey())) {
				replace(entry.getKey(), client);
				return;
			}
		}
	}

	private static void replace(Client clientOld, Client clientNew) {
		ArrayList<Loan> loans = data.remove(clientOld);
		if (doesClientExist(clientNew)) {
			clientNew.setLoans();
			data.put(clientNew, loans);
			CLIENT_DATABASE.updateDataList(data);
		} else
			throw new ClientAlreadyExistsException("That ID is taken!");
	}
}
