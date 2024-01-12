package org.example.lesson1;

import java.util.Collections;
import java.util.List;

import static org.example.lesson1.Constants.*;

/*Создайте java-приложение, которое будет считывать данные из источника (файл in.txt) и выполнять их сортировку.
Данные имеют табличную структуру. Строки разделены переносом строки. Столбцы – знаком табуляции.
Данные необходимо отсортировать и вывести в файл out.txt.
Сортировка производится сначала по первой колонке строк, потом по второй (если строки или числа в первой колонке совпадают) и т.д..
Данные относящиеся к одной строке в гриде должны быть также в одной строке.
При этом: любое число в колонке выше любого не числа, числа отсортированы по возрастанию, строки в лексикографическом порядке.
*/
public class Main {

    public static void main(String[] args) {
        List<DataComparator> data = FileManager.readFile(PATH_IN, COLUMN_SEPARATOR);
        Collections.sort(data);
        FileManager.writeFile(PATH_OUT, data);
    }
}