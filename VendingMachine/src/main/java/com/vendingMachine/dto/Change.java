package com.vendingMachine.dto;



import java.math.BigDecimal;

/**Use this class to return back the change after the user has bought an item. */

public class Change {


    //These arrays will be used to display the correct coin with the amount of coin in the remainingChange function
    static int[] ChangeArray;
    static Coins coinArray[] = Coins.values();
    public Change(BigDecimal amount) {
        //Converts the amount to an integer
        remainingChange(amount.multiply(new BigDecimal(100)).intValue());
    }

    //Used to calculate the change by checking if the amount is bigger than a certain coin
    //then if it is : add a coin to the counter
    // and take the coin value away from the amount the user entered
    void remainingChange(double amount) {
        ChangeArray = new int[7];

        double currentAmount =  amount;
        //dividing to see how many can fit first,
        // then calculating the remainder of that result and setting the amount to result
        //in order to see how many of each coin can fit in the amount
        //and remainder to use on next coin.
            ChangeArray[0] = (int) (currentAmount / Coins.Pound.getValue());

            currentAmount =  currentAmount % Coins.Pound.getValue();

            ChangeArray[1] = (int) (currentAmount / Coins.Fifty.getValue());
            currentAmount =  currentAmount % Coins.Fifty.getValue();

            ChangeArray[2] = (int) (currentAmount / Coins.Twenty.getValue());

            currentAmount =  currentAmount % Coins.Twenty.getValue();

            ChangeArray[3] = (int) (currentAmount / Coins.Ten.getValue());
            currentAmount = currentAmount % Coins.Ten.getValue();

            ChangeArray[4] = (int) (currentAmount / Coins.Five.getValue());
            currentAmount =  currentAmount % Coins.Five.getValue();

            ChangeArray[5] = (int) (currentAmount / Coins.Two.getValue());
            currentAmount =  currentAmount % Coins.Two.getValue();

            ChangeArray[6]  = (int)( currentAmount / Coins.One.getValue());
            currentAmount =  currentAmount % Coins.One.getValue();



        Double newAmount = amount /  100;

        //PRINTS OUT ALL OF THE CHANGE BY ITERATING THROUGH THE COIN AND CHANGE ARRAY TO DISPLAY AMOUNT OF EACH COIN
        //BACK TO THE USER
        //IT WILL ONLY RETURN COINS THAT HAVE BEEN USED MORE THAN ONCE.
        System.out.println("Your total change: "+ (newAmount));
        for (int i = 0; i < ChangeArray.length; i++) {
            if(ChangeArray[i] !=0 && ChangeArray[i]>=1) {
                    ChangeArray[i] = ChangeArray[i];
                    System.out.println(coinArray[i] + ": " + ChangeArray[i]);
                }//end if

        }//end loop


    }//end function


}//end class


