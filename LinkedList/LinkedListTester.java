import java.util.LinkedList;

public class LinkedListTester {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        SinglyLinkedList mylist = new SinglyLinkedList();
        mylist.add("A");
        mylist.add("B");
        mylist.add("C");
        list.add("A");
        list.add("B");
        list.add("C");
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
        mylist.remove(0);
        System.out.println(mylist.toString());
        System.out.println(mylist.getHead().getValue());
        System.out.println(mylist.get(0));

    }
}
