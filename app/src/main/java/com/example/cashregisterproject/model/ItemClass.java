package com.example.cashregisterproject.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

public class ItemClass implements Parcelable {
    int qnty;
    String name;
    double price;

    //pant in a store in general will have a name, qnty, price
    public ItemClass(String name, int qnty, Double price){
        this.qnty = qnty;
        this.name = name;
        this.price = price;
    }

    protected ItemClass(Parcel in) {
        qnty = in.readInt();
        name = in.readString();
        price = in.readDouble();
    }

    public static final Creator<ItemClass> CREATOR = new Creator<ItemClass>() {
        @Override
        public ItemClass createFromParcel(Parcel in) {
            return new ItemClass(in);
        }

        @Override
        public ItemClass[] newArray(int size) {
            return new ItemClass[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(qnty);
        dest.writeString(name);
        dest.writeDouble(price);
    }



    //Getters
    public int getQnty() {
        return qnty;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    //Setters
    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

