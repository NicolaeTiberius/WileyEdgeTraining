package com.vendingMachine.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class UserIOConsoleImpl implements UserIO {
    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            print(prompt);
            num = Integer.parseInt(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

}
