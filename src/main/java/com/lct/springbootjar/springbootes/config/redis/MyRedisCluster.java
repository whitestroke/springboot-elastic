package com.lct.springbootjar.springbootes.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.Closeable;
import java.util.Set;

/**
 * @author :lct
 * @date : 2023/9/25
 */
public class MyRedisCluster extends JedisCluster implements Closeable {
    public MyRedisCluster(HostAndPort node) {
        super(node);
    }

    public MyRedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout,
                          int maxAttempts, String password, final GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
    }

    public JedisCluster getResource() {
        return this;
    }


}
