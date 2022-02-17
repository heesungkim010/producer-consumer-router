package main.router;

import java.util.concurrent.Semaphore;

public class RouterManyToOne implements Router{

    private int in, out, bufferSize;
    private Semaphore mutexP, mutexC, nrfull, nrempty;
    private Object[] buffer;

    public RouterManyToOne(int bufferSize){
        this.in = 0;
        this.out = 0;
        this.bufferSize = bufferSize;
        this.mutexP = new Semaphore(1, true);
        this.mutexC = new Semaphore(1, true);
        this.nrfull = new Semaphore(0, true);
        this.nrempty = new Semaphore(bufferSize, true);
        this.buffer = new Object[bufferSize];
    }

    @Override
    public void send(Object obj) throws InterruptedException {
        mutexP.acquire();
        nrempty.acquire();

        buffer[in] = obj;
        in = (in+1) % this.bufferSize;

        nrfull.release();
        mutexP.release();
    }

    @Override
    public Object receive() throws InterruptedException {

        nrfull.acquire();

        Object output = buffer[out];
        out = (out+1) % this.bufferSize;

        nrempty.release();
        return output;
    }

}
