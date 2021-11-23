package com.example.cashregisterproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cashregisterproject.model.HistoryProduct;
import com.example.cashregisterproject.R;
import com.example.cashregisterproject.model.HistoryListViewAdapter;
import com.example.cashregisterproject.model.ItemClass;

import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity implements HistoryListViewAdapter.listClickListener{
    RecyclerView recyclerView;
    ArrayList<HistoryProduct> recievedTransaction;

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = (RecyclerView) findViewById( R.id.recycleListView);

        Bundle bundle = getIntent().getExtras();
        recievedTransaction = bundle.getParcelableArrayList("received_purchase_history");
        HistoryListViewAdapter historyListAdapter = new HistoryListViewAdapter(this,recievedTransaction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(historyListAdapter);
    }


    @Override
    public void onTransactionSelected(ItemClass selectedPurchase) {
        Intent toHistoryDetailsActivity = new Intent(this, HistoryListActivity.class); //this refers to Main
        //send a message
        Bundle bundle = new Bundle();
        //send the arrayList
        bundle.putParcelableArrayList("received_purchase_details",recievedTransaction);
        //parameters - id, message
        toHistoryDetailsActivity.putExtras(bundle);
//            Bundle bundle = new Bundle();
//            //bundle.putParcelable("list of purchase",purchaseHistory);
//            toManagerActivity.putExtras(bundle);
        startActivity(toHistoryDetailsActivity);
    }
}