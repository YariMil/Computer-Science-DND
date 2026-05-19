import java.util.HashMap;

public class DynamicTester {
    public static void main(String[] args) {
        // hiLoStress test, should be 17
        int[] lowPayouts = {1, 3, 10};
        int[] highPayouts = {1, 6, 15};
        System.out.println(DynamicProgramming.hiLoStress(lowPayouts, highPayouts));
        // System.out.println(DynamicProgramming.hiLoStress(arr1, arr2));
        // Scav hunt test, should be 28 points by taking the 10, the 8, and then the 10
        int[] times = {3, 5, 16, 25, 26, 27};
        int[] points = {10, 4, 8, 10, 1, 3};
        System.out.println(DynamicProgramming.scavHunt(times, points));
        // Another scav hunt test, shifting the values around so that you should take the last
        // reward. Should still be 29
        int[] times2 = {3, 5, 16, 25, 26, 27};
        int[] points2 = {10, 4, 8, 1, 9, 10};
        System.out.println(DynamicProgramming.scavHunt(times2, points2));
        // Final test, also 28 but we're just shuffling values around
        int[] times3 = {3, 5, 16, 25, 26, 27};
        int[] points3 = {4, 10, 8, 1, 10, 9};
        System.out.println(DynamicProgramming.scavHunt(times3, points3));
        // Cookie test, should be 26 cookies
        int[][] cookies = {{5, 9, -1, 10, 11, 234, 12, 445, 21, 213, 401, 21}, 
                           {3, 1, 4, 2, 1, 4, 10, 11, 0, -1, 5, 6}, 
                           {6, 8, 3, 4, 5, 1, 3, 5, 1, 10, 11, 12}};
        System.out.println(DynamicProgramming.dynamicCookies(cookies));

    }
}
