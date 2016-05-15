package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import model.Mahasiswa;

// SERVER : Single Server
// Tipe : One-Way Communication (Server to Client)
// Description : Send class object to client
public class Server {

    private int port = 8081;
    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");

            socket = serverSocket.accept();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

            // create object
            Mahasiswa mahasiswa=new Mahasiswa();
            mahasiswa.setNim("672011061");
            mahasiswa.setNama("Marsel");
            mahasiswa.setProgdi("Teknik Informatika");
            // send object
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(mahasiswa);
            objectOutputStream.flush();

            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server1 = new Server();
    }
}