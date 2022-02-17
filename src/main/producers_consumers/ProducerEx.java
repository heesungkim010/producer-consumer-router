package main.producers_consumers;

import main.router.Router;

public class ProducerEx implements Runnable {
    private Router pc;

    public ProducerEx(Router pc){
        this.pc = pc;
    }

    public void send(Object obj) throws InterruptedException {
        pc.send(obj);
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
