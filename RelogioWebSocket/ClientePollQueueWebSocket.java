package com.diniz.flavio.RelogioWebSocket.utils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class ClientePollQueueWebSocket extends WebSocketClient {
    private static Queue<String> timezones = new
            LinkedList<>(Arrays.asList("America/Sao_Paulo", "Europe/Paris",
            "Europe/Moscow", "Asia/Shanghai", "Asia/Tokyo"));
    public ClientePollQueueWebSocket(URI serverUri) {
        super(serverUri);
    }
    public static void main(String[] args) throws Exception {
        ClientePollQueueWebSocket client = new ClientePollQueueWebSocket(
                new URI("ws://localhost:8080/relogio"));
// inicia a conexão de forma assíncrona, onde o código seguinte à chamada de connect() será executado imediatamente, sem esperar pela conclusão da conexão
        client.connect();
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Conectado! Solicitando horários...");
        sendNextTimezone();
    }

    private void sendNextTimezone() {
        if (!timezones.isEmpty()) {// timezones é uma fila
            String timezone = timezones.poll(); // O recupera e remove da fila
            System.out.println("\nSolicitando horário para '" + timezone + "'...");
            send(timezone);
        } else {
            System.out.println("\nTodas as solicitações foram enviadas.");
// O código 1000 indica um fechamento normal.
            this.close(1000, "Fechamento solicitado pelo cliente");
//this.close(getConnectionLostTimeout(), getResourceDescriptor());
        }
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Horário recebido: " + message);
        sendNextTimezone(); // Envia a próxima solicitação após receber a resposta
    }
    // onClose é um callback que é chamado automaticamente quando a conexão é fechada
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Conexão fechada: " + reason);
    }
    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}