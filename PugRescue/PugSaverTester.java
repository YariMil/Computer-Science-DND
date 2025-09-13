import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        // Create a dog list that alternates golden and non golden dogs
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        for (int i = 0; i < 1000000; i++) {
            if (i % 2 == 0) {
                dogs.add(new Dog("P"));
            } else {
                dogs.add(new Dog("G", "Golden-Doodle"));
            }
        }
        // printDogs(dogs);
        PugSaver.rescuePugs(dogs);
        // printDogs(dogs);
    }

    // This method is for accuracy testing, makes it easier to see accuracy errors
    public static void printDogs(ArrayList<Dog> dog) {
        System.out.println(dog.size());
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < dog.size(); i++) {
            try {
                s.append(dog.get(i).getName());
                s.append(" ");
            } catch (Exception e) {
                System.out.println();
            }
        }
        System.out.println(s);
    }
}
