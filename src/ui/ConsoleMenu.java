package ui;

import java.util.EnumMap;
import java.util.Map;

public class ConsoleMenu extends Menu {

    private final Map<Property, String> properties = new EnumMap<>(Property.class);

    private final UI ui;

    public ConsoleMenu(UI userInterface) {
        ui = userInterface;
    }

    public ConsoleMenu(UI userInterface, String title) {
        this(userInterface);
        set(Property.TITLE, title);
    }

    @Override
    public void run() {
        do {
            ui.println("");
            ui.println(get(Property.TITLE));
            menu.forEach((key, entry) -> ui.println(get(Property.FORMAT), key, entry));
            final var key = ui.readLine().toLowerCase();
            ui.println("");
            menu.getOrDefault(key, new MenuEntry("Error",
                    () -> ui.println(get(Property.ERROR), menu.size()))
            ).run();
        } while (!isOnlyOnce);
    }

}
