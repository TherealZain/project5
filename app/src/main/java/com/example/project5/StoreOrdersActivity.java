package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner orderSelectSpinner;
    private TextView orderTotalTextView;
    private ListView allOrdersListView;
    private ArrayAdapter<String> ordersAdapter;
    private ArrayList<String> orderNumbers;
    private ArrayList<String> orderDetails;
    private Button cancelOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        orderSelectSpinner = findViewById(R.id.orderSelectSpinner);
        allOrdersListView = findViewById(R.id.ordersListView);
        cancelOrderButton = findViewById(R.id.cancelOrderButton);

        // Initialize your ArrayAdapter with orderNumbers
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSelectSpinner.setAdapter(spinnerAdapter);

        // Set up ListView adapter
        ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderDetails);
        allOrdersListView.setAdapter(ordersAdapter);

        orderSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // display selected order's details
                String selectedOrderNumber = orderNumbers.get(position);
                displaySelectedOrder(selectedOrderNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing is selected
            }
        });

        cancelOrderButton.setOnClickListener(v -> {
            // cancel selected order
        });
    }

    private void displaySelectedOrder(String orderNumber) {
        // Logic to display selected order
    }
}
