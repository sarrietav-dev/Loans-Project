package logic.company_members.employee_crud;

import logic.company_members.Employee;

public class UpdateEmployee extends EmployeeCRUD {
    public static void update(Employee employee) {
        if (ReadEmployee.doesEmployeeExist(employee)) {
            employees.removeIf(employee::equals);
            database.updateDataList(employees);
            employees.add(employee);
            database.updateDataList(employees);
        }
    }
}
