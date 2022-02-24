package main.router;

import main.producers_consumers.ObjectToPass;

import java.util.concurrent.Semaphore;

public class RouterManyToOne implements Router{

    private int in, out, bufferSize;
    private Semaphore mutexP, mutexC, nrfull, nrempty;
    private ObjectToPass[] buffer;

    public RouterManyToOne(int bufferSize){
        this.in = 0;
        this.out = 0;
        this.bufferSize = bufferSize;
        this.mutexP = new Semaphore(1, true);
        this.mutexC = new Semaphore(1, true);
        this.nrfull = new Semaphore(0, true);
        this.nrempty = new Semaphore(bufferSize, true);
        this.buffer = new ObjectToPass[bufferSize];
    }

    @Override
    public void send(ObjectToPass obj) throws InterruptedException {
        mutexP.acquire();
        nrempty.acquire();

        buffer[in] = obj;
        in = (in+1) % this.bufferSize;

        nrfull.release();
        mutexP.release();
    }

    @Override
    public ObjectToPass receive() throws InterruptedException {

        nrfull.acquire();

        ObjectToPass output = buffer[out];
        out = (out+1) % this.bufferSize;

        nrempty.release();
        return output;
    }

}
