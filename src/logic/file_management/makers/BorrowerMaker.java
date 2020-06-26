package logic.file_management.read_classes.class_makers;

import logic.file_management.Maker;
import logic.loan_classes.Borrower;

public class BorrowerMaker implements Maker {
    private final String[] info;

    private final static int ID = 0;
    private final static int NAME = 1;
    private final static int HOME_PHONE_NUMBER = 2;
    private final static int CELLPHONE_NUMBER = 3;
    private final static int ADDRESS = 4;

    public BorrowerMaker(String[] info) {
        this.info = info;
    }

    @Override
    public Borrower make() {
        return new Borrower(getID(), getName(), getHomePhoneNumber(), getCellphoneNumber(), getAddress());
    }

    private int getID() {
        return Integer.parseInt(info[ID]);
    }

    private String getName() {
        return info[NAME];
    }

    private String getHomePhoneNumber() {
        return info[HOME_PHONE_NUMBER];
    }

    private String getCellphoneNumber() {
        return info[CELLPHONE_NUMBER];
    }

    private String getAddress() {
        return info[ADDRESS];
    }
}
