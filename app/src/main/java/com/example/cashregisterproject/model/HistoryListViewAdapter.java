package com.example.cashregisterproject.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashregisterproject.R;

import java.util.ArrayList;

public class HistoryListViewAdapter extends RecyclerView.Adapter<HistoryListViewAdapter.viewHolder> {
    Context context;
    ArrayList<HistoryProduct> purchaseHistoryList = new ArrayList<HistoryProduct>(0);

    public listClickListener listener;

    public interface listClickListener{
        void onTransactionSelected(ItemClass selectedPurchase);
    }



    public static class viewHolder extends RecyclerView.ViewHolder{
        //declare the variables for the layouts
        private final TextView prodName;
        private final TextView prodTotal;
        private final TextView prodQnty;
        //constructor of the viewholder
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            prodName = itemView.findViewById(R.id.prodNameTextView);
            prodTotal= itemView.findViewById(R.id.prodTotalTextView);
            prodQnty = itemView.findViewById(R.id.productQntyTextView);
        }

        public TextView getProdNameTextView() {
            return prodName;
        }

        public TextView getProdTotalTextView() {
            return prodTotal;
        }

        public TextView getProdQntyTextView() {
            return prodQnty;
        }
    }

    public HistoryListViewAdapter(Context c, ArrayList<HistoryProduct> transactionList){
        context = c;
        purchaseHistoryList = transactionList;






    }

    @NonNull
    //inflate the layout
    @Override
    public HistoryListViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.history_list_view,parent,false);

        return new HistoryListViewAdapter.viewHolder(view);
    }
    ItemClass obj;
    //setting text to the textview
    @Override
    public void onBindViewHolder(@NonNull HistoryListViewAdapter.viewHolder holder, int position) {

        obj = purchaseHistoryList.get(position);
        holder.getProdNameTextView().setText(obj.getName());
        holder.getProdQntyTextView().setText(String.valueOf(obj.getQnty()));
        holder.getProdTotalTextView().setText(String.valueOf(obj.getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTransactionSelected(purchaseHistoryList.get(position));
            }
        });
    }

    //the size of the arraylist
    @Override
    public int getItemCount() {
        return purchaseHistoryList == null ? 0 : purchaseHistoryList.size();

    }
}


