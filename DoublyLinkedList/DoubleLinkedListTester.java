public class DoubleLinkedListTester {
    public static void main(String[] args) {
        Nucleotide[] doubleTest = new Nucleotide[] {Nucleotide.A, Nucleotide.C, Nucleotide.G, Nucleotide.T};
        DoublyLinkedList d = new DoublyLinkedList(doubleTest);
        System.out.println(d.toString());
        d.add(0, Nucleotide.A);
        System.out.println(d.toString());
        d.add(5, Nucleotide.C);
        System.out.println(d.toString());
        d.add(2, Nucleotide.T);
        System.out.println(d.toString());
        d.remove(2);
        System.out.println(d.toString());
        System.out.println(d.indexOf(Nucleotide.C));
        d.remove(Nucleotide.C);
        System.out.println(d.toString());

    }
}
