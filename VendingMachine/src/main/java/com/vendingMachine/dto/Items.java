package com.vendingMachine.dto;



import java.util.ArrayList;

/**This class will be used to create vending machine items that will be displayed in the Arraylist
 * ,instantiated in the main ui class
 * which will then be used for when the user would like to purchase items */

public class Items {

    public static ArrayList<Items>  ItemList = new ArrayList<>();
    private String Name;
    private double Price;
    private int Stock;


    //CONSTRUCTOR
    public Items(String name, double price, int stock) {
        Name = name;
        Price = price;
        Stock = stock;
    }


    //GETTERS AND SETTERS
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {

        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    @Override
    public String toString() {
        return "com.vendingMachine.dto.Items{" +
                "Item Name: '" + Name + '\'' +
                ", Price: " + Price +
                ", Stock:" + Stock +
                '}';
    }
}
