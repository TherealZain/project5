package com.example.project5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BYOActivity extends AppCompatActivity {

    Spinner pizzaSizeSpinner;
    RadioGroup sauceRadioGroup;
    CheckBox extraCheeseCheckBox, extraSauceCheckBox;
    ArrayAdapter<String> toppingsAdapter;
    Button addToppingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        setUpViews();
    }

    private void setUpViews() {
        pizzaSizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        String[] sizes = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSizeSpinner.setAdapter(sizeAdapter);

        sauceRadioGroup = findViewById(R.id.sauceRadioGroup);
        sauceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.tomatoRadio) {
                    // Handle tomat selection
                } else if(checkedId == R.id.alfredoRadio) {
                    // Handle alfredo selection
                }

            }
        });

        extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
        extraSauceCheckBox = findViewById(R.id.extraSauceCheckBox);

        ListView listView = findViewById(R.id.addToppings);
        ArrayList<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

    }

    private List<String> getAllToppings() {
        List<String> toppings = new ArrayList<>();
        toppings.add("Sausage");
        toppings.add("Pepperoni");
        return toppings;
    }
}
