package com.vendingMachine.dto;



/**THIS IS THE ENUM OF COINS THAT ARE CONSTANT VALUES THAT WILL BE USED TO RETURN BACK THE CHANGE TO THE USER. */

public enum Coins {



    Pound(100),Fifty(50),Twenty(20),Ten(10),Five(5),Two(2),One(1);
    private double value;

    Coins(double i) {
        this.value = i;
    }

    public double getValue(){
        return value;
    }

    public int Add(int i ,int x){
        int result = i + x;
        return  result;
    }
}
