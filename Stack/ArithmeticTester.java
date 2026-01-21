public class ArithmeticTester {
    public static void main(String[] args) {
        String classic = "9 + 4 * 3 / 2";
        String stout = Arithmetic.convertClassicToStout(classic);
        System.out.println(stout);
    }
}
