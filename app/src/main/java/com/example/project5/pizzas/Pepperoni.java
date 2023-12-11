package com.example.project5.pizzas;

import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents the Pepperoni pizza, a specific pizza type with pepperoni as its primary topping.
 * This class extends the Pizza class and offers a concrete implementation of its abstract methods,
 * tailored to a pizza featuring pepperoni.
 * The Pepperoni pizza comes with a fixed pepperoni topping and calculates its price based on
 * the selected size.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class Pepperoni extends Pizza{

    private static final double PEPPERONI_SMALL_PRICE = 10.99;

    /**
     * Constructor to create a Pepperoni pizza with default settings.
     * Initializes the pizza with pepperoni as its sole topping and tomato sauce.
     */
    public Pepperoni(){
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.PEPPERONI);
    }

    /**
     * Calculates and returns the price of the Pepperoni pizza based on its
     * size and extra selections.
     * The base price is for a small size and increases for medium and large sizes.
     *
     * @return double representing the total price of the Pepperoni pizza.
     */
    @Override
    public double price() {
        double price = PEPPERONI_SMALL_PRICE;
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
     * Adding additional toppings is not supported for the Pepperoni pizza, as it is
     * designed to be a single-topping pizza.
     *
     * @param topping The topping that the user attempts to add (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removing the existing topping is not supported for the Pepperoni pizza,
     * as it is a core feature of this pizza type.
     *
     * @param topping The topping that the user attempts to remove (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }

    /**
     * Sets the size of the Pepperoni pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }

    /**
     * Provides a string representation of the Pepperoni pizza.
     * The implementation should ideally include details such as the type of pizza,
     * the list of fixed toppings, size, and sauce,
     * followed by the total price. However, it currently returns null and needs to be
     * properly implemented.
     *
     * @return String representing the details of the Pepperoni pizza
     */
    @Override
    public String toString() {
        String pizzaType = "[Pepperoni] ";
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
