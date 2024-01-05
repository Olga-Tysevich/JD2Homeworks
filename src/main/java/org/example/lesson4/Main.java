package org.example.lesson4;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

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

public class Main {
    private static final int GROUP_SIZE = 100;
    private static final int MIN_AGE = 15;
    private static final Predicate<Person> PERSON_UNDER_21 = p -> p.getAge() < 21;
    private static final String FILE_PATH = "src/main/java/org/example/lesson4/Persons.txt";

    public static void main(String[] args) {
        List<Person> personList =
                Stream.generate(Main::generatePerson)
                        .limit(GROUP_SIZE)
                        .peek(System.out::println)
                        .filter(PERSON_UNDER_21)
                        .sorted(Comparator.comparing(Person::getSurname)
                                .thenComparing(Person::getName))
                        .distinct()
                        .toList();
        FieManager.writeObjects(personList, FILE_PATH);
        personList.forEach(System.out::println);

        List<String> surnamesAndNames =
                FieManager.readObjects(FILE_PATH).stream()
                        .map(p -> ((Person) p).getSurname() + " " + ((Person) p).getName())
                        .toList();
        surnamesAndNames.forEach(System.out::println);
    }

    public static Person generatePerson() {
        String randomName = Names.values()[new Random().nextInt(Names.values().length)].name();
        String randomSurname = Surnames.values()[new Random().nextInt(Surnames.values().length)].name();
        int randomAge = new Random().nextInt(MIN_AGE) + MIN_AGE;
        return new Person(randomName, randomSurname, randomAge);
    }
}
