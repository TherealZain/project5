package com.example.project5.pizzas;

import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents the Seafood pizza, a unique pizza type featuring a selection of seafood toppings.
 * This class extends the Pizza class and provides a specific implementation of its abstract methods,
 * catering to a seafood-themed pizza.
 * The Seafood pizza includes a unique set of seafood toppings and calculates its
 * price based on the chosen size.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class Seafood extends Pizza{
    private static final double SEAFOOD_SMALL_PRICE = 17.99;


    /**
     * Constructor to create a Seafood pizza with default settings.
     * Initializes the pizza with a selection of seafood toppings
     * and Alfredo sauce.
     */
    public Seafood() {
        toppings = new ArrayList<>();
        sauce = Sauce.ALFREDO;
        toppings.add(Toppings.SHRIMP);
        toppings.add(Toppings.SQUID);
        toppings.add(Toppings.CRABMEAT);
    }

    /**
     * Calculates and returns the price of the Seafood pizza based on its size.
     * The base price is for a small size and increases for medium and large sizes.
     *
     * @return double representing the total price of the Seafood pizza.
     */
    @Override
    public double price() {
        double price = SEAFOOD_SMALL_PRICE;
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(extraCheese){
            price++;
        }
        if(extraSauce){
            price++;
        }
        return price;
    }

    /**
     * Adding additional toppings is not supported for the Seafood pizza,
     * as it comes with a specific set of seafood toppings.
     *
     * @param topping The topping that the user attempts to add (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removing existing toppings is not supported for the Seafood pizza,
     * as they are integral to its theme.
     *
     * @param topping The topping that the user attempts to remove (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Sets the size of the Seafood pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Provides a string representation of the Seafood pizza.
     * The string includes the type of pizza, the list of seafood toppings, size, sauce, and the total price.
     *
     * @return String representing the details of the Seafood pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[Seafood] ";
        String toppingsString = "";
        for (Toppings topping : toppings) {
            if (!toppingsString.isEmpty()) {
                toppingsString += ", ";
            }
           // toppingsString += BuildOwnController.capitalize(topping.name().toLowerCase().replace('_', ' '));
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
