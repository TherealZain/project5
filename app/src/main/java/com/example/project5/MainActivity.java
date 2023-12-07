package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void displayBYOActivity(View view){
        Intent intent = new Intent(MainActivity.this, BYOActivity.class);
        startActivity(intent);
    }

    public void displaySpecialtyActivity(View view){
        Intent intent = new Intent(MainActivity.this, SpecialtyActivity.class);
        startActivity(intent);
    }

    public void displayCurrentOrderActivity(View view){
        Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    public void displayStoreOrdersActivity(View view){
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }


}