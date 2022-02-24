package main.producers_consumers;
import main.router.Router;

import java.util.ArrayList;

public class ConsumerEx implements Runnable {

    private ArrayList<Integer> integers;

    private Router router;

    public ConsumerEx(Router r){
        this.router = r;
    }

    public ObjectToPass receive() throws InterruptedException {
        return router.receive();
    }

    @Override
    public void run() {
        for( int i = 0 ; i< 100000; i++){
            ObjectToPass objectToPass = new ObjectToPass(i);

            String currentThreadName = Thread.currentThread().getName();
            System.out.println("currentThreadName = " + currentThreadName);

            try {
                ObjectToPass otp = this.receive();

                System.out.println("received : " + otp.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
