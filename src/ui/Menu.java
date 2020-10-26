package ui;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static java.text.MessageFormat.format;

/**
 * An interface for a simple menu system for console applications.
 */
public interface Menu extends Runnable {

    /**
     * Set additional property for the menu.
     *
     * @param property
     * @param value
     * @return this menu
     */
    Menu set(Property property, String value);

    /**
     * Add new menu entry with key, description and action
     *
     * @param key
     * @param description
     * @param action
     * @return this menu
     */
    Menu add(String key, String description, Runnable action);

    /**
     * Add new menu entry with description and action.
     * The key will be number from 1 (menu.size + 1)
     *
     * @param description
     * @param action
     * @return
     */
    Menu add(String description, Runnable action);

    /**
     * Disable last added menu entry.
     *
     * @return this menu
     */
    Menu disable();

    /**
     * Disable menu entry with specified key
     *
     * @param key
     * @return this menu
     */
    Menu disable(String key);

    /**
     * Enable menu entry with specified key
     *
     * @param key
     * @return this menu
     */
    Menu enable(String key);

    /**
     * The menu should be finished after executing any menu entry
     *
     * @return this menu
     */
    Menu onlyOnce();

    /**
     * Add the exit entry to the menu.
     * <p>
     * The key is specified in Property.EXIT_KEY
     * The description is specified in Property.EXIT
     * <p>
     * If you would like to change default properties
     * you should do this with set method before addExit.
     *
     * @return this menu
     */
    Menu addExit();

    static Menu create() {
        return new SimpleMenu();
    }

    static Menu create(String text) {
        return new SimpleMenu(text);
    }

    enum Property {
        TITLE("Choose your action:"),
        FORMAT("{0}. {1}"),
        ERROR("Please enter the number from 1 to {0} or 0 for exit."),
        EXIT("Exit"),
        EXIT_KEY("0");

        private final String value;

        Property(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    class SimpleMenu implements Menu {

        protected final Map<String, MenuEntry> menu = new LinkedHashMap<>();
        protected final Map<Property, String> properties = new EnumMap<>(Property.class);
        protected MenuEntry defaultEntry = new MenuEntry("Incorrect option",
                () -> System.out.println(format(get(Property.ERROR), menu.size())));
        protected boolean isOnlyOnce;
        protected Scanner scanner = new Scanner(System.in);

        public SimpleMenu() {

        }

        public SimpleMenu(String title) {
            set(Property.TITLE, title);
        }

        @Override
        public SimpleMenu set(Property property, String value) {
            properties.put(property, value);
            return this;
        }

        @Override
        public SimpleMenu add(String key, String description, Runnable action) {
            menu.put(key, new MenuEntry(description, action));
            return this;
        }

        @Override
        public SimpleMenu add(String description, Runnable action) {
            return this.add(String.valueOf(menu.size() + 1), description, action);
        }

        @Override
        public SimpleMenu disable() {
            disable(String.valueOf(menu.size()));
            return this;
        }

        @Override
        public SimpleMenu disable(String key) {
            menu.get(key).isEnabled = false;
            return this;
        }

        @Override
        public SimpleMenu enable(String key) {
            menu.get(key).isEnabled = true;
            return this;
        }

        @Override
        public SimpleMenu onlyOnce() {
            isOnlyOnce = true;
            return this;
        }

        @Override
        public SimpleMenu addExit() {
            menu.put(get(Property.EXIT_KEY), new MenuEntry(get(Property.EXIT), this::onlyOnce));
            return this;
        }

        @Override
        public void run() {
            do {
                System.out.println();
                System.out.println(get(Property.TITLE));
                menu.forEach((key, entry) -> {
                    if (entry.isEnabled) {
                        System.out.println(format(get(Property.FORMAT), key, entry));
                    }
                });
                final var key = scanner.nextLine().toLowerCase();
                System.out.println();
                menu.getOrDefault(key, defaultEntry).run();
            } while (!isOnlyOnce);
        }

        String get(Property property) {
            return properties.getOrDefault(property, property.getValue());
        }

        static final class MenuEntry implements Runnable {
            private final String description;
            private final Runnable action;
            boolean isEnabled = true;

            MenuEntry(final String description, final Runnable action) {
                this.description = description;
                this.action = action;
            }

            @Override
            public String toString() {
                return description;
            }

            @Override
            public void run() {
                action.run();
            }
        }
    }
}
