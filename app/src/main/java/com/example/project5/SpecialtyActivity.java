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

public class SpecialtyActivity extends AppCompatActivity {

    private static Order currOrder = Order.getInstance();
    private static final int PIZZA_NAME_INDEX = 0;
    private static final int PIZZA_PRICE_INDEX = 1;
    private static final int PIZZA_SAUCE_INDEX = 2;
    private static final int PIZZA_CHEESE_INDEX = 3;
    private static final int PIZZA_QUANTITY_INDEX = 4;
    private static final int PIZZA_SIZE_SPINNER = 5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty);
        setRecycler();
    }

    public void setRecycler(){
        ArrayList<Item> pizzaDisplays = fillItemArrays();
        ItemsAdapter adapter = new ItemsAdapter(this, pizzaDisplays);
        RecyclerView recyclerView = findViewById(R.id.specialtyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public static void addPizzaToOrder(View[] pizzaProps){
        int numOfPizzas = Integer.parseInt(((EditText)
                pizzaProps[PIZZA_QUANTITY_INDEX]).getText().toString());
        TextView pizzaTypeView = (TextView) pizzaProps[PIZZA_NAME_INDEX];
        Spinner sizeSpinner = (Spinner) pizzaProps[PIZZA_SIZE_SPINNER];
        CheckBox extraSauceBox = (CheckBox) pizzaProps[PIZZA_SAUCE_INDEX];
        CheckBox extraCheeseBox = (CheckBox) pizzaProps[PIZZA_CHEESE_INDEX];
        String pizzaType = pizzaTypeView.getText().toString();
        String size = sizeSpinner.getSelectedItem().toString();
        boolean extraSauce = extraSauceBox.isSelected();
        boolean extraCheese = extraCheeseBox.isSelected();
        for(int i =0; i < numOfPizzas; i++){
            Pizza pizza = PizzaMaker.createPizza(pizzaType);
            pizza.setSize(Size.valueOf(size.toUpperCase()));
            pizza.setExtraSauce(extraSauce);
            pizza.setExtraCheese(extraCheese);
            currOrder.addToOrder(pizza);
        }
    }

    private ArrayList<Item> fillItemArrays(){
        Pizza deluxe = PizzaMaker.createPizza("Deluxe");
        Pizza meatzza = PizzaMaker.createPizza("Meatzza");
        Pizza pepperoni = PizzaMaker.createPizza("Pepperoni");
        Pizza seafood = PizzaMaker.createPizza("Seafood");
        Pizza supreme = PizzaMaker.createPizza("Supreme");
        ArrayList<Item> pizzaDisplays = new ArrayList<>();
        Item deluxeItem = new Item("Deluxe", R.drawable.deluxe_pizza,
                Double.toString(deluxe.price()), deluxe.getToppings());
        Item meatzzaItem = new Item("Meatzza",
                R.drawable.meatzza_pizza,
                Double.toString(meatzza.price()), meatzza.getToppings());
        Item pepperoniItem = new Item("Pepperoni", R.drawable.pepperoni_pizza,
                Double.toString(pepperoni.price()), pepperoni.getToppings());
        Item seafoodItem = new Item("Seafood", R.drawable.seafood_pizza,
                Double.toString(seafood.price()), seafood.getToppings());
        Item supremeItem = new Item("Supreme", R.drawable.supreme_pizza,
                Double.toString(supreme.price()), supreme.getToppings());
        pizzaDisplays.add(deluxeItem);
        pizzaDisplays.add(meatzzaItem);
        pizzaDisplays.add(pepperoniItem);
        pizzaDisplays.add(seafoodItem);
        pizzaDisplays.add(supremeItem);
        return pizzaDisplays;
    }
}
