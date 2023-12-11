package com.example.project5;

import com.example.project5.pizzas.BBQChicken;
import com.example.project5.pizzas.BuildYourOwn;
import com.example.project5.pizzas.Deluxe;
import com.example.project5.pizzas.Hawaiian;
import com.example.project5.pizzas.Margherita;
import com.example.project5.pizzas.Mexican;
import com.example.project5.pizzas.Supreme;
import com.example.project5.pizzas.Meatzza;
import com.example.project5.pizzas.Seafood;
import com.example.project5.pizzas.Pepperoni;
import com.example.project5.pizzas.Pizza;
import com.example.project5.pizzas.Veggie;

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
        switch (pizzaType) {
            case "BYO":
                return new BuildYourOwn();
            case "Deluxe":
                return new Deluxe();
            case "Supreme":
                return new Supreme();
            case "Meatzza":
                return new Meatzza();
            case "Seafood":
                return new Seafood();
            case "Pepperoni":
                return new Pepperoni();
            case "BBQ Chicken":
                return new BBQChicken();
            case "Hawaiian":
                return new Hawaiian();
            case "Margherita":
                return new Margherita();
            case "Mexican":
                return new Mexican();
            case "Veggie":
                return new Veggie();
            default:
                throw new IllegalArgumentException("Unknown pizza type: " + pizzaType);
        }
    }
}
