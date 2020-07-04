package logic.loan_classes;

import java.io.Serializable;
import java.util.Objects;

public class Borrower implements Serializable {
    private int id;
    private String name;
    private String homePhone;
    private String mobilePhone;
    private String address;

    public Borrower() {

    }

    public Borrower(int id, String name, String homePhone, String mobilePhone, String address){
        this.id = id;
        this.name = name;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return id == borrower.id;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homePhoneNumber='" + homePhone + '\'' +
                ", cellphoneNumber='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, homePhone, mobilePhone, address);
    }

}
