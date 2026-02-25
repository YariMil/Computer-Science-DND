public class ChocolateHashMapTester {
  public static void main(String[] args) {
    ChocolateHashMap<String, String> s = new ChocolateHashMap<String, String>();
    s.put("A", "BIG A");
    s.put("B", "BIG B");
    s.put("C", "BIG C");
    s.put("D", "BIG D");
    s.put("E", "BIG E");
    s.put("F", "BIG F");
    s.put("G", "small g");
    System.out.println(s.toString());
    System.out.println(s.containsKey("A"));
    System.out.println(s.containsValue("BIG A"));
    System.out.println(s.get("A"));
    System.out.println(s.currentLoadFactor());
    s.put("H", "Medium h");
    System.out.println(s.toString());
    System.out.println(s.remove("A"));
    System.out.println(s.toString());
    System.out.println(s.containsValue("Medium h"));
    s.rehash(500);
    System.out.println(s.toString());
    s.rehash(7);
    System.out.println(s.toString());
    ChocolateHashMap<String, String> test2 = new ChocolateHashMap<String, String>(5, 0.99);
    test2.put("A", "vA");
    System.out.println("A".hashCode());
    test2.put("A2", "vA2");
    System.out.println("A2".hashCode());
    test2.put("B", "vB");
    System.out.println("B".hashCode());
    test2.put("C", "vC");
    System.out.println("C".hashCode());
    System.out.println(test2.toString());
    test2.rehash(8);
    System.out.println(test2.toString());
    System.out.println(test2.remove("C"));
    System.out.println(test2.toString());
    System.out.println(test2.remove("B"));
    System.out.println(test2.remove("A"));
    System.out.println(test2.toString());
    System.out.println(test2.containsValue("vA2"));
    test2.put("A", "vA");
    test2.remove("A2");
    test2.remove("A");
    System.out.println(test2.remove("Anything"));
    System.out.println(test2.toString());
    ChocolateHashMap<String, String> test3 = new ChocolateHashMap<String, String>();
    for (int i = 0; i < 500; i++) {
      test3.put("" + i, "" + i);
    }
    test3.rehash(1);
    System.out.println(test3.toString());
    for (int i = 100; i < 500; i++) {
      test3.remove("" + i);
    }
    System.out.println(test3.toString());
    test3.put("Hello", "Hi");
    System.out.println(test3.toString());
    System.out.println(test3.get("Hello"));
    test3.put("Check", null);
    System.out.println(test3.toString());
    System.out.println(test3.containsValue(null));
    System.out.println(test3.containsKey("Check"));
    // System.out.println(test3.put(null, null));
  }
}
