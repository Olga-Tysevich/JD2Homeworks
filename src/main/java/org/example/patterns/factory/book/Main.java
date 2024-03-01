package org.example.patterns.factory.book;

import org.example.patterns.factory.book.models.pizza.Pizza;
import org.example.patterns.factory.book.store.ChicagoPizzaStore;
import org.example.patterns.factory.book.store.NYPizzaStore;
import org.example.patterns.factory.book.store.PizzaStore;
import org.example.patterns.factory.book.utils.PizzaSize;
import org.example.patterns.factory.book.utils.PizzaType;

public class Main {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        Pizza pizza = nyStore.orderPizza(PizzaType.CHEESE, PizzaSize.SMALL);
        System.out.println(pizza.getDescription() + "\n");
        pizza = chicagoStore.orderPizza(PizzaType.VEGGIE, PizzaSize.MEDIUM);
        System.out.println(pizza.getDescription());
    }
}
