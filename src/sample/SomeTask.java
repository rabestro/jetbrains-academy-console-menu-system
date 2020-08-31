package sample;

public class SomeTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Some useful job");
    }
}
