package com.diniz.flavio.RelogioWebSocket.config;

import com.diniz.flavio.RelogioWebSocket.handler.RelogioWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final RelogioWebSocketHandler handler;
    public WebSocketConfig(RelogioWebSocketHandler handler) {
        this.handler = handler;
    }
    //define que o endpoint do WebSocket será /relogio
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
// setAllowedOrigins("*") permite conexões de qualquer origem.
        registry.addHandler(handler, "/relogio").setAllowedOrigins("*");
    }
}