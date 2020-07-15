/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.employee;

import logic.company_members.Employee;

/**
 *
 * @author Administrador
 */
public class EmployeeUsser {
    
    private static Employee usser;
    
    public static void setUsser(Employee usser)
    {
        EmployeeUsser.usser = usser;
    }
    
    public static Employee getUsser()
    {
        return EmployeeUsser.usser;
    }
    
}
