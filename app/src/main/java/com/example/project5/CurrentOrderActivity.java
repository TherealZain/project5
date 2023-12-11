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

/**
 * Activity class for managing and displaying the current order
 * in the pizza ordering application.
 * This class allows users to view, add,
 * or remove pizzas from their current order, and place the order.
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private ListView pizzaListView;
    private TextView orderNumberTextView, subTotalTextView, salesTaxTextView, orderTotalTextView;
    private Button placeOrderButton, removePizzaButton;
    private ArrayAdapter<String> pizzaAdapter;
    private ArrayList<String> pizzaList;
    private static final double SALES_TAX_RATE = 0.06625;
    private static Order order = Order.getInstance();
    private static StoreOrders storeOrders = StoreOrders.getInstance();

    /**
     * Initializes the activity. Sets the content view and configures UI components.
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down, this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        pizzaList = new ArrayList<>();

        setUpViews();
        updateOrderDisplay();
    }

    /**
     * Sets up the views and UI components used in the activity.
     */
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
            /**
             * Sets selected position when a item is clicked
             * @param parent The AdapterView where the click happened.
             * @param view The view within the AdapterView that was clicked (this
             *            will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((HighlightArrayAdapter)pizzaAdapter).setSelectedPosition(position);
            }
        });
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls handlePlaceOrder when place order button is clicked
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                handlePlaceOrder();
            }
        });
        removePizzaButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls handleRemovePizza when remove pizza is clicked
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                handleRemovePizza();
            }
        });
    }

    /**
     * Handles the action for placing an order. Validates the order and updates the StoreOrders singleton.
     * Displays a Toast message on successful placement of the order.
     */
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
        SpecialtyActivity.setOrder(order);
        Toast.makeText(this, "Order successfully placed. Your order number is " + orderNumber + ".", Toast.LENGTH_LONG).show();
        updateOrderDisplay();
    }

    /**
     * Handles the action for removing a selected pizza from the order.
     * Updates the order and the pizza list view. Displays a Toast message on successful removal.
     */
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

    /**
     * Updates the display of the current order, including the list of pizzas and order totals.
     */
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


    /**
     * Calculates and updates the subtotal, sales tax, and total cost of the order.
     * @param pizzaItems The list of Pizza objects in the current order.
     */
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
