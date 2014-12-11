package sw7.Cornfieldz;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.net.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Client {
    private Socket ClientSocket = null;
    private Thread SocketThread = null;
    private Thread ReadThread = null;
    private String Response = "";

    private final String SERVER_IP = "192.168.43.03";
    private final int SERVER_PORT = 11000;

    public Client() {

        SocketThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress serverName = InetAddress.getByName(SERVER_IP);
                    ClientSocket = new Socket(serverName, SERVER_PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        SocketThread.start();
    }

    public void send(String data) {
        try {
            SocketThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //If logcat produces errors on line below, check that the server is running and that port matches SERVERPORT
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream())), true);
            out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document read() {
        ReadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream in;
                try {
                    in = ClientSocket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    line = bufferedReader.readLine();

                    while (line != null) {
                        Response += line;
                        line = bufferedReader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ReadThread.start();

        try {
            ReadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(Response));
            Document doc = db.parse(is);
            return doc;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            ClientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
