package sw7.cornfield;

import android.location.Location;

import java.net.*;
import java.io.*;

public class Client {
    private static final String SERVER_IP = "172.25.18.10";
    public static final int SERVER_PORT = 4444;
    Socket socket;

    public Client() {
        Thread cThread = new Thread(new ClientThread());
        cThread.start();
    }

    public void sendLocation(Location location) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),
                    true);
            out.println(String.format("%f,%f",
                    location.getLatitude(), location.getLongitude()));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
