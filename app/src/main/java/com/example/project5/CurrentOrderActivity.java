package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private static StoreOrders storeOrders = StoreOrders.getInstance();

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
        pizzaAdapter = new HighlightArrayAdapter(this, android.R.layout.simple_list_item_1, pizzaList);
        pizzaListView.setAdapter(pizzaAdapter);

        pizzaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((HighlightArrayAdapter)pizzaAdapter).setSelectedPosition(position);
            }
        });
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlePlaceOrder();
            }
        });
        removePizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRemovePizza();
            }
        });
    }

    public void handlePlaceOrder() {
        if (pizzaList.isEmpty()) {
            Toast.makeText(this, "No pizzas in the order. Please add pizzas " +
                    "before placing the order.", Toast.LENGTH_LONG).show();
            return;
        }

        int orderNumber = order.getOrderNum();

        storeOrders.addOrder(order);
        Order.createNewOrder();
        order = Order.getInstance();

        Toast.makeText(this, "Order successfully placed. Your order number is " + orderNumber + ".", Toast.LENGTH_LONG).show();

        updateOrderDisplay(); // Update the display to show the new order (which should be empty)
    }

    public void handleRemovePizza() {
        int selectedPosition = ((HighlightArrayAdapter)
                pizzaAdapter).getSelectedPosition();
        if (selectedPosition >= 0 && selectedPosition < pizzaList.size()) {
            pizzaList.remove(selectedPosition);
            order.removePizza(selectedPosition);
            ((HighlightArrayAdapter) pizzaAdapter).setSelectedPosition(-1);
            pizzaAdapter.notifyDataSetChanged();
            calculateTotals(order.getPizzas());
            Toast.makeText(this, "Pizza removed from order.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select a pizza to remove.",
                    Toast.LENGTH_SHORT).show();
        }
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
