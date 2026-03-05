package executors;

import java.util.Random;
import java.util.concurrent.*;

public class ExecutorCallable {
    static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            Future<String> result = executor.submit(new Task());
            String str = result.get(1L, TimeUnit.MILLISECONDS);
            System.out.println(result.isDone());
            System.out.println(str);
            System.out.println(result.isDone());
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(result.isDone());
        } catch (ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }

    public static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(500);
            String name = Thread.currentThread().getName();
            return "[" + name + "]: Testing task" + new Random().nextInt();
        }
    }
}
