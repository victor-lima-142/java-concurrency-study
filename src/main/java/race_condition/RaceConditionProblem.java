package race_condition;

class CounterProblem {
    int count = 0;

    public void increment() {
        count++;
    }
}

class RaceConditionProblem {
    static void main(String[] args) throws InterruptedException {
        CounterProblem counter = new CounterProblem();

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