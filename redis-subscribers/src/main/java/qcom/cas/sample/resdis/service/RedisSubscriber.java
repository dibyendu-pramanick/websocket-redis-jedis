package qcom.cas.sample.resdis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qcom.cas.sample.resdis.Subscriber;
import redis.clients.jedis.Jedis;

@Service
public class RedisSubscriber {
    @Autowired
    private Jedis jedis;
    private Subscriber subscriber;

    @Autowired
    public RedisSubscriber(Jedis jedis, Subscriber subscriber) {
        this.jedis = jedis;
        this.subscriber = subscriber;
    }

    public void subscribe( final String channel ) {
        new Thread(new Runnable() {
            // @Override
            public void run() {
                try {
                    //new Jedis("localhost", 6379).subscribe(subscriber, channel);
                    jedis.subscribe(subscriber, channel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
