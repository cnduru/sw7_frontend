package sw7.Cornfieldz;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Client {
    final String SERVER_IP = "192.168.43.03";
    final Integer SERVER_PORT = 11000;

    Socket ClientSocket = null;
    Thread SocketThread = null;
    String Response;


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
            //Error on line below means no connection to server
            //Check: Is server up? Does SERVER_PORT and SERVER_IP match? Are you on the same network?
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream())), true);
            out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document read() {
        Thread ReadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream in;
                try {
                    in = ClientSocket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    Response = bufferedReader.readLine();
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
            return db.parse(is);
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
