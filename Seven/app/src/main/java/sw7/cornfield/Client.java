package sw7.cornfield;

import android.location.Location;

import java.net.*;
import java.io.*;

public class Client {
    private static final String SERVER_IP = "192.168.1.59";
    public static final int SERVER_PORT = 8000;
    Socket socket;

    public Client() {
        Thread cThread = new Thread(new ClientThread());
        cThread.start();
    }

    public void sendData(Location location, String deviceId, String gsmStrength) {
        try {
            //If logcat produces errors here, check that the server port matches SERVER_PORT
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            if(location != null && gsmStrength != null) {
                out.println(String.format("%f;%f;%s;%s", location.getLatitude(), location.getLongitude(), deviceId, gsmStrength));
            } else {
                out.println(String.format("%s, waiting for gps", deviceId));
            }
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

    public void getInfo() {

    }

    public class ClientThread implements Runnable {
        public void run() {
            String ip = SERVER_IP;
            try {
                InetAddress serverName = InetAddress.getByName(ip);
                socket = new Socket(serverName, SERVER_PORT);

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
