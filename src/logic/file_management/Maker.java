package logic.file_management;

import java.io.IOException;

public abstract class Maker {
    protected String[] info;

    protected Maker(String[] info) {
        this.info = info;
    }

    public abstract Object make() throws IOException;
}

