public class ArrayListTester {
    public static void main(String[] args) {
        // Test efficiency of the add method
        MyArrayList<String> addTest = new MyArrayList<String>();
        for (int i = 0; i < 100000000; i++) {
            addTest.add("Hi!");
        }
        System.out.println("Done with adding!");
        
        System.out.println("Size: " + addTest.size() + ". Should be 10000000");

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

    }
}
