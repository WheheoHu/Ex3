package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientGUI extends  JFrame {
    private JTextArea ChatContext;
    private JButton Send;
    private JTextField tf_serverip;
    private JTextField tf_serverport;
    private JTextField tf_username;
    private JPasswordField tf_password;
    private JButton connectButton;
    private JTextField msgtosend;
    private JPanel Pannel_Client;
    private ChatClient chatClient;


    public ClientGUI() {
      super("Client");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setContentPane(Pannel_Client);
      this.setVisible(true);
      this.pack();
      chatClient=new ChatClientImpl();
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    chatClient.connectServer(tf_serverip.getText(),Integer.parseInt(tf_serverport.getText()));
                    ChatContext.append("connect to server: "+tf_serverip.getText()+":"+tf_serverport.getText()+"\n");
                    msgtosend.setEnabled(true);
                    Send.setEnabled(true);
                    ChatContext.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            ChatContext.append(msgtosend.getText());
            }
        });
    }
}
