package logic;

import logic.file_management.client_crud.CreateClient;
import logic.file_management.loan_crud.CreateLoan;
import logic.loan_classes.Client;
import logic.loan_classes.Loan;
import logic.loan_management.PaymentMethods;

import java.util.Date;

// TODO: 5/07/20 Create an employee database
public class Employee {
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
}
