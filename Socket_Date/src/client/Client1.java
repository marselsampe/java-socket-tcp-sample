package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client1 {

    private String serverName = "localhost";
    private int serverPort = 8081;
    private Socket socket = null;
    private DataInputStream dis = null;

    public Client1() {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());

            dis = new DataInputStream(socket.getInputStream());
            String messageFromServer = dis.readUTF();
            System.out.println("Server Date : " + messageFromServer);

            dis.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Client1 client1 = new Client1();
    }
}
