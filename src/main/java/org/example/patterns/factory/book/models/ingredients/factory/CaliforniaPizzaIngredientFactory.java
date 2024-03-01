package org.example.patterns.factory.book.models.ingredients.factory;

import org.example.patterns.factory.book.models.ingredients.cheese.Cheese;
import org.example.patterns.factory.book.models.ingredients.cheese.ReggianoCheese;
import org.example.patterns.factory.book.models.ingredients.clams.Clams;
import org.example.patterns.factory.book.models.ingredients.clams.FreshClams;
import org.example.patterns.factory.book.models.ingredients.dough.Dough;
import org.example.patterns.factory.book.models.ingredients.dough.ThinCrustDough;
import org.example.patterns.factory.book.models.ingredients.pepperoni.Pepperoni;
import org.example.patterns.factory.book.models.ingredients.pepperoni.SlicedPepperoni;
import org.example.patterns.factory.book.models.ingredients.sauce.MarinaraSauce;
import org.example.patterns.factory.book.models.ingredients.sauce.PlumTomatoSauce;
import org.example.patterns.factory.book.models.ingredients.sauce.Sauce;
import org.example.patterns.factory.book.models.ingredients.veggies.Mushroom;
import org.example.patterns.factory.book.models.ingredients.veggies.Onion;
import org.example.patterns.factory.book.models.ingredients.veggies.Veggies;

import java.util.List;

public class CaliforniaPizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public List<Veggies> createVeggies() {
        return List.of(new Mushroom(), new Onion());
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
