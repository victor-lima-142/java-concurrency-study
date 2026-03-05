package race_condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockCounter {
    private final Lock locker = new ReentrantLock();
    int count = 0;

    public void increment() {
        locker.lock();
        try {
            count++;
        } finally {
            locker.unlock();
        }
    }
}

class LockRaceCondition {
    static void main(String[] args) throws InterruptedException {
        LockCounter counter = new LockCounter();

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
