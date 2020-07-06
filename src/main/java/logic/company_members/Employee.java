package logic.company_members;

import logic.file_management.client_crud.CreateClient;
import logic.file_management.loan_crud.CreateLoan;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;
import logic.loan_management.PaymentMethods;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private String id;
    private String password;

    private String name;
    private String homePhone;
    private String mobilePhone;
    private String address;

    private double baseSalary;
    private double currentSalary;

    public Employee() {

    }

    public Employee(String id) {
        this.id = id;
    }

    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public Employee(String name, String homePhone, String mobilePhone, String address, double baseSalary) {
        this.name = name;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.address = address;
        this.baseSalary = baseSalary;
    }

    public void payAnInstallment(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        PaymentMethods.payInstallment(this, LOAN_ID, DATE_OF_PAYMENT);
    }

    public void payALoan(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        PaymentMethods.payLoan(this, LOAN_ID, DATE_OF_PAYMENT);
    }

    public void addAClient(Client client) {
        CreateClient.create(client);
    }

    public void addALoan(Loan loan, final int CLIENT_ID) {
        CreateLoan.create(loan, CLIENT_ID);
        sumToSalary(loan.getAmount() * .01);
    }

    private void sumToSalary(double amount) {
        currentSalary += amount;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getAddress() {
        return address;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                ", baseSalary=" + baseSalary +
                ", currentSalary=" + currentSalary +
                '}';
    }
}

