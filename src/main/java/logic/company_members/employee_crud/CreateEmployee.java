package logic.company_members.employee_crud;

import logic.company_members.Employee;
import logic.exceptions.EmployeeAlreadyExistsException;

public class CreateEmployee extends CRUD {
    public static void create(Employee employee) {
        if (ReadEmployee.doesEmployeeExist(employee))
            throw new EmployeeAlreadyExistsException();
        else
            createEmployee(employee);
    }

    public static void create(Employee... employees) {
        try {
            for (Employee employee : employees)
                create(employee);
        } catch (EmployeeAlreadyExistsException ignored) {

        }
    }

    private static void createEmployee(Employee employee) {
        getEmployees().add(employee);
        database.updateDataList(getEmployees());
    }
}
