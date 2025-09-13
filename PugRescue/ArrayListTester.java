import java.util.ArrayList;

public class ArrayListTester {
    public static void main(String[] args) {
        // Test efficiency of the add method
        MyArrayList<String> addTest = new MyArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            addTest.add(i, "Hi!");
        }
        
        System.out.println("Size: " + addTest.size() + ". Should be 1000000");

        // Test toString along with addition and removing
        MyArrayList<String> stringTest = new MyArrayList<String>();
        for (int i = 0; i < 5; i++) {
            stringTest.add("Hello!");
        }
        stringTest.add(null);
        System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        stringTest.add(2, "Hi!");
        System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        stringTest.remove(2);
        System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        stringTest.remove("Hello!");
        System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        stringTest.remove(null);
        System.out.println(stringTest.toString() + ": Size " + stringTest.size());
        stringTest.add(4, "Hi!");
        System.out.println(stringTest.toString());
        System.out.println(stringTest.contains("Hi!"));

        MyArrayList<String> h = new MyArrayList<String>();
        h.add(null);
        System.out.println(h.contains(null));

    }
}
