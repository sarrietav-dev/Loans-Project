package logic.loan_classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Borrower implements Serializable {
    private int id;
    private String name;
    private String homePhoneNumber;
    private String cellphoneNumber;
    private String address;
    private ArrayList<Loan> loans;
    private String username;
    private String password;

    public Borrower() {

    }

    public Borrower(String username, String password, int id, String name, String homePhoneNumber,
                    String cellphoneNumber, String address){
        this.id = id;
        this.name = name;
        this.homePhoneNumber = homePhoneNumber;
        this.cellphoneNumber = cellphoneNumber;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public String[] getInfo() {
        return new String[] {
                String.valueOf(id),
                name,
                homePhoneNumber,
                cellphoneNumber,
                address
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return id == borrower.id || username.equalsIgnoreCase(borrower.username);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", cellphoneNumber='" + cellphoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, homePhoneNumber, cellphoneNumber, address);
    }

    public int getId() {
        return id;
    }

    public String[] getDataAccess() {
        return new String[] {
            username, password
        };
    }

}
