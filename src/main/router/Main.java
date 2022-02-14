package main.router;

public class Main {

    public static void main(String[] args) {
        ProducerConsumer pc1 = new ProducerConsumer();
        Thread thread1 = new Thread(pc1);
        Thread thread2 = new Thread(pc1);

        thread1.start();
        thread2.start();

    }
}
