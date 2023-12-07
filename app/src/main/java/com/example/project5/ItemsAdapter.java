package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

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
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ItemsHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new ItemsHolder(view, context);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder( ItemsHolder holder, int position) {
        //assign values for each row
        holder.pizza_name.setText(items.get(position).getItemName());
        holder.pizza_price.setText(items.get(position).getUnitPrice());
        holder.im_item.setImageResource(items.get(position).getImage());
        holder.toppingsDisplay.setText(items.get(position).toppingsToString());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView pizza_name, pizza_price, toppingsDisplay;
        private CheckBox sauceBox, cheeseBox;
        private ImageView im_item;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout
        private Spinner specialtySpinner;
        private EditText quantity;
        private Context context;
        View[] pizzaProperties;

        public ItemsHolder( View itemView, Context context) {
            super(itemView);
            this.context=context;
            pizza_name = itemView.findViewById(R.id.pizza_type);
            pizza_price = itemView.findViewById(R.id.pizza_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            toppingsDisplay = itemView.findViewById(R.id.toppingsDisplay);
            sauceBox = itemView.findViewById(R.id.sauceBox);
            cheeseBox = itemView.findViewById(R.id.cheeseBox);
            quantity = itemView.findViewById(R.id.quantityInput);

            populateSpinner(itemView);
            setAddButtonOnClick(itemView); //register the onClicklistener for the button on each row.

        }

        private void populateSpinner(View itemView){
            specialtySpinner = itemView.findViewById(R.id.specialtySizeSpinner);
            String[] sizes = new String[]{"Small", "Medium", "Large"};
            ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, sizes);
            sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            specialtySpinner.setAdapter(sizeAdapter);

        }
        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView
         */
        private void setAddButtonOnClick( View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    if(!checkQuantity()){
                        return;
                    }
                    alert.setTitle("Do you want to place order?");
                    alert.setMessage(pizza_name.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            pizzaProperties = new View[] {pizza_name, pizza_price,sauceBox,cheeseBox, quantity, specialtySpinner};
                            SpecialtyActivity.addPizzaToOrder(pizzaProperties);
                            Toast.makeText(itemView.getContext(),
                                    pizza_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                            resetInputs();
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    pizza_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }

        private void resetInputs(){
            sauceBox.setSelected(false);
            cheeseBox.setSelected(false);
            quantity.setText("");
        }
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

