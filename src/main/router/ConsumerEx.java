package main.router;
import java.util.ArrayList;

public class ConsumerEx implements Runnable {

    private ArrayList<Integer> integers;

    private RouterOneToOne pc;

    public ConsumerEx(RouterOneToOne pc){
        this.pc = pc;
    }

    public RouterOneToOne getPc() {
        return pc;
    }

    public Object receive() throws InterruptedException {
        return pc.receive();
    }

    @Override
    public void run() {
        for( int i = 0 ; i< 100000; i++){
            ObjectToPass objectToPass = new ObjectToPass(i);

            String currentThreadName = Thread.currentThread().getName();
            System.out.println("currentThreadName = " + currentThreadName);

            try {
                Object o = this.receive();
                // convert type after check the class type
                ObjectToPass otp = (ObjectToPass) o;

                System.out.println("received : " + otp.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
