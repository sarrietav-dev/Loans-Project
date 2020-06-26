package logic.file_management;

public interface Searchable {
    boolean LOAN = false;
    boolean BORROWER = true;

    void search(int ID, boolean filter);
}
