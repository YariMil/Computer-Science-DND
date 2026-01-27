public class ArithmeticTester {
    public static void main(String[] args) {
        String classic = "4 + 1";
        String stout = Arithmetic.convertClassicToStout(classic);
        System.out.println(stout);
        String stoutTest = "3 4 3 - 5 1 - + *";
        System.out.println(Arithmetic.evaluateStout(stoutTest));
        System.out.println(Arithmetic.evaluate(classic));
        System.out.println(Arithmetic.evaluate("2 * ( -5 - 2 )"));
        // System.out.println(Arithmetic.evaluateStout(""));
    }
}
