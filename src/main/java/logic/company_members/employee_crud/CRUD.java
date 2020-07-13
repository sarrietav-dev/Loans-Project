package logic.company_members.employee_crud;

import logic.company_members.Employee;
import logic.databases.EmployeeDatabase;

import java.util.ArrayList;

public abstract class CRUD {
    protected static final EmployeeDatabase database = EmployeeDatabase.getInstance();

    protected static ArrayList<Employee> getEmployees() {
        return database.getEmployees();
    }
}
