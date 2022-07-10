package com.vendingMachine;

import com.vendingMachine.Controller.VendingController;
import com.vendingMachine.dao.ItemController;
import com.vendingMachine.dao.ItemInterface;
import com.vendingMachine.service.ItemService;
import com.vendingMachine.ui.ItemView;
import com.vendingMachine.ui.UserIO;
import com.vendingMachine.ui.UserIOConsoleImpl;


public class App {
    public static void main(String[] args) {

        UserIO io = new UserIOConsoleImpl();
        ItemView view = new ItemView(io);

        ItemInterface dao = new ItemController();

        ItemService service = new ItemService(dao);

        VendingController controller = new VendingController(view,service);

        controller.runController();
    }



}
