package au.com.philology.tcpudp;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddress
{

    public static String getLocalAddress()
    {
        String address = "Unknown";

        try
        {
            InetAddress addr = InetAddress.getLocalHost();

            address = addr.toString();
            Debug.print("address: " + address);
            address = address.substring(address.indexOf("/") + 1);
        } catch (UnknownHostException e)
        {
            Debug.print("Exception: " + e);
        }

        return address;
    }
}
