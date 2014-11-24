package sw7.cornfield;

import android.location.Location;

import java.net.*;
import java.io.*;

public class Client {
    private Socket ClientSocket;

    public Client() {
        Thread clientThread = new Thread(new ClientThread(ClientSocket));
        clientThread.start();
    }

    public void sendData(String data) {
        try {
            //If logcat produces errors on line below, check that the server port matches SERVER_PORT
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream())), true);
            out.println(String.format("%s", data));

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
            ClientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getInfo() {

    }
}
