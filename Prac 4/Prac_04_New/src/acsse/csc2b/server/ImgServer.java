package acsse.csc2b.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author B, Bukanga 221005009
 *
 */
public class ImgServer {
    
    private ServerSocket server;
    private boolean running;
    
    /**
     * Main function to run the Server side
     * Connects to port 9876
     * @param args
     */
    public static void main(String[] args) {
        ImgServer imageServer = new ImgServer(9876);
        imageServer.start();
    }
    
    /**
     * Constructor
     * @param port
     */
    public ImgServer(int port) {
        try {
            server = new ServerSocket(port);
            running = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Start method to try connect the to the port
     * 	Handles errors in case theirs failure to connect
     */
    public void start() {
        System.out.println("Waiting for connection");
        while (running) {
            try {
                Socket clientSocket = server.accept();
                System.out.println("Client Connected");
                handleClient(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Function to handle client functions
     * @param clientSocket
     */
    private void handleClient(Socket clientSocket) {
        ImgHandler imgHandler = new ImgHandler(clientSocket);
        Thread thread = new Thread(imgHandler);
        thread.start();
    }
}
