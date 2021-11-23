package com.example.cashregisterproject.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashregisterproject.R;
import com.example.cashregisterproject.model.ItemClass;

import java.util.ArrayList;

public class ItemsAdapter extends BaseAdapter {

    ArrayList<ItemClass> inventory; //data for the listView
    Context myContext;
    CharSequence text = "Out of Stock!";
    int duration = Toast.LENGTH_SHORT;
    public TextView productQnty;


    public ItemsAdapter(ArrayList<ItemClass> list, Context activity_context){
        this.inventory = list;
        this.myContext = activity_context;
    }
    @Override
    public int getCount() {
        return this.inventory.size();
    }

    @Override
    public Object getItem(int position) {
        return inventory.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    //creating a listview
    @Override
    public View getView(int position, View view, ViewGroup parent) { //insert the data to the listView
        if (view == null) {
            view = LayoutInflater.from(myContext).inflate(R.layout.activity_list_view, parent, false);
            //need this layoutinflator to draw the custom row in the mainactivity
            //add one xml to another - add activity_list_view.xml to MainActivity
            //myContext here is a placeholder which will be taken by the MainActivity
        }
        ItemClass currentProduct = (ItemClass) getItem(position);
        TextView productName = (TextView) view.findViewById(R.id.productNameTextView);
        productQnty = (TextView) view.findViewById(R.id.productQntyTextView);
        TextView productPrice = (TextView) view.findViewById(R.id.productPriceTextView);

        productName.setText(currentProduct.getName());
        productQnty.setText(Integer.toString(currentProduct.getQnty()));
        productPrice.setText(Double.toString(currentProduct.getPrice()));
        return view;
    }

}
