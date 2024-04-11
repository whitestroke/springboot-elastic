package com.lct.springbootjar.springbootes.config.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :lct
 * @date : 2023/9/25
 */
@Configuration
public class RedisBean {

    @Bean(name = "BeanIn")
    @ConditionalOnProperty(value = "redis.model",havingValue = "standalone",matchIfMissing = true)
    public JedisPool jedisPool() {
        String host = "172.17.0.228";
        Integer port = 6400;
        String password = "redis_123456";

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(500);
        config.setMaxWaitMillis(1000);
        JedisPool jedisPool = new JedisPool(config, host, port, 1000, password);

        Jedis jedis = jedisPool.getResource();
        // String s = jedis.get("aa");

        return jedisPool;
    }

    @Bean
    @ConditionalOnProperty(value = "redis.model",havingValue = "cluster",matchIfMissing = false)
    public MyRedisCluster jedisCluster() {
        HostAndPort hostAndPort = new HostAndPort("172.17.0.228", 6479);
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(hostAndPort);


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(30);
        jedisPoolConfig.setMaxIdle(30);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setNumTestsPerEvictionRun(-1);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(false);
        jedisPoolConfig.setBlockWhenExhausted(false);

        MyRedisCluster jedis =  new MyRedisCluster(hostAndPortSet,
                60000,
                1000,
                1000,
                "redis_123456",
                jedisPoolConfig
        );

        return jedis;
    }
}
