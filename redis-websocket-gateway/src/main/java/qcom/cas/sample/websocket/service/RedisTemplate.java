package qcom.cas.sample.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qcom.cas.sample.websocket.RedisMessageHandler;
import redis.clients.jedis.Jedis;

@Service
public class RedisTemplate {
    private Jedis jedis;

    @Autowired
    public RedisTemplate(Jedis jedis) {
        this.jedis = jedis;
    }

    public void subscribe( RedisMessageHandler redisMessageHandler, final String channel ) {
        jedis.subscribe(redisMessageHandler, channel);
    }

    public void unsubscribe( final String channel, final RedisMessageHandler messageHandler ) {
        messageHandler.unsubscribe(channel);
    }

    public void publish( String channel, String message ) {
        jedis.publish(channel, message);
    }
}
