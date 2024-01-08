package org.example.lesson4;


import org.example.lesson4.util.FileManager;
import org.example.lesson4.models.Person;
import org.example.lesson4.util.PersonsGenerator;
import org.example.lesson4.util.PersonsManager;
import java.util.List;
import static org.example.lesson4.Constants.*;
import static org.example.lesson4.Constants.FILE_PATH;

/*Делаем в maven покрываем unit test-ами
Создайте класс Person, с полями name, surname, age. Сгенерируйте группу из 100 человек в возрасте от 15 до 30.
1.	выберете объекты, возраст которых меньше 21;
2.	распечатать их на экран;
3.	сортируем по фамилии, а потом по имени (использовать thenComparing у объекта Comparator);
4.	убираем дубли (если name, surname, age одинаковые)
5.	сохраняем в файл, как объект, каждый экземпляр класса Person
6.	читаем из файла
7.	создаем при помощи stream новую коллекцию (List<String>) только из Фамилии и Имени для прочтенных из фалов объектов
8.	выводим на экран
*/
public class PersonApp {
    public static void main(String[] args) {
        List<Person> personList = new PersonsGenerator(MIN_AGE, MAX_AGE).generatePersonsGroup(GROUP_SIZE);
        personList = PersonsManager.filterPersonsGroup(personList, PERSON_UNDER_21);
        personList.forEach(System.out::println);
        personList = PersonsManager.sortBySurnameAndName(personList);
        personList = PersonsManager.removeDuplicates(personList);

        FileManager.writeObjects(personList, FILE_PATH);

        List<Person> persons =  FileManager.readObjects(FILE_PATH).stream()
                .map(p -> (Person) p)
                .toList();

        List<String> surnamesAndNames = PersonsManager.getSurnamesAndNames(persons);
        surnamesAndNames.forEach(System.out::println);
    }
}
