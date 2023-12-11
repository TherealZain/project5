package com.example.project5;

import com.example.project5.enums.Toppings;
import com.example.project5.enums.Sauce;

import java.util.ArrayList;

/**
 * This is an Item class that establishes parameters used in the ItemAdapter for the RecyclerView.
 * These parameters apply to the Pizza objects, and the class includes getters for each parameter,
 * as well as a setter for sauce.
 * Parameters include itemName, image, unitPrice, toppings, and sauce.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

public class Item {
    private String itemName;
    private int image;
    private String unitPrice; //for demo purpose, the unitPrice is of String type
    private ArrayList<Toppings> toppings;
    private Sauce sauce;

    /**
     * Parameterized constructor.
     * @param itemName as String
     * @param image as int
     * @param unitPrice as String
     * @param toppings as ArrayList<Toppings>
     * @param sauce as Sauce
     */
    public Item(String itemName, int image, String unitPrice, ArrayList<Toppings> toppings, Sauce sauce) {
        this.itemName = itemName;
        this.image = image;
        this.unitPrice = unitPrice;
        this.toppings = toppings;
        this.sauce = sauce;
    }

    /**
     * Getter method that returns the item name of an item.
     * @return the item name of an item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Getter method that returns the image of an item.
     * @return the image of an item.
     */
    public int getImage() {
        return image;
    }

    /**
     * Getter method that returns the unit price.
     * @return the unit price of the item.
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * Getter method for sauce
     * @return sauce of pizza item
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     * Setter method for sauce
     * @param sauce of pizza item
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Toppings in string form of pizza
     * @return String of toppings
     */
    public String toppingsToString() {return toppings.toString();}
}


