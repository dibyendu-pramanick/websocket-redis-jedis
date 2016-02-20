package qcom.cas.sample.redis.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class RedisPublisher {

    @Autowired
    private Jedis jedis;

    @Autowired
    public RedisPublisher(Jedis jedis) {
        this.jedis = jedis;
    }

    public void publish( final String topic, final String message ) {
        //new Jedis("localhost", 6379).publish(topic, message);
        jedis.publish(topic, message);
    }
}
