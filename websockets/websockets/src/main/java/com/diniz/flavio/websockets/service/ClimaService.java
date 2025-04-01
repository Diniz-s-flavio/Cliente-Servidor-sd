package com.diniz.flavio.websockets.service;

import com.diniz.flavio.websockets.model.TemperaturaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ClimaService {

    private final String API_KEY = "d1d80418e1d55eabacfbfcc2820a8989";
    private final List<String> cidades = List.of(
            "Tokyo", "New York", "Paris", "London", "Sydney",
            "São Paulo", "Berlin", "Moscow", "Cape Town", "Toronto"
    );

    private final RestTemplate restTemplate = new RestTemplate();

    public TemperaturaDTO buscarClimaAleatorio() {
        String cidade = cidades.get(new Random().nextInt(cidades.size()));
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric&lang=pt", cidade, API_KEY);

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> body = response.getBody();
        Map<String, Object> main = (Map<String, Object>) body.get("main");
        List<Map<String, Object>> weather = (List<Map<String, Object>>) body.get("weather");
        Map<String, Object> sys = (Map<String, Object>) body.get("sys");

        TemperaturaDTO dto = new TemperaturaDTO();
        dto.setCidade(cidade);
        dto.setPais((String) sys.get("country"));
        dto.setTemperatura((Double) main.get("temp"));
        dto.setDescricao((String) weather.get(0).get("description"));
        dto.setDataHora(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return dto;
    }
}
