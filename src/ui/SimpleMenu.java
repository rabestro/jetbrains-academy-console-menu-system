package ui;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static java.text.MessageFormat.format;

public class SimpleMenu implements Menu {
    private final Map<String, MenuEntry> menu = new LinkedHashMap<>();
    private final Map<Property, String> properties = new EnumMap<>(Property.class);

    private boolean isOnlyOnce;

    public SimpleMenu(String title) {
        set(Property.TITLE, title);
        set(Property.ERROR, "Please enter the number from 0 up to {0}");
        set(Property.FORMAT, "{0}. {1}");
        set(Property.EXIT, "Exit");
    }

    @Override
    public Menu set(Property key, String value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public Menu add(String key, String description, Runnable action) {
        menu.put(key, new MenuEntry(description, action));
        return this;
    }

    @Override
    public Menu add(String description, Runnable action) {
        return this.add(String.valueOf(menu.size() + 1), description, action);
    }

    @Override
    public Menu onlyOnce() {
        isOnlyOnce = true;
        return this;
    }

    @Override
    public Menu addExit() {
        menu.put("0", new MenuEntry(properties.get(Property.EXIT), this::onlyOnce));
        return this;
    }

    @Override
    public Menu addExit(String key) {
        menu.put(key, new MenuEntry(properties.get(Property.EXIT), this::onlyOnce));
        return this;
    }

    @Override
    public void run() {
        do {
            System.out.println();
            System.out.println(properties.getOrDefault(Property.TITLE, Property.TITLE.name()));
            menu.forEach((key, entry) -> System.out.println(format(properties.get(Property.FORMAT), key, entry)));
            final var key = new Scanner(System.in).nextLine().toLowerCase();
            System.out.println();
            menu.getOrDefault(key, new MenuEntry("Error",
                    () -> System.out.println(format(properties.get(Property.ERROR), menu.size())))
            ).run();
        } while (!isOnlyOnce);
    }
}
