package logic.file_management.client_crud;

import logic.exceptions.ClientAlreadyExistsException;
import logic.file_management.client_crud.CreateClient;
import logic.file_management.client_crud.ReadClient;
import logic.loan_classes.Client;
import org.junit.jupiter.api.Test;

class CreateClientTest {
    @Test
    void createBorrower() {
        Client tempClient = new Client(1002244364, "Sebastian", "696969696",
                "420", "home");
        Client tempClient2 = new Client(1002244364, "Sebastian", "696969696",
                "420", "home");
        Client tempClient3 = new Client(20023023, "Sebastian", "696969696",
                "420", "home");
        Client tempClient4 = new Client(4949494, "Jess", "696969696",
                "420", "home");
        try {
            CreateClient.create(tempClient, tempClient2, tempClient3, tempClient4);
            for (Client client : ReadClient.getAllClients())
                System.out.println(client);
        } catch (ClientAlreadyExistsException e) {
            System.out.println("Borrower already exists!");
        }
    }
}