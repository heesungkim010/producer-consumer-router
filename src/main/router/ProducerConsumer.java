package main.router;

public class ProducerConsumer implements Runnable{

    private int testNum = 1;

    @Override
    public void run() {

        for( int i = 0 ; i< 100000; i++){
            String currentThreadName = Thread.currentThread().getName();
            this.multiply();
            this.divide();
            System.out.println("currentThreadName = " + currentThreadName);
            System.out.println("testNum = " + testNum);
        }
    }

    public void multiply(){
        this.testNum += 100;
        this.testNum -= 100;

    }
    public void divide(){
        this.testNum += 100;
        this.testNum -= 100;

    }
}
