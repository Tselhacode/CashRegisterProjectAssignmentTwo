package com.example.cashregisterproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cashregisterproject.model.HistoryProduct;
import com.example.cashregisterproject.R;
import com.example.cashregisterproject.model.InventoryClass;
import com.example.cashregisterproject.model.ItemClass;
import com.example.cashregisterproject.model.ItemsAdapter;

import java.util.ArrayList;
import java.util.Date;


//java class for main activity, base adapter, product class, manager class
//one for row, one for main activity
//all the logic - checking quantity, initializing the stock, updating quantity when user purchase something
//activity only for layout

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declaration
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button num0;
    Button C;
    Button buy;
    Button Manager;

    //label fields to update
    public static TextView typeLabel;
    public static TextView quantityLabel;
    public static TextView totalLabel;

    //ListView and Products in the ListView and index of the Products in the ListView
    ListView inventoryListView;
    TextView productName;
    public static TextView productQntyTextView;
    public static TextView productPriceTextView;
    int selectedIndex;

    //ArrayList
    ArrayList<ItemClass> listOfInventory;
    //in this arraylist, parameters will be name,qnty,total,date
    ArrayList<HistoryProduct> purchaseHistory = new ArrayList<HistoryProduct>();

    InventoryClass inventoryObj = new InventoryClass();
    ItemsAdapter adapterObj;

    //connection
    @Override
    public void onCreate(Bundle savedInstanceState) { //oncreate starts the Activity , //bundle savedinstancestate lets activity save the previous state
        super.onCreate(savedInstanceState); //super is the call the parent class constructor
        setContentView(R.layout.activity_main); //this activity is connected to activity_main.xml, set the xml
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num0 = findViewById(R.id.num0);
        C = findViewById(R.id.C);
        buy = findViewById(R.id.buy);
        typeLabel = findViewById(R.id.typeLabel);
        totalLabel = findViewById(R.id.totalLabel);
        quantityLabel = findViewById(R.id.quantityLabel);
        productName = findViewById(R.id.productNameTextView);
        productQntyTextView = findViewById(R.id.productQntyTextView);
        productPriceTextView = findViewById(R.id.productPriceTextView);
        Manager = findViewById(R.id.manager);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);
        C.setOnClickListener(this);
        buy.setOnClickListener(this);

        inventoryListView = findViewById(R.id.inventoryListView);
        //initialize model classes and create objects
        listOfInventory = inventoryObj.getS();

        //initialize the baseadapter using its custom constructor
        ItemsAdapter adapterObj = new ItemsAdapter(listOfInventory, this);
        inventoryListView.setAdapter(adapterObj);//displays items in the listView
        //when items in the listView is clicked
        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                String selectedFromList = ((TextView) view.findViewById(R.id.productNameTextView)).getText().toString(); //updates the typeLabel
                typeLabel.setText(selectedFromList);
            }
        });
    }

    String description;
    String itemName;
    Double roundedTotal;
    int actualQuantity;
    ArrayList<HistoryProduct> sendingMessage;
    //HistoryProduct historyObj = new HistoryProduct(name, itemQnty, totalPrice, (new Date().toString()));


    @Override
    public void onClick(View v) { //onclick refers to the view that was clicked - can be a button, textview,imageview

        if (v != Manager) {
            if (v == C) {
                quantityLabel.setText("Quantity");
                totalLabel.setText("Total");
                typeLabel.setText("Product Type");
            } else if (v == buy) {
                if ((quantityLabel.getText().toString().equals("Quantity")) || (totalLabel.getText().toString().equals("Total")) || (typeLabel.getText().toString().equals("Product Type"))) {
                    Toast.makeText(MainActivity.this, "All fields are Required!!", Toast.LENGTH_SHORT).show();
                } else {
                    String name = typeLabel.getText().toString();
                    Double totalPrice = Double.parseDouble(totalLabel.getText().toString());
                    int itemQnty = Integer.parseInt(quantityLabel.getText().toString());
                    purchaseHistory.add(new HistoryProduct(name, itemQnty, totalPrice, (new Date().toString())));
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    // set title
                    alertDialogBuilder.setTitle("Thank You for your purchase");
                    // set dialog message
                    description = String.format("Your purchase is %d %s for %f", actualQuantity, itemName, roundedTotal);
                    alertDialogBuilder.setMessage(description).setCancelable(false);
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();
                    //                 Hide after some seconds
                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (alertDialog.isShowing()) {
                                alertDialog.dismiss();
                            }
                        }
                    };
                    alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            handler.removeCallbacks(runnable);
                        }
                    });
                    handler.postDelayed(runnable, 2500);

                }
                //need to pass two things
                //purchaseHistory arraylist through history button
                //productList through restock button
                //Updating historyList Array
                //purchaseHistory.add(new HistoryProduct(itemName,actualQuantity,roundedTotal,new Date().toString()));
            } else {
                String quantityString = inventoryObj.push(((Button) v).getText().toString()); //concatenated qnty order
                actualQuantity = Integer.parseInt(quantityString); //qnty order in integer
                if (inventoryObj.instockQnty(selectedIndex, actualQuantity)) {
                    int newListViewQnty = inventoryObj.updateListViewQuantity(selectedIndex, actualQuantity);
                    //                adapterObj.productQnty.setText(Integer.toString(newListViewQnty));
                    quantityLabel.setText(Integer.toString(actualQuantity));
                    Double total = Double.parseDouble(String.valueOf(actualQuantity)) * inventoryObj.getS().get(selectedIndex).getPrice();
                    roundedTotal = Math.round(total * 100.0) / 100.0;
                    totalLabel.setText(Double.toString(roundedTotal));
                    itemName = inventoryObj.getS().get(selectedIndex).getName();
                } else {
                    Toast.makeText(MainActivity.this, "Sorry, your order exceeds the stock!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            String name = typeLabel.getText().toString();
            Double totalPrice = Double.parseDouble(totalLabel.getText().toString());
            int itemQnty = Integer.parseInt(quantityLabel.getText().toString());
            //purchaseHistory.add(new HistoryProduct(name, itemQnty, totalPrice, (new Date().toString())));


            //create an object for intent
            //parameters - source activity and the destination activity (add .class)
            Intent intent = new Intent(this, ManagerPanelActivity.class); //this refers to Main
            //send a message
            Bundle bundle = new Bundle();
            //send the arrayList
            bundle.putParcelableArrayList("purchase history", purchaseHistory);
            //parameters - id, message
            intent.putExtras(bundle);

            startActivity(intent);


        }

    }
    }



//click buy, update history list
//MainActivity - declare a new arraylist of history product, under the buy button click listener {start buying,use new arraylist
//of historyproduct - Historyproduct product = new Historyproduct(name,qnty,price,date), update history list by
// historylist.add(product)}

//in the historyproduct model, name,qnty,price,date(includes time)-date is a string
//class historyproduct extends product {
        //string date;
        //historyproduct(name,qnty,price,date)
        //super() - remaining stuff in super
        //this.date = date;
   // }

    //click on manager button and move to next activity
    //managerbutton.onclicklistener(){
    //call the intent to navigate to manager activity
    //create a bundle object - bundle is a class
    //bundle bundle = new bundle().putparceleablearraylist("",historylist) - helps send multiple message - name, qnty, date, price
    //intent.putextra(bundle)
    //startactivity(intent)
    //when you click on manager button, you pass the history list to manager activity

    //in the manager activity
    //use get intent - this.getintent.getextras.parceleablearraylist("same id",
    //historyist = this.getInTENT.GETEXTRAS.parceleablearraylist() - store it in a new arraylist
    //historybutton.onclicklistner(){
    //call intent

        //historylist activity has your purchase details
    //in the history list activity, you have recyler view, and textview(by default invisible)
    //if your history list is emtpry, make textview visible ("no hisotry to show")
    //if history list has some value, call the recycler view
    //for recycler view, we need recycler adapter class - doesn't have its own clicklistener - so we have to call the onclick inside your adapter
//recycler adapater is different from listview (baseadapter), recycler adapter does not have override methods
    //need to use recycler viewholder - mandatory methods to implement - viewholderoncreate, bindview
    //viewholderoncreate - calling just the inflater - display the layout
    //bindview - get the lement from database and put it in a layout


//add myapp in manifest