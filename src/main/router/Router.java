package main.router;

import main.producers_consumers.ObjectToPass;

public interface Router {
    public void send(ObjectToPass obj) throws InterruptedException;

    public ObjectToPass receive() throws InterruptedException;

}
