package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;
import java.util.List;

public class BYOActivity extends AppCompatActivity {

    private Pizza buildYourOwn = PizzaMaker.createPizza("BYO");
    private Spinner pizzaSizeSpinner;
    private RadioGroup sauceRadioGroup;
    private CheckBox extraCheeseCheckBox, extraSauceCheckBox;
    private TextView priceTextView;
    private ArrayAdapter<String> availableToppingsAdapter;
    private ArrayAdapter<String> selectedToppingsAdapter;
    private ArrayList<String> availableToppings = new ArrayList<>();
    private ArrayList<String> selectedToppings = new ArrayList<>();
    private static final int MAX_TOPPINGS = 7;
    private static final int SMALL_SELECTION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        setUpViews();
    }

    private void setUpViews() {
        setUpSpinner();
        setUpExtras();
        setUpToppings();
        priceTextView = findViewById(R.id.priceTextView);
        String price = String.format("%.2f", buildYourOwn.price());
        priceTextView.setText("$" + price);

        Button addToOrderButton = findViewById(R.id.addToOrderButton); // Assuming this is your button ID
        addToOrderButton.setOnClickListener(v -> addPizzaToOrder());
    }
    private void setUpSpinner() {
        pizzaSizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        String[] sizes = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSizeSpinner.setAdapter(sizeAdapter);

        pizzaSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = (String) parent.getItemAtPosition(position);
                Size size = Size.valueOf(selectedSize.toUpperCase());
                buildYourOwn.setSize(size);
                handlePriceChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing is selected
            }
        });
    }

    private void setUpExtras() {
        sauceRadioGroup = findViewById(R.id.sauceRadioGroup);
        RadioButton tomatoRadio = findViewById(R.id.tomatoRadio);

        sauceRadioGroup.check(tomatoRadio.getId());
        buildYourOwn.setSauce(Sauce.TOMATO);

        sauceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tomatoRadio) {
                    buildYourOwn.setSauce(Sauce.TOMATO);
                } else if (checkedId == R.id.alfredoRadio) {
                    buildYourOwn.setSauce(Sauce.ALFREDO);
                }
                handlePriceChange();
            }
        });

        extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
        extraSauceCheckBox = findViewById(R.id.extraSauceCheckBox);

        extraCheeseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            buildYourOwn.setExtraCheese(isChecked);
            handlePriceChange();
        });

        extraSauceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            buildYourOwn.setExtraSauce(isChecked);
            handlePriceChange();
        });
    }

    private void setUpToppings() {
        initializeToppingsLists();

        ListView availableToppingsListView = findViewById(R.id.availableToppingsListView);
        ListView selectedToppingsListView = findViewById(R.id.selectedToppingsListView);

        availableToppingsAdapter = new HighlightArrayAdapter(this, android.R.layout.simple_list_item_1, availableToppings);
        selectedToppingsAdapter = new HighlightArrayAdapter(this, android.R.layout.simple_list_item_1, selectedToppings);

        setupListViewWithHighlightAdapter(availableToppingsListView, availableToppingsAdapter);
        setupListViewWithHighlightAdapter(selectedToppingsListView, selectedToppingsAdapter);

        Button addToppingButton = findViewById(R.id.addToppingButton);
        Button removeToppingButton = findViewById(R.id.removeToppingButton);

        addToppingButton.setOnClickListener(v -> handleAddTopping(availableToppingsListView));
        removeToppingButton.setOnClickListener(v -> handleRemoveTopping(selectedToppingsListView));
    }

    private void setupListViewWithHighlightAdapter(ListView listView, ArrayAdapter<String> adapter) {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) ->
                ((HighlightArrayAdapter) adapter).setSelectedPosition(position)
        );
    }

    private void initializeToppingsLists() {
        for (Toppings topping : Toppings.values()) {
            availableToppings.add(topping.name());
        }
    }

    private void handleAddTopping(ListView listView) {
        if (selectedToppings.size() >= MAX_TOPPINGS) {
            Toast.makeText(this, "Cannot add more toppings. Maximum of 7 toppings.", Toast.LENGTH_LONG).show();
            return;
        }

        int position = listView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = availableToppingsAdapter.getItem(position);
            Toppings toppingEnum = Toppings.valueOf(topping.toUpperCase());
            buildYourOwn.addToppings(toppingEnum);

            availableToppings.remove(topping);
            selectedToppings.add(topping);
            updateAdapters();
            listView.setItemChecked(position, false);
            handlePriceChange();
        }
        ((HighlightArrayAdapter) availableToppingsAdapter).setSelectedPosition(-1);
    }

    private void handleRemoveTopping(ListView listView) {
        int position = listView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = selectedToppingsAdapter.getItem(position);
            Toppings toppingEnum = Toppings.valueOf(topping.toUpperCase());
            buildYourOwn.removeToppings(toppingEnum);

            selectedToppings.remove(topping);
            availableToppings.add(topping);
            updateAdapters();
            listView.setItemChecked(position, false);
            handlePriceChange();
        }
        ((HighlightArrayAdapter) selectedToppingsAdapter).setSelectedPosition(-1);
    }

    private void updateAdapters() {
        availableToppingsAdapter.notifyDataSetChanged();
        selectedToppingsAdapter.notifyDataSetChanged();
    }

    private void handlePriceChange() {
        double price = buildYourOwn.price();
        String priceString = String.format("%.2f", price);
        priceTextView.setText("$" + priceString);
    }

    public void addPizzaToOrder() {
        Order currentOrder = Order.getInstance();
        currentOrder.addToOrder(buildYourOwn);
        Toast.makeText(this, "Pizza added to order", Toast.LENGTH_SHORT).show();

        resetUI();
    }

    private void resetUI() {

        pizzaSizeSpinner.setSelection(SMALL_SELECTION);

        extraCheeseCheckBox.setChecked(false);
        extraSauceCheckBox.setChecked(false);

        selectedToppings.clear();
        availableToppings.clear();
        initializeToppingsLists();
        updateAdapters();

        buildYourOwn = PizzaMaker.createPizza("BYO");
        handlePriceChange();
    }

}
