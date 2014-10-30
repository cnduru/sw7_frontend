package sw7.cornfield;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientListener extends Activity {

    private ServerSocket ServerSocket;
    private Handler UpdateConversationHandler = new Handler();
    private Thread ServerThread = new Thread(new ServerThread());
    private TextView Text;

    public static final int SERVERPORT = 8080;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = (TextView) findViewById(R.id.server);
        this.ServerThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            ServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ServerThread implements Runnable {

        public void run() {
            Socket socket = null;
            try {
                ServerSocket = new ServerSocket(SERVERPORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!Thread.currentThread().isInterrupted()) {

                try {

                    socket = ServerSocket.accept();

                    CommunicationThread commThread = new CommunicationThread(socket);
                    new Thread(commThread).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CommunicationThread implements Runnable {

        private Socket clientSocket;

        private BufferedReader input;

        public CommunicationThread(Socket clientSocket) {

            this.clientSocket = clientSocket;

            try {

                this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {

            while (!Thread.currentThread().isInterrupted()) {

                try {

                    String read = input.readLine();

                    UpdateConversationHandler.post(new updateUIThread(read));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class updateUIThread implements Runnable {
        private String msg;
        private boolean first = true;

        public updateUIThread(String str) {
            this.msg = str;
        }

        @Override
        public void run()
        {
            if(msg != null && first == true)
            {
                Text.setText(Text.getText().toString()+"\nClient Says: "+ msg + "\n");
            }else if (msg != null)
            {
                Text.setText(Text.getText().toString()+"Client Says: "+ msg + "\n");
            }
        }
    }
}

