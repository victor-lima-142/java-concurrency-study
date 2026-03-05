package race_condition;

class SynchronizedCounter {
    int count = 0;

    public synchronized void increment() {
        count++;
    }
}

class SynchronizedRaceCondition {
    static void main(String[] args) throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();

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