package sample;

public class Game implements Runnable {
    private final String title;

    Game(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        System.out.println();
        System.out.println("Playing the " + title);
        System.out.println();
    }
}
