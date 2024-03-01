package org.example.patterns.factory.book.models.pizza;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.example.patterns.factory.book.models.ingredients.cheese.Cheese;
import org.example.patterns.factory.book.models.ingredients.dough.Dough;
import org.example.patterns.factory.book.models.ingredients.sauce.Sauce;
import org.example.patterns.factory.book.models.ingredients.factory.PizzaIngredientFactory;
import org.example.patterns.factory.book.models.ingredients.veggies.Veggies;
import org.example.patterns.factory.book.utils.PizzaSize;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.patterns.factory.book.utils.Constants.*;

@Getter
@Setter
public abstract class Pizza {
    private PizzaIngredientFactory ingredientFactory;
    private String name;
    private PizzaSize size;
    private Dough dough;
    private Sauce sauce;
    private Cheese cheese;
    @Setter(AccessLevel.NONE)
    private List<Veggies> veggies = new ArrayList<>();

    public abstract Pizza prepare();

    public Pizza bake() {
        try {
            System.out.println("Bake for 25 minutes at 350");
            Thread.sleep(BAKE_TIME);
            System.out.println("Pizza is ready");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Pizza cut() {
        System.out.println("Cutting the pizza into diagonal slices");
        return this;
    }

    public Pizza box() {
        System.out.println("Place pizza in official PizzaStore box");
        return this;
    }

    public String getDescription() {
        return String.format(PIZZA_DESCRIPTION, name,  dough.getDescription(), cheese.getDescription(), sauce.getDescription(), size.getSizeName())
                + "veggies: " + getVeggies().stream()
                .map(Veggies::getDescription)
                .collect(Collectors.joining());
    }

    public void removeVeggies(Veggies veggies) {
        if (veggies != null) {
            this.veggies.remove(veggies);
        }
    }

    public void addVeggies(Veggies veggies) {
        if (veggies != null) {
            this.veggies.add(veggies);
        }
    }
}
