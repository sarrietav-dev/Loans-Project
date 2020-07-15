/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.employee;

import java.util.Set;
import logic.file_management.client_crud.ReadClient;
import logic.loan_classes.Client;

/**
 *
 * @author Administrador
 */
public class EmployeeSelectedClient {

    private static Client theClient;
    
    public static void setSelectedClientClient(Client theClient)
    {
        EmployeeSelectedClient.theClient = theClient;
    }
    
    public static Client getSelectedClient()
    {
        return EmployeeSelectedClient.theClient;
    }
    
}
