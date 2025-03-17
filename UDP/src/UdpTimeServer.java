import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class UdpTimeServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            byte[] buffer = new byte[1024];
            System.out.println("Servidor UDP esperando requisições...");

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String region = new String(request.getData(), 0, request.getLength(), StandardCharsets.UTF_8);

                String responseMessage;
                try {
                    ZonedDateTime time = ZonedDateTime.now(ZoneId.of(region));
                    responseMessage = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
                } catch (Exception e) {
                    responseMessage = "Região inválida!";
                }

                byte[] responseData = responseMessage.getBytes(StandardCharsets.UTF_8);
                DatagramPacket response = new DatagramPacket(responseData, responseData.length, request.getAddress(), request.getPort());
                socket.send(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}