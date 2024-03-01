package org.example.patterns.factory.book.models.pizza;


import lombok.Getter;
import lombok.Setter;
import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;
import org.example.patterns.factory.book.models.ingredients.pepperoni.Pepperoni;

import static org.example.patterns.factory.book.utils.Constants.PREPARING;

@Setter
@Getter
public class PepperoniPizza extends Pizza {
    private Pepperoni pepperoni;

    public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
        setName("Pepperoni Pizza");
        setIngredientFactory(ingredientFactory);
    }

    public Pizza prepare() {
        System.out.println(String.format(PREPARING, getName()));
        setDough(getIngredientFactory().createDough());
        setSauce(getIngredientFactory().createSauce());
        setCheese(getIngredientFactory().createCheese());
        pepperoni = getIngredientFactory().createPepperoni();
        return this;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", pepperoni: " + pepperoni.getDescription();
    }

}
