package race_condition;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {
    AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }
}

class AtomicRaceCondition {

    static void main(String[] args) throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }, "Thread A");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }, "Thread B");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.count);
    }
}
