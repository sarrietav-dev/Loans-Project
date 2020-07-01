package logic.loan_classes;

import java.util.Date;

public class DateSorter {

    public static void dateQuickSort(Date[] dates, int low, int high) {
        if (low < high) {
            int pivot = partition(dates, low, high);
            dateQuickSort(dates, low, pivot - 1);
            dateQuickSort(dates, pivot + 1, high);
        }
    }

    private static int partition(Date[] dates, int low, int high) {
        int lowest_value = low - 1;
        Date pivot = dates[high];

        for (int actual_value = low; actual_value < high; actual_value++) {
            if (dates[actual_value].before(pivot)) {
                lowest_value++;
                swap(dates, lowest_value, actual_value);
            }
        }
        swap(dates, lowest_value + 1, high);
        return lowest_value + 1;
    }

    private static void swap(Date[] dates, int indexA, int indexB) {
        Date temp = dates[indexA];
        dates[indexA] = dates[indexB];
        dates[indexB] = temp;
    }
}
