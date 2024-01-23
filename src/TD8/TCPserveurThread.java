package TD8;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPserveurThread extends Thread {
    private static final int START_PORT = 21000;
    private static final int END_PORT = 23000;
    private static AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) throws IOException {
        for (int port = START_PORT; port <= END_PORT; port++) {
            int finalPort = port;
            new Thread(() -> listenOnPort(finalPort)).start();
        }
    }

    private static void listenOnPort(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;
                    if ((inputLine = in.readLine()) != null) {
                        if ("NUMERO ?".equals(inputLine.trim())) {
                            out.println("NUMERO=" + counter.getAndIncrement());
                        } else {
                            out.println("VOUS AVEZ FAIT UNE ERREUR.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port " + port + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
            System.out.println(e.getMessage());
        }
    }
}
