package com.example.project5.enums;

/**
 * Enumeration of the different sizes available for pizzas with associated additional prices.
 * This enum defines the size options for pizzas along with their respective additional cost.
 * The available sizes are SMALL, MEDIUM, and LARGE, each with a specific added cost.
 *
 * SMALL is the base size for a pizza with the base price (8.99).
 * MEDIUM represents a medium-sized pizza with an additional cost of 2.00.
 * LARGE represents a large-sized pizza with an additional cost of 4.00.
 *
 * This enum also includes a method to retrieve the additional cost associated with each size.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public enum Size {
    SMALL(8.99),
    MEDIUM(2.00),
    LARGE(4.00);
    private final double priceAdd;


    /**
     * Constructor for the Size enum.
     * Initializes the enum value with the additional price for that specific size.
     *
     * @param priceAdd The additional price for the size.
     */
     Size(double priceAdd) {
        this.priceAdd = priceAdd;
    }


    /**
     * Retrieves the additional price associated with the pizza size.
     *
     * @return double representing the additional price for the size.
     */
    public double getPriceAdd() {
        return this.priceAdd;
    }
}
