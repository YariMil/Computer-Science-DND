import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree. A directory can contain other directories and
 * files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(String name, FolderNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Returns a list view of the children contained directly inside this directory. Modifying the
     * returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        // TODO: return the list of child nodes (possibly a defensive copy)
        return children;
    }

    /**
     * Searches the children of this directory for a node whose name matches the input. Only direct
     * children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        // TODO: scan children for a matching name and return the node if found
        for (FileSystemNode child : children) {
            if (child.getName().equals(childName)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Creates a new file directly inside this directory with the given name and size. If a child
     * with the same name already exists, no file is created and false is returned. Otherwise the
     * new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        if (getChildByName(fileName) == null) {
            FileNode newFile = new FileNode(this, fileName, size);
            children.add(newFile);
            return true;
        }
        return false;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given name. If a child
     * with the same name already exists, no folder is created and false is returned. Otherwise the
     * new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        if (getChildByName(folderName) == null) {
            FolderNode newFolder = new FolderNode(folderName, this);
            children.add(newFolder);
            return true;
        }
        return false;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name matches the input.
     * When a match is found, its full path can be printed by the caller using toString().
     */
    public boolean containsNameRecursive(String searchName) {
        FileSystemNode childFolder = getChildByName(searchName);
        if (childFolder != null) {
            System.out.println(childFolder.toString());
            return true;
        } 
        for (FileSystemNode child : children) {
            if (child.isFolder()) {
                FolderNode childCheck = (FolderNode) (child);
                if (childCheck.containsNameRecursive(searchName)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int getHeight() {
        // TODO: compute the maximum height among children; empty folders have value 0
        if (children.size() == 0) {
            return 0;
        }
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (FileSystemNode child : children) {
            heights.add(child.getHeight());
        }
        int max = -1;
        for (Integer i : heights) {
            if (i > max) {
                max = i;
            }
        }
        return max + 1;
    }

    @Override
    public int getSize() {
        int sum = 0;
        for (int i = 0; i < children.size(); i++) {
            sum += children.get(i).getSize();
        }
        return sum;
    }

    @Override
    public int getTotalNodeCount() {
        int totalCount = 0;
        for (FileSystemNode child : children) {
            totalCount += child.getTotalNodeCount();
        }
        return totalCount + 1;
    }
}
