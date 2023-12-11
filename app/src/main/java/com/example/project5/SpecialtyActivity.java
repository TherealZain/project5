package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.enums.Size;
import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**
 * Activity class for the Specialty pizza functionality in the pizza ordering app.
 * This class handles user interactions for selecting between 10 preset
 * specialty pizzas: Deluxe, Meatzza, Pepperoni, Seafood, Supreme, BBQ Chicken,
 * Hawaiian, Margherita, Mexican, and Veggie.
 *
 * This class also implements a RecyclerView to display all the pizza options.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

public class SpecialtyActivity extends AppCompatActivity {

    private static Order currOrder = Order.getInstance();
    private ArrayList<Item> pizzaDisplays;
    private static final int PIZZA_NAME_INDEX = 0;
    private static final int PIZZA_PRICE_INDEX = 1;
    private static final int PIZZA_SAUCE_INDEX = 2;
    private static final int PIZZA_CHEESE_INDEX = 3;
    private static final int PIZZA_QUANTITY_INDEX = 4;
    private static final int PIZZA_SIZE_SPINNER = 5;

    /**
     * Called when the activity is starting. This is where most initialization should go.
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down, this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        pizzaDisplays = new ArrayList<>();
        setRecycler();
    }

    /**
     * Sets the RecyclerView and corresponding UI features to
     * display each Specialty pizza option.
     */
    public void setRecycler(){
        fillItemArrays();
        addNewSpecialtyPizzas();
        ItemsAdapter adapter = new ItemsAdapter(this, pizzaDisplays);
        RecyclerView recyclerView = findViewById(R.id.specialtyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    /**
     * Adds the configured Specialty pizza to the current order,
     * displays a confirmation message.
     *
     * @param pizzaProps The array (of views) of pizza properties:
     *                   pizza name, pizza price, extra sauce selection
     *                   extra cheese selection, quantity, size
     */
    public static void addPizzaToOrder(View[] pizzaProps){
        int numOfPizzas = Integer.parseInt(((EditText)
                pizzaProps[PIZZA_QUANTITY_INDEX]).getText().toString());
        TextView pizzaTypeView = (TextView) pizzaProps[PIZZA_NAME_INDEX];
        Spinner sizeSpinner = (Spinner) pizzaProps[PIZZA_SIZE_SPINNER];
        CheckBox extraSauceBox = (CheckBox) pizzaProps[PIZZA_SAUCE_INDEX];
        CheckBox extraCheeseBox = (CheckBox) pizzaProps[PIZZA_CHEESE_INDEX];
        String pizzaType = pizzaTypeView.getText().toString();
        String size = sizeSpinner.getSelectedItem().toString();
        boolean extraSauce = extraSauceBox.isChecked();
        boolean extraCheese = extraCheeseBox.isChecked();
        for(int i =0; i < numOfPizzas; i++){
            Pizza pizza = PizzaMaker.createPizza(pizzaType);
            pizza.setSize(Size.valueOf(size.toUpperCase()));
            pizza.setExtraSauce(extraSauce);
            pizza.setExtraCheese(extraCheese);
            currOrder.addToOrder(pizza);
        }
    }

    /**
     * Sets currOrder to the value of the new order that is created
     *
     * @param newOrder The new order that the user creates through interaction
     */
    public static void setOrder(Order newOrder){currOrder = newOrder;}

    /**
     * Fills displays and adds the 5 original Specialty pizzas by creating custom Items,
     * including pizza details (name, image, price, toppings, sauce).
     * Pizzas include: Deluxe, Meatzza, Pepperoni, Seafood, Supreme
     *
     * @return pizzaDisplays as ArrayList<Item>
     */
    private ArrayList<Item> fillItemArrays(){
        Pizza deluxe = PizzaMaker.createPizza("Deluxe");
        Pizza meatzza = PizzaMaker.createPizza("Meatzza");
        Pizza pepperoni = PizzaMaker.createPizza("Pepperoni");
        Pizza seafood = PizzaMaker.createPizza("Seafood");
        Pizza supreme = PizzaMaker.createPizza("Supreme");
        Item deluxeItem = new Item("Deluxe", R.drawable.deluxe_pizza,
                Double.toString(deluxe.price()),
                deluxe.getToppings(), deluxe.getSauce());
        Item meatzzaItem = new Item("Meatzza", R.drawable.meatzza_pizza,
                Double.toString(meatzza.price()),
                meatzza.getToppings(), meatzza.getSauce());
        Item pepperoniItem = new Item("Pepperoni", R.drawable.pepperoni_pizza,
                Double.toString(pepperoni.price()),
                pepperoni.getToppings(), pepperoni.getSauce());
        Item seafoodItem = new Item("Seafood", R.drawable.seafood_pizza,
                Double.toString(seafood.price()),
                seafood.getToppings(), seafood.getSauce());
        Item supremeItem = new Item("Supreme", R.drawable.supreme_pizza,
                Double.toString(supreme.price()),
                supreme.getToppings(), supreme.getSauce());
        pizzaDisplays.add(deluxeItem);
        pizzaDisplays.add(meatzzaItem);
        pizzaDisplays.add(pepperoniItem);
        pizzaDisplays.add(seafoodItem);
        pizzaDisplays.add(supremeItem);
        return pizzaDisplays;
    }

    /**
     * Fills displays and adds the 5 additional Specialty pizzas by creating custom Items,
     * including pizza details (name, image, price, toppings, sauce).
     * Pizzas include: BBQ Chicken, Hawaiian, Margherita, Mexican, Veggie
     *
     * Nothing is returned since these pizzas are added to the already
     * existing/altered pizzaDisplays ArrayList.
     */
    private void addNewSpecialtyPizzas(){
        Pizza bbqChicken = PizzaMaker.createPizza("BBQ Chicken");
        Pizza hawaiian = PizzaMaker.createPizza("Hawaiian");
        Pizza margherita = PizzaMaker.createPizza("Margherita");
        Pizza mexican = PizzaMaker.createPizza("Mexican");
        Pizza veggie = PizzaMaker.createPizza("Veggie");
        Item bbqChickenItem = new Item("BBQ Chicken",
                R.drawable.bbq_chicken_pizza, Double.toString(bbqChicken.price()),
                bbqChicken.getToppings(), bbqChicken.getSauce());
        Item hawaiianItem = new Item("Hawaiian",
                R.drawable.hawaiian_pizza, Double.toString(hawaiian.price()),
                hawaiian.getToppings(), hawaiian.getSauce());
        Item margheritaItem = new Item("Margherita",
                R.drawable.margherita_pizza, Double.toString(margherita.price()),
                margherita.getToppings(), margherita.getSauce());
        Item mexicanItem = new Item("Mexican",
                R.drawable.mexican_pizza, Double.toString(mexican.price()),
                mexican.getToppings(), mexican.getSauce());
        Item veggieItem = new Item("Veggie",
                R.drawable.veggie_pizza, Double.toString(veggie.price()),
                veggie.getToppings(), veggie.getSauce());
        pizzaDisplays.add(bbqChickenItem);
        pizzaDisplays.add(hawaiianItem);
        pizzaDisplays.add(margheritaItem);
        pizzaDisplays.add(mexicanItem);
        pizzaDisplays.add(veggieItem);
    }
}
