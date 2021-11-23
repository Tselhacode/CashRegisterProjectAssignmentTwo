package com.example.cashregisterproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cashregisterproject.R;
import com.example.cashregisterproject.model.HistoryProduct;
import com.example.cashregisterproject.model.ItemClass;

import java.util.ArrayList;

public class HistoryDetailActivity extends AppCompatActivity {
    ArrayList<HistoryProduct> recievedDetails = new ArrayList<HistoryProduct>();
    TextView detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        detail = findViewById(R.id.detail);
        Bundle bundle = getIntent().getExtras();
        //get the data when you click on history
        //create a variable to store the message
//        recievedPurchaseList = this.getIntent().getExtras().getParcelableArrayList("list of purchase");
        recievedDetails = bundle.getParcelableArrayList("received_purchase_details");

        String name = recievedDetails.get(0).getName();
        Double total = recievedDetails.get(0).getPrice();
        int qnty = recievedDetails.get(0).getQnty();
        String date = recievedDetails.get(0).getDate();

        detail.setText("Product" + name + "\n Price:" + total + "\n Purchase Date: " + date);
    }
}