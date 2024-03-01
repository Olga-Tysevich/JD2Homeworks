package org.example.patterns.factory.book.models.pizza;


import lombok.Getter;
import lombok.Setter;
import org.example.patterns.factory.book.models.ingredients.clams.Clams;
import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;

import static org.example.patterns.factory.book.utils.Constants.PREPARING;

@Setter
@Getter
public class ClamPizza extends Pizza {
    private Clams clams;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        setName("Clam Pizza");
        setIngredientFactory(ingredientFactory);
    }

    @Override
    public Pizza prepare() {
        System.out.println(String.format(PREPARING, getName()));
        setDough(getIngredientFactory().createDough());
        setSauce(getIngredientFactory().createSauce());
        setCheese(getIngredientFactory().createCheese());
        setClams(getIngredientFactory().createClam());
        return this;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", clams: " + clams.getDescription();
    }
}
