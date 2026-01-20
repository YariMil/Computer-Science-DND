public class BinaryTester {
    public static void main(String[] args) {
        // MyBST<Integer> test = new MyBST<Integer>();
        // test.add(-5);
        // test.add(0);
        // // test.add(2);
        // // test.add(1);
        // System.out.println(test.toString());
        // System.out.println(test.min());
        // test.remove(-5);
        // System.out.println(test.min());
        // // System.out.println(test.toString());
        // System.out.println(test.toString());
        // System.out.println(test.getHeight());
        // System.out.println(test.add(50));
        // System.out.println(test.add(25));
        // System.out.println(test.add(25));
        // test.add(24);
        // test.add(37);
        // test.add(35);
        // test.add(33);
        // test.add(31);
        // test.add(36);
        // test.add(34);
        // System.out.println(test.toString());
        // test.remove(25);

        // System.out.println(test.toString());
        // System.out.println(test.contains(25));
        // System.out.println(test.contains(31));
        // System.out.println(test.contains(-1));
        // System.out.println(test.contains(510));
        // System.out.println("Ok here we go ==============");
        // MyBST<String> t = new MyBST<String>();
        // System.out.println(t.contains("Hello!"));
        // t.add("a");
        // t.add("d");
        // t.add("c");
        // t.add("e");
        // System.out.println(t.toString());
        // t.remove("a");
        // System.out.println(t.toString());
        // System.out.println("===== TIME =======");
        // MyBST<Integer> timeTest = new MyBST<Integer>();
        // // System.out.println(timeTest.toString());
        // // timeTest.add(50);
        // // System.out.println(timeTest.toString());
        // // timeTest.remove(50);
        // // System.out.println(timeTest.toString());
        // // timeTest.remove(45);

        // for (int i = 0; i < 1000; i++) {
        // timeTest.add(i / 2);
        // timeTest.add(i * 2);
        // }
        // System.out.println("Well that's one thing done");
        // timeTest.remove(198);
        // System.out.println("Remove done");
        // for (int i = 0; i < 1000; i++) {
        // timeTest.remove(i / 2);
        // timeTest.remove(i * 2);
        // }
        // System.out.println(timeTest.getRoot());
        // // System.out.println("Second, bigger remove is finished");
        // System.out.println("Big Tree");
        MyBST<Integer> bigtest = new MyBST<Integer>();
        bigtest.add(50);
        bigtest.add(25);
        bigtest.add(75);
        bigtest.add(12);
        bigtest.add(37);
        bigtest.add(63);
        bigtest.add(87);
        bigtest.add(6);
        bigtest.add(18);
        bigtest.add(31);
        bigtest.add(43);
        bigtest.add(57);
        bigtest.add(69);
        bigtest.add(81);
        bigtest.add(93);
        bigtest.add(3);
        bigtest.add(9);
        bigtest.add(15);
        bigtest.add(21);
        bigtest.add(28);
        bigtest.add(34);
        bigtest.add(40);
        bigtest.add(46);
        bigtest.add(54);
        bigtest.add(60);
        bigtest.add(66);
        bigtest.add(72);
        bigtest.add(78);
        bigtest.add(84);
        bigtest.add(90);
        bigtest.add(96);
        bigtest.add(80);
        bigtest.add(79);
        System.out.println(bigtest.toString());
        // System.out.println(bigtest.toStringWithDetails());
        // System.out.println(bigtest.toString());
        bigtest.remove(50);
        System.out.println(bigtest.toString());
        // bigtest.remove(63);
        // System.out.println(bigtest.toString());
        // System.out.println(bigtest.toStringWithDetails());
        // bigtest.remove(54);
        // bigtest.remove(57);
        // bigtest.remove(60);
        // bigtest.remove(63);
        // bigtest.remove(66);
        // bigtest.remove(69);
        // while (bigtest.getRoot().getRight() != null) {
        // bigtest.remove(bigtest.getRoot().getValue());
        // }
        // bigtest.remove(96);
        // bigtest.remove(46);
        // System.out.println(bigtest.toString());
        // System.out.println(bigtest.toStringWithDetails());
        // System.out.println(bigtest.getHeight());
        // System.out.println(bigtest.remove(63));
        // System.out.println(bigtest.toString());
        // System.out.println("Doing this");
        // System.out.println(bigtest.remove(63));
        // System.out.println("Did that");
        // System.out.println(bigtest.add(12));



        System.out.println("======== DOUBLES TEST AAAAAA ======");
        MyBST<Double> bst = new MyBST<Double>();;
        // for (double i = 1; i < 101; i++) {
        // bst.add(i);
        // }
        // for (double i = 1; i < 101; i++) {
        // bst.remove(i);
        // }
        // bst.add(1.0);
        // bst.add(0.5);
        // bst.remove(1.0);
        // bst.remove(0.5);
        // bst.add(10.0);
        // System.out.println(bst.add(1.1));
        // System.out.println(bst.toString());
        // System.out.println(bst.min());
        // System.out.println(bst.max());
        // bst.remove(1.1);
        // System.out.println(bst.getRoot());
        // System.out.println(bst.getHeight());

        // bst.add(7.0);
        // bst.add(6.0);
        // bst.add(4.0);
        System.out.println(bst.toString());
        bst.add(8.0);
        bst.add(9.0);
        bst.add(6.0);
        // bst.add(10.0);
        // bst.add(11.0);
        // bst.add(9.0);
        // bst.add(9.5);
        // bst.add(12.0);
        // bst.add(8.5);
        // bst.add(8.75);
        // bst.add(11.5);
        System.out.println(bst.toStringWithDetails());
        // System.out.println(bst.getHeight());
        bst.remove(8.0);
        System.out.println(bst.toStringWithDetails());
        // System.out.println(bst.getHeight());

        // bst.add(45.0);
        // bst.remove(45.0);

        // System.out.println(bst);
    }
}
