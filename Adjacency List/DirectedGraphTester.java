import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectedGraphTester {
    public static void main(String[] args) {
      ArrayList<String> strList = DirectedGraph.readAllLines();
      for (int i = 0; i < strList.size(); i++) {
        System.out.println(strList.get(i));
      }
    }
}
