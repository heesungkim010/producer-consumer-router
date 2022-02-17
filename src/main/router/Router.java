package main.router;

public interface Router {
    public void send(Object obj) throws InterruptedException;

    public Object receive() throws InterruptedException;

}
