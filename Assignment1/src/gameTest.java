import java.util.Scanner;

public class gameTest {
    public static void main(String[] args) {
        /*
    This code here calculates the score of a GAA game
     */
        char x = 'a';
        Scanner valueIn = new Scanner(System.in);
        int homeGoals = -1;
        while (homeGoals <= -1) {
            System.out.println("Enter home team goals");
            homeGoals = valueIn.nextInt();
            if (homeGoals <= -1) {
                System.out.println("Invalid input ... reinput goals values");
            }
        }
        int homePoints = 10, awayGoals = 13, awayPoints = 9; // Declaring and Initializing variables
        final int POINTSPERGOAL = 3; // Constant Variable
        String homeTeam = "Tyrone"; // String is an Object, not a Primitive
        System.out.println(homeTeam);
        String awayTeam = "Donegal";
/*        homeTeam = homeTeam.toLowerCase();
        System.out.println(homeTeam);*/
        int homeTotal = (homeGoals * 3) + homePoints;
        int awayTotal = (awayGoals * 3) + awayPoints;
        //System.out.println("Score " + homeTotal);
        if (homeTotal > awayTotal) {
            System.out.println(homeTeam + "team wins");
        } else if (awayTotal > homeTotal) {
            System.out.println(awayTeam + " team wins");
        } else if (awayTotal == homeTotal) {
            System.out.println("It's a draw!");
        }

    } // End of main


    }

