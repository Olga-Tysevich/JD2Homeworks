package org.example.patterns.factory.book.models.ingredients.factory;

import org.example.patterns.factory.book.models.ingredients.cheese.Cheese;
import org.example.patterns.factory.book.models.ingredients.cheese.MozzarellaCheese;
import org.example.patterns.factory.book.models.ingredients.clams.Clams;
import org.example.patterns.factory.book.models.ingredients.clams.FrozenClams;
import org.example.patterns.factory.book.models.ingredients.dough.Dough;
import org.example.patterns.factory.book.models.ingredients.dough.ThickCrustDough;
import org.example.patterns.factory.book.models.ingredients.pepperoni.Pepperoni;
import org.example.patterns.factory.book.models.ingredients.pepperoni.SlicedPepperoni;
import org.example.patterns.factory.book.models.ingredients.sauce.PlumTomatoSauce;
import org.example.patterns.factory.book.models.ingredients.sauce.Sauce;
import org.example.patterns.factory.book.models.ingredients.veggies.BlackOlives;
import org.example.patterns.factory.book.models.ingredients.veggies.EggPlant;
import org.example.patterns.factory.book.models.ingredients.veggies.Spinach;
import org.example.patterns.factory.book.models.ingredients.veggies.Veggies;

import java.util.List;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public List<Veggies> createVeggies() {
        return List.of(new Spinach(), new BlackOlives(), new EggPlant());
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FrozenClams();
    }
}
