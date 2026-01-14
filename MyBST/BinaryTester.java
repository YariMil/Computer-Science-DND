public class BinaryTester {
    public static void main(String[] args) {
        MyBST<Integer> test = new MyBST<Integer>();
        test.add(50);
        test.add(25);
        test.add(12);
        test.add(20);
        test.add(19);
        test.add(22);
        test.add(21);
        test.add(24);
        System.out.println(test.toString());
        test.remove(25);
        System.out.println(test.toString());
    }
}
