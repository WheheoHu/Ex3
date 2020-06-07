package ServerClass;

import java.net.Socket;

public class ServerTh implements Runnable {
    Socket clientsocket;

    public ServerTh(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    @Override
    public void run() {

    }
}
