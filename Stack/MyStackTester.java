public class MyStackTester {
    public static void main(String[] args) {
        MyStack<Integer> test = new MyStack<Integer>();
        System.out.println(test.empty());
        // System.out.println(test.peek());
        test.push(1);
        System.out.println(test.empty());
        System.out.println(test.peek());
        test.push(2);
        System.out.println(test.empty());
        System.out.println(test.peek());
        test.pop();
        System.out.println(test.empty());
        System.out.println(test.peek());
        test.pop();
        System.out.println(test.empty());
        // System.out.println(test.peek());
        // System.out.println(test.pop());
        test.push(null);
        System.out.println(test.peek());

    }
}
