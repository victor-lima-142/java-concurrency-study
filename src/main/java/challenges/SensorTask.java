package challenges;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Simulate reading data from 3 industrial temperature sensors.
 * Each reading takes a random amount of time (1 to 3 seconds).
 * Use Callable, submit it to a 3-threaded ExecutorService, and print the results once all readings are complete.
 */
public class SensorTask {
    static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            Callable<Integer> callable = new ReadSensor();
            Callable<Integer>[] callables = new Callable[]{callable,callable,callable};
            List<Future<Integer>> futures = executor.invokeAll(Arrays.stream(callables).toList());
            for (Future<Integer> future : futures) {
                System.out.println("Readed temperature: " + future.get());
            }
        } catch (Exception e) {
            System.out.println("\n[ERROR]: " + e.getMessage());
            System.out.println("\n[CAUSE]: " + e.getCause() + "\n");
        } finally {
            executor.shutdownNow();
        }
    }

    static class ReadSensor implements Callable<Integer> {
        @Override
        public Integer call() throws InterruptedException {
            String name = Thread.currentThread().getName();
            System.out.println("Thread " + name + " reading temperature...");
            int sleepValue = ThreadLocalRandom.current().nextInt(1, 110);
            Thread.sleep(Long.parseLong(Integer.toString(sleepValue)));
            int temperature = (int) (Math.random() * 50 + 20);
            System.out.println("Thread " + name + " defines temperature: " + temperature);
            return temperature;
        }
    }
}
