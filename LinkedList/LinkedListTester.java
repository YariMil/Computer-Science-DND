public class LinkedListTester {
    public static void main(String[] args) {
        String[] test = new String[] {"a", "b", "c", "d", "e", "d"};
        SinglyLinkedList<String> l = new SinglyLinkedList<String>(test);
        System.out.println(l.toString());
        System.out.println(l.contains("d"));
        System.out.println(l.indexOf("a"));
        System.out.println(l.indexOf("c"));
        System.out.println(l.indexOf("aaaa"));
        System.out.println(l.remove(4));
        System.out.println(l.toString());
        System.out.println(l.remove("d"));
        System.out.println(l.toString());
        l.add(0, "a");
        System.out.println(l.toString());
        l.add(2, "t");
        System.out.println(l.toString());
        System.out.println(l.set(5, "B"));
        System.out.println(l.toString());
        System.out.println(l.size());
        System.out.println(l.remove(5));
        System.out.println(l.toString());
    }
}
