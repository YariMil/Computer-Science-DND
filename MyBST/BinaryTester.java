public class BinaryTester {
    public static void main(String[] args) {
        MyBST<Integer> test = new MyBST<Integer>();
   
        System.out.println(test.add(50));
        System.out.println(test.add(25));
        System.out.println(test.add(25));
        test.add(24);
        test.add(37);
        test.add(35);
        test.add(33);
        test.add(31);
        test.add(36);
        test.add(34);
        System.out.println(test.toString());
        test.remove(25);
        System.out.println(test.toString());
        System.out.println(test.contains(25));
        System.out.println(test.contains(31));
        System.out.println(test.contains(-1));
        System.out.println(test.contains(510));
        System.out.println("Ok here we go ==============");
        MyBST<String> t = new MyBST<String>();
          System.out.println(t.contains("Hello!"));
        t.add("a");
        t.add("d");
        t.add("c");
        t.add("e");
        System.out.println(t.toString());
        t.remove("a");
        System.out.println(t.toString());
        System.out.println("===== TIMMEEEE =======");
        MyBST<Integer> timeTest = new MyBST<Integer>();
        for (int i = 0; i < 100000; i++) {
            timeTest.add(i/2);
            timeTest.add(i*2);
        }
        System.out.println("Well that's one thing done");
        timeTest.remove(198);
        System.out.println("Remove done");
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
            timeTest.remove(i/2);
            timeTest.remove(i*2);
        }
        System.out.println("Second, bigger remove is finished");
    }
}
