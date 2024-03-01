package org.example.patterns.factory.book.store;

import lombok.Data;
import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;
import org.example.patterns.factory.book.models.pizza.Pizza;
import org.example.patterns.factory.book.utils.PizzaSize;
import org.example.patterns.factory.book.utils.PizzaType;

@Data
public abstract class PizzaStore {
    private PizzaIngredientFactory ingredientFactory;


    public Pizza orderPizza(PizzaType type, PizzaSize size) {
        Pizza pizza = createPizza(type);
        pizza.setSize(size);
        return pizza.prepare()
                .bake()
                .cut()
                .box();
    }

    protected abstract Pizza createPizza(PizzaType type);
}
