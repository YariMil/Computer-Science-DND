public class RecursionTest {
    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2, a);
        ListNode c = new ListNode(1, b);
        Recursion.printListInReverse(c);

        // String[][] gridTest = new String[][] {{"a", "a", "a"}, {"v", "a", "v"}, {"a", "a", "v"}};
        // //{"a, a, a"}, {"v, a, v"}, {"a, v, a"}
        // printGrid(gridTest);
        // Recursion.infect(gridTest, 0, 0);
        // printGrid(gridTest);
        // System.out.println(Recursion.countNonConsecutiveSubsets(5));
        System.out.println(Recursion.countWaysToJumpUpStairs(7));
        StringBuilder test = new StringBuilder();
        // for (int i = 0; i < 1000; i++) {
        //     char adding = (char) ('a' + i % 26);
        //     test.append(adding);
        // }
        Recursion.printSubsets(test.toString());
        Recursion.printPermutations("a");
        // Recursion.printPermutations("abc");
        // System.out.println();
        // int[] arr = { 3, 7, 18, 2, 1, 20, 5, 8, 16, 0 };
        // Recursion.quickSort(arr);
        // int[] arr2 = { 0, 1, 2, 3, 5, 7, 8, 16, 18, 20 };
        // System.out.println();
        // for (int i = 0; i < arr.length; i++) {
        // System.out.print(arr[i]);
        // }
        // Recursion.solveHanoi(8);

        // int[] times = new int[] {3, 7, 9}; // 2, 6, 7, 20, 21, 40, 41, 43, 45, 47, 51, 53, 62,
        // 63,
        // // 64
        // int[] points = new int[] {10, 15, 10}; // 1000000, 4, 7, 300, 8, 20, 251, 23, 21, 1220,
        // 8,
        // // 9, 14, 81, 82
        // int p = Recursion.scavHunt(times, points);
        // System.out.println(p);
        int[] times = new int[4];
        int[] points = new int[4];
        int[] sortTest = new int[1000000];
        int index = 0;
        for (int i = 999999; i > 0; i--) {
            sortTest[index] = i;
            index++;
        }
        // for (int i = 0; i < sortTest.length; i++) {
        //     System.out.print(sortTest[i] + " ");
        // }
        System.out.println();
        Recursion.mergeSort(sortTest);
        //    for (int i = 0; i < sortTest.length; i++) {
        //     System.out.print(sortTest[i] + " ");
        // }
        System.out.println();
        System.out.println(Recursion.scavHunt(times, points));

    }

    public static void printGrid(String[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c]);
                System.out.print(", ");
            }
            System.out.println();
        }
    }
}
