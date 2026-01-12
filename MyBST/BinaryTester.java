public class BinaryTester {
    public static void main(String[] args) {
        MyBST<Integer> test = new MyBST<Integer>();
        test.add(50);
        test.add(25);
        test.add(75);
        test.add(26);
        test.add(27);
        test.add(28);
        System.out.println(test.toString());
        test.remove(25);
        System.out.println(test.toString());
    }
}
