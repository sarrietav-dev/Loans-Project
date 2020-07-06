package logic.company_members.employee_crud;

import logic.company_members.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateEmployeeTest {
    @Test
    void createEmployeeTest() {
        Employee employee = new Employee("100", "Sebastian", 20000);
        CreateEmployee.create(employee);

        for (Employee employeeItem : ReadEmployee.getAllEmployees())
            System.out.println(employeeItem);
    }
}