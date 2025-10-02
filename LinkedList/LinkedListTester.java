import java.util.LinkedList;

public class LinkedListTester {
    public static void main(String[] args) {
        String[] l = new String[] {"A", "B", "C"};
        LinkedList list = new LinkedList();
        SinglyLinkedList mylist = new SinglyLinkedList();
        // list.set(0, "A");
        // mylist.set(0, "A");
        mylist.add("A");
        mylist.add("B");
        mylist.add("C");
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list.toString());
        System.out.println(mylist.toString());
        int i = 0;
        for (ListNode node = mylist.getHead(); node != null; node = node.getNext()) {
            if (!list.get(i).equals(node.getValue())) {
                System.out.println(list.get(i) + " does not equal " + node.getValue());
            }
            i++;
            if (node.getNext() == null) {
                if (!node.equals(mylist.getTail())) {
                    System.out.println(node.getValue());
                    System.out.println(mylist.getTail().getValue());
                    System.out.println("Tail is missing!");
                }
            }
        }
        if (i != list.size()) {
            System.out.println("Not the same size list!");
        }
        mylist.add(null);
        System.out.println(mylist.toString());
        mylist.remove(null);
        System.out.println(mylist.toString());
        // list.set(3, "A");
        i = 0;
        // mylist.remove(0);
        System.out.println(mylist.toString());
        System.out.println(mylist.getHead().getValue());
        System.out.println(mylist.get(0));
        list.add(3, "A");
        mylist.add(3, "A");
        // list.remove(4);
        // mylist.remove(4);
        list.add(null);
        System.out.println(list.toString());
        mylist.add(null);
        System.out.println(mylist.toString());
        System.out.println(mylist.getTail().getValue());
        System.out.println(list.indexOf(null));
        System.out.println(mylist.indexOf(null));
        System.out.println(list.contains(null));
        System.out.println(mylist.contains(null));
        // for (i = 0; i < 1000000; i++) {
        // mylist.add("A");
        // }
        System.out.println(mylist.toString());
        mylist.add(0, "Z");
        System.out.println(mylist.toString());
        System.out.println(mylist.getHead().getValue());
        System.out.println(mylist.remove(0));
        System.out.println(mylist.toString());
        mylist.set(0, "V");
        System.out.println(mylist.toString());
        System.out.println(mylist.getHead().getValue());
        System.out.println(mylist.set(4, "Y"));
        mylist.add(5, "J");
        System.out.println(mylist.toString());
        System.out.println(mylist.indexOf(mylist.getTail().getValue()));
        System.out.println(mylist.getTail().getValue());
        System.out.println(mylist.remove(5));

    }
}
