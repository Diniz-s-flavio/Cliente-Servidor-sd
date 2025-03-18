import java.io.*;
import java.net.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class TcpTimeServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9876)) {
            System.out.println("Servidor TCP esperando conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String region;
            while ((region = in.readLine()) != null) {
                if (region.equalsIgnoreCase("sair")) {
                    break;
                }

                String responseMessage;
                try {
                    ZonedDateTime time = ZonedDateTime.now(ZoneId.of(region));
                    responseMessage = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
                } catch (Exception e) {
                    responseMessage = "Região inválida!";
                }

                out.println(responseMessage);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
