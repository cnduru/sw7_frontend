package sw7.cornfield;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Morten on 24-11-2014.
 */
public class ClientThread implements Runnable{

    private final String SERVER_IP = "192.168.1.59";
    private final int SERVER_PORT = 8000;
    private Socket ClientSocket;

    public ClientThread(Socket socket) {
        ClientSocket = socket;
    }

    public void run() {
        try {
            InetAddress serverName = InetAddress.getByName(SERVER_IP);
            ClientSocket = new Socket(serverName, SERVER_PORT);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream()));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
