package sample;

import menu.Menu;

public class Main {

    public static void main(String[] args) {
        new Menu("What do you want to do:")
                .add("Play the guessing game", new Game())
                .add("Search for an animal", Main::searchAnimal)
                .add("Entering Submenu",
                        new Menu("This is the submenu")
                                .add("The sub-menu entry", new SomeTask()))
                .run();
    }

    static void searchAnimal() {
        System.out.println("Search for an animal");
    }
}
