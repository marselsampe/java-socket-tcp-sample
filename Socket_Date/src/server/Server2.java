package server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// SERVER : Single Server
// Tipe : One-Way Communication (Server to Client)
// Description : Return the current date of Server every 1 second to client
public class Server2 {

    private int port = 8081;
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataOutputStream dos = null;

    public Server2() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");
            System.out.println("Waiting for client...");

            socket = serverSocket.accept();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

            dos = new DataOutputStream(socket.getOutputStream());
            
            while (true) {
                try {
                    // send message
                    String messageToClient = new Date().toString();
                    dos.writeUTF(messageToClient);
                    dos.flush();
                    Thread.sleep(1000); // sleep for 1 second
                } catch (Exception e) {
                    break;
                }
            }
            dos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }
    
    public static void main(String[] args) throws IOException {
        Server2 server2 = new Server2();
    }
}
