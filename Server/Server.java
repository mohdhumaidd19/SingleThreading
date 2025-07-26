import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234); // Port number
        System.out.println("Server started on port 1234...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Accept new client
            System.out.println("New client connected");

            // Create a new thread for each client
            ClientHandler handler = new ClientHandler(clientSocket);
            new Thread(handler).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            // Get input and output streams
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            // Echo loop
            while ((message = in.readLine()) != null) {
                System.out.println("Received from client: " + message);
                out.println("Echo: " + message); // Send back
            }

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error in client communication: " + e.getMessage());
        }
    }
}
