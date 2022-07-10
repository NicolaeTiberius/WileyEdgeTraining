package com.vendingMachine.dao;

import com.vendingMachine.dto.Items;

public interface ItemInterface {

/**
 * Service layer that will be implemented to carry out the business logic of the program.
 *
 * @return
 */

    public Items createItem(String Name, Double  Price, int Stock);
    public void readItems();

    public void writeItems();

    public void auditLog();

}
