package org.example.patterns.factory.book.models.pizza;


import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;

import static org.example.patterns.factory.book.utils.Constants.PREPARING;

public class VeggiePizza extends Pizza {

    public VeggiePizza(PizzaIngredientFactory ingredientFactory) {
        setName("Veggie Pizza");
        setIngredientFactory(ingredientFactory);
    }

    public Pizza prepare() {
        System.out.println(String.format(PREPARING, getName()));
        setDough(getIngredientFactory().createDough());
        setSauce(getIngredientFactory().createSauce());
        setCheese(getIngredientFactory().createCheese());
        getIngredientFactory().createVeggies().forEach(super::addVeggies);
        return this;
    }
}
