package logic.loan_classes;

import logic.FetchInformation;
import logic.IDGetterSetter;

import java.util.Objects;

public class Borrower implements IDGetterSetter, FetchInformation {
    private int id;
    private String name;
    private String homePhoneNumber;
    private String cellphoneNumber;
    private String address;

    public Borrower() {

    }

    public Borrower(int id) {
        setID(id);
    }

    public Borrower(int id, String name) {
        setID(id);
        this.name = name;
    }

    public Borrower(String name) {
        this.name = name;
    }

    public Borrower(int id, String name, String homePhoneNumber, String cellphoneNumber, String address) {
        setID(id);
        this.name = name;
        this.homePhoneNumber = homePhoneNumber;
        this.cellphoneNumber = cellphoneNumber;
        this.address = address;
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
        return id == borrower.id &&
                name.equals(borrower.name) &&
                homePhoneNumber.equals(borrower.homePhoneNumber) &&
                cellphoneNumber.equals(borrower.cellphoneNumber) &&
                address.equals(borrower.address);
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

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
