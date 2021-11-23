package com.example.cashregisterproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cashregisterproject.model.ItemClass;

public class HistoryProduct extends ItemClass implements Parcelable {
    String date;

    public HistoryProduct(String name, int qnty, Double price, String date) {
        super(name, qnty, price);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    protected HistoryProduct(Parcel in) {
        super(in);
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

}

