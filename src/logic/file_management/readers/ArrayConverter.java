package logic.file_management.readers;

import java.util.ArrayList;

interface ArrayConverter {
    static String[][] convert(ArrayList<String[]> arrayList) {
        String[][] returnString = new String[arrayList.size()][];

        for (int i = 0; i < arrayList.size(); i++)
            returnString[i] = arrayList.get(i);

        return returnString;
    }
}

