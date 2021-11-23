package com.example.cashregisterproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.cashregisterproject.R;
import com.example.cashregisterproject.model.ItemClass;
import com.example.cashregisterproject.model.ItemsAdapter;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {
    ArrayList<ItemClass> listOfInventory;
    ListView inventoryListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        inventoryListView = findViewById(R.id.inventoryListView);

        ItemsAdapter adapterObj = new ItemsAdapter(listOfInventory, this);
        inventoryListView.setAdapter(adapterObj);
    }
}