package logic.company_members.employee_crud;

import logic.company_members.Employee;
import logic.exceptions.EmployeeAlreadyExistsException;

public class CreateEmployee extends EmployeeCRUD {

    public static void create(Employee employee) {
        if (ReadEmployee.doesEmployeeExist(employee))
            throw new EmployeeAlreadyExistsException();
        else
            employees.add(employee);
        database.updateDataList(employees);

    }

    public static void create(Employee... employeesArr) {
        try {
            for (Employee employee : employeesArr)
                create(employee);
        } catch (EmployeeAlreadyExistsException ignored) {

        }
    }
}
