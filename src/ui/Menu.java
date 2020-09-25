package ui;

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

}
