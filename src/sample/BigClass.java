package sample;

public class BigClass {
    private final String config;
    private long secondCounter;

    public BigClass(String config) {
        this.config = config;
        secondCounter = 0;
    }

    public void firstTask() {
        System.out.println();
        System.out.println("Running first task in Big Class with " + config);
        System.out.println();
    }
    public void secondTask() {
        secondCounter++;
        System.out.println();
        System.out.println("Running second task in Big Class with " + config);
        System.out.println("This task launched " + secondCounter + " times.");
    }
}
