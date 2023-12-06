package com.example.project5;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

public class SpecialtyActivity extends AppCompatActivity {

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

    private ArrayList<Item> fillItemArrays(){
        Pizza deluxe = PizzaMaker.createPizza("Deluxe");
        Pizza meatzza = PizzaMaker.createPizza("Meatzza");
        Pizza pepperoni = PizzaMaker.createPizza("Pepperoni");
        Pizza seafood = PizzaMaker.createPizza("Seafood");
        Pizza supreme = PizzaMaker.createPizza("Supreme");
        ArrayList<Item> pizzaDisplays = new ArrayList<>();
        Item deluxeItem = new Item("Deluxe",
                R.drawable.deluxe_pizza, Double.toString(deluxe.price()));
        Item meatzzaItem = new Item("Meatzza",
                R.drawable.meatzza_pizza, Double.toString(meatzza.price()));
        Item pepperoniItem = new Item("Pepperoni",
                R.drawable.pepperoni_pizza, Double.toString(pepperoni.price()));
        Item seafoodItem = new Item("Seafood",
                R.drawable.seafood_pizza, Double.toString(seafood.price()));
        Item supremeItem = new Item("Supreme",
                R.drawable.supreme_pizza, Double.toString(supreme.price()));
        pizzaDisplays.add(deluxeItem);
        pizzaDisplays.add(meatzzaItem);
        pizzaDisplays.add(pepperoniItem);
        pizzaDisplays.add(seafoodItem);
        pizzaDisplays.add(supremeItem);
        return pizzaDisplays;
    }
}
