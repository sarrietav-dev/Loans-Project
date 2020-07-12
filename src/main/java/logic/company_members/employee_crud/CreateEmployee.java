package logic.company_members.employee_crud;

import logic.company_members.Employee;
import logic.exceptions.EmployeeAlreadyExistsException;

import java.util.ArrayList;

public class CreateEmployee extends CRUD {
    private static ArrayList<Employee> employees;
    public static void create(Employee employee) {
        if (ReadEmployee.doesEmployeeExist(employee))
            throw new EmployeeAlreadyExistsException();
        else
            employees.add(employee);
        database.updateDataList(employees);
    }

    public static void create(Employee... employeesArr) {
        try {
            employees = new ArrayList<>();
            for (Employee employee : employeesArr)
                create(employee);
        } catch (EmployeeAlreadyExistsException ignored) {

        }
    }

    private static void createEmployee(Employee employee) {
        getEmployees().add(employee);
        database.updateDataList(getEmployees());
    }
}
