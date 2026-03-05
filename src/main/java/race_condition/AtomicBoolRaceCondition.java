package race_condition;

import java.util.concurrent.atomic.AtomicBoolean;

class MyBoolRunnable implements Runnable {
    AtomicBoolean bool = new AtomicBoolean(false);

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + bool.compareAndExchange(false, true));
    }
}

public class AtomicBoolRaceCondition {
    static void main(String[] args) {
        Runnable myBoolRunnable = new MyBoolRunnable();

        Thread tA = new Thread(myBoolRunnable, "Thread-A");
        Thread tB = new Thread(myBoolRunnable, "Thread-B");
        Thread tC = new Thread(myBoolRunnable, "Thread-C");

        tA.start();
        tB.start();
        tC.start();
    }
}
