package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents the Deluxe pizza, a pre-defined pizza type with a specific set of toppings.
 * This class extends the Pizza class and provides a concrete implementation of its
 * abstract methods.
 * The Deluxe pizza includes a fixed set of toppings and calculates
 * its price based on the size and additional options.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class Deluxe extends Pizza{

    private static final double DELUXE_SMALL_PRICE = 14.99;

    /**
     * Constructor to create a Deluxe pizza with default settings.
     * Initializes the pizza with a pre-defined set of toppings, small size, and tomato sauce.
     */
    public Deluxe() {
        toppings = new ArrayList<>();
        this.size = Size.SMALL;
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.GREENPEPPER);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.MUSHROOM);
    }

    /**
     * Calculates and returns the price of the Deluxe pizza based on its size and
     * additional options.
     * The base price is for a small size and increases for medium and large sizes.
     * Additional costs are incurred for extra cheese and sauce.
     *
     * @return double representing the total price of the Deluxe pizza.
     */
    @Override
    public double price() {
        double price = DELUXE_SMALL_PRICE;
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }else
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(extraCheese){
            price++;
        }
        if(extraSauce) {
            price++;
        }
        return price;
    }

    /**
     * Adding toppings is not supported for Deluxe pizza, as it comes with a
     * pre-defined set of toppings.
     *
     * @param topping The topping that the user attempts to add (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removing toppings is not supported for Deluxe pizza, as it comes
     * with a pre-defined set of toppings.
     *
     * @param topping The topping that the user attempts to remove (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Sets the size of the Deluxe pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Provides a string representation of the Deluxe pizza.
     * The string includes the type of pizza, the list of fixed toppings,
     * size, sauce, and additional options,
     * followed by the total price.
     *
     * @return String representing the details of the Deluxe pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[Deluxe] ";
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
