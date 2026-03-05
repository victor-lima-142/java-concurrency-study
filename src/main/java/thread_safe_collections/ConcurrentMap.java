package thread_safe_collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {
    private static final Map<String, String> map = new ConcurrentHashMap<>();

    static void main(String[] args) throws InterruptedException {
        Runnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable, "Thread 0");
        Thread t1 = new Thread(myRunnable, "Thread 1");
        Thread t2 = new Thread(myRunnable, "Thread 2");
        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(150);
        System.out.println(map);
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            map.put(name, "New string into list: ");
        }
    }
}
