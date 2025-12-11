import java.util.Scanner;

/**
 * 
 * Handles interactive navigation of the file system This class reads commands from standard input,
 * interprets them, and invokes operations on the current directory node.
 */
public class Navigator {

    private final FileSystemTree fileSystem;
    private FolderNode currentDirectory;
    private boolean shouldExit;

    /**
     * Constructs a navigator for a given file system tree. The starting location is the root
     * directory.
     */
    public Navigator(FileSystemTree fst) {
        this.fileSystem = fst;
        this.currentDirectory = fst.getRoot();
    }

    /**
     * Starts a command loop that repeatedly reads a line of input, interprets it as a command with
     * arguments, and executes it until a request to terminate is received.
     */
    public void run() {
        shouldExit = false;
        Scanner kb = new Scanner(System.in);

        while (!shouldExit) {
            // Prompt can be customized to show current path if desired.
            String line = kb.nextLine();
            processUserInputString(line);
            System.out.println(currentDirectory.toString());
        }

        kb.close();
    }

    /**
     * Changes the current directory based on a single path argument. Behavior should mirror typical
     * Unix shells: - "." refers to the current directory (no change). - ".." moves to the parent
     * directory (if one exists). - Paths starting with "/" are interpreted from the root directory.
     * - Other paths are interpreted relative to the current directory.
     */
    private void cd(String[] args) {
        FolderNode originalDirectory = currentDirectory;
        if (args.length == 0) {
            return;
        }
        if (args[0].equals("..") && currentDirectory.getParent() != null) {
            currentDirectory = currentDirectory.getParent();
            return;
        }
        if (args[0].equals("/")) {
            currentDirectory = fileSystem.getRoot();
            return;
        }
        String directoryLine = args[0];
        if (directoryLine.charAt(0) == '/') {
            currentDirectory = fileSystem.getRoot();
            directoryLine = directoryLine.substring(1);
        }
        String[] directoryChanges = directoryLine.split("/");
        for (String directory : directoryChanges) {
            if (directory.equals("..") && currentDirectory.getParent() != null) {
                currentDirectory = currentDirectory.getParent();
            } else if (!directory.equals(".")) {
                FileSystemNode directoryChange = currentDirectory.getChildByName(directory);
                if (directoryChange != null && directoryChange.isFolder()) {
                    currentDirectory = (FolderNode) currentDirectory.getChildByName(directory);
                } else {
                    currentDirectory = originalDirectory;
                    return;
                }
            }
        }
    }


    /**
     * Lists all items contained directly in the current directory. Output formatting can mirror
     * typical file system listings.
     */
    private void ls(String[] args) {
        StringBuilder sb = new StringBuilder();
        // ArrayList<FileSystemNode> children = currentDirectory.getChildren();
        for (FileSystemNode child : currentDirectory.getChildren()) {
            String name = child.getName();
            if (child.isFolder()) {
                name += "/";
            }
            sb.append(name);
            if (child != currentDirectory.getChildren()
                    .get(currentDirectory.getChildren().size() - 1)) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * Creates a new directory inside the current directory using the provided name.
     */
    private void mkdir(String[] args) {
        if (args.length == 0) {
            return;
        }
        String folderName = args[0];
        currentDirectory.addFolder(folderName);
    }

    /**
     * Creates a new file inside the current directory with a given name and size.
     */
    private void touch(String[] args) {
        if (args.length == 0) {
            return;
        }
        String fileName = args[0];
        int size = Integer.parseInt(args[1]);
        currentDirectory.addFile(fileName, size);
    }

    /**
     * Searches the current directory and its descendants for nodes with a given name and prints
     * their paths.
     */
    private void find(String[] args) {
        if (args.length == 0) {
            return;
        }
        findHelper(args[0], currentDirectory);
    }

    private void findHelper(String name, FileSystemNode directory) {
        if (directory.getName().equals(name)) {
            System.out.println(directory.toString());
        } else {
            if (directory.isFolder()) {
                FolderNode d = (FolderNode) directory;
                for (FileSystemNode child : d.getChildren()) {
                    findHelper(name, child);
                }
            }
        }
    }

    /**
     * Prints the absolute path of the current directory, from the root to this node.
     */
    private void pwd(String[] args) {
        System.out.println(currentDirectory.toString());
    }

    /**
     * Displays the contents of the current directory as a tree, optionally respecting flags or
     * depth limits if provided by the arguments.
     */
    private void tree(String[] args) {
        int depthLimit = -1;
        if (args.length > 0) {
            depthLimit = Integer.parseInt(args[0]);
        }
        StringBuilder tree = new StringBuilder();
        tree = treeHelper(currentDirectory, tree, 0, depthLimit);
        String returnedTree = tree.substring(1);
        System.out.println(returnedTree);
    }

    private StringBuilder treeHelper(FileSystemNode directory, StringBuilder currTree, int depth,
            int depthLimit) {
        if (depthLimit != -1 && depth >= depthLimit) {
            return currTree;
        }
        if (directory.isFolder()) {
            FolderNode d = (FolderNode) directory;
            // Creating branches
            for (FileSystemNode child : d.getChildren()) {
                currTree.append("\n");
                // Continuing the branches that are already going
                for (int i = 0; i < depth; i++) {
                    if (i == 0) {
                        currTree.append("|   ");
                    } else {
                        currTree.append("    ");
                    }
                }
                currTree.append("|---" + child.getName());
                currTree = treeHelper(child, currTree, depth + 1, depthLimit);
            }
            return currTree;
        } else {
            return currTree;
        }
    }

    /**
     * Prints how many nodes (files and folders) exist in the current directory and all of its
     * subdirectories.
     */
    private void count(String[] args) {
        System.out.println(currentDirectory.getTotalNodeCount() - 1);
    }

    /**
     * Prints the total size of all files reachable from the current directory.
     */
    private void size(String[] args) {
        System.out.println(currentDirectory.getSize());
    }

    /**
     * Prints the depth of the current directory, defined as the number of edges from the root
     * directory down to this directory.
     */
    private void depth(String[] args) {
        System.out.println(currentDirectory.getDepth());
    }

    /**
     * Prints the height of the current directory, defined as the longest downward distance from
     * this directory to any file or subdirectory beneath it. An empty directory has value 0.
     */
    private void height(String[] args) {
        System.out.println(currentDirectory.getHeight());
    }

    /**
     * Signals that the interactive loop should terminate after the current command.
     */
    private void quit(String[] args) {
        shouldExit = true;
    }

    /**
     * Interprets a line of user input by splitting it into a command and arguments, then forwarding
     * control to the appropriate helper method.
     *
     * Example inputs and how they are interpreted: "ls" -> command: "ls" args: []
     *
     * "mkdir docs" -> command: "mkdir" args: ["docs"]
     *
     * "touch notes.txt 100" -> command: "touch" args: ["notes.txt", "100"]
     *
     * "cd .." -> command: "cd" args: [".."]
     */
    public void processUserInputString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.trim().split("\\s+");
        String command = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        switch (command) {
            case "cd":
                cd(args);
                break;
            case "ls":
                ls(args);
                break;
            case "mkdir":
                mkdir(args);
                break;
            case "touch":
                touch(args);
                break;
            case "find":
                find(args);
                break;
            case "pwd":
                pwd(args);
                break;
            case "tree":
                tree(args);
                break;
            case "count":
                count(args);
                break;
            case "size":
                size(args);
                break;
            case "depth":
                depth(args);
                break;
            case "height":
                height(args);
                break;
            case "quit":
                quit(args);
                break;
            default:
                // Unknown commands can be reported back to the user.
                System.out.println("Unrecognized command: " + command);
        }
    }
}


