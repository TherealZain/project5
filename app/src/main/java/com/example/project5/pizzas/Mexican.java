package com.example.project5.pizzas;


import com.example.project5.BYOActivity;
import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import java.util.ArrayList;

    /**
     * Represents a Mexican pizza, a specialty pizza with a variety of toppings commonly found in Mexican cuisine.
     * This class extends the Pizza class and offers a concrete implementation of its abstract methods,
     * tailored to a Mexican-style pizza.
     *
     * @author Zain Zulfiqar, Nicholas Yim
     */
    public class Mexican extends Pizza {

        private static final double MEXICAN_SMALL_PRICE = 15.99;

        /**
         * Constructor to create a Mexican pizza with default settings.
         * Initializes the pizza with a set of toppings typical for a Mexican pizza.
         */
        public Mexican() {
            toppings = new ArrayList<>();
            sauce = Sauce.TOMATO;
            toppings.add(Toppings.GROUNDBEEF);
            toppings.add(Toppings.JALAPENOS);
            toppings.add(Toppings.ONION);
            toppings.add(Toppings.TOMATOES);
            toppings.add(Toppings.CORN);
            toppings.add(Toppings.AVOCADO);
            toppings.add(Toppings.CHEDDAR);
        }

        /**
         * Calculates and returns the price of the Mexican pizza based on its
         * size and extra selections.
         * The base price is for a small size and increases for medium and large sizes.
         *
         * @return double representing the total price of the Pepperoni pizza.
         */
        @Override
        public double price() {
            double price = MEXICAN_SMALL_PRICE;
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
         * Sets the size of the Mexican pizza.
         * @param newSize The new size to set for the pizza.
         */
        @Override
        public void setSize(Size newSize) {
            this.size = newSize;
        }

        /**
         * Adds a specified topping to the Mexican pizza.
         * This implementation is currently empty as the Hawaiian pizza has predefined toppings.
         * @param topping The topping to be added (not used).
         */
        @Override
        public void addToppings(Toppings topping) {

        }

        /**
         * Removes a specified topping from the Mexican pizza.
         * This implementation is currently empty as the Hawaiian pizza has predefined toppings.
         * @param topping The topping to be removed (not used).
         */
        @Override
        public void removeToppings(Toppings topping) {

        }

        /**
         * Provides a string representation of the Mexican pizza.
         * The string includes the type of pizza, the list of fixed toppings,
         * size, sauce, and additional options,
         * followed by the total price.
         *
         * @return String representing the details of the Mexican pizza.
         */
        @Override
        public String toString() {
            String pizzaType = "[Mexican Pizza] ";
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
