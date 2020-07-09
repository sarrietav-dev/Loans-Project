package logic.file_management.client_crud;

import logic.exceptions.ClientAlreadyExistsException;
import logic.file_management.CRUD;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static logic.file_management.client_crud.ReadClient.doesClientExist;

public class CreateClient extends CRUD {

    /**
     * Creates a client and adds it to the database. It must have an unique ID.
     * @param client The client that will be added.
     * @throws ClientAlreadyExistsException if the ID exists in the database.
     */
    public static void create(Client client) {
        if (!doesClientExist(client))
            createClient(client);
        else
            throw new ClientAlreadyExistsException("That ID is taken!");
    }

    /**
     * Takes a series of clients and add each one to the database.
     * @param clients The array of clients that will be added
     */
    public static void create(Client... clients) {
        Arrays.stream(clients).forEach(client -> {
            try {
                createClient(client);
            } catch (ClientAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private static void createClient(Client client) {
        HashMap<Client, ArrayList<Loan>> data = CLIENT_DATABASE.getData();
        data.put(client, new ArrayList<>());
        CLIENT_DATABASE.updateDataList(data);
    }
}