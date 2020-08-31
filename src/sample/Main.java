package sample;

import menu.Menu;

public class Main {

    public static void main(String[] args) {
        final var bigClass = new BigClass("Some Configuration");

        new Menu("What do you want to do:")
                .add("Play the guessing game", new Game("Guessing Game"))
                .add("Play the sudoku", new Game("Sudoku"))
                .add("Search for an animal", Main::searchAnimal)
                .add("Entering Submenu", new Menu("This is the submenu")
                        .add("The sub-menu entry", new SomeTask())
                        .add("A simple sub-menu task", () -> System.out.println("A simple task"))
                        .add("First Task in Big Class", bigClass::firstTask)
                        .add("Second Task in Big Class", bigClass::secondTask))
                .run();
    }

    static void searchAnimal() {
        System.out.println("Search for an animal");
    }
}
