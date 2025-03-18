import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpTimeClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9876);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.print("Digite a região desejada (Padrão: America/Sao_Paulo): ");
                String region = scanner.nextLine().trim();

                out.println(region);
                if (region.equalsIgnoreCase("sair")) break;

                String responseMessage = in.readLine();
                System.out.println("Hora na região " + region + ": " + responseMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

