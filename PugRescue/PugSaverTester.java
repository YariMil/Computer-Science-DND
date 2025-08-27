import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        // Create a dog list that alternates golden and non golden dogs
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                dogs.add(new Dog("Bobby", "Pug"));
            } else if (i % 3 == 1) {
                dogs.add(new Dog("Jeremy", "Golden Retriever"));
            } else {
                
            }
        }
        printDogs(dogs);
        PugSaver.rescuePugs(dogs);
        printDogs(dogs);

    }

    public static void printDogs(ArrayList<Dog> dog) {
        String s = "";
        for (Dog d : dog) {
            s += d.getName() + " ";
        }
        System.out.println(s);
    }
}
