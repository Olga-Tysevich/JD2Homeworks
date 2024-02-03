package org.example.patterns.decorator.first_example.prices;

public enum CoffeePrice {
    DARK_ROAST("Dark roast ", 1.5),
    ESPRESSO("Espresso ", 1.99),
    HOUSE_BLEND("House blend ", 1.0);

    private String name;
    private double price;

    CoffeePrice(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void changePrice(double price) {
        this.price = price;
    }
}
