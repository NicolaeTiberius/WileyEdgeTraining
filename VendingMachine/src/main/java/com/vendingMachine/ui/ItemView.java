package com.vendingMachine.ui;
import com.vendingMachine.InsufficientFundsException;
import com.vendingMachine.NoItemInventoryException;
import com.vendingMachine.dto.Change;
import com.vendingMachine.dto.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Stream;

@Component
public class ItemView {

    @Autowired
    private UserIO io;
    ArrayList<String> AuditFile = new ArrayList<String>(); // create arraylist to store audit time

    public ItemView(UserIO io){this.io = io;}

    public int displayMainMenu(){
        io.print("");
        io.print("Vending Machine Menu");
        io.print("1. Purchase something.");
        io.print("0.Quit the application.");
        return io.readInt("Please select an option",0,1);
    }

    /**THIS METHOD displays all of the items in the arraylist that have a stock of over 0
     *  so that they can be chosen for purchase by the user afterwards.
     *  It uses a LAMBDA STREAM function that filters out all of the item objects that have a stock count of over 0 and prints them all
     *  in a presentable string */
    public void displayVendingItems(){
        System.out.println("\nCURRENT ITEMS IN THE VENDING MACHINE: ");
        Stream<Items> lambda = Items.ItemList.stream().filter(p -> p.getStock() > 0);
        lambda.forEach(items -> System.out.println("-------------------------------------\n" + "Item: " + items.getName() + ", Price: £" + items.getPrice()));
    }

    /**THIS METHOD WILL BE TAKING IN THE NAME OF THE PRODUCT AND THE AMOUNT THE USER HAS INPUTTED TO PURCHASE IT.
     Then a try catch method will be run for exception handling if they select an item that is out of stock or don't
     have enough money to purchase it.

     Afterwards a loop will iterate over the entire item arraylist and check if the product name exists,
     then a getter is used to retrieve the price of the product so it can be used to calculate the change

     There is a lot of checking over in this function;

     1.If the user has enough money to pay but the item is not in stock then an exception will be provoked and caught later that will
     let the user know that the item is out of stock and cannot be purchased.

     2.If the user hasn't got enough money to pay for the item then another exception will be thrown that will let the user know
     that they have insufficient funds and their amount is returned so they can start again.

     3.If the user has enough money and the item is not out of stock then it will convert their amount to bigdecimal first to carry out the
     com.vendingMachine.dto.Change Function which will calculate how many COINS are needed to be returned back to the user from the remainder that they have left.
     The remainder will simply be calculated by substracting the itemPrice from userAmount and that amount will be used in the change function
     to return the correct amount of number.

     */
    public void selectProduct(String name,double userAmount){
       //goes through array list

       try {
           for (int i = 0; i < Items.ItemList.size(); i++) {
               //if it matches name and is a correct item
               if (Items.ItemList.get(i).getName().equals(name)) {
                   System.out.println(name + " has been selected.");


                   //sets the price so it can be compared
                   double itemPrice = Items.ItemList.get(i).getPrice();
                   //if user has enough money but item out of stock.
                   if (userAmount >= itemPrice) {
                       //if the item is out of stock then they cannot select it.
                       if (Items.ItemList.get(i).getStock() == 0) {
                           throw new NoItemInventoryException(); // Provoke and catch is defined below
                           //DO EXCEPTION HERE
                       }
                       // if item exists and is in stock.
                       else {
                           // removes 1 stock of that item from the total stock.
                           Items.ItemList.get(i).setStock(Items.ItemList.get(i).getStock() - 1);
                           //getting the remainder after they selected item and returning change.
                           userAmount = userAmount - itemPrice;
                           //CONVERTS TO BIG DECIMAL TO USE FOR CHANGE
                           BigDecimal userItem = new BigDecimal(userAmount);
                           //RETURNS THE CHANGE
                           Change newChange = new Change(userItem);

                       }//end else
                   }//end if user has amount.

                   //if item exists and  user does not have enough money.
                   else if (itemPrice > userAmount) {
                       throw new InsufficientFundsException(); // Provokes and catch is below.
                   }//end else if
               }//end big if
           }//end for loop
       }//end try

       //CATCHES THE EXCEPTION IF USER DOES NOT HAVE ENOUGH MONEY.
       catch (InsufficientFundsException e) {
           System.out.println("\nInsufficient Funds");
           System.out.println("Your current amount has been returned: " + userAmount+"\n");
       }//end catch

       //CATCHES THE EXCEPTION AND DISPLAYS TO USER IF THE ITEM IS OUT OF STOCK.
       catch (NoItemInventoryException e) {
           System.out.println("Item is out of stock. Please select another item");

       }
   }//end method

}//end class
