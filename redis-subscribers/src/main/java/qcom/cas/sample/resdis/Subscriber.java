package qcom.cas.sample.resdis;

import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPubSub;

@Service
public class Subscriber extends JedisPubSub {
    //private static Logger logger = LoggerFactory.getLogger(Subscriber.class);

    @Override
    public void onMessage( String channel, String message ) {
        System.out.println(String.format("Message received. Channel: %s, Msg: %s", channel, message));
    }

    @Override
    public void onPMessage( String pattern, String channel, String message ) {

    }

    @Override
    public void onSubscribe( String channel, int subscribedChannels ) {

    }

    @Override
    public void onUnsubscribe( String channel, int subscribedChannels ) {

    }

    @Override
    public void onPUnsubscribe( String pattern, int subscribedChannels ) {

    }

    @Override
    public void onPSubscribe( String pattern, int subscribedChannels ) {

    }
}
