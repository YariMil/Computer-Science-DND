import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(MyArrayList<Dog> list) {
		int numToCheck = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBreed().indexOf("Golden") != -1) {
				int endingDog = findEndDog(list, numToCheck);
				if (endingDog == -1) {
					return;
				}
				Dog temp = list.set(endingDog, list.get(i));
				list.set(i, temp);
				
			}
			numToCheck++; 
		}
	}

	public static int findEndDog(MyArrayList<Dog> list, int check) {
		for (int i = list.size() - 1; i > check; i--) {
			if (list.get(i).getBreed().indexOf("Golden") == -1) {
				return i;
			}
		}
		return -1;
	}
}
