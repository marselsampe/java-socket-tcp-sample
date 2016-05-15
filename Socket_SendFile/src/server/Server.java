package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// SERVER : Single Server
// Tipe : One-Way Communication (Server to Client)
// Description : Send file to client
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

            // SEND FILE
            System.out.println("Sending file to client...");
            String projectPath = System.getProperty("user.dir");
            String imagePath = projectPath + "/gambarServer/gambar1.jpg";

            File myFile = new File(imagePath);
            byte[] myByteArray = new byte[(int) myFile.length()];

            FileInputStream fileInputStream = new FileInputStream(myFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.read(myByteArray, 0, myByteArray.length);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(myByteArray, 0, myByteArray.length);
            outputStream.flush();
            System.out.println("Send file success...");
            
            fileInputStream.close();
            bufferedInputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}
