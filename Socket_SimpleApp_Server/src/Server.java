import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// SERVER : Multi Server
// TIPE : Two-Way Communication (Client to Server, Server to Client)
// Description : 
// Example of Server that receive data from client and save it to List
// and also send info total of Data to client
public class Server implements Runnable {

    private int port = 8081;
    private ServerSocket serverSocket = null;
    private Thread thread = null;
    private ChatServerThread client = null;
    private List<Mahasiswa> daftarMahasiswa = new ArrayList<Mahasiswa>();

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");
            System.out.println("Waiting for client...");
            thread = new Thread(this);
            thread.start();
        } catch (IOException e) {
            System.out.println("Error : " + e);
        }
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                // wait until client socket connecting, then add new thread
                addThreadClient(serverSocket.accept());
            } catch (IOException e) {
                System.out.println("Error : " + e);
            }
        }
    }

    public void addThreadClient(Socket socket) {
        client = new ChatServerThread(this, socket);
        client.start();
    }
    
    public void addMahasiswa(Mahasiswa mahasiswa){
        daftarMahasiswa.add(mahasiswa);
    }
    public int getJumlahMahasiswa(){
        return daftarMahasiswa.size(); 
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
