import java.util.Scanner;

public class HealthyHearts {


    public static void main(String[] args) {

        //This program will first ask the user to input their age which will then be stored as an integer value passed onto
        //the static void method heartMetre which will take that integer value and display the optimal result.
        Scanner userAge = new Scanner(System.in);
        System.out.print("What is your age? "); // didn't use println so they type age on same line
        int userResult = userAge.nextInt();
        heartMetre(userResult); // calls the method and passes on the target integer age using the user input.
        userAge.close();
    }//end main

    //This is the method that will calculate the persons maximum heart rate and target HR zone using simple maths.
    private static void heartMetre(int age){

        double maximumHeartRate =220 - age;
        double targetZone1 = Math.round((50* maximumHeartRate) / 100); // calculates the 50% of maximum
        double targetZone2 = Math.round((85*maximumHeartRate) / 100); // calculates the 85% of maximum
        //// cast to int to make the value more concise.
        System.out.println("\nYour Maximum heart rate should be "+ (int)maximumHeartRate + " beats per minute.");

        System.out.println("Your target HR Zone is "+(int) targetZone1 + " - "+(int) targetZone2 + " beats per minute.\n");
    }
}//end class


