package sample;

public class BigClass {
    private final String config;
    private long secondCounter;
    private long firstCounter;

    public BigClass(String config) {
        this.config = config;
        secondCounter = 0;
    }

    public void firstTask() {
        System.out.println("Running first task in Big Class with " + config);
        System.out.println("This task launched " + ++firstCounter + " times.");
    }

    public void secondTask() {
        secondCounter++;
        System.out.println("Running second task in Big Class with " + config);
        System.out.println("This task launched " + secondCounter + " times.");
    }
}
