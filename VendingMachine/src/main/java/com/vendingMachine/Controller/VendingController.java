package com.vendingMachine.Controller;

import com.vendingMachine.dao.ItemController;
import com.vendingMachine.service.ItemService;
import com.vendingMachine.ui.ItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/** MAIN CLASS OR THE MODEL VIEW FOR THE USER WHERE THEY WILL INTERACT WITH THE SYSTEM AND IT WILL COMMUNICATE WITH THE CONTROLLER TO
 * GIVE BACK THE DESIRED OUTPUT
 */
@Component
public class VendingController {

        private ItemView view;

        private ItemService service;

        @Autowired
        public VendingController(ItemView view, ItemService service){
            this.view = view;
            this.service = service;
        }

        public void runController(){
            ItemController menu = new ItemController(); // to use controller functionality.
            //READ THE ITEMS IN THE STOCK FILE
            service.readItems();
            //CALCULATE TIME THIS OCCURRED
            //while loop will run
            while(true) {
                //display the vending machine items

               Scanner userOption = new Scanner(System.in);
               view.displayVendingItems();
                int choice = view.displayMainMenu();

                if (choice == 0) {
                    System.out.println("Thanks for vending! See you soon");
                    break;
                }
                //Insert amount of money into the vending machine
                else if (choice == 1) {
                    System.out.println("\nEnter amount to insert into vending machine: ");
                    double userAmount = userOption.nextDouble();
                    System.out.println("You have inserted: " + userAmount);

                    //Choose an item.
                    System.out.println("Enter 1 to choose an item or 0 start again.");
                    double userMenu = userOption.nextDouble();
                    //Start the loop again if they would like to start again.
                    if (userMenu == 0) {
                        System.out.println("Amount refunded.");
                        System.out.println("Thanks for vending. See you soon.");
                    }
                    //Let user select an item.
                    else if (userMenu == 1) {
                        view.displayVendingItems();
                        System.out.println("\nSelect an Item from the machine by entering name:");
                        String userItem = userOption.next();
                        view.selectProduct(userItem,userAmount);

                        //calculate execution time during the write method and add that to audit log
                        service.writeItems();
                        service.auditLog();

                    }//end else if

                }//end else if
                else {
                    //IF USER DOES NOT ENTER THE CORRECT INPUT.
                    System.out.println("--------PLEASE INPUT only 1 or 0---------");
                    System.out.println("---------BEGINNING OF PROGRAM----");
                }
        }

        }//end main loop
    }//end main class



