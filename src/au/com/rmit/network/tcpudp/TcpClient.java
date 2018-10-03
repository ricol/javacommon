package au.com.rmit.network.tcpudp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpClient extends BasicNet implements IClientTCP
{

    Socket theConnectionSocket;
    ClientCommunicationThread theCommunicationThread;
    IClientDelegate theClientDelegate;

    public TcpClient(IClientDelegate clientDelegate, IScanDelegate scanDelegate)
    {
        super(scanDelegate);
        this.theClientDelegate = clientDelegate;
    }

    @Override
    public void send(String message)
    {
        // TODO Auto-generated method stub
        if (this.theCommunicationThread != null)
        {
            this.theCommunicationThread.sendMessage(message);
        }
    }

    @Override
    public void connect(String serverAddress, int port)
    {
        // TODO Auto-generated method stub
        this.disconnect();

        new Thread(new ClientThread(serverAddress, port, this)).start();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void disconnect()
    {
        // TODO Auto-generated method stub

        if (this.theConnectionSocket != null)
        {
            try
            {
                if (this.theConnectionSocket.isConnected())
                {
                    if (this.theCommunicationThread != null)
                    {
                        this.theCommunicationThread.interrupt();
                        this.theCommunicationThread = null;
                    }
                    this.theConnectionSocket.close();
                    this.theConnectionSocket = null;
                }
            } catch (IOException ex)
            {
                Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

    @Override
    public boolean isConnected()
    {
        // TODO Auto-generated method stub

        if (theConnectionSocket != null)
        {
            return theConnectionSocket.isConnected();
        } else
        {
            return false;
        }
    }

    @Override
    public void CommunicationThreadStart(CommunicationThread thread)
    {
    }

    @Override
    public void CommunicationThreadEnd(CommunicationThread thread)
    {
    }

    @Override
    public void send(int code)
    {
        if (this.theCommunicationThread != null)
        {
            this.theCommunicationThread.sendMessage(code);
        }
    }

    @Override
    public void send(char[] bytes)
    {
        if (this.theCommunicationThread != null)
        {
            this.theCommunicationThread.sendMessage(bytes);
        }
    }

    class ClientThread implements Runnable
    {

        ICommunicationThreadDelegate communicationThreadDelegate;
        String serverAddress;
        int port;

        public ClientThread(String serverAddress, int port,
                ICommunicationThreadDelegate communicationThreadDelegate)
        {
            this.serverAddress = serverAddress;
            this.port = port;
            this.communicationThreadDelegate = communicationThreadDelegate;
        }

        @Override
        public void run()
        {
            // TODO Auto-generated method stub
            try
            {
                disconnect();

                InetAddress serverAddress = InetAddress
                        .getByName(this.serverAddress);
                Debug.print("TcpClient try to connect: " + serverAddress
                        + " at port " + this.port);

                theConnectionSocket = new Socket(serverAddress, this.port);
                Debug.print("TcpClient connected to " + serverAddress
                        + " at port " + this.port);
                theCommunicationThread = new ClientCommunicationThread(
                        theConnectionSocket, theClientDelegate,
                        this.communicationThreadDelegate);
                Debug.print("TcpClient try to launch the communication thread...");
                theCommunicationThread.start();
            } catch (UnknownHostException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    @Override
    public String getServerIp()
    {
        // TODO Auto-generated method stub
        if (this.isConnected())
        {
            return this.theCommunicationThread.remoteAddress;
        }
        return null;
    }

    @Override
    public int getServerPort()
    {
        // TODO Auto-generated method stub
        if (this.isConnected())
        {
            return this.theCommunicationThread.remotePort;
        }
        return -1;
    }

}
