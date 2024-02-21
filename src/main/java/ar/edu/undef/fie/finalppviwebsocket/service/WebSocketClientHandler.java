package ar.edu.undef.fie.finalppviwebsocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebSocketClientHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClientHandler.class);

    private final Map<Integer, List<WebSocketSession>> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        try {
            int storeId = Integer.parseInt(session.getUri().getPath().replace("/", ""));
            if (!sessions.containsKey(storeId)) {
                sessions.put(storeId, new ArrayList<>());
            }
            if (!sessions.get(storeId).contains(session)) {
                sessions.get(storeId).add(session);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Session closed: {}", session.getId());
        sessions.values().forEach(list -> list.remove(session));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Message received: {}", message.getPayload());


    }
}
