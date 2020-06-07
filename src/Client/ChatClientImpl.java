package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientImpl implements ChatClient {
    @Override
    public void connectServer(String ServerIP, int ServerPort) throws IOException {
        Socket s = new Socket(ServerIP, ServerPort);

        new Thread(new ClientTh(s)).start();
    }
}

class ClientTh implements Runnable {
    Socket socket;

    public ClientTh(Socket s) {
        this.socket = s;
    }

    BufferedReader input;
    PrintWriter output;

    @Override
    public void run() {
        try {
            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output.println("Connected");
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}