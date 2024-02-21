package ar.edu.undef.fie.finalppviwebsocket.configurations;

import ar.edu.undef.fie.finalppviwebsocket.service.WebSocketClientHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private WebSocketClientHandler webSocketClientHandler;

    public WebSocketConfig(WebSocketClientHandler webSocketClientHandler) {
        this.webSocketClientHandler = webSocketClientHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketClientHandler, "/{storeId}");
    }
}
