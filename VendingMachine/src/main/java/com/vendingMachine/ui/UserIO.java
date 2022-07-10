package com.vendingMachine.ui;

public interface UserIO {
    void print(String message);

    int readInt(String prompt, int min, int max);

}
