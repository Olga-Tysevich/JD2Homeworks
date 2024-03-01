package org.example.patterns.factory.book.utils;

public enum  PizzaSize {
    SMALL("small", 1),
    MEDIUM("medium", 1.5),
    LARGE("large", 2);

    private String sizeName;
    private double priceFactor;

    PizzaSize(String sizeName, double priceFactor) {
        this.sizeName = sizeName;
        this.priceFactor = priceFactor;
    }

    public String getSizeName() {
        return sizeName;
    }

    public double getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }
}
