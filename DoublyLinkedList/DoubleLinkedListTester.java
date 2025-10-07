public class DoubleLinkedListTester {
    public static void main(String[] args) {
        Nucleotide[] doubleTest =
                new Nucleotide[] {Nucleotide.A, Nucleotide.C, Nucleotide.G, Nucleotide.T};
        DoublyLinkedList d = new DoublyLinkedList(doubleTest);
        System.out.println(d.toString());
        d.add(0, Nucleotide.C);
        System.out.println(d.toString());
        System.out.println(d.getHead().getValue());
        d.add(5, Nucleotide.G);
        System.out.println(d.toString());
        System.out.println(d.getTail().getValue());
        System.out.println("+============================+");
        d.add(5, Nucleotide.C);
        System.out.println(d.toString());
        d.add(2, Nucleotide.T);
        System.out.println(d.toString());
        d.remove(2);
        System.out.println(d.toString());
        System.out.println(d.indexOf(Nucleotide.C));
        d.remove(Nucleotide.C);
        System.out.println(d.toString());
        System.out.println(d.remove(5));
        System.out.println(d.getTail().getValue());
        System.out.println(d.toString());
        System.out.println(d.getTail().getNext().getValue());

        System.out.println("==== Testing removing and adding things weirdly ===");
        System.out.println("====== Testing additional methods =====");
        Nucleotide[] additionalTest = new Nucleotide[] {Nucleotide.T, Nucleotide.T, Nucleotide.T};
        DoublyLinkedList secondTest = new DoublyLinkedList(additionalTest);
        DoublyLinkedList thirdTest = new DoublyLinkedList();
        thirdTest.add(Nucleotide.A);
        thirdTest.add(Nucleotide.A);
        for (int i = 0; i < 20; i++) {
            thirdTest.add(Nucleotide.C);
        }
        System.out.println(thirdTest.toString());
        System.out.println("Removing!");
        thirdTest.removeCCCCCCCCGGGGGGGG(thirdTest.getHead().getNext());
        System.out.println(thirdTest.toString());
        // System.out.println(thirdTest.limitedToString(2));
        System.out.println("=== Testing deleteSegment ===");
        Nucleotide[] deleteTest =
                new Nucleotide[] {Nucleotide.C, Nucleotide.C, Nucleotide.C, Nucleotide.C};
        DoublyLinkedList deleteT = new DoublyLinkedList(deleteTest);
        System.out.println(thirdTest.deleteSegment(deleteT));
        System.out.println(thirdTest.toString());
        System.out.println(thirdTest.getTail().getValue());
        System.out.println("== Testing deleteCG again but with 1 less than needed");
        DoublyLinkedList fourthTest = new DoublyLinkedList();
        for (int i = 0; i < 15; i++) {
            fourthTest.add(Nucleotide.A);
        }
        try {
            fourthTest.removeCCCCCCCCGGGGGGGG(fourthTest.getHead());
        } catch (Exception e) {
            System.out.println("Caught exception! *********");
        }
        System.out.println("== Test addSegment ==");
        d.addSegmentToEnd(secondTest);
        System.out.println(d.toString());
        System.out.println(d.getTail().getValue());
        System.out.println("== Testing removeThree ==");
        System.out.println(d.deleteLastThree());
        System.out.println(d.toString());
        deleteT.remove(Nucleotide.C);
        System.out.println(deleteT.toString());
        System.out.println(deleteT.deleteLastThree());
        System.out.println(deleteT.toString());
        DoublyLinkedList badDelete = new DoublyLinkedList();
        badDelete.add(Nucleotide.A);
        badDelete.add(Nucleotide.A);
        System.out.println(badDelete.deleteLastThree());
        System.out.println(badDelete.toString());
        System.out.println("== Testing replaceA ==");
        DoublyLinkedList aTest = new DoublyLinkedList();
        for (int i = 0; i < 4; i++) {
            aTest.add(Nucleotide.A);
        }
        System.out.println(aTest.toString());
        aTest.replaceEveryAWithTAC();
        System.out.println(aTest.toString());
        System.out.println("======= Testing with empty lists now =======");
        DoublyLinkedList emptyTest = new DoublyLinkedList();
        System.out.println(emptyTest.toString());
        System.out.println(emptyTest.getHead());
        System.out.println(emptyTest.getTail());
        emptyTest.add(Nucleotide.A);
        System.out.println(emptyTest.getHead());
        System.out.println(emptyTest.getTail());
        System.out.println(emptyTest.toString());
        System.out.println(emptyTest.remove(0));
        System.out.println(emptyTest.toString());
        System.out.println(emptyTest.getHead());
        System.out.println(emptyTest.getTail());
        System.out.println(emptyTest.getSentinel());
        emptyTest.add(0, Nucleotide.A);
        System.out.println(emptyTest.getHead().getValue());
        System.out.println(emptyTest.getTail().getValue());
        System.out.println(emptyTest.toString());
        emptyTest.add(1, Nucleotide.C);
        System.out.println(emptyTest.getHead().getValue());
        System.out.println(emptyTest.getTail().getValue());
        System.out.println(emptyTest.toString());
        emptyTest.add(0, Nucleotide.T);
        System.out.println(emptyTest.getHead().getValue());
        System.out.println(emptyTest.getTail().getValue());
        System.out.println(emptyTest.toString());
        System.out.println(secondTest.contains(null));
        System.out.println(secondTest.indexOf(null));
        System.out.println(emptyTest.remove(0));
        System.out.println(emptyTest.remove(0));
        System.out.println(emptyTest.remove(0));
        System.out.println(emptyTest.toString());
        System.out.println(emptyTest.getHead());
        System.out.println(emptyTest.getTail());
        System.out.println(thirdTest.toString());
        System.out.println(thirdTest.deleteSegment(new DoublyLinkedList(new Nucleotide[] {Nucleotide.A, Nucleotide.A, Nucleotide.A})));
        System.out.println(thirdTest.toString());



    }
}
