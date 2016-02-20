package qcom.cas.sample.websocket.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import qcom.cas.sample.websocket.RedisMessageHandler;
import qcom.cas.sample.websocket.service.RedisTemplate;
import qcom.cas.sample.websocket.service.SessionHandlerService;

@Service
public class WebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    private WebSocketSession session;

    @Autowired
    private RedisMessageHandler redisMessageHandler;

    @Autowired
    private SessionHandlerService sessionHandlerService;

    @Override
    public void handleTextMessage( WebSocketSession session, TextMessage message ) throws Exception {
        Thread.sleep(3000);
        TextMessage msg = new TextMessage("Hello, " + message.getPayload() + "!");
        session.sendMessage(msg);
    }

    @Override
    public void afterConnectionEstablished( WebSocketSession session ) throws Exception {
        this.session = session;
        String deviceId = extractDeviceId();
        sessionHandlerService.putSessionHandlerMap(deviceId, session);
        redisTemplate.subscribe(redisMessageHandler, deviceId);
    }

    private String extractDeviceId() {
        return this.session.getUri().toString().substring(this.session.getUri().toString().indexOf("=") + 1,
                this.session.getUri().toString().length());
    }

    public void sendMessageToClient() throws IOException {
        TextMessage msg = new TextMessage("Hello, Dibyendu");
        this.session.sendMessage(msg);
    }

    @Override
    public void afterConnectionClosed( WebSocketSession session, CloseStatus status ) throws Exception {
        String deviceId = extractDeviceId();
        sessionHandlerService.removeFromSessionHandlerMap(deviceId);
    }

}
