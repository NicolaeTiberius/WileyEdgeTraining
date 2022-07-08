import java.util.ArrayList;
import java.util.List;

public class Streams {

    public static void main(String[] args) {

        List<Integer> lst = new ArrayList<Integer>();
        lst.add(1);
        lst.add(2);
        lst.add(3);
        lst.add(4);
        lst.add(5);
        lst.add(6);
        lst.add(0);
        lst.add(-1);
        //printing each value - 1.
        System.out.println("Array List" + lst);
        System.out.println("\nNumbers above 0 in the list\n");
        lst.stream().map(x-> x>0).forEach(x->System.out.printf("%3d",x));
        System.out.println("\nOdd numbers within the list. \n");
        lst.stream().filter(x-> x%2!=0).forEach(x-> System.out.printf("%3d",x));
        System.out.println("\nOdd numbers squared: ");
        lst.stream().filter(x-> x%2!=0).map(x->x*x).forEach(x-> System.out.printf("%3d",x));


    }
}
