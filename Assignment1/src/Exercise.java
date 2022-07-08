import java.util.Arrays;

public class Exercise {

    public static void main(String[] args) {

       Exercise test1 = new Exercise();
//        test1.Exercise1(42);
//        test1.Exercise2("word",1);
        test1.Exercise3(10.57);

    }//end of main
 void Exercise1(int value) {
         String category = "";
         // checking if it is between infant category
         if (value >= 0 && value <= 3) {
             category = "Infant";
         }//end infant
         else if (value >= 4 && value <= 12) {
             category = "Child";
         }//end Child
         else if (value >= 13 && value <= 18) {
             category = "Teen";
         }//end Teen
         else if (value >= 19 && value <= 64) {
             category = "Adult";
         }//end of Adult
         else if (value >= 65) {
             category = "OAP";
         }//end of OAP
        else
         {
             System.out.println("You have provided a invalid value");
         }
         System.out.println("Your current age: " + value);
         System.out.println("You are in the category: " + category);
 }//end of Exercise1

void Exercise2(String value,int Position){
        //Returns the character of the position of the string the user wants to check
        char firstValue = value.charAt(Position);
        //Checks if the value is equal to a vowel character, does OR to check for every vowel.
        if(firstValue == 'a' ||firstValue == 'e' || firstValue == 'i' || firstValue == 'o' || firstValue == 'u' )
        System.out.println(firstValue + " is vowel");
        else{
            System.out.println("The first character:  "+ firstValue+" is not  a vowel");
        }


        //Test if first letter in a word is a vowel or not.

        //Check if at a certain position of the word it is a vowel.

    }//end Exercise2
void Exercise3(double amount) {

    //Find out how many pounds, fifty pence, 20 pence etc., will be allocated from a
    //vending machine.
    double coins[] = {2.00, 1.00, 0.50, 0.20, 0.10, 0.05, 0.02, 0.01};
    int[] counter = new int[coins.length];
    double totalAmount = amount;
    //loop through the coin array to test which coins can fit into amount.
    System.out.println("The total amount is " + amount);
    for (int i = 0; i < coins.length; i++) {
        //if a specified coin is divisible by 0 then increment counter for that coin.
        //divide the amount by the coins and append it to a new double array.
        counter[i] = (int) (totalAmount / coins[i]); // this will store the amount of each coin used.
        totalAmount %= coins[i];    // gets the remainder and add its to total amount
        // which coins don't go over the limit of total amount, to return only the correct coins.
        if (counter[i] != 0) {
            System.out.println("Coin " + coins[i] + " can be used " + counter[i] + " amount of times");
        }
    }//end for loop

    }//end Exercise3

}



