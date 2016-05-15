package chat.server3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatServerThread extends Thread {

    private Socket socket = null;
    private Server server = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;

    public ChatServerThread(Server _server, Socket _socket) {
        server = _server;
        socket = _socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

            bis = new BufferedInputStream(socket.getInputStream());
            dis = new DataInputStream(bis);

            while (true) {
                try {
                    String messageFromClient = dis.readUTF();
                    if (messageFromClient.equals("exit")) {
                        break;
                    }
                    System.out.println("Client [" + socket.getRemoteSocketAddress() + "] : " + messageFromClient);
                } catch (IOException e) {
                    break;
                }
            }
            dis.close();
            socket.close();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " disconnect from server...");
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }
}