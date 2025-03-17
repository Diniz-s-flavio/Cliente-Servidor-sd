import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpTimeClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(); Scanner scanner = new Scanner(System.in)) {
            InetAddress serverAddress = InetAddress.getByName("localhost");

            while (true) {
                System.out.print("Digite a região desejada (Padrão: America/Sao_Paulo): ");
                String region = scanner.nextLine().trim();

                byte[] requestData = region.getBytes(StandardCharsets.UTF_8);
                DatagramPacket request = new DatagramPacket(requestData, requestData.length, serverAddress, 9876);
                socket.send(request);

                byte[] buffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                String responseMessage = new String(response.getData(), 0, response.getLength(), StandardCharsets.UTF_8);

                System.out.println("Hora na região " + region + ": " + responseMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}