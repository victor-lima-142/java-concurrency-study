package thread;

class MainThread {
    static void main(String[] args) {
        MyCustomThread t1 = new MyCustomThread("Worker-A");
        MyCustomThread t2 = new MyCustomThread("Worker-B");
        t1.start();
        t2.start();
    }
}