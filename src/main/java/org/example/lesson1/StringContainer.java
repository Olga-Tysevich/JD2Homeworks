package org.example.lesson1;

import java.util.ArrayList;

import static java.lang.Math.min;

public class StringContainer implements Comparable<StringContainer> {
    private final ArrayList<String> string;

    public StringContainer(ArrayList<String> string) {
        this.string = string;
    }

    @Override
    public int compareTo(StringContainer anotherString) {
        int lastSortableColumns = 1;
        int currentIndex = 0;
        int maxIndex = min(min(string.size() - 1, anotherString.getString().size() - 1), lastSortableColumns);
        int result = compareElements(string.get(currentIndex), anotherString.getString().get(currentIndex));

        while (result == 0 && currentIndex <= maxIndex) {
            result = compareElements(string.get(currentIndex), anotherString.getString().get(currentIndex));
            currentIndex++;
        }

        return result;
    }

    public int compareElements(String firstString, String secondString) {
        boolean isFirstElNumber = isNumber(firstString);
        boolean isSecondElNumber = isNumber(secondString);

        if (isFirstElNumber && isSecondElNumber) {
            return Double.compare(Double.parseDouble(firstString), Double.parseDouble(secondString));
        } else if (!isFirstElNumber && !isSecondElNumber) {
            return firstString.compareTo(secondString);
        } else if (!isFirstElNumber) {
            return 1;
        } else {
            return -1;
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
        string.forEach(el -> sb.append(el).append("\t"));
        return sb.toString();
    }

    public ArrayList<String> getString() {
        return string;
    }
}
