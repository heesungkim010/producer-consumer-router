package main.producers_consumers;

import main.router.Router;
import main.router.RouterOneToOne;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // 1. initialization
        // choose the router type and initialize producer/consumer with the chosen router
        // bufferSize : size of buffer in router
        int bufferSize = 128; // set buffer size
        Router router = new RouterOneToOne(bufferSize);
        ProducerEx producer = new ProducerEx(router);
        ConsumerEx consumer = new ConsumerEx(router);
        // connection made between producer and consumer

        // 2. producer sends data to the router
        ObjectToPass objectToPass = new ObjectToPass(1);
        try {
            producer.send(objectToPass);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. consumer receives data from the router
        try {
            ObjectToPass otp = consumer.receive();
            System.out.println(otp.getClass());

            System.out.println("received : " + otp.getId());
            System.out.println(objectToPass == otp);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4. producer and consumer running by thread
        Thread threadProducer = new Thread(producer, "producer");
        Thread threadConsumer = new Thread(consumer, "consumer");

        threadProducer.start();
        threadConsumer.start();

    }
}