package logic.company_members.employee_crud;

import logic.company_members.Employee;
import org.junit.jupiter.api.Test;

class CreateEmployeeTest {
    @Test
    void createEmployeeTest() {
        Employee employee = new Employee( "100", "epic", "Sebastian", 20000);
        Employee employee2 = new Employee("200", "the man","Joc", 21000);
        Employee employee3 = new Employee("300", "the epic", "Luigy", 24000);
        Employee employee4 = new Employee("400", "the","Yabrudy", 25000);
        Employee employee5 = new Employee("500", "gay","Jeronimo", 27000);
        Employee employee6 = new Employee("600", "MAN","MAN-Horse", 28000);
        CreateEmployee.create(employee, employee2, employee3, employee4, employee5, employee6);

        for (Employee employeeItem : ReadEmployee.getAllEmployees())
            System.out.println(employeeItem);
    }
}