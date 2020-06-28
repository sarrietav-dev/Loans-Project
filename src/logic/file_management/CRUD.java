package logic.file_management;

import logic.DataBase;

import java.io.IOException;

public abstract class CRUD {
    protected static DataBase dataBase;

    static {
        try {
            dataBase = DataBase.getInstance();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
