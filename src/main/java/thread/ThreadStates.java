package thread;

public class ThreadStates {
    static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread threadA = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(2000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Thread-A");
        Thread threadB = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread-B segurando lock por 1s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Thread-B");
        System.out.println("threadA estado: " + threadA.getState());
        threadB.start();
        Thread.sleep(100);
        threadA.start();
        Thread.sleep(100);
        System.out.println("threadA estado: " + threadA.getState());
        System.out.println("threadB estado: " + threadB.getState());
        threadB.join();
        Thread.sleep(100);
        System.out.println("threadA estado: " + threadA.getState());
        threadA.join();
        System.out.println("threadA estado: " + threadA.getState());
    }
}
