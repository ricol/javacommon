package au.com.rmit.network.tcpudp;

public interface IClientTCP extends IScanTCP
{

    public void send(String message);

    public void send(int code);

    public void send(char[] bytes);

    public void connect(String serverAddress, int port);

    public void disconnect();

    public boolean isConnected();

    public String getServerIp();

    public int getServerPort();
}
