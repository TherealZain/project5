package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**
 * Activity class for managing and displaying the store orders
 * in the pizza ordering application.
 * This class allows the user to view different orders that
 * have been placed and cancel any of them.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner orderSelectSpinner;
    private TextView orderTotalTextView;
    private ListView allOrdersListView;
    private ArrayAdapter<String> ordersAdapter;
    private ArrayList<String> orderNumbers;
    private ArrayList<String> orderDetails;
    private Button cancelOrderButton;
    private StoreOrders storeOrders;


    /**
     * Initializes the activity. Sets the content view and configures UI components.
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down, this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        storeOrders = StoreOrders.getInstance();

        orderNumbers = new ArrayList<>();
        orderDetails = new ArrayList<>();
        setupViews();
    }

    /**
     * Sets up the views and UI components used in the activity.
     */
    private void setupViews() {
        orderSelectSpinner = findViewById(R.id.orderSelectSpinner);
        orderTotalTextView = findViewById(R.id.orderTotalTextView);
        allOrdersListView = findViewById(R.id.allOrdersListView);
        cancelOrderButton = findViewById(R.id.cancelOrderButton);
        populateOrderNumbers();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        orderSelectSpinner.setAdapter(spinnerAdapter);
        ordersAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, orderDetails);
        allOrdersListView.setAdapter(ordersAdapter);
        orderSelectSpinner.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {
            /**
             * Calls display method to display selected order
             * when a spinner item is selected.
             * @param parent The AdapterView where the selection happened.
             * @param view The view within the AdapterView that was clicked (this
             *            will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that was selected.
             */
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                String selectedOrderNumber = orderNumbers.get(position);
                displaySelectedOrder(selectedOrderNumber);
            }

            /**
             * Invoked when spinner selection disappears from the view.
             * Not used in this case.
             * @param parent The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        cancelOrderButton.setOnClickListener(v -> cancelSelectedOrder());
        if (!orderNumbers.isEmpty()) {
            orderSelectSpinner.setSelection(0);
        }
    }


    /**
     * Populates the spinner with order numbers from the StoreOrders instance.
     */
    private void populateOrderNumbers() {
        for (Order order : storeOrders.getOrders()) {
            orderNumbers.add(String.valueOf(order.getOrderNum()));
        }
    }

    /**
     * Displays details of the selected order in the ListView.
     * @param orderNumber Order number of the selected order as a String.
     */
    private void displaySelectedOrder(String orderNumber) {
        int orderNum = Integer.parseInt(orderNumber);
        Order selectedOrder = storeOrders.getOrderById(orderNum);

        orderDetails.clear();
        for (Pizza pizza : selectedOrder.getPizzas()) {
            orderDetails.add(pizza.toString());
        }
        ordersAdapter.notifyDataSetChanged();

        orderTotalTextView.setText("$" + selectedOrder.getOrderTotal());
    }

    /**
     * Cancels (removes) the selected order and updates the UI accordingly.
     */
    private void cancelSelectedOrder() {
        int selectedPosition = orderSelectSpinner.getSelectedItemPosition();
        if (selectedPosition >= 0 && selectedPosition < orderNumbers.size()) {
            String selectedOrderNumber = orderNumbers.get(selectedPosition);
            int orderNum = Integer.parseInt(selectedOrderNumber);
            storeOrders.removeOrder(storeOrders.getOrderById(orderNum));
            orderNumbers.remove(selectedPosition);
            ((ArrayAdapter) orderSelectSpinner.getAdapter()).
                    notifyDataSetChanged();

            // update spinner selection
            if (!orderNumbers.isEmpty()) {
                int newPosition = Math.max(0, selectedPosition - 1);
                orderSelectSpinner.setSelection(newPosition);
                displaySelectedOrder(orderNumbers.get(newPosition));
            } else {
                orderDetails.clear();
                ordersAdapter.notifyDataSetChanged();
                orderTotalTextView.setText(getString(R.string.zero_placeholder));
            }
            Toast.makeText(this, "Order " + orderNum + " cancelled.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select an order to cancel.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
