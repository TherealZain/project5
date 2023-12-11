package com.example.project5.pizzas;

import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.util.ArrayList;

/**
 * Represents a BBQ Chicken pizza, a specific type of pizza in the pizza ordering application.
 * This class extends the Pizza class and is characterized by BBQ sauce and specific toppings like chicken, onion, and cilantro.
 */
public class BBQChicken extends Pizza {
    private static final double BBQ_CHICKEN_SMALL_PRICE = 15.99;

    /**
     * Constructor for creating a BBQ Chicken pizza. Initializes with default BBQ sauce and toppings.
     */
    public BBQChicken() {
        toppings = new ArrayList<>();
        sauce = Sauce.BBQ;
        toppings.add(Toppings.CHICKEN);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.CILANTRO);
    }

    /**
     * Calculates and returns the price of the BBQ Chicken pizza based on its size and additional options.
     * @return The total price of the pizza.
     */
    @Override
    public double price() {
        double price = BBQ_CHICKEN_SMALL_PRICE;
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
     * Sets the size of the BBQ Chicken pizza.
     * @param newSize The new size to set for the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Adds a specified topping to the BBQ Chicken pizza.
     * This implementation is currently empty as the BBQ Chicken pizza has predefined toppings.
     * @param topping The topping to be added (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removes a specified topping from the BBQ Chicken pizza.
     * This implementation is currently empty as the BBQ Chicken pizza has predefined toppings.
     * @param topping The topping to be removed (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Provides a string representation of the BBQ Chicken pizza, including its type, toppings, size, sauce,
     * and extra options, followed by the total price.
     * @return A string representing the details of the BBQ Chicken pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[BBQ Chicken] ";
        String toppingsString = "";
        for (Toppings topping : toppings) {
            if (!toppingsString.isEmpty()) {
                toppingsString += ", ";
            }
            //toppingsString += BuildOwnController.capitalize(topping.name().toLowerCase().replace('_', ' '));
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
