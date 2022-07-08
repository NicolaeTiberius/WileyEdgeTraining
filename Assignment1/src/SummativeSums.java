public class SummativeSums {
    public static void main(String[] args) {

        int[] array1= { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] array2= { 999, -60, -77, 14, 160, 301 };
        int[] array3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,140, 150, 160, 170, 180, 190, 200, -99};
        System.out.println("#1 Array Sum: " +totalSum(array1));
        System.out.println("#2 Array Sum: " +totalSum(array2));
        System.out.println("#3 Array Sum: " +totalSum(array3));
    }//end main
    private static int totalSum(int[] integers) {

        int total = 0; //total that will hold the sum of the integer array.
        for (int integer : integers) { //goes through every integer in the array
            total = total + integer; //adds the integer to the total each iteration. until every integer has been iterated.
        }
        return total;
    }
}//end class
