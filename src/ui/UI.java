package ui;

import java.math.BigDecimal;

public interface UI {

    void println(String pattern, Object... args);

    String readLine();

    BigDecimal readNumber();

    SimpleMenu menu();

    SimpleMenu menu(String title);

}
