package logic.company_members.employee_crud;

import logic.company_members.Employee;

import java.util.ArrayList;

public class ReadEmployee extends CRUD {
    public static boolean doesEmployeeExist(Employee employee) {
        return getEmployees().stream()
                .anyMatch(employee::equals);
    }

    public static ArrayList<Employee> getAllEmployees() {
        return getEmployees();
    }
}
