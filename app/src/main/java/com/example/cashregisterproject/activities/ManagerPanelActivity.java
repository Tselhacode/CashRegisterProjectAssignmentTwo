package com.example.cashregisterproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cashregisterproject.R;
import com.example.cashregisterproject.model.HistoryProduct;

import java.util.ArrayList;


//how to populate the items - arraylist of items
//calculate the total
//check availability
//update quantity by refresh the adapter
//prepare for the history list -

public class ManagerPanelActivity extends AppCompatActivity {

    //    private ArrayList<Parcelable> recievedPurchaseList = new ArrayList<>();
    Button historyButton;
    Button restockButton;
    ArrayList<HistoryProduct> recievedHistory = new ArrayList<HistoryProduct>();

    //create an Intent object outside of onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);

        //create the connection
        historyButton = findViewById(R.id.historyButton);
        Bundle bundle = getIntent().getExtras();
        //get the data when you click on history
        //create a variable to store the message
//        recievedPurchaseList = this.getIntent().getExtras().getParcelableArrayList("list of purchase");
        recievedHistory = bundle.getParcelableArrayList("purchase history");

    }

    public void historyClicked(View view) {
        Intent toHistoryActivity = new Intent(this, HistoryListActivity.class); //this refers to Main
        //send a message
        Bundle bundle = new Bundle();
        //send the arrayList
        bundle.putParcelableArrayList("received_purchase_history",recievedHistory);
        //parameters - id, message
        toHistoryActivity.putExtras(bundle);
//            Bundle bundle = new Bundle();
//            //bundle.putParcelable("list of purchase",purchaseHistory);
//            toManagerActivity.putExtras(bundle);
        startActivity(toHistoryActivity);
    }

    public void restockClicked(View view) {
        Intent toHistoryActivity = new Intent(this, HistoryListActivity.class); //this refers to Main
        //send a message
        Bundle bundle = new Bundle();
        //send the arrayList
        bundle.putParcelableArrayList("received_purchase_history",recievedHistory);
        //parameters - id, message
        toHistoryActivity.putExtras(bundle);
//            Bundle bundle = new Bundle();
//            //bundle.putParcelable("list of purchase",purchaseHistory);
//            toManagerActivity.putExtras(bundle);
        startActivity(toHistoryActivity);
    }
}

//in the manager activity
//use get intent - this.getintent.getextras.parceleablearraylist("same id",
//historyist = this.getInTENT.GETEXTRAS.parceleablearraylist() - store it in a new arraylist
//historybutton.onclicklistner(){
