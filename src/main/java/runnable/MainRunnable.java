package runnable;

import java.util.List;

public class MainRunnable {
    static void main(String[] args) throws InterruptedException {
        List<String> lote1 = List.of("A", "B", "C");
        List<String> lote2 = List.of("D", "E", "F");
        Runnable tarefa1 = new MyCustomRunnable(lote1, "P1");
        Runnable tarefa2 = new MyCustomRunnable(lote2, "P2");
        Thread t1 = new Thread(tarefa1::run, "thread-processador-1");
        Thread t2 = new Thread(tarefa2, "thread-processador-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Todos os processamentos concluidos.");
        Thread t3 = new Thread(() -> System.out.println("Tarefa lambda em: " + Thread.currentThread().getName()), "thread-lambda");
        t3.start();
    }
}
