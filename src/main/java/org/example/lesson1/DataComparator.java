package org.example.lesson1;

import java.util.List;

import static java.lang.Math.min;
import static org.example.lesson1.Constants.COLUMN_SEPARATOR;
import static org.example.lesson1.Constants.LAST_SORT_COLUMN_INDEX;

public class DataComparator implements Comparable<DataComparator> {
    private List<String> row;

    public DataComparator(List<String> row) {
        this.row = row;
    }

    @Override
    public int compareTo(DataComparator anotherRow) {
        int currentIndex = 0;
        int maxIndex = min(min(row.size() - 1, anotherRow.getRow().size() - 1), LAST_SORT_COLUMN_INDEX);
        int result;

        do {
            result = compareElements(row.get(currentIndex), anotherRow.getRow().get(currentIndex));
            currentIndex++;
        } while (result == 0 && currentIndex <= maxIndex);

        return result;
    }

    public int compareElements(String firstElement, String secondElement) {
        boolean isFirstElNumber = isNumber(firstElement);
        boolean isSecondElNumber = isNumber(secondElement);

        if (isFirstElNumber && isSecondElNumber) {
            return Double.compare(Double.parseDouble(firstElement), Double.parseDouble(secondElement));
        } else if (!isFirstElNumber && !isSecondElNumber) {
            return firstElement.compareTo(secondElement);
        } else {
            return !isFirstElNumber? 1 : -1;
        }
    }

    private boolean isNumber(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        row.forEach(el -> sb.append(el).append(COLUMN_SEPARATOR));
        return sb.toString();
    }

    public List<String> getRow() {
        return row;
    }

}
