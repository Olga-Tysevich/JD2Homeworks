package org.example.patterns.decorator.first_example.prices;

public enum Size {
    TALL("tall", 1),
    GRANDE("grande", 1.5),
    VENTI("venti", 2);

    private String sizeName;
    private double priceFactor;

    Size(String sizeName, double priceFactor) {
        this.sizeName = sizeName;
        this.priceFactor = priceFactor;
    }

    public double getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public String getSizeName() {
        return sizeName;
    }
}
