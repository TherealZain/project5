package com.example.project5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView pizzaListView;
    private TextView orderNumberTextView, subTotalTextView, salesTaxTextView, orderTotalTextView;
    private Button placeOrderButton, removePizzaButton;
    private ArrayAdapter<String> pizzaAdapter;
    private ArrayList<String> pizzaList;
    private static final double SALES_TAX_RATE = 0.06625;
    private static Order order = Order.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        pizzaList = new ArrayList<>();

        setUpViews();
        updateOrderDisplay();
    }

    private void setUpViews() {
        orderNumberTextView = findViewById(R.id.orderNumberTextView);
        subTotalTextView = findViewById(R.id.subTotalTextView);
        salesTaxTextView = findViewById(R.id.salesTaxTextView);
        orderTotalTextView = findViewById(R.id.orderTotalTextView);
        pizzaListView = findViewById(R.id.pizzaListView);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        removePizzaButton = findViewById(R.id.removePizzaButton);

        pizzaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaList);
        pizzaListView.setAdapter(pizzaAdapter);

        placeOrderButton.setOnClickListener(v -> handlePlaceOrder());
        removePizzaButton.setOnClickListener(v -> handleRemovePizza());
    }

    private void handlePlaceOrder() {
    }

    private void handleRemovePizza() {
    }

    private void updateOrderDisplay() {
        pizzaAdapter.clear();
        pizzaAdapter.addAll(pizzaList);
        pizzaAdapter.notifyDataSetChanged();

        if (order != null) {
            orderNumberTextView.setText(String.valueOf(order.getOrderNum()));

            ArrayList<Pizza> pizzaItems =  order.getPizzas();
            for(Pizza pizza : pizzaItems){
                if(pizza != null) {
                    pizzaList.add(pizza.toString());
                }
            }
        }
        calculateTotals(order.getPizzas());
    }

    private void calculateTotals(ArrayList<Pizza> pizzaItems) {
        double subTotal = 0;
        for(Pizza pizza : pizzaItems){
            if(pizza != null) {
                subTotal += pizza.price();
            }
        }

        double salesTax = Math.round((subTotal*SALES_TAX_RATE)*100.0)/100.0;
        double totalCost = Math.round((subTotal + salesTax)*100.0)/100.0;
        String orderNumString = Integer.toString(order.getOrderNum());
        String subTotalString = String.format("%.2f", subTotal);
        String salesTaxString = String.format("%.2f", salesTax);
        String totalString = String.format("%.2f", totalCost);
        orderTotalTextView.setText(orderNumString);
        subTotalTextView.setText("$" + subTotalString);
        salesTaxTextView.setText("$" + salesTaxString);
        orderTotalTextView.setText("$" + totalString);
    }

}
