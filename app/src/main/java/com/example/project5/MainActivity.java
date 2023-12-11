package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Main activity class for the pizza ordering application.
 * This class serves as the entry point and main menu for the application,
 * providing navigation to various features such as building your own pizza,
 * selecting specialty pizzas, viewing the current order, and viewing store orders.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Initializes the activity. Sets the content view to the main menu layout.
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down, this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Launches the Build Your Own (BYO) Pizza activity.
     * Triggered by clicking the BYO pizza option in the main menu.
     * @param view The view that was clicked to trigger this method.
     */
    public void displayBYOActivity(View view){
        Intent intent = new Intent(MainActivity.this, BYOActivity.class);
        startActivity(intent);
    }


    /**
     * Launches the Specialty Pizza activity.
     * Triggered by clicking the Specialty pizza option in the main menu.
     * @param view The view that was clicked to trigger this method.
     */
    public void displaySpecialtyActivity(View view){
        Intent intent = new Intent(MainActivity.this, SpecialtyActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the Current Order activity.
     * Triggered by clicking the Current Order option in the main menu.
     * @param view The view that was clicked to trigger this method.
     */
    public void displayCurrentOrderActivity(View view){
        Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Launches the Store Orders activity.
     * Triggered by clicking the Store Orders option in the main menu.
     * @param view The view that was clicked to trigger this method.
     */
    public void displayStoreOrdersActivity(View view){
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }


}