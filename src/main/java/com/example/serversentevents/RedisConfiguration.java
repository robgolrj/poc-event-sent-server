package com.example.serversentevents;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by robson on 19/05/2019.
 */
@Configuration
public class RedisConfiguration {
    private final RedisServer redisServer;

    public RedisConfiguration(@Value("${spring.redis.port}") final int redisPort) {
        this.redisServer = new RedisServer(redisPort);
    }

    @PostConstruct
    public void iniciar(){
        redisServer.start();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory());
        return redisTemplate;
    }

    @PreDestroy
    public void parar(){
        redisServer.stop();
    }
}
