import java.util.HashMap;

public class DynamicTester {
    public static void main(String[] args) {
        int[] arr1 = {3, 5, 6, 7, 10};
        int[] arr2 = {10, 4, 8, 9, 15};
        System.out.println(DynamicProgramming.hiLoStress(arr1, arr2));
        System.out.println(DynamicProgramming.scavHunt(arr1, arr2));

    }
}
