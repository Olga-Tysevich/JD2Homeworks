package org.example.patterns.factory.book.models.ingredients.factory;

import org.example.patterns.factory.book.models.ingredients.cheese.Cheese;
import org.example.patterns.factory.book.models.ingredients.clams.Clams;
import org.example.patterns.factory.book.models.ingredients.dough.Dough;
import org.example.patterns.factory.book.models.ingredients.pepperoni.Pepperoni;
import org.example.patterns.factory.book.models.ingredients.sauce.Sauce;
import org.example.patterns.factory.book.models.ingredients.veggies.Veggies;

import java.util.List;

public interface PizzaIngredientFactory {

    abstract Dough createDough();

    abstract Sauce createSauce();

    abstract Cheese createCheese();

    abstract List<Veggies> createVeggies();

    abstract Pepperoni createPepperoni();

    abstract Clams createClam();
}
