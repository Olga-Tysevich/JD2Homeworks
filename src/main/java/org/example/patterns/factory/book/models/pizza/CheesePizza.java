package org.example.patterns.factory.book.models.pizza;


import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;

import static org.example.patterns.factory.book.utils.Constants.PREPARING;

public class CheesePizza extends Pizza {

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        setName("Cheese Pizza");
        setIngredientFactory(ingredientFactory);
    }

    @Override
    public Pizza prepare() {
        System.out.println(String.format(PREPARING, getName()));
        setDough(getIngredientFactory().createDough());
        setSauce(getIngredientFactory().createSauce());
        setCheese(getIngredientFactory().createCheese());
        return this;
    }
}
