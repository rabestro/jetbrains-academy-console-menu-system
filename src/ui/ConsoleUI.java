package ui;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Scanner;

public class ConsoleUI implements UI {
    final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void println(String pattern, Object... args) {
        System.out.println(MessageFormat.format(pattern, args));
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public BigDecimal readNumber() {
        return new BigDecimal(scanner.nextLine());
    }

    @Override
    public SimpleMenu menu() {
        return new ConsoleMenu(this);
    }

    @Override
    public SimpleMenu menu(String title) {
        return new ConsoleMenu(this, title);
    }
}
