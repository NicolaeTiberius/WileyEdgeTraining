import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @AUTHOR: Nicolae Chelmenciuc
 * This class will be the MAIN driver class which is calling of the methods
 * that the user requires in the form of a simple text-based user interface.
 */
public class Main {

    public static void main(String[] args) {

        //Created a new arraylist that will be getting the current DVD arraylist.
        ArrayList<DVD> list = DVD.getDVDs();

        //To use the dvd methods
        DVDLibrary Library = new DVDLibrary(); //this line allows you to use dvd library methods
        Library.ReadFile("dvd.txt"); // read through the file
        while(true){
            //The user menu when the while loop starts it will ask the user to select an option,
            // after each option it will ask them again
            // until they input 0 and the loop will stop and program will end
            try {
                Scanner userInput = new Scanner(System.in);
                System.out.println("----------DVD USER MENU:--------------- ");
                System.out.println("\nEnter the number for options:\n\t1.View current List of DVDS.\n\t2.Create a DVD.\n\t3.Delete a DVD.\n\t4.Edit a DVD.\n\t5.View a DVD.\n\t6.Search for a DVD\n\t0. to Quit.\n");
                System.out.print("User Choice: ");
                int userChoice = userInput.nextInt();
                if (userChoice == 0){
                    System.out.println("You have selected to quit the menu. Goodbye");

                    //do the read to file method.
                    File fileNameOut = new File("dvdOutput.txt");
                    try{
                        FileWriter fw = new FileWriter(fileNameOut);
                        BufferedWriter bw = new BufferedWriter(fw);
                        //iterating through the dvd arraylist and adding this to a string which will store it for each dvd and write it to a file.
                        for (int i = 0; i < DVD.DVDs.size(); i++) {
                            String outputText = "\nDVD ID : " + DVD.DVDs.get(i).getID() +", Title: "+DVD.DVDs.get(i).getTitle() + ",Release Date: "+ DVD.DVDs.get(i).getRelease() +", Mpaa: "+ DVD.DVDs.get(i).getMpaa() + ", Director: "+ DVD.DVDs.get(i).getDirector() +", Studio: "+ DVD.DVDs.get(i).getStudio() +
                                    ", Rating: "+ DVD.DVDs.get(i).getRating()+"\n";
                            bw.write(outputText);
                        }
                        bw.flush();
                        bw.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("New Output file has been updated");
                    break;
                }
                //Viewing the arraylist - uses the DVDlibrary method
                if (userChoice == 1){
                    System.out.println("You have selected to view current list of DVDS");
                    Library.ListDvd();
                    continue; // continues with the loop after.
                }
                //To create DVD - it will then instantiate a DVD object
                else if (userChoice ==2){
                    System.out.println("You have selected to Create a DVD.\n");
                    System.out.println("Enter DVD Title: ");
                    String Title = userInput.next();
                    System.out.println("Enter DVD Release Date in DDMMYYYY format: ");
                    String Release = userInput.next();
                    System.out.println("Enter DVD Mpaa Rating or 0 if unknown: ");
                    String Mpaa = userInput.next();
                    System.out.println("Enter DVD Director or None if unknown: ");
                    String Director = userInput.next();
                    System.out.println("Enter DVD Studio  or None if unknown: ");
                    String Studio = userInput.next();
                    System.out.println("Finally, enter DVD personal rating: ");
                    String Rating = userInput.next();
                    Library.CreateDvd(Title,Release,Mpaa,Director,Studio,Rating);
                    continue;
                }
                //To Delete a dvd
                else if (userChoice ==3){
                    System.out.println("You have selected to Delete a DVD.");
                    System.out.println("Enter DVD ID to delete: ");
                    int dvdID =  userInput.nextInt();
                    Library.DeleteDvd(dvdID);
                    continue;

                }
                //To Edit a DVD
                else if (userChoice ==4){
                    System.out.println("You have selected to Edit a DVD. ");
                    System.out.println("Enter DVD Title to  edit: ");
                    String Title = userInput.next();
                    Library.EditDVD(Title);
                    continue;

                }
                //To view a DVD
                else if (userChoice ==5){
                    System.out.println("You have selected to View a DVD.");
                    System.out.println("Enter DVD ID");
                    int dvdID =  userInput.nextInt();
                    Library.DisplayDvd(dvdID);
                    continue;
                }
                //To search for a dvd
                else if (userChoice ==6){
                    System.out.println("You have selected to Search for a DVD.");
                    System.out.println("Enter DVD Title to  Search: ");
                    String Title = userInput.next();
                    Library.SearchDVD(Title);
                    continue;
                }

            }//end try
            //If they dont enter a valid number.
            catch(Exception e){
                System.out.println("Enter only a number 0-6");
                continue;
            }
        }//end loop

    }//end main

}//end class


