package com.example.project5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class CurrentOrderActivity extends AppCompatActivity {

    private EditText orderNumberField, subTotalField;
    private ListView pizzaListView;
    private Button placeOrderButton, removePizzaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        setUpViews();
    }

    private void setUpViews() {
        orderNumberField = findViewById(R.id.orderNumberField);
        subTotalField = findViewById(R.id.subTotalField);
        pizzaListView = findViewById(R.id.pizzaListView);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        removePizzaButton = findViewById(R.id.removePizzaButton);

        // set pizzas
        String[] pizzaArray = {""};
        ArrayAdapter<String> pizzaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, pizzaArray);
        pizzaListView.setAdapter(pizzaAdapter);

        placeOrderButton.setOnClickListener(v -> {
            // place order
        });

        removePizzaButton.setOnClickListener(v -> {
            // remove pizza
        });

        orderNumberField.setText(""); // set order number
        subTotalField.setText("$"); // set subtotal
    }
}
