package ar.edu.undef.fie.finalppviwebsocket.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;

public class Subscriber implements MessageListener {

    Logger logger = LoggerFactory.getLogger(Subscriber.class);
    private final WebSocketClientHandler webSocketClientHandler;

    public Subscriber(WebSocketClientHandler webSocketClientHandler) {
        this.webSocketClientHandler = webSocketClientHandler;
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("Message received: {} ",  message);

        String messageStr = message.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonObject = objectMapper.readTree(messageStr);
            int storeId = jsonObject.get("storeId").asInt();

            webSocketClientHandler.sendMessageToAll(storeId, messageStr);
        } catch (IOException e) {
            logger.error("Error parsing JSON", e);
        }
    }
}
