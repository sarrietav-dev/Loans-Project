package logic.company_members;

import logic.exceptions.ClientAlreadyExistsException;
import logic.exceptions.LoanAlreadyPaidException;
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

    /**
     * Pays an installment of a loan.
     * @param LOAN_ID The id of the loan that will be paid.
     * @param DATE_OF_PAYMENT The date when the installment was paid.
     * @throws LoanAlreadyPaidException If the loan was already paid (Each installment was paid).
     */
    public void payAnInstallment(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        PaymentMethods.payInstallment(this, LOAN_ID, DATE_OF_PAYMENT);
    }

    /**
    * Takes the whole loan and pays the unpaid installments.
    * @param LOAN_ID The id of the loan that will be paid.
    * @param DATE_OF_PAYMENT If the loan was already paid (Each installment was paid).
    * @throws LoanAlreadyPaidException If the loan was already paid (Each installment was paid).
     */
    public void payALoan(final int LOAN_ID, final Date DATE_OF_PAYMENT) {
        PaymentMethods.payLoan(this, LOAN_ID, DATE_OF_PAYMENT);
    }

    /**
     * Adds a client to the database.
     * @param client A client object to be added to the database.
     * @throws ClientAlreadyExistsException if the client ID matches other client ID in the database.
     * */
    public void addAClient(Client client) {
        CreateClient.create(client);
    }

    /**
    * Creates a loan and assigns it to a Client. The employee earns 1% of the total amount borrowed from the loan.
    * @param loan The loan that will be created.
    * @param CLIENT_ID The client ID where the load will be assigned.
     */
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
