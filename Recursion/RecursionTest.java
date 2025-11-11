public class RecursionTest {
    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2, a);
        ListNode c = new ListNode(1, b);
        Recursion.printListInReverse(c);

        // String[][] gridTest = new String[][] {{"a", "a", "a"}, {"v", "a", "v"}, {"a", "a", "v"}}; //{"a, a, a"}, {"v, a, v"}, {"a, v, a"}
        // printGrid(gridTest);
        // Recursion.infect(gridTest, 0, 0);
        // printGrid(gridTest);
        // System.out.println(Recursion.countNonConsecutiveSubsets(5));
        System.out.println(Recursion.countWaysToJumpUpStairs(7));
        Recursion.printSubsets("abc");
        System.out.println("A".substring(0, 0));
        Recursion.printPermutations("abc");
        System.out.println();
        int[] ints = new int[] {3, 2, 6, 4, 7, 1};
        Recursion.quickSort(ints);
        Recursion.solveHanoi(8);

        int[] times = new int[] {3, 7, 9}; // 2, 6, 7, 20, 21, 40, 41, 43, 45, 47, 51, 53, 62, 63, 64
        int[] points = new int[] {10, 15, 10}; // 1000000, 4, 7, 300, 8, 20, 251, 23, 21, 1220, 8, 9, 14, 81, 82
        int p = Recursion.scavHunt(times, points);
        System.out.println(p);
        
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