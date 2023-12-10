package com.example.project5.pizzas;

import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;

import java.util.ArrayList;

public class BBQChicken extends Pizza {
    private static final double BBQ_CHICKEN_SMALL_PRICE = 15.99;

    public BBQChicken() {
        toppings = new ArrayList<>();
        sauce = Sauce.BBQ;
        toppings.add(Toppings.CHICKEN);
        toppings.add(Toppings.ONION);
        toppings.add(Toppings.CILANTRO);
    }

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

    @Override
    public void setSize(Size newSize) {
        this.size = newSize;
    }
    @Override
    public void addToppings(Toppings topping) {

    }

    @Override
    public void removeToppings(Toppings topping) {

    }

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
