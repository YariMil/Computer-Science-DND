import java.util.ArrayList;

public class ArrayListTester {
    public static void main(String[] args) {
        // // Test efficiency of the add method
        // MyArrayList<String> addTest = new MyArrayList<String>();
        // for (int i = 0; i < 1000000; i++) {
        //     addTest.add(i, "Hi!");
        // }
        
        // System.out.println("Size: " + addTest.size() + ". Should be 1000000");

        // // Test toString along with addition and removing
        // MyArrayList<String> stringTest = new MyArrayList<String>();
        // for (int i = 0; i < 5; i++) {
        //     stringTest.add("Hello!");
        // }
        // stringTest.add(null);
        // System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        // stringTest.add(2, "Hi!");
        // System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        // stringTest.remove(2);
        // System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        // stringTest.remove("Hello!");
        // System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        // stringTest.remove(null);
        // System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        // stringTest.add(4, "Hi!");
        // System.out.println(stringTest.toString());
        // System.out.println(stringTest.contains("Hi!"));

        // MyArrayList<String> h = new MyArrayList<String>();
        // System.out.println(h.toString());
        // h.add("s");
        // System.out.println(h.contains(null));
        // System.out.println(h.toString());
        // System.out.println(h.remove(0));
        // ArrayList<String> s = new ArrayList<String>();
        // s.add(0, "h");
        // s.add(1, "B");
        // System.out.println(s.toString());
        // s.add(2, "H");
        // System.out.println(s.toString());
        // // h.remove(2);
        // // h.set(2, "C");
        // // h.get(2);
        // int[] test = new int[] {1, 2, 3, 4, 5, 6, 7};
        // System.out.println(SortedArrayList.binarySearch(test, 4));

        System.out.println("==== SORTED LIST ====");
        SortedArrayList<String> l = new SortedArrayList<String>();
        l.toString();
        l.add("B");
        System.out.println(l.toString());
        l.add("C");
        System.out.println(l.toString());
        l.add("D");
        System.out.println(l.toString());
        l.add("A");
        System.out.println(l.toString());     
        System.out.println(l.contains("B"));  
        System.out.println(l.contains("Abra")); 
    }
}
