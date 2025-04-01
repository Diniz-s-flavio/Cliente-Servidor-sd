package com.diniz.flavio.websockets.component;

import com.diniz.flavio.websockets.model.TemperaturaDTO;
import com.diniz.flavio.websockets.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TemperaturaScheduler {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ClimaService climaService;

    private int contador = 0;
    private final int MAX_ENVIOS = 10;

    @Scheduled(fixedRate = 5000)
    public void enviarTemperatura() {
        if (contador > MAX_ENVIOS) {
            return;
        }

        TemperaturaDTO temperatura = climaService.buscarClimaAleatorio();
        messagingTemplate.convertAndSend("/topic/temperatura", temperatura);
        contador++;
    }
}
