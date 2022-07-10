package com.vendingMachine.service;
import com.vendingMachine.dao.ItemInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemService {

    private ItemInterface dao;

    @Autowired
    public ItemService(ItemInterface dao){this.dao=dao;}

    public void readItems(){dao.readItems();}

    public void auditLog(){dao.auditLog();}

    public void writeItems(){dao.writeItems();}



}
