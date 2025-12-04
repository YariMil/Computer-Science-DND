import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Small manual tester for the solution file-system implementation.
 *
 * This does NOT use JUnit. It just runs a few operations and prints out
 * what it's doing plus the observed behavior.or
 *
 * And also assumes (based on our design):
 *  - FileSystemTree#getRoot() returns a non-null FolderNode
 *  - FolderNode has addFolder(String) and addFile(String, int) that return boolean
 *  - FileSystemNode has getDepth(), getHeight(), getSize(), getTotalNodeCount()
 *  - Navigator has processUserInputString(String) which prints results to System.out
 */
public class FileSystemTester {

    public static void main(String[] args) {

        // 1. Construct a tree and check root
        FileSystemTree tree = new FileSystemTree();
        FolderNode root = tree.getRoot();

        if (root == null) {
            System.out.println("[FAIL] Root is null. FileSystemTree.getRoot() must return a non-null root folder.");
            return;
        } else {
            System.out.println("[PASS] Root is non-null.");
        }

        System.out.println("Root toString(): " + root.toString());
        System.out.println("Expected at root: '/' (or equivalent)");

        // 2. Build a small structure under root
        System.out.println("\n=== Building tree structure under root ===");
        boolean addedDocs = root.addFolder("docs");
        boolean addedSrc = root.addFolder("src");
        FolderNode docs = (FolderNode) root.getChildByName("docs");
        docs.addFolder("Hello");
        FolderNode hello = (FolderNode) docs.getChildByName("Hello");
        hello.addFile("Test", 2);
        // System.out.println(hello.addFolder("Test"));
        boolean addedMainJava = root.addFile("main.java", 120);
        boolean addedReadme = root.addFile("README.md", 80);
        boolean yes = root.containsNameRecursive("Hello");
        System.out.println(yes);
        System.out.println(docs.getHeight());
        int depthRoot = root.getDepth();
        int heightRoot = root.getHeight();
        int sizeRoot = root.getSize();
        int totalNodesRoot = root.getTotalNodeCount();
        System.out.println(totalNodesRoot);
        Navigator navigator = new Navigator(tree);
        navigator.run();
    }
}