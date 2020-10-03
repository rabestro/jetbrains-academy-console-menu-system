package sample;

import ui.CommandMenu;

public class TestCommandMenu {
    public static void main(String[] args) {
        new TestCommandMenu().run();
    }

    void run() {
        System.out.println("Welcome!");

        new CommandMenu()
                .add("new", "a list of new albums with artists and links on Spotify", this::commandNew)
                .addExit()
                .run();
    }

    void commandNew() {
        System.out.println("---NEW RELEASES---\n" +
                "Mountains [Sia, Diplo, Labrinth]\n" +
                "Runaway [Lil Peep]\n" +
                "The Greatest Show [Panic! At The Disco]\n" +
                "All Out Life [Slipknot]");
    }
}
