package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SubmitExecutor {
    static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            Future<?> future = executor.submit(new SingleThreadRunnable.Task());
            System.out.println(future.isDone());
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println(future.isDone());
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
