package com.example.project5.pizzas;

import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**
 * Represents a specific type of pizza where customers
 * can build their own pizza by choosing toppings, size, and sauce.
 * This class extends the Pizza class and implements its abstract methods
 * to provide functionality specific to a customizable pizza.
 * The price of the pizza is calculated based on the selected size,
 * number of toppings, and additional options like extra cheese and sauce.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class BuildYourOwn extends Pizza{
    private static final int MAX_TOPPINGS = 3;
    private ArrayList<Toppings> toppings;
    private static final double EXTRA_TOPPING_COST = 1.49;

    /**
     * Constructor to create a new BuildYourOwn pizza with default settings.
     * Initializes the pizza with an empty toppings list, small size, tomato sauce, and without extra cheese or sauce.
     */
    public BuildYourOwn() {
        this.toppings = new ArrayList<>();
        this.size = Size.SMALL;
        this.sauce = Sauce.TOMATO;
        this.extraCheese = false;
        this.extraSauce = false;
    }


    /**
     * Sets the size of the pizza.
     *
     * @param size The new size of the pizza.
     */
    @Override
    public void setSize(Size size) {
        this.size = size;
    }


    /**
     * Sets the type of sauce for the pizza.
     *
     * @param sauce The new sauce for the pizza.
     */
    @Override
    public void setSauce(Sauce sauce){
        this.sauce = sauce;
    }


    /**
     * Calculates and returns the price of the pizza based on its size,
     * toppings, and additional options.
     * The base price varies with the size and increases
     * with the number of toppings beyond the maximum allowed without extra cost.
     * Additional costs are incurred for extra cheese and sauce.
     *
     * @return double representing the total price of the pizza.
     */
    @Override
    public double price() {
        double price = Size.SMALL.getPriceAdd();
        if(size == Size.MEDIUM){
            price += Size.MEDIUM.getPriceAdd();
        }else
        if(size == Size.LARGE){
            price += Size.LARGE.getPriceAdd();
        }
        if(toppings.size() > MAX_TOPPINGS){
            price += (toppings.size() - MAX_TOPPINGS)*EXTRA_TOPPING_COST;
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
     * Adds a specified topping to the pizza.
     *
     * @param topping The topping to be added.
     */
    @Override
    public void addToppings(Toppings topping){
        toppings.add(topping);
    }

    /**
     * Removes a specified topping from the pizza.
     *
     * @param topping The topping to be removed.
     */
    @Override
    public void removeToppings(Toppings topping){

        toppings.remove(topping);
    }

    /**
     * Provides a string representation of the BuildYourOwn pizza.
     * The string includes the type of pizza, the list of toppings,
     * size, sauce, and additional options,
     * followed by the total price.
     *
     * @return String representing the details of the BuildYourOwn pizza.
     */
    @Override
    public String toString() {
        String pizzaType = "[Build Your Own] ";
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

        return pizzaType + toppingsString + sizeString + sauceString + extraCheeseString + extraSauceString + priceString;
    }
}

