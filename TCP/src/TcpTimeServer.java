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
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String region = in.readLine();
                    String responseMessage;

                    try {
                        ZonedDateTime time = ZonedDateTime.now(ZoneId.of(region));
                        responseMessage = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
                    } catch (Exception e) {
                        responseMessage = "Região inválida!";
                    }

                    out.println(responseMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}