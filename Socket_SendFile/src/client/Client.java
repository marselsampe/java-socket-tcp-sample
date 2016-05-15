package client;

import java.io.*;
import java.net.Socket;

public class Client {

    private String serverName = "localhost";
    private int serverPort = 8081;
    private Socket socket = null;

    public Client() {
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());

            System.out.println("Getting file from server...");
            int FILE_SIZE = 6022386;
            byte[] myByteArray = new byte[FILE_SIZE];
            
            String projectPath = System.getProperty("user.dir");
            String imagePath = projectPath + "/gambarClient/gambar1.jpg";
            
            InputStream inputStream = socket.getInputStream();
            int bytesRead = inputStream.read(myByteArray, 0, myByteArray.length);
            int current = bytesRead;
            do {
                bytesRead = inputStream.read(myByteArray, current, (myByteArray.length - current));
                if (bytesRead >= 0) {
                    current += bytesRead;
                }
            } while (bytesRead > -1);
            
            FileOutputStream fileOutputStream  = new FileOutputStream(imagePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(myByteArray, 0, current);
            bufferedOutputStream.flush();
            
            System.out.println("Get file success...");
                        
            inputStream.close();
            fileOutputStream.close();
            bufferedOutputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
    }
}
