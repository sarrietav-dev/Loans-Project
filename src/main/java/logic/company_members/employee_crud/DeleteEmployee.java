package logic.company_members.employee_crud;

import logic.company_members.Employee;

public class DeleteEmployee extends CRUD {
    public static void delete(Employee employee) {
        employees.removeIf(employee::equals);
        database.updateDataList(employees);
    }
}
