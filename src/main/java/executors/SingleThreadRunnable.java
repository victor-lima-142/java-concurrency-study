package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadRunnable {
    static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(new Task());
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("[" + name + "]: Testing task");
        }
    }
}
