package com.example.project5.pizzas;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents an abstract Pizza class defining the structure and behaviors of various pizza types.
 * This class serves as a base for different kinds of pizzas, providing methods to manipulate toppings,
 * size, sauce, and cheese options. Specific pizza types will extend this class and implement its abstract methods.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

public abstract class Pizza {
    protected ArrayList<Toppings> toppings; //Topping is a enum class
    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Calculates and returns the price of the pizza.
     * This method must be implemented by subclasses to account for variations in pizza pricing.
     *
     * @return double representing the price of the pizza
     */
    public abstract double price(); //polymorphism

    /**
     * Adds a specified topping to the pizza.
     * Implementations will define how the topping is added.
     *
     * @param topping The topping to be added to the pizza.
     */
    public abstract void addToppings(Toppings topping);

    /**
     * Removes a specified topping from the pizza.
     * Implementations will define how the topping is removed.
     *
     * @param topping The topping to be removed from the pizza.
     */
    public abstract void removeToppings(Toppings topping);


    /**
     * Sets the size of the pizza.
     *
     * @param newSize The new size of the pizza.
     */
    public void setSize(Size newSize) {
    }

    /**
     * Sets the type of sauce for the pizza.
     *
     * @param newSauce The new sauce for the pizza.
     */
    public void setSauce(Sauce newSauce) {
    }

    /**
     * Sets the option for extra cheese on the pizza.
     *
     * @param extraCheese A boolean indicating whether extra cheese should be added.
     */
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    /**
     * Sets the option for extra sauce on the pizza.
     *
     * @param extraSauce A boolean indicating whether extra sauce should be added.
     */
    public void setExtraSauce(boolean extraSauce){
        this.extraSauce = extraSauce;
    }

    /**
     * Gets the list of toppings currently on the pizza.
     *
     * @return ArrayList of Toppings representing the current toppings on the pizza.
     */
    public ArrayList<Toppings> getToppings() {
        return toppings;
    }
    public Size getSize(){return size;}

    /**
     * Gets the current type of sauce on the pizza.
     *
     * @return Sauce representing the current sauce on the pizza.
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     * Provides a string representation of the pizza.
     * This method must be implemented by subclasses to provide details specific to the pizza type.
     *
     * @return String representing the details of the pizza.
     */
    @Override
    public abstract String toString();
}
