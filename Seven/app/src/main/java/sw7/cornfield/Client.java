package sw7.cornfield;

import android.location.Location;

import java.net.*;
import java.io.*;

public class Client {
    private static final String SERVER_IP = "192.168.43.234";
    public static final int SERVER_PORT = 8080;
    Socket socket;

    public Client() {
        Thread cThread = new Thread(new ClientThread());
        cThread.start();
    }

    public void sendData(Location location, String deviceId, String gsmStrength) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(String.format("%f,%f,%s,%s",
                    location.getLatitude(), location.getLongitude(), deviceId, gsmStrength));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getInfo()
    {

    }

    public class ClientThread implements Runnable {
        public void run() {
            String ip = SERVER_IP;
            try {
                InetAddress serverName = InetAddress.getByName(ip);
                socket = new Socket(serverName, SERVER_PORT);

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                out.write("Hello server");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
