package com.vendingMachine.dao;
import com.vendingMachine.dto.Items;
import org.springframework.stereotype.Component;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**This class will be handling all of the interactions that the user will be choosing from in the com.vendingMachine.Controller.VendingController class
 * which is the UI class, this handles all of the controls that may happen within the system.*/
@Component
public class ItemController implements ItemInterface {

    ArrayList<String> AuditFile = new ArrayList<String>(); // create arraylist to store audit time

    /**
     * THIS FUNCTION WILL BE USED to create an Item which will be added to the array list of item objects. Each item holds the name, price and stock
     *
     *
     */
    @Override
    public Items createItem(String Name, Double Price, int Stock) {
        Items item = new Items(Name, Price, Stock); //Create a new item by instantiating it.
        Items.ItemList.add(item); // add the item to the arraylist once it has been created
        return item;
    }//end method

    /**This method will read in the stock file each time it is called, it will read through each line in the file until it is blank
     * then it will split and remove any whitespaces as well as split it by comma, then it will read each column of each line and assign it to
     * an item name, price and stock which can then be used to create the items that were read from the file accordingly.
     * This is mainly data marshalling and file I/O handling.
     * */
    @Override
    public void readItems() {
        String filename = "Stock.txt";
        ItemController Controller = new ItemController();
        String[] linesRead;
        File readFile = new File(filename);
        try {
            //Read each line  from the  file
            FileReader fr = new FileReader(readFile);
            BufferedReader br = new BufferedReader(fr);
            String fileResult = " ";
            do {
                fileResult = br.readLine();

                if (fileResult != null) {
                    //Split
                    linesRead = fileResult.replaceAll("\s+", "").split(",");  // delimeter to split and remove whitespaces simply
                    String itemName = linesRead[0];
                    String itemPrice = linesRead[1];
                    String itemStock = linesRead[2];
                    double Price = Double.parseDouble(itemPrice);
                    int Stock = Integer.parseInt(itemStock);
                    //creates a new item from the lines that were read.
                    Controller.createItem(itemName, Price, Stock);
                }//end if
            }//end do
            //reading from file until its empty, do -> parse each line into variables that will create an item.
            while (fileResult != null);
            //closes the writers.
            br.close();
            fr.close();
        }//end try
        catch (IOException e) {
            e.printStackTrace();
        }
        //FOR THE AUDIT LOG
        LocalDateTime time = LocalDateTime.now();
        String timeRead = "READ: UPDATED STOCK FILE AT: " + time.toString();
        System.out.println("Item Stock file has been read");
        AuditFile.add(timeRead);
        System.out.println(timeRead);


    }//end method


    /**THIS METHOD WRITES TO THE FILE ALL OF THE CONTENTS OF THE ITEM ARRAYLIST BY ITERATING THROUGH IT AND ADDING A STRING CONTAINING THE
     * ITEM INFORMATION UNTIL THE WHOLE ARRAYLIST HAS BEEN ADDED.*/
    @Override
    public void writeItems() {
        File filename = new File("Stock.txt");
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            //GOES THROUGH ARRAYLIST AND CREATES A STRING THAT CONTAINS THE ITEM NAME, PRICE AND STOCK AND WRITES IT TO THE FILE.
            for (int i = 0; i < Items.ItemList.size(); i++) {
                String items = Items.ItemList.get(i).getName() + ", " + Items.ItemList.get(i).getPrice() + ", " + Items.ItemList.get(i).getStock() + "\n";
                bw.write(items);
            }//end for
            //closes the writers.
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Item Stock file has been updated.");
        //CALCULATE TIME THIS FUNCTION HAPPENED AND UPDATE AUDIT LOG FILE
        LocalDateTime time = LocalDateTime.now();
        String timeWrite = "WRITE: UPDATED STOCK FILE AT: " + time.toString();
        AuditFile.add(timeWrite);
        System.out.printf(timeWrite);

    }


    //FOR PRINTING THE TIME THE READ AND WRITE HAPPENED : AUDIT DAO OF EVENTS
    @Override
    public void auditLog() {
        File filename = new File("AuditLog.txt");

        //ITERATES THROUGH THE AUDIT FILE AND ADDS EACH ACTION THAT HAS HAPPENED WITH THE DATE/TIME.
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < AuditFile.size(); i++) {
                int action = i + 1;
                //WRITES THIS TO THE FILE.
                bw.write("Action: '" + action + "': " + String.valueOf(AuditFile.get(i)) + "\n");
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //INFORMS US THAT THE FILE HAS BEEN UPDATED AT THE END.
        System.out.println("\nAUDIT LOG has been updated.");

    }

    /**A PREVIOUS IMPLEMENTATION OF DISPLAYING ALL OF THE ITEMS IN ARRAYLIST WHICH WAS SWAPPED FOR THE LAMBDA FUNCTION  */
    public String displayLoop(){
        String result = " ";
        for (int i = 0; i < Items.ItemList.size(); i++) {
           //if item is not of out of stock then display it.
           if(Items.ItemList.get(i).getStock() > 0) { //  use a lambda for this.
               double itemPrice = Items.ItemList.get(i).getPrice();
               String itemName = Items.ItemList.get(i).getName();
               double price = itemPrice / 100;System.out.println("Item: "+itemName + ", Price:"+price);
             System.out.println("-------------------------------------");
              result = ("Item: "+itemName + ", Price:"+price + "\n" + "-------------------------------------");
            }
       }
        return result;
    }//end method

    /**USED FOR TESTING PURPOSES TO DISPLAY THE SIMPLE CHANGE AT THE END  */
    public double changeGiven(double itemPrice, double userAmount) {

        return  userAmount- itemPrice;
    }



}//end class




