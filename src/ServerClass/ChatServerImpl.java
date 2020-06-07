package ServerClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

public class ChatServerImpl implements ChatServer {


    @Override
    public String getIP() throws UnknownHostException {
        if (InetAddress.getLocalHost().getHostAddress() == null) {
            return "Error to get IP Address,Please input manually!";
        } else {
            return InetAddress.getLocalHost().getHostAddress();
        }
    }

    @Override
    public String getPort() {
        return Integer.toString(serverSocket.getLocalPort());
    }


    private static ServerSocket serverSocket;

    @Override
    public void StartServer(Integer port_num) throws IOException {
        serverSocket = new ServerSocket(port_num);
        System.out.println("Server Start");
        ServerState = true;
         new Thread(new SocketTh()).start();
    }

    private Map<Integer, String> members = null;

    class SocketTh implements Runnable {


        int Member_count = 0;
        Socket socketclient;
        String client_msg = "";
        String client_name = "";
        BufferedReader input;
        PrintWriter output;

        @Override
        public void run() {
            try {
                while (isServerStarted()) {
                    socketclient = serverSocket.accept();

                    output = new PrintWriter(socketclient.getOutputStream());
                    input = new BufferedReader(new InputStreamReader(socketclient.getInputStream()));
                    client_name = input.readLine();
                    members.put(Member_count, client_name);
                    Member_count++;
                    System.out.println(socketclient.getRemoteSocketAddress().toString());
                    new Thread(new ServerTh(socketclient)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean ServerState = false;

    @Override
    public boolean isServerStarted() {
        return ServerState;
    }

    @Override
    public Map<Integer, String> getmenbers() {
        return members;
    }

    @Override
    public void CloseServer() throws IOException {
        serverSocket.close();
        ServerState = false;
    }
}
