package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.util.ArrayList;

/**
 * Represents a Margherita pizza, a specific type of pizza in the pizza ordering application.
 * This class extends the Pizza class and is characterized by tomato sauce and specific toppings like mozzarella, tomatoes, and basil.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

public class Margherita extends Pizza {
    private static final double MARGHERITA_SMALL_PRICE = 13.99;

    /**
     * Constructor for creating a Margherita pizza.
     * Initializes with default tomato sauce and specific toppings.
     */
    public Margherita() {
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.MOZZARELLA);
        toppings.add(Toppings.TOMATOES);
        toppings.add(Toppings.BASIL);
    }

    /**
     * Calculates and returns the price of the Margherita pizza based on its
     * size and extra selections.
     * The base price is for a small size and increases for medium and large sizes.
     *
     * @return double representing the total price of the Pepperoni pizza.
     */
    @Override
    public double price() {
        double price = MARGHERITA_SMALL_PRICE;
        if (size == Size.MEDIUM) {
            price += Size.MEDIUM.getPriceAdd();
        }
        if (size == Size.LARGE) {
            price += Size.LARGE.getPriceAdd();
        }
        if (extraCheese) {
            price++;
        }
        if (extraSauce) {
            price++;
        }
        return price;
    }

    /**
     * Sets the size of the Margherita pizza.
     * @param newSize The new size to set for the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Adds a specified topping to the Margherita pizza.
     * This implementation is currently empty as the Hawaiian pizza has predefined toppings.
     * @param topping The topping to be added (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removes a specified topping from the Margherita pizza.
     * This implementation is currently empty as the Hawaiian pizza has predefined toppings.
     * @param topping The topping to be removed (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Provides a string representation of the Margherita pizza.
     * The string includes the type of pizza, the list of fixed toppings,
     * size, sauce, and additional options,
     * followed by the total price.
     *
     * @return String representing the details of the Margherita pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[Margherita] ";
        String toppingsString = "";
        for (Toppings topping : toppings) {
            if (!toppingsString.isEmpty()) {
                toppingsString += ", ";
            }
            toppingsString += BYOActivity.capitalize(topping.name().toLowerCase());
        }
        String sizeString = ", " + size.toString().toLowerCase();
        String sauceString = ", " + sauce.toString().toLowerCase();
        String extraCheeseString = extraCheese ? ", extra cheese" : "";
        String extraSauceString = extraSauce ? ", extra sauce" : "";
        String priceString = " $" + String.format("%.2f", price());
        return pizzaType + toppingsString + sizeString + sauceString + extraCheeseString +
                extraSauceString + priceString;
    }

}
