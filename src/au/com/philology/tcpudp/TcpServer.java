package au.com.philology.tcpudp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpServer extends BasicNet implements IServerTCP
{

    ServerSocket theServerSocketForListening;
    int port;
    IServerDelegate theServerDelegate;

    public TcpServer(IServerDelegate serverDelegate, IScanDelegate scanDelegate)
    {
        super(scanDelegate);
        this.theServerDelegate = serverDelegate;
    }

    public TcpServer(IServerDelegate serverDelegate)
    {
        super(null);
        this.theServerDelegate = serverDelegate;
    }

    @Override
    public void send(String message, String address, int Port)
    {
        // TODO Auto-generated method stub
        for (CommunicationThread thread : this.allCommunicationThreads)
        {
            if (thread.remoteAddress.equals(address)
                    && thread.remotePort == Port)
            {
                thread.sendMessage(message);
                Debug.print("TcpServer.send: " + message + " sent.");
                break;
            }
        }
    }

    @Override
    public void startListening(int port)
    {
        this.stopListenning();

        // TODO Auto-generated method stub
        this.port = port;
        new Thread(new ServerThread(this.theServerSocketForListening,
                this.port, this)).start();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void stopListenning()
    {
        try
        {
            // TODO Auto-generated method stub
            if (this.theServerSocketForListening != null)
            {
                if (!this.theServerSocketForListening.isClosed())
                {
                    Debug.print("TcpServer.stopListening...");
                    this.theServerSocketForListening.close();
                    this.theServerSocketForListening = null;
                }
            }
        } catch (IOException ex)
        {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null,
                    ex);
        }

        for (CommunicationThread thread : this.allCommunicationThreads)
        {
            try
            {
                if (thread.aClientSocket != null)
                {
                    if (thread.aClientSocket.isConnected())
                    {
                        thread.aClientSocket.close();
                        thread.aClientSocket = null;
                    }
                }
            } catch (IOException ex)
            {
                Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }

        if (this.theServerDelegate != null)
        {
            this.theServerDelegate.ServerDelegateStopListening(this.port);
        }
    }

    @Override
    public boolean isListenning()
    {
        // TODO Auto-generated method stub
        if (this.theServerSocketForListening != null)
        {
            return !this.theServerSocketForListening.isClosed();
        }
        return false;
    }

    class ServerThread implements Runnable
    {

        ICommunicationThreadDelegate communicationThreadDelegate;
        int port;

        public ServerThread(ServerSocket ServerSocket, int port,
                ICommunicationThreadDelegate communicationThreadDelegate)
        {
            this.port = port;
            this.communicationThreadDelegate = communicationThreadDelegate;
        }

        @Override
        public void run()
        {
            try
            {
                stopListenning();

                theServerSocketForListening = new ServerSocket(this.port);

                // TODO Auto-generated method stub
                while (!Thread.currentThread().isInterrupted())
                {
                    try
                    {
                        Debug.print("TcpServer: listening at port " + this.port
                                + "...");
                        if (theServerDelegate != null)
                        {
                            theServerDelegate
                                    .ServerDelegateStartListening(this.port);
                        }
                        Socket aClient = theServerSocketForListening.accept();
                        Debug.print("TcpServer: a client accepted - " + aClient);
                        CommunicationThread aThread = new ServerCommunicationThread(
                                aClient,
                                ServerCommunicationThread.DEFAULT_WELCOME_MSG,
                                theServerDelegate,
                                this.communicationThreadDelegate);
                        aThread.start();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                        break;
                    }

                }
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (theServerDelegate != null)
            {
                theServerDelegate.ServerDelegateStopListening(this.port);
            }
        }
    }

    @Override
    public ArrayList<String> getAllClientsIp()
    {
        // TODO Auto-generated method stub
        ArrayList<String> allClients = new ArrayList<String>();
        for (int i = 0; i < this.allCommunicationThreads.size(); i++)
        {
            CommunicationThread thread = this.allCommunicationThreads.get(i);

            if (thread.isAlive())
            {
                allClients.add(thread.remoteAddress);
            }
        }

        return allClients;
    }

    @Override
    public ArrayList<Integer> getAllClientsPort()
    {
        // TODO Auto-generated method stub
        ArrayList<Integer> allClients = new ArrayList<Integer>();
        for (int i = 0; i < this.allCommunicationThreads.size(); i++)
        {
            CommunicationThread thread = this.allCommunicationThreads.get(i);

            if (thread.isAlive())
            {
                allClients.add(thread.remotePort);
            }
        }

        return allClients;
    }

    @Override
    public void broadcast(String msg)
    {
        // TODO Auto-generated method stub
        for (CommunicationThread thread : this.allCommunicationThreads)
        {
            thread.sendMessage(msg);
        }

        Debug.print("TcpServer.broadcast: " + msg + " sent.");
    }
}
