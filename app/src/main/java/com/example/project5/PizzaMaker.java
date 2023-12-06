package com.example.project5;

import com.example.project5.pizzas.BuildYourOwn;
import com.example.project5.pizzas.Deluxe;
import com.example.project5.pizzas.Supreme;
import com.example.project5.pizzas.Meatzza;
import com.example.project5.pizzas.Seafood;
import com.example.project5.pizzas.Pepperoni;
import com.example.project5.pizzas.Pizza;

/**
 * Factory class for creating various types of Pizza objects.
 * This class provides a static method to create different pizza
 * types based on a given string identifier.
 * It acts as a central point for creating instances of pizza subclasses
 * like BuildYourOwn, Deluxe, Supreme, Meatzza, Seafood, and Pepperoni.
 *
 * The createPizza method takes a string that identifies the type of pizza to be created and
 * returns an instance of the corresponding Pizza subclass.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */
public class PizzaMaker {

    /**
     * Creates new instance of pizza based on pizzaType provided, this is a factory method,
     * Other controllers use this method to create pizzas
     * @param pizzaType
     * @return Pizza type of pizzaType
     */
    public static Pizza createPizza(String pizzaType) {
        if (pizzaType.equals("BYO")) {
            return new BuildYourOwn();
        } else if (pizzaType.equals("Deluxe")) {
            return new Deluxe();
        } else if (pizzaType.equals("Supreme")) {
            return new Supreme();
        } else if (pizzaType.equals("Meatzza")) {
            return new Meatzza();
        } else if (pizzaType.equals("Seafood")) {
            return new Seafood();
        } else if (pizzaType.equals("Pepperoni")) {
            return new Pepperoni();
        } else {
            throw new IllegalArgumentException("Unknown pizza type: " + pizzaType);
        }
    }
}
