package sw7.cornfield;

import java.net.*;
import java.io.*;

/**
 * Created by Casper on 09-09-2014.
 */
public class Client {
    public Client() {
        Thread cThread = new Thread(new ClientThread());
        cThread.start();
    }

    public class ClientThread implements Runnable {
        public void run() {
            String result = new String();
            String ip = "192.168.1.206";
            try {
                InetAddress serverName = InetAddress.getByName(ip);
                Socket client = new Socket(serverName, 8000);

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                out.write("Hello server");
                out.flush();

                result = in.readLine();


            /*
            OutputStream outToServer = client.getOutputStream();
            OutputStreamWriter osr = new OutputStreamWriter(outToServer);

            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Helle from client");


            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            result = in.toString();
            client.close();
            */


                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
