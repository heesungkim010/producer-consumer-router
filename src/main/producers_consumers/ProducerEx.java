package main.producers_consumers;

import main.router.Router;

public class ProducerEx implements Runnable {
    private Router router;

    public ProducerEx(Router r){
        this.router = r;
    }

    public void send(ObjectToPass obj) throws InterruptedException {
        router.send(obj);
    }

    @Override
    public void run() {
        for( int i = 0 ; i< 100000; i++){
            ObjectToPass objectToPass = new ObjectToPass(i);

            String currentThreadName = Thread.currentThread().getName();
            System.out.println("currentThreadName = " + currentThreadName);
            try {
                this.send(objectToPass);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sent : " + i);

            //produce();
        }
    }
}
