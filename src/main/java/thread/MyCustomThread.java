package thread;

public class MyCustomThread extends Thread {
    private final String nome;

    public MyCustomThread(String nome) {
        super(nome);
        this.nome = nome;
    }

    @Override
    public void run() {
        System.out.println("Thread " + nome + " iniciada: " + Thread.currentThread().getName()); // Simulando trabalho
        for (int i = 0; i < 5; i++) {
            System.out.println(nome + " processando item " + i);
        }
        System.out.println("Thread " + nome + "finalizada.");
    }
}