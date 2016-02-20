package qcom.cas.sample.resdis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisSubscriberConfiguration {
    @Value("${redis.host}")
    public String host;

    @Value("${redis.port}")
    public int port;

    @Bean
            JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean
            JedisPool jedisPool() {
        return new JedisPool(jedisPoolConfig(), host, port, 0);
    }

    @Bean
    @Scope("prototype")
            Jedis jedis() {
        return jedisPool().getResource();
    }
}
