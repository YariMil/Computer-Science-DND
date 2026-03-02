import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectedGraph {
    private HashMap<String, List<String>> connections;
    private Path f;

    public static List<String> readAllLines(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    public DirectedGraph() {
        connections = new HashMap<String, List<String>>();
        f = Paths.get("names.txt");

    }

    public static ArrayList<String> readAllLines() {
        ArrayList<String> names = new ArrayList<String>();
        try {


            // Each element is one line from the file
            Path p = Paths.get("NameOfYourFile.txt");
            return (ArrayList<String>) Files.readAllLines(p);
        } catch (Exception e) {
            System.out.println("Couldnt read file");
        }
        return names;
    }

}
