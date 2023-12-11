package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.util.ArrayList;

/**
 * Represents a Veggie pizza, a specific type of pizza in the pizza ordering application.
 * This class extends the Pizza class and is characterized by tomato sauce and specific vegetable toppings.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

public class Veggie extends Pizza {
    private static final double VEGGIE_SMALL_PRICE = 11.99;

    /**
     * Constructor for creating a Veggie pizza.
     * Initializes with default tomato sauce and specific toppings.
     */
    public Veggie() {
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.MUSHROOM);
        toppings.add(Toppings.BLACKOLIVE);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.GREENPEPPER);
    }

    /**
     * Calculates and returns the price of the Veggie pizza based on its
     * size and extra selections.
     * The base price is for a small size and increases for medium and large sizes.
     *
     * @return double representing the total price of the Pepperoni pizza.
     */
    @Override
    public double price() {
        double price = VEGGIE_SMALL_PRICE;
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
     * Sets the size of the Veggie pizza.
     * @param newSize The new size to set for the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Adds a specified topping to the Veggie pizza.
     * This implementation is currently empty as the Hawaiian pizza has predefined toppings.
     * @param topping The topping to be added (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removes a specified topping from the Veggie pizza.
     * This implementation is currently empty as the Hawaiian pizza has predefined toppings.
     * @param topping The topping to be removed (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Provides a string representation of the Veggie pizza.
     * The string includes the type of pizza, the list of fixed toppings,
     * size, sauce, and additional options,
     * followed by the total price.
     *
     * @return String representing the details of the Veggie pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[Veggie] ";
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