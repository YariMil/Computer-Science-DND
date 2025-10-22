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
        System.out.println(Recursion.countNonConsecutiveSubsets(5));
        System.out.println(Recursion.countWaysToJumpUpStairs(4));
        
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