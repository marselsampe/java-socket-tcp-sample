package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// SERVER : Single Server
// Tipe : One-Way Communication (Server to Client)
// Description : Return the current date of Server to client
public class Server1 {

    private int port = 8081;
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataOutputStream dos = null;

    public Server1() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");

            socket = serverSocket.accept();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

            dos = new DataOutputStream(socket.getOutputStream());
            
            // send message
            String messageToClient = new Date().toString();
            dos.writeUTF(messageToClient);
            dos.flush();
            
            dos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String[] args) throws IOException {
        Server1 server1 = new Server1();
    }
}
