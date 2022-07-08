import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors
{
    public static void main(String[] args) {

        /* Game introduction rules */
        System.out.println("ROCK PAPER SCISSORS: WILEY-EDGE COMPUTER EDITION");
        System.out.println("Rules:");
        System.out.println("1. Choose to play from 1-10 rounds");
        System.out.println("2.Decide if you would like to pick Rock/Paper/Scissors each round.");
        System.out.println("3.Each round the computer or player can win a point.");
        System.out.println("4.At the end of the set-rounds, the user with the most points win the game!");
        System.out.println("5. Have fun and enjoy!");

        Scanner user_input = new Scanner(System.in);
        //this will be used in an equals that will quit the game.
        String quit = "quit";
        //to decide the winner of game, will be used later.
        int playerPoints = 0;
        int computerPoints = 0;
        int drawPoints = 0;
        /* Main Game loop where everything will be running all at once. */
        while(true){
            /* Input variable and string to quit main game loop */

            System.out.println("Enter amount of game rounds you would you like to play, or enter ONLY 'quit' to exit the game. ");
            String gameRounds = user_input.nextLine(); //inputs the amount of game rounds as a string to be able to take in quit as well.

            if(quit.equalsIgnoreCase(gameRounds)){
                System.out.println("Thank you for playing");
                break; // exit game loop
            }
            //if player does not enter quit then it will assume the value was an integer and proceed to starting the game with a set amount of rounds.
            else{
                // converted string input to int to get working rounds, as it requires an integer to loop properly.
                int rounds = Integer.parseInt(gameRounds);
                //if they enter above 10 or 0 in the game rounds it will exit.
                if(rounds>10|| rounds<=0) {
                    System.out.println("Error out-of-range rounds");
                    System.out.println("The game will exit now.");
                    break;
                }
                //This while loop will iterate by asking the user to enter rock/paper/scissors and increment
                //a counter each time to count the rounds played, if they have played the set rounds it will stop the loop.
                int nRounds = 1;
                while(nRounds<=rounds){
                    //Takes input from the player
                    System.out.println("Round: "+ nRounds);
                    System.out.println("Enter ROCK/PAPER/SCISSORS");
                    String userInput = user_input.nextLine();

                    //Generating the computer's decision.
                    String[] choicesArray = {"Rock","Paper","Scissors"}; //array to store decision choices
                    int randomChoice = new Random().nextInt(choicesArray.length); // chooses a random value from choices Array.
                    String computerChoice = choicesArray[randomChoice]; //applies random value to a position in the choices array

                    // Handling Player decisions by making boolean to check if a player inputs any of the type string values then its compared and something happens
                    //i.e wins or loses
                    boolean userRock = userInput.equals("rock") || userInput.equals("ROCK") || userInput.equals("Rock");
                    boolean userPaper = userInput.equals("paper") || userInput.equals("PAPER") || userInput.equals("Papers");
                    boolean userScissors = userInput.equals("scissors") || userInput.equals("SCISSORS") || userInput.equals("Scissors");

                    //if Player is Rock and Robot is Paper - Player loses
                    if(userRock && computerChoice.equals("Paper")  ){
                        System.out.println("Player is Rock, and Computer is Paper so Computer wins round!");
                        computerPoints++;
                    }
                    //if player rock and computer scissors - Player wins
                    else if(userRock && computerChoice.equals("Scissors")) {
                        System.out.println("Player is Rock, and Computer is " + computerChoice + " so Computer loses round!");
                        playerPoints++;
                    }
                    //If Player and Rock is equal - a tie
                    else if(userRock && computerChoice.equals("Rock")){
                        System.out.println("Player is Rock, and Computer is Rock, its a draw! ");
                        drawPoints++;
                    }

                    //User paper and comp rock - player wins
                    else if(userPaper && computerChoice.equals("Rock")){
                        System.out.println("Player is Paper, and Computer is Rock, so Player wins the round!");
                        playerPoints++;
                    }// a draw
                    else if (userPaper &&  computerChoice.equals("Paper")){
                        System.out.println("User is Paper, and Computer is Paper, so it's a draw!");
                        drawPoints++;
                    }
                    //user paper and computer is scissors - comp wins
                    else if (userPaper && computerChoice.equals("Scissors")){
                        System.out.println("User is Paper, and Computer is Scissors, so Computer wins the round!");
                        computerPoints++;
                    }
                    //if user scissors and computer scissors - draw
                    else if(userScissors && computerChoice.equals("Scissors")){
                        System.out.println("User is Scissors, and Computer is Scissors, so its a draw!");
                        drawPoints++;
                    }
                    //if user scissors and computer paper - player wins
                    else if(userScissors && computerChoice.equals("Paper")){
                        System.out.println("User is Scissors, and Computer is Paper, so Player wins the round!");
                        playerPoints++;
                    }
                    //if user scissors and computer rock - computer wins
                    else if(userScissors && computerChoice.equals("Rock")){
                        System.out.println("User is Scissors, and Computer is Rock, so Computer wins the round!");
                        computerPoints++;
                    }

                    nRounds++; // iterating the amount of rounds
                }    // end Game Rounds Loop

                //display the scores and finding a winner using basic comparision operators for bigger than or smaller than.
                System.out.println("The game has finished and the scores have been finalized! ");
                System.out.println("--------SCORES--------");
                System.out.println("Number of ties: "+drawPoints);
                System.out.println("Player Score: "+ playerPoints);
                System.out.println("Computer Score: "+computerPoints);

                //if player wins
                if(playerPoints>computerPoints){
                    System.out.println("Player wins! ");
                    playerPoints = 0; // to reset the score - used below as well
                }
                //if computer wins
                else if(computerPoints>playerPoints){
                    System.out.println("Computer wins!");
                    computerPoints = 0;
                }
                //if its a draw.
                else if (playerPoints == computerPoints){ //
                    System.out.println("It is a draw!");
                    computerPoints = 0;
                    playerPoints = 0;
                }

                //this is executed after it exits out of the main loop to signify a new game
                // and it reruns the loop again
                System.out.println("----NEW GAME------");

            }


        }//end game while loop.

    }//end main
}//end of class


