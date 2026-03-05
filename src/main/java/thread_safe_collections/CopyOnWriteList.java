package thread_safe_collections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteList {
    private static final List<String> list = new CopyOnWriteArrayList<>();

    static void main(String[] args) throws InterruptedException {
        Runnable myRunnable = new MyRunnable();

        Thread t0 = new Thread(myRunnable, "Thread 0");
        Thread t1 = new Thread(myRunnable, "Thread 1");
        Thread t2 = new Thread(myRunnable, "Thread 2");
        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(150);
        System.out.println(list);
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            list.add("New string into list: " + name);
        }
    }
}
