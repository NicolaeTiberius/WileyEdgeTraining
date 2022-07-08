import java.util.ArrayList;

/**@AUTHOR: Nicolae Chelmenciuc
 *
 * This is the DVD object class that is used to instantiate dvd objects in other classes
 * It stores the needed information for a DVD and an arraylist that will be used to
 * fetch and manipulate dvd
 *
 * this class just serves to be used to create objects and manipulating the arraylist*/


public class DVD {
    private String Release;
    private String Title;
    private String Rating;
    private String Director;

    private String Studio;
    private String Mpaa;
    private int ID;
    private static int count= 1;

    public static ArrayList<DVD> DVDs = new ArrayList<>();

    public DVD(String title, String release, String mpaa, String director,String studio,String rating) {

        Title = title;
        Release = release;
        Mpaa = mpaa;
        Director = director;
        Studio = studio;
        Rating = rating;
        setID(count++);
        DVDs.add(this);

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRelease() {
        return Release;
    }

    public void setRelease(String release) {
        Release = release;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public static ArrayList<DVD> getDVDs() {
        return DVDs;
    }

    public static void setDVDs(ArrayList<DVD> DVDs) {
        DVD.DVDs = DVDs;
    }

    public String getMpaa() {
        return Mpaa;
    }

    public void setMpaa(String mpaa) {
        Mpaa = mpaa;
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String studio) {
        Studio = studio;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "ID='" + ID + '\'' +
                ", Title='" + Title + '\'' +
                "Release='" + Release + '\'' +
                ", Mpaa='" + Mpaa + '\'' +
                ", Director='" + Director + '\'' +
                ", Studio='" + Studio + '\'' +
                ", Rating='" + Rating + '\'' +
                '}';
    }
}


