package race_condition;

import java.util.concurrent.atomic.AtomicReference;

class MyRefRunnable implements Runnable {
    AtomicReference<Boolean> bool = new AtomicReference<>(false);

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + bool.compareAndSet(false, true));
    }
}

public class AtomicRefCondition {
    static void main(String[] args) {
        Runnable myBoolRunnable = new MyRefRunnable();

        Thread tA = new Thread(myBoolRunnable, "Thread-A");
        Thread tB = new Thread(myBoolRunnable, "Thread-B");
        Thread tC = new Thread(myBoolRunnable, "Thread-C");

        tA.start();
        tB.start();
        tC.start();
    }
}
