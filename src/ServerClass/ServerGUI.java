package ServerClass;


import javax.swing.*;
import java.io.IOException;
import java.net.UnknownHostException;

public class ServerGUI extends JFrame {

    private JPanel MainP;
    private JTextArea ServerMas;
    private JButton cleanButton;
    private JTextField Port_Text;
    private JButton ServerStartOrStop;
    private JTextField IP_Text;
    private JTextField TF_ServerState;
    private JList InChatList;

    private final ChatServer chatServer;

    public ServerGUI() throws UnknownHostException {
        super("Server");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainP);
        this.setVisible(true);
        this.pack();


        chatServer = new ChatServerImpl();
        setIP(chatServer.getIP());
        cleanButton.addActionListener(actionEvent -> ServerMas.setText(""));
        setServerState();


        ServerStartOrStop.addActionListener(actionEvent -> {
            if (!chatServer.isServerStarted()) {
                try {
                    int port;
                    try {
                        port = Integer.parseInt(Port_Text.getText());
                    } catch (NumberFormatException numberFormatException) {
                        ServerMas.append("Use auto ports\n");
                        port = 0;
                        numberFormatException.printStackTrace();
                    }
                    chatServer.StartServer(port);
                    new Thread(new th_members()).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Port_Text.setText(chatServer.getPort());

                ServerMas.append("Server Started at port: " + Port_Text.getText() + "\n");
            } else {

                try {
                    chatServer.CloseServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ServerMas.append("Server Closed\n");
            }

            setServerState();

        });


    }

    public void setServerState() {
        if (chatServer.isServerStarted()) {
            TF_ServerState.setText("Server Started");
            ServerStartOrStop.setText("Close Server");
        } else {
            TF_ServerState.setText("Server Close");
            ServerStartOrStop.setText("Start Server");
        }
    }

    public void setIP(String IPAddress) {
        IP_Text.setText(IPAddress);
    }

    class th_members implements Runnable {

        @Override
        public void run() {
            while (chatServer.isServerStarted()) {
               chatServer.getmenbers();
            }
        }
    }

}
