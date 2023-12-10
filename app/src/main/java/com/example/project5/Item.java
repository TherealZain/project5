package com.example.project5;

import com.example.project5.enums.Toppings;
import com.example.project5.enums.Sauce;

import java.util.ArrayList;

public class Item {
    private String itemName;
    private int image;
    private String unitPrice; //for demo purpose, the unitPrice is of String type
    private ArrayList<Toppings> toppings;
    private Sauce sauce;

    /**
     * Parameterized constructor.
     * @param itemName
     * @param image
     * @param unitPrice
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

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public String toppingsToString() {return toppings.toString();}
}


