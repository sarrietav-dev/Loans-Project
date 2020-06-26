package logic.file_management.read_classes.class_makers;

import logic.loan_classes.Dates;

public class DatesMaker {
    private final String date;

    public DatesMaker(String date) {
        this.date = date;
    }

    public Dates make() {
        return new Dates(date);
    }
}
