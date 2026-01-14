public class BinaryTester {
    public static void main(String[] args) {
        MyBST<Integer> test = new MyBST<Integer>();
        test.add(50);
        test.add(25);
        test.add(24);
        test.add(37);
        test.add(35);
        test.add(33);
        test.add(null);
        test.add(36);
        test.add(34);
        System.out.println(test.toString());
        test.remove(25);
        System.out.println(test.toString());
    }
}
