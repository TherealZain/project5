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

/**
 * Activity class for the Build Your Own (BYO) pizza functionality in the pizza ordering app.
 * This class handles the user interface and interactions for creating a custom pizza with
 * selectable options such as size, sauce, extra toppings, and cheese.
 *
 * @author Zain Zulfiqar, Nicholas Yim
 */

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
    private static final int MIN_TOPPINGS = 3;
    private static final int SMALL_SELECTION = 0;

    /**
     * Called when the activity is starting. This is where most initialization should go.
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down, this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        setUpViews();
    }

    /**
     * Sets up the views and user interface elements in the activity.
     */
    private void setUpViews() {
        setUpSpinner();
        setUpSauces();
        setUpToppings();
        priceTextView = findViewById(R.id.priceTextView);
        String price = String.format("%.2f", buildYourOwn.price());
        priceTextView.setText("$" + price);

        Button addToOrderButton = findViewById(R.id.addToOrderButton); // Assuming this is your button ID
        addToOrderButton.setOnClickListener(v -> addPizzaToOrder());
    }

    /**
     * Sets up the spinner for selecting pizza size and handles the selection events.
     */
    private void setUpSpinner() {
        pizzaSizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        String[] sizes = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSizeSpinner.setAdapter(sizeAdapter);

        pizzaSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Sets size if new size is selected
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = (String) parent.getItemAtPosition(position);
                Size size = Size.valueOf(selectedSize.toUpperCase());
                buildYourOwn.setSize(size);
                handlePriceChange();
            }

            /**
             * Does nothing when nothing selected
             * @param parent The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing is selected
            }
        });
    }

    /**
     * Configures the checkboxes for sauce, and the radio group for sauce selection.
     */
    private void setUpSauces() {
        sauceRadioGroup = findViewById(R.id.sauceRadioGroup);
        RadioButton tomatoRadio = findViewById(R.id.tomatoRadio);
        sauceRadioGroup.check(tomatoRadio.getId());
        buildYourOwn.setSauce(Sauce.TOMATO);
        sauceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            /**
             * Changes sauce if different sauce box is checked
             * @param group the group in which the checked radio button has changed
             * @param checkedId the unique identifier of the newly checked radio button
             */
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
    }

    /**
     * Sets extra cheese or extra sauce for buildYourOwn pizza
     * @param view of BYO xml file
     */
    public void setExtras(View view){
        buildYourOwn.setExtraCheese(extraCheeseCheckBox.isChecked());
        buildYourOwn.setExtraSauce(extraSauceCheckBox.isChecked());
        handlePriceChange();
    }

    /**
     * Configures the ListViews for available and selected toppings. Sets up adapters and click listeners.
     */
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

    /**
     * Sets up a ListView with a HighlightArrayAdapter and configures its item click listener.
     * @param listView The ListView to be set up.
     * @param adapter The ArrayAdapter to be associated with the ListView.
     */
    private void setupListViewWithHighlightAdapter(ListView listView, ArrayAdapter<String> adapter) {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) ->
                ((HighlightArrayAdapter) adapter).setSelectedPosition(position)
        );
    }

    /**
     * Initializes the lists of available and selected toppings.
     */
    private void initializeToppingsLists() {
        for (Toppings topping : Toppings.values()) {
            availableToppings.add(capitalize(topping.name().toLowerCase().replace('_', ' ')));
        }
    }

    /**
     * Handles adding a topping to the pizza. Adds the selected topping from available toppings to
     * selected toppings and updates the pizza configuration.
     * @param listView The ListView representing available toppings.
     */
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

    /**
     * Handles removing a topping from the pizza. Removes the selected topping from selected toppings and
     * updates the pizza configuration.
     * @param listView The ListView representing selected toppings.
     */
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

    /**
     * Updates the adapters for available and selected toppings ListViews.
     */
    private void updateAdapters() {
        availableToppingsAdapter.notifyDataSetChanged();
        selectedToppingsAdapter.notifyDataSetChanged();
    }

    /**
     * Handles the change in pizza price. Updates the price displayed on the UI.
     */
    private void handlePriceChange() {
        double price = buildYourOwn.price();
        String priceString = String.format("%.2f", price);
        priceTextView.setText("$" + priceString);
    }


    /**
     * Adds the configured pizza to the current order and displays a confirmation message.
     */
    public void addPizzaToOrder() {
        if(selectedToppings.size() < MIN_TOPPINGS){
            Toast.makeText(this,
                    "Must have minimum 3 toppings.", Toast.LENGTH_LONG).show();
            return;
        }
        Order currentOrder = Order.getInstance();
        currentOrder.addToOrder(buildYourOwn);
        Toast.makeText(this, "Pizza added to order", Toast.LENGTH_SHORT).show();
        resetUI();
    }

    /**
     * Capitalizes the first letter of the given string.
     * @param input The string to be capitalized.
     * @return The capitalized string.
     */
    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Resets the UI components to their initial state. Clears any pizza customization made.
     */
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
