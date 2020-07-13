package logic.company_members.employee_crud;

import logic.company_members.Employee;
import logic.exceptions.LoginIncorrectException;

import java.util.ArrayList;

public class ReadEmployee extends CRUD {
    public static boolean doesEmployeeExist(Employee employee) {
        return employees.stream()
                .anyMatch(employee::equals);
    }

    public static ArrayList<Employee> getAllEmployees() {
        return database.getEmployees();
    }

    public static Employee login(String user, String pass) {
        for (Employee employee : database.getEmployees())
            if (employee.areFieldsCorrects(user, pass))
                return employee;
        throw new LoginIncorrectException("User or password incorrect");
    }
}
