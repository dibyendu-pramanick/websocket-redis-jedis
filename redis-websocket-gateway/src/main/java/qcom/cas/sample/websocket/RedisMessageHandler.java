package qcom.cas.sample.websocket;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import qcom.cas.sample.websocket.service.SessionHandlerService;
import redis.clients.jedis.JedisPubSub;

@Service
public class RedisMessageHandler extends JedisPubSub {

    @Autowired
    private SessionHandlerService sessionHandlerService;

    @Override
    public void onMessage( String channel, String message ) {
        System.out.println(String.format("Message received. Channel: %s, Msg: %s", channel, message));
        TextMessage msg = new TextMessage(message.toString());
        try {
            sessionHandlerService.getSessionHandlerMap().get(channel).sendMessage(msg);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
