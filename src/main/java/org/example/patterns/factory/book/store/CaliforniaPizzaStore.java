package org.example.patterns.factory.book.store;

import org.example.patterns.factory.book.models.ingredients.factory.ChicagoPizzaIngredientFactory;
import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;
import org.example.patterns.factory.book.models.pizza.*;
import org.example.patterns.factory.book.utils.PizzaType;

public class CaliforniaPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(PizzaType type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
        if (PizzaType.CHEESE == type) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("California Style Cheese Pizza");
        } else if (PizzaType.PEPPERONI == type) {
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("California Style Pepperoni Pizza");
        } else if (PizzaType.CLAM == type) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("California Style Clam Pizza");
        } else if (PizzaType.VEGGIE == type) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("California Style Veggie Pizza");
        }
        return pizza;
    }
}
