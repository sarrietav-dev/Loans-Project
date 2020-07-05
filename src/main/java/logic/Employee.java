package logic;

import java.awt.*;

public class Employee {
    private String id;
    private String password;

    private String name;
    private String homePhone;
    private String mobilePhone;
    private String address;

    private double baseSalary;
    private double currentSalary;

    public Employee(String name, String homePhone, String mobilePhone, String address, double baseSalary) {
        this.name = name;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.address = address;
        this.baseSalary = baseSalary;
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
