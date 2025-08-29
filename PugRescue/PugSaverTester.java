import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        // Create a dog list that alternates golden and non golden dogs
        MyArrayList<Dog> dogs = new MyArrayList<Dog>();
        for (int i = 0; i < 3; i++) {
            if (i % 3 == 0) {
                dogs.add(new Dog("P"));
            } else if (i % 3 == 1) {
                dogs.add(new Dog("P"));
            } else {
                dogs.add(new Dog("G", "Golden-Doodle"));
            }
        }
        printDogs(dogs);
        PugSaver.rescuePugs(dogs);
        printDogs(dogs);

    }

    public static void printDogs(MyArrayList<Dog> dog) {
        String s = "";
        for (int i = 0; i < dog.size(); i++) {
            s += dog.get(i).getName() + " ";
        }
        System.out.println(s);
    }
}
