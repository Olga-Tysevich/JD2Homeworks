package org.example.lesson1;

import java.util.ArrayList;
import java.util.Collections;

/*Создайте java-приложение, которое будет считывать данные из источника (файл in.txt) и выполнять их сортировку.
Данные имеют табличную структуру. Строки разделены переносом строки. Столбцы – знаком табуляции.
Данные необходимо отсортировать и вывести в файл out.txt.
Сортировка производится сначала по первой колонке строк, потом по второй (если строки или числа в первой колонке совпадают) и т.д..
Данные относящиеся к одной строке в гриде должны быть также в одной строке.
При этом: любое число в колонке выше любого не числа, числа отсортированы по возрастанию, строки в лексикографическом порядке.
*/
public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        String pathToInputFile = "src/main/resources/in.txt";
        String pathToOutputFile = "src/main/resources/out.txt";
        ArrayList<StringContainer> data = fileManager.readFile(pathToInputFile, "\\s+");

        Collections.sort(data);

        fileManager.writeFile(pathToOutputFile, data);
    }
}