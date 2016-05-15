
import java.io.*;
import java.net.Socket;

public class ChatServerThread extends Thread {

    private Socket socket = null;
    private Server server = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    public ChatServerThread(Server _server, Socket _socket) {
        server = _server;
        socket = _socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to server...");

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            
            while (true) {
                try {
                    String action = objectInputStream.readUTF();
                    switch (action) {
                        case "save":
                            // get data from client
                            Object object = objectInputStream.readObject();
                            Mahasiswa mahasiswa = (Mahasiswa) object;
                            // save object to list
                            server.addMahasiswa(mahasiswa);
                            System.out.println("Save data from "+socket.getRemoteSocketAddress()+" success...");
                            break;
                        case "getJumlah":
                            // send data to client
                            int jumlahMahasiswa=server.getJumlahMahasiswa();
                            objectOutputStream.writeUTF(String.valueOf(jumlahMahasiswa));
                            objectOutputStream.flush();
                            break;
                    }
                } catch (IOException e) {
                    break;
                }
            }
            objectInputStream.close();
            socket.close();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " disconnect from server...");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
}