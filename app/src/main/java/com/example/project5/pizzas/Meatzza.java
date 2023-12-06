package com.example.project5.pizzas;

import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

/**
 * Represents the Meatzza pizza, a pre-defined pizza type with a focus on meat toppings.
 * This class extends the Pizza class and offers a concrete implementation
 * of its abstract methods, specifically catering to a pizza rich in various meats.
 * The Meatzza pizza includes a fixed set of meat toppings and calculates its
 * price based on the selected size.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class Meatzza extends Pizza{

    private static final double MEATZZA_SMALL_PRICE = 16.99;

    /**
     * Constructor to create a Meatzza pizza with default settings.
     * Initializes the pizza with a pre-defined set of meat toppings and tomato sauce.
     */
    public Meatzza(){
        toppings = new ArrayList<>();
        sauce = Sauce.TOMATO;
        toppings.add(Toppings.SAUSAGE);
        toppings.add(Toppings.PEPPERONI);
        toppings.add(Toppings.BEEF);
        toppings.add(Toppings.HAM);
    }

    /**
     * Calculates and returns the price of the Meatzza pizza based on its size.
     * The base price is for a small size and increases for medium and large sizes.
     *
     * @return double representing the total price of the Meatzza pizza.
     */
    @Override
    public double price() {
        double price = MEATZZA_SMALL_PRICE;
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
     * Adding toppings is not supported for Meatzza pizza, as it comes with a pre-defined set of meat toppings.
     *
     * @param topping The topping that the user attempts to add (not used).
     */
    @Override
    public void addToppings(Toppings topping) {

    }

    /**
     * Removing toppings is not supported for Meatzza pizza, as it comes with a pre-defined set of meat toppings.
     *
     * @param topping The topping that the user attempts to remove (not used).
     */
    @Override
    public void removeToppings(Toppings topping) {

    }


    /**
     * Sets the size of the Meatzza pizza.
     *
     * @param newSize The new size of the pizza.
     */
    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }


    /**
     * Provides a string representation of the Meatzza pizza.
     * The implementation should ideally include details such
     * as the type of pizza, the list of fixed toppings, size, and sauce,
     * followed by the total price. However, it currently returns null and
     * needs to be properly implemented.
     *
     * @return String representing the details of the Meatzza pizza
     */
    @Override
    public String toString() {
        String pizzaType = "[Meatzza] ";
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
