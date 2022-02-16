package main.ex;

import main.router.RouterOneToOne;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // 1. make router object and initialize the producer and consumer objects
        // bufferSize : size of buffer in router
        int bufferSize = 128;
        RouterOneToOne router = new RouterOneToOne(bufferSize);
        ProducerEx producer = new ProducerEx(router);
        ConsumerEx consumer = new ConsumerEx(router);

        // 2. producer sends data to the router
        ObjectToPass objectToPass = new ObjectToPass(1);
        try {
            producer.send(objectToPass);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. consumer receives data from the router
        try {
            Object o = consumer.receive();
            System.out.println(o.getClass());

            // convert type after check the class type
            ObjectToPass otp = (ObjectToPass) o;

            System.out.println("received : " + otp.getId());
            System.out.println(objectToPass == otp);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long beforeTime = System.currentTimeMillis();

        // 4. producer and consumer running by thread
        Thread threadProducer = new Thread(producer, "producer");
        Thread threadConsumer = new Thread(consumer, "consumer");

        threadProducer.start();
        threadConsumer.start();

    }
}