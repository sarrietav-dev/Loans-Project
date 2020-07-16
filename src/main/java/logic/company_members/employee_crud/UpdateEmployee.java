package logic.company_members.employee_crud;

import logic.company_members.Employee;

import java.util.ArrayList;

public class UpdateEmployee extends CRUD{
    public static void update(Employee employee) {
        if (ReadEmployee.doesEmployeeExist(employee)) {
            employees.removeIf(employee::equals);
            employees.add(employee);
            database.updateDataList(employees);
        }
    }
}
