import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @AUTHOR: Nicolae Chelmenciuc
 *          This class will be implementing the methods
 *          that will be fetching and displaying the data that the user requires
 *          or manipulating the DVD library
 */

public class DVDLibrary implements DVDInterface {

    public String filename; // will be used later in the program for file i/o purposes. (reading and writing
                            // to a file).

    /**
     * USED TO CREATE DVDS THAT WILL BE STORED IN THE ARRAYLIST - USING CONSTRUCTOR
     * FROM THE DVD OBJECT CLASS
     */
    @Override
    public void CreateDvd(String Title, String Release, String Mpaa, String Director, String Studio, String Rating) {

        DVD dvd = new DVD(Title, Release, Mpaa, Director, Studio, Rating);
        System.out.println("Your DVD has been created and sent to the DVD List.");
    }

    /**
     * USED TO LIST DVDS FROM THE DVD.DVDS ARRAYLIST
     * BY ITERATING THROUGH THEM ALL AND PRINTING EACH ONE
     * - RETURNS USING .TOSTRING METHOD.
     */
    @Override
    public void ListDvd() {
        System.out.println("LIST OF CURRENT DVDS:");
        // print out the entire DVDs library
        for (int i = 0; i < DVD.DVDs.size(); i++) {
            System.out.println("\n" + DVD.DVDs.get(i).toString());
        }
        if (DVD.DVDs.size() == 0) {
            System.out.println("DVD List is currently empty\n");
        }

    }

    /**
     * READS FROM A TEXTFILE AND ADDS EACH LINE TO THE ARRAYLIST WITH CORRESPONDING
     * ID AND CORRECT ORDER.
     */
    @Override
    public void ReadFile(String filename) {
        String[] lineValues;
        File fileName = new File(filename);
        DVDLibrary Library = new DVDLibrary();
        try {
            // Used to read from the file at every line.
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String lineFromFile = " ";
            do {
                lineFromFile = br.readLine();
                // checking every line in the file until it reaches an empty line or no lines.
                if (lineFromFile != null) {
                    // Split
                    lineValues = lineFromFile.split(",");
                    // These variables are made to create a DVD from the lines being read - Data
                    // marshalling
                    String Title = lineValues[0];
                    String Release = lineValues[1];
                    String Mppa = lineValues[2];
                    String Director = lineValues[3];
                    String Studio = lineValues[4];
                    String Rating = lineValues[5];
                    Library.CreateDvd(Title, Release, Mppa, Director, Studio, Rating);
                } // end if
            } // end do
              // reading from the file until there is nothing there.
            while (lineFromFile != null);
            br.close();
            fr.close();
        } // end try
          // if there is an exception
        catch (IOException e) {
            e.printStackTrace();
        } // end catch
    }

    /**
     * DISPLAYS THE DVD INFORMATION THAT THE USER REQUIRES
     * BY FETCHING THE ID AND RETURNING
     * THE CORRESPONDING DETAILS THAT MATCH THE ID.
     */
    @Override
    public void DisplayDvd(int ID) {
        // Iterates through the array list and retrieves the corresponding ID
        // information that matches to parameter ID.
        for (int i = 0; i < DVD.DVDs.size(); i++) {
            if (ID == DVD.DVDs.get(i).getID()) {
                System.out.println(" DVD ID: " + ID + "\nDVD Information:");
                System.out.println("Title: " + DVD.DVDs.get(i).getTitle());
                System.out.println("Release: " + DVD.DVDs.get(i).getRelease());
                System.out.println("Mpaa: " + DVD.DVDs.get(i).getMpaa());
                System.out.println("Director: " + DVD.DVDs.get(i).getDirector());
                System.out.println("Studio: " + DVD.DVDs.get(i).getStudio());
                System.out.println("Rating: " + DVD.DVDs.get(i).getRating());
            } // end if
        } // end for
    }// end method

    /**
     * DELETES AN ID FROM THE DVD ARRAYLIST
     * * BY FETCHING THE ID AND REMOVING ALL THE CONTENTS OF THAT ITEM
     * IN THE LIST AS WELL AS UPDATING LIST.
     */

    @Override
    public void DeleteDvd(int ID) {
        try {
            for (int i = 0; i < DVD.DVDs.size(); i++) {
                if (ID == DVD.DVDs.get(i).getID()) {
                    System.out.println("DVD ID:" + DVD.DVDs.get(i).getID() + " has been removed.");
                    DVD.DVDs.remove(i);
                } // end if
            } // end for
        } // end try
          // If they enter a value that is out of bounds of the current IDS available.
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter the correct DVD ID!");
            e.printStackTrace();
        }

    }// end method

    /**
     * This method iterates through the arraylist of dvds then
     * checks if it contains a title that the user provided
     * then it will ask the user what they would like to change about that specific
     * dvd
     * and it will update the contents of the matching title dvd by using setters
     */
    @Override
    public void EditDVD(String Title) {
        for (int i = 0; i < DVD.DVDs.size(); i++) {
            if (Title.equals(DVD.DVDs.get(i).getTitle())) {
                Scanner userInput = new Scanner(System.in);
                System.out.println("What would you like to edit for DVD: " + Title);
                System.out.println(
                        "\tEnter ONLY: t for Title, \trd for Release Date\t m for MPPA rating\t d for Director\t s for Studio\t u for User Rating\t");
                String user = userInput.nextLine();

                // Editing what the user would like to edit

                // Title
                if (user.equals("t")) {
                    System.out.println("Enter new title:");
                    Title = userInput.nextLine();
                    DVD.DVDs.get(i).setTitle(Title);
                    System.out.println("Title has been updated to: " + Title);
                }
                // Release Date
                else if (user.equals("rd")) {
                    System.out.println("Enter new Release Date:");
                    String Release = userInput.nextLine();
                    DVD.DVDs.get(i).setRelease(Release);
                    System.out.println("Release Date has been updated to: " + Release);
                }
                // MPPA Rating
                else if (user.equals("m")) {
                    System.out.println("Enter new MPPA Rating:");
                    String Mppa = userInput.nextLine();
                    DVD.DVDs.get(i).setMpaa(Mppa);
                    System.out.println("MPPA Rating has been updated to: " + Mppa);
                }
                // Directors Name
                else if (user.equals("d")) {
                    System.out.println("Enter new Director Name:");
                    String Director = userInput.nextLine();
                    DVD.DVDs.get(i).setDirector(Director);
                    System.out.println("Director's Name has been updated to: " + Director);
                }
                // Studio
                else if (user.equals("s")) {
                    System.out.println("Enter new Studio Date:");
                    String Studio = userInput.nextLine();
                    DVD.DVDs.get(i).setStudio(Studio);
                    System.out.println("Studio has been updated to: " + Studio);
                }
                // User Rating
                else if (user.equals("u")) {
                    System.out.println("Enter new User Rating:");
                    String UserRating = userInput.nextLine();
                    DVD.DVDs.get(i).setRating(UserRating);
                    System.out.println("User Rating has been updated to: " + UserRating);
                }

                // printing the new DVD
                System.out.println("Updated DVD: " + DVD.DVDs.get(i));

            } // end if

        } // end for
    }// end method

    /**
     * Searches for a dvd in the arraylist simply by iterating through each item in
     * the list
     * and checking if it matches the title then displaying the remaining
     * information of the
     * corresponding title dvd.
     */
    @Override
    public void SearchDVD(String Title) {
        for (int i = 0; i < DVD.DVDs.size(); i++) {
            if (Title.equals(DVD.DVDs.get(i).getTitle())) {
                System.out.println(DVD.DVDs.get(i).getTitle() + " exists in the DVD list.");
                System.out.println("The remaining DVD information is:");
                System.out.println(DVD.DVDs.get(i).getID());
                System.out.println(DVD.DVDs.get(i).getRelease());
                System.out.println(DVD.DVDs.get(i).getMpaa());
                System.out.println(DVD.DVDs.get(i).getDirector());
                System.out.println(DVD.DVDs.get(i).getStudio());
                System.out.println(DVD.DVDs.get(i).getRating());
            } // end if
            else {
                System.out.println("DVD does not exist in the list.");
            }
        } // end loop
    }// end method

}// end class
