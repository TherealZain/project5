package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.pizzas.Pizza;
import com.example.project5.enums.Size;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Lily Chang
 *
 * @author Nicholas Yim, Zain Zulfiqar
 */
class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder> {
    private Context context; //need the context to inflate the layout
    private ArrayList<Item> items; //need the data binding to each row of RecyclerView

    public ItemsAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent The ViewGroup into which the new View will be added after
     *               it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return ItemsHolder(view, context)
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new ItemsHolder(view, context);
    }

    /**
     * Assign data values for each row according to their "position" (index)
     * when the item becomes visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder( ItemsHolder holder, int position) {
        String pizzaName = items.get(position).getItemName();
        String sauce = items.get(position).getSauce().toString();

        holder.pizza_name.setText(pizzaName);
        holder.pizza_price.setText(items.get(position).getUnitPrice());
        holder.im_item.setImageResource(items.get(position).getImage());

        holder.sauceDisplay.setText(context.getString(R.string.
                sauce_label, sauce));
        holder.toppingsDisplay.setText(items.get(position).toppingsToString());
        holder.createDisplayPizza(pizzaName);
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView pizza_name, pizza_price, toppingsDisplay, sauceDisplay;
        private static final int SMALL_INDEX = 0;
        private static final int MEDIUM_INDEX = 1;
        private static final int LARGE_INDEX = 2;
        private Pizza displayPizza;
        private CheckBox sauceBox, cheeseBox;
        private ImageView im_item;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout
        private Spinner specialtySpinner;
        private EditText quantity;
        private Context context;
        View[] pizzaProperties;


        /**
         * Constructor for initializing the ItemsHolder.
         * Sets up the views and event listeners for each item in the RecyclerView.
         *
         * @param itemView The view of the individual list item.
         * @param context  The context of the application.
         */
        public ItemsHolder( View itemView, Context context) {
            super(itemView);
            this.context=context;
            pizza_name = itemView.findViewById(R.id.pizza_type);
            pizza_price = itemView.findViewById(R.id.pizza_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            toppingsDisplay = itemView.findViewById(R.id.toppingsDisplay);
            sauceDisplay = itemView.findViewById(R.id.sauceDisplay);
            sauceBox = itemView.findViewById(R.id.sauceBox);
            cheeseBox = itemView.findViewById(R.id.cheeseBox);
            quantity = itemView.findViewById(R.id.quantityInput);
            populateSpinner(itemView);
            setAddButtonOnClick(itemView);
            setExtraButtonClick(itemView);
        }

        /**
         * Sets click listeners for the extra sauce and cheese CheckBoxes.
         * Handles the changes in pizza configuration when these CheckBoxes are clicked.
         *
         * @param view The view in which the CheckBoxes are contained.
         */
        public void setExtraButtonClick(View view){
            sauceBox.setOnClickListener(new View.OnClickListener() {
                /**
                 * Toggles the state of extra sauce for the pizza, updates pizza price.
                 * Called when the extra sauce CheckBox is clicked.
                 *
                 * @param view The view that was clicked (extra sauce CheckBox).
                 */
                @Override
                public void onClick(View view){
                    displayPizza.setExtraSauce(sauceBox.isChecked());
                        pizza_price.setText(Double.toString(displayPizza.price()));
                }
            });
            cheeseBox.setOnClickListener(new View.OnClickListener() {
                /**
                 * Toggles the state of extra cheese for the pizza, updates pizza price.
                 * Called when the extra cheese CheckBox is clicked.
                 *
                 * @param view The view that was clicked (extra cheese CheckBox).
                 */
                @Override
                public void onClick(View view){
                    displayPizza.setExtraCheese(cheeseBox.isChecked());
                    pizza_price.setText(String.format("%.2f", displayPizza.price()));
                }
            });
        }

        /**
         * Creates and initializes the displayPizza object based on the given pizza name.
         * This method configures the pizza object for display in the ViewHolder.
         *
         * @param pizzaName The name of the pizza to be displayed.
         */
        private void createDisplayPizza(String pizzaName){
            displayPizza = PizzaMaker.createPizza(pizzaName);
        }

        /**
         * Populates the spinner with pizza sizes and handles the selection events.
         * Updates the display pizza object when a different size is selected.
         *
         * @param itemView The view containing the spinner.
         */
        private void populateSpinner(View itemView){
            specialtySpinner = itemView.findViewById(R.id.specialtySizeSpinner);
            String[] sizes = new String[]{"Small", "Medium", "Large"};
            ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(context,
                    android.R.layout.simple_spinner_item, sizes);
            sizeAdapter.setDropDownViewResource(android.R.layout.
                    simple_spinner_dropdown_item);
            specialtySpinner.setAdapter(sizeAdapter);
            specialtySpinner.setOnItemSelectedListener(new AdapterView.
                    OnItemSelectedListener() {
                /**
                 * Selects size based on user selection in pizza size spinner.
                 *
                 * @param parent The AdapterView where the selection happened
                 * @param view The view within the AdapterView that was clicked
                 * @param position The position of the view in the adapter
                 * @param id The row id of the item that is selected
                 */
                @Override
                public void onItemSelected(AdapterView<?> parent,
                                           View view, int position, long id) {
                    switch (position) {
                        case SMALL_INDEX:
                            displayPizza.setSize(Size.SMALL);
                            break;
                        case MEDIUM_INDEX:
                            displayPizza.setSize(Size.MEDIUM);
                            break;
                        case LARGE_INDEX:
                            displayPizza.setSize(Size.LARGE);
                            break;
                    }
                    pizza_price.setText(String.format("%.2f", displayPizza.price()));
                }

                /**
                 * Invoked when nothing is selected in the spinner.
                 * Not used in this context.
                 *
                 * @param parent The AdapterView that now contains no selected item.
                 */
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        }
        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView as View
         */
        private void setAddButtonOnClick(View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                /**
                 * Handles the click event on the 'Add to Order' button.
                 * Presents a confirmation dialog and processes the user's
                 * response of 'yes' or 'no'.
                 *
                 * @param view The view that was clicked ('Add to Order' button).
                 */
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert =
                            new AlertDialog.Builder(itemView.getContext());
                    if(!checkQuantity()){
                        return;
                    }
                    alert.setTitle("Do you want to place order?");
                    alert.setMessage(pizza_name.getText().toString());
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /**
                         * Handles the click on the 'YES' button in the dialog.
                         * Adds the pizza to the order, resets input fields.
                         *
                         * @param dialog the dialog that received the click
                         * @param which the button that was clicked
                         */
                        public void onClick(DialogInterface dialog, int which) {
                            pizzaProperties = new View[] {pizza_name, pizza_price,
                                    sauceBox, cheeseBox, quantity, specialtySpinner};
                            SpecialtyActivity.addPizzaToOrder(pizzaProperties);
                            Toast.makeText(itemView.getContext(),
                                    pizza_name.getText().toString() +
                                            " added.", Toast.LENGTH_LONG).show();
                            resetInputs();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /**
                         * Handles the click on the 'NO' button in the dialog.
                         * Displays a Toast message indicating that the pizza was not added.
                         *
                         * @param dialog the dialog that received the click
                         * @param which the button that was clicked
                         */
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    pizza_name.getText().toString() + " not added.",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }

        /**
         * Resets the inputs in the ViewHolder to their default state.
         * Clears the selections for extra sauce, extra cheese, and quantity.
         */
        private void resetInputs(){
            sauceBox.setSelected(false);
            cheeseBox.setSelected(false);
            quantity.setText("");
        }

        /**
         * Checks if the quantity entered is valid.
         * Displays a toast message if the quantity is invalid.
         *
         * @return A boolean indicating whether the quantity is valid.
         */
        private boolean checkQuantity(){
            Toast quantityNotify = new Toast(context);
            if(quantity.getText().toString().isEmpty()){
                quantityNotify.setText("Please enter a quantity");
                quantityNotify.show();
                return false;
            }
            if(Integer.parseInt(quantity.getText().toString()) <= 0){
                quantityNotify.setText("Quantity must be greater than 0");
                quantityNotify.show();
                return false;
            }
            return true;
        }
    }
}

