package ui;

public class CommandMenu extends Menu.SimpleMenu {
    public CommandMenu() {
        set(Property.EXIT_KEY, "exit");
    }

    @Override
    public void run() {
        do {
            final var key = scanner.nextLine().toLowerCase();
            menu.getOrDefault(key, defaultEntry).run();
        } while (!isOnlyOnce);
    }
}
