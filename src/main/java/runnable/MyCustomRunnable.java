package runnable;

import java.util.List;

public class MyCustomRunnable implements Runnable {
    private final List<String> dados;
    private final String processorId;

    public MyCustomRunnable(List<String> dados, String processorId) {
        this.dados = dados;
        this.processorId = processorId;
    }

    @Override
    public void run() {
        System.out.println("[" + processorId + "] Iniciando processamento de " + dados.size() + " itens na thread: " + Thread.currentThread().getName());
        for (String item : dados) {
            processar(item);
        }
        System.out.println("[" + processorId + "] Concluido.");
    }

    private void processar(String item) {
        System.out.println("[" + processorId + "] Processou: " + item);
    }


}