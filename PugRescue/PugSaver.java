import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		ArrayList<Dog> goldenList = new ArrayList<Dog>();
		ArrayList<Dog> nonGoldList = new ArrayList<Dog>();
		for (Dog d : list) {
			if (d.getBreed().indexOf("Gold") != -1) {
				goldenList.add(d);
			} else {
				nonGoldList.add(d);
			}
		}
		list.clear();
		list.addAll(nonGoldList);
		list.addAll(goldenList);
	}
}
