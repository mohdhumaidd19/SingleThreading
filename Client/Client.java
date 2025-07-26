import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234); // Connect to server

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Connected to server. Type messages:");

        String msg;
        while ((msg = input.readLine()) != null) {
            out.println(msg); // Send to server
            System.out.println("From Server: " + in.readLine()); // Receive from server
        }

        socket.close();
    }
}
