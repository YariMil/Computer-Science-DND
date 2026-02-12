public class HeapPQTester {
    public static void main(String[] args) {
        HeapPQ<Integer> heapTest = new HeapPQ<Integer>();
        heapTest.add(1);
        heapTest.add(19);
        heapTest.add(20);
        heapTest.add(2);
        System.out.println(heapTest.toString());
        heapTest.removeMin();
        System.out.println(heapTest.toString());
        heapTest.removeMin();
        System.out.println(heapTest.toString());
        System.out.println(heapTest.removeMin());

    }
}
