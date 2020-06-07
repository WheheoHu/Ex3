package ServerClass;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

public interface ChatServer {
    String getIP() throws UnknownHostException;
    String getPort();
    void StartServer(Integer port_num) throws IOException;
    boolean isServerStarted();
    Map<Integer,String> getmenbers();
    void CloseServer() throws IOException;
}
