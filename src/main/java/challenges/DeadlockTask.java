package challenges;

/**
 * Write code where Thread 1 blocks Resource A and attempts to access Resource B, while Thread 2 blocks Resource B and attempts to access Resource A.
 */
public class DeadlockTask {
    private static final Object resourceAlpha = new Object();
    private static final Object resourceOmega = new Object();

    static void main(String[] args) {
        DeadlockSolution.runSolution();
    }

    static class DeadlockProblem {
        static void runProblem() {
            Thread threadA = new Thread(() -> {
                synchronized (resourceAlpha) {
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    synchronized (resourceOmega) {
                        System.out.println("[THREAD]: " + Thread.currentThread().getName());
                    }
                }
            }, "Thread A");
            Thread threadB = new Thread(() -> {
                synchronized (resourceOmega) {
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    synchronized (resourceAlpha) {
                        System.out.println("[THREAD]: " + Thread.currentThread().getName());
                    }
                }
            }, "Thread B");
            threadA.start();
            threadB.start();
        }
    }

    static class DeadlockSolution {
        static void runSolution() {
            Thread threadA = new Thread(() -> {
                synchronized (resourceAlpha) {
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    synchronized (resourceOmega) {
                        System.out.println("[THREAD]: " + Thread.currentThread().getName());
                    }
                }
            }, "Thread A");
            Thread threadB = new Thread(() -> {
                synchronized (resourceAlpha) {
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                    synchronized (resourceOmega) {
                        System.out.println("[THREAD]: " + Thread.currentThread().getName());
                    }
                }
            }, "Thread B");
            threadA.start();
            threadB.start();
        }
    }
}
