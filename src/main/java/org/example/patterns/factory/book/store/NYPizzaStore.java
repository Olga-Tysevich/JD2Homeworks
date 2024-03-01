package org.example.patterns.factory.book.store;

import org.example.patterns.factory.book.models.ingredients.factory.NYPizzaIngredientFactory;
import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;
import org.example.patterns.factory.book.models.pizza.*;
import org.example.patterns.factory.book.utils.PizzaType;

public class NYPizzaStore extends PizzaStore {


    @Override
    protected Pizza createPizza(PizzaType type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if (PizzaType.CHEESE == type) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if (PizzaType.PEPPERONI == type) {
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("New York Style Pepperoni Pizza");
        } else if (PizzaType.CLAM == type) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        } else if (PizzaType.VEGGIE == type) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("New York Style Veggie Pizza");
        }
        return pizza;
    }
}
