package challenges;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * An industrial system sends a command via TCP network to a PLC (Programmable Logic Controller).
 * The response should arrive within 500ms. If it fails or takes too long, it should return a default value ("PLC_OFFLINE"). Use CompletableFuture.
 */
public class CLP_PingerTask {
    static void main(String[] args) {
        CompletableFuture<String> clpCall = CompletableFuture.supplyAsync(() -> {
            try {
                // Network latency may or may not exceed 500ms.
                Thread.sleep((long) (Math.random() * 800));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "CLP_OK";
        });
        String clpResult = clpCall
                .completeOnTimeout("CLP_OFFLINE", 500, TimeUnit.MILLISECONDS)
                .exceptionally(ex -> "COMMUNICATION_ERROR: " + ex.getMessage())
                .join();

        System.out.println("[STATUS]: " + clpResult);

    }
}
