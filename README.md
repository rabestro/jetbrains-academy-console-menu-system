# Console Menu System

A simple class for building menus for console applications.This class was designed to make it easy to create simple console menus for educational projects in particular the project of the JetBrains Academy).

### Usage example:

```java
    public static void main(String[] args) {
        final var bigClass = new BigClass("Some big class with methods to call");

        new SimpleMenu("What do you want to do:")
                .add("Play the guessing game", new Game("Guessing Game"))
                .add("Play the sudoku", new Game("Sudoku"))
                .add("Search for an animal", Main::searchAnimal)
                .add("Entering Submenu", new SimpleMenu("This is the submenu")
                        .add("The sub-menu entry", new SomeTask())
                        .add("A simple sub-menu task", () -> System.out.println("A simple task"))
                        .add("First Task in Big Class", bigClass::firstTask)
                        .add("Second Task in Big Class", bigClass::secondTask)
                        .add("One more sub-menu", new SimpleMenu("Select file format:")
                                .add("J", "JSON", () -> bigClass.select("JSON"))
                                .add("X", "XML", () -> bigClass.select("XML"))
                                .add("Y", "YAML", () -> bigClass.select("YAML"))
                                .onlyOnce())
                        .addExit("Back"))
                .addExit()
                .run();
    }
```

