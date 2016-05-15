package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class Client {

    private String serverName = "localhost";
    private int serverPort = 8081;
    private Socket socket = null;

    public Client() {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            
            List<String> listNama = (List<String>) object;
            for (int i = 0; i < listNama.size(); i++) {
                System.out.println(listNama.get(i));                
            }            
            
            objectInputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Client client1 = new Client();
    }
}