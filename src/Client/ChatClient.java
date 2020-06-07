package Client;

import java.io.IOException;

public interface ChatClient {
void connectServer(String ServerIP,int ServerPort) throws IOException;

}
