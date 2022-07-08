/**@AUTHOR: Nicolae Chelmenciuc
 * This interface will be used to display the menu and objectives
 * the user is able to handle while using the DVD library program
 * */
public interface DVDInterface {

    public void ListDvd();

    public void ReadFile(String filename);
   public void DisplayDvd(int ID);

    void CreateDvd(String  Title,String Release,String Mpaa,String  Director,String Studio,String Rating);

    public void DeleteDvd(int ID);

    public void EditDVD(String Title);

    public void SearchDVD(String Title);




    }

