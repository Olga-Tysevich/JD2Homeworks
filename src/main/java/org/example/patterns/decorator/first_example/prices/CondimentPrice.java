package org.example.patterns.decorator.first_example.prices;

public enum CondimentPrice {
    MOCHA(", Mocha", 0.50),
    SOY(", Soy", 0.10),
    WHIP(", Whip", 0.35) ;

    private String name;
    private double price;

    CondimentPrice(String name, double price) {
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
