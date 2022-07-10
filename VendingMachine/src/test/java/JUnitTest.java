import com.vendingMachine.dao.ItemController;
import com.vendingMachine.dto.Items;
import com.vendingMachine.NoItemInventoryException;
import com.vendingMachine.ui.ItemView;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.View;

import static org.junit.jupiter.api.Assertions.*;

/**In this class the DAO testing will be carried out for the entire program */

public class JUnitTest {

    ItemController controller = new ItemController();
    private ItemView view;

    @Autowired
    public JUnitTest(ItemView view){
        this.view = view;
    }


    //TESTING IF THE METHOD RETURNS THE CORRECT CHANGE TO THE USER CORRECTLY USING A SIMPLE METHOD
    @Test
    public void TestChange(){

        //Testing change given.
        double testChange = controller.changeGiven(1.55,2.50);

        assertEquals(0.95,testChange,"com.vendingMachine.dto.Change is not equal");

    }
    @Test
    public void TestStock(){

        view.selectProduct("Rolo",1.50);

        controller.createItem("Mango",1.50,10);
        int stock = Items.ItemList.get(0).getStock();
        int actualStock = 10;
        assertEquals(actualStock,stock);
    }

    //Tried to test the exception.
    @Before
    public void TestException(){
        controller.createItem("Mars",1.20,0);
        NoItemInventoryException thrown = assertThrows(NoItemInventoryException.class, ()->{
            view.selectProduct("Mars",1.50);
        }
        );
    }

   }


