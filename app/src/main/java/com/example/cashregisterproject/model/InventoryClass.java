package com.example.cashregisterproject.model;

import java.util.ArrayList;

//storeManager Class
//a store will have different items like pants
//create objects of ItemClass to access these properties
public class InventoryClass {
    private ItemClass pants;
    private ItemClass shoes;
    private ItemClass hats;

    //initialize constructor
    public InventoryClass(){
        this.pants = new ItemClass("Pants",10,20.44);
        this.shoes = new ItemClass("Shoes",100,10.44);
        this.hats = new ItemClass("Hats",30,5.9);
        s.add(this.pants);
        s.add(this.shoes);
        s.add(this.hats);
    }

    //create an empty ArrayList
    ArrayList<ItemClass> s = new ArrayList();

    public ArrayList<ItemClass> getS() {
        return s;
    }

    public ItemClass getPants() {
        return pants;
    }

    public ItemClass getShoes() {
        return shoes;
    }

    public ItemClass getHats() {
        return hats;
    }

    String concat1 = "";
    Double result;
    //if I concat 1, 1 is added to concat string = "1"
    //if I concat 2, 2 is added to concat string = "12"
    public String push(String input) {

        this.concat1 += input;
        return this.concat1;
    }


    public Boolean instockQnty(int index,int qnty){
        int currentQnty = this.getS().get(index).getQnty();
        if (qnty < currentQnty){
            return true;
        }else{
            return false;
        }
    }



    public Integer updateListViewQuantity(int index, int qnty){
        int leftQnty = this.getS().get(index).getQnty() - qnty;
        return leftQnty;
    }

    public int updateQuantityLabel(int qnty){
        return qnty;
    }



    //check if there is enough stock
    //if there is, then it will update the quantity in the stock
    //it will also display the ordered quantity in the label

}
