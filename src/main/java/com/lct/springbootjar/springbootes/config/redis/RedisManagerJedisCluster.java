package com.lct.springbootjar.springbootes.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * @author :lct
 * @date : 2023/9/26
 */
@Component
@ConditionalOnProperty(value = "redis.model",havingValue = "cluster",matchIfMissing = false)
public class RedisManagerJedisCluster implements RedisManager{

    private int expire = 0;

    @Autowired(required = false)
    private JedisCluster jedisCluster;

    @Override
    public byte[] get(byte[] key) {
        return jedisCluster.get(key);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public byte[] set(byte[] key, byte[] value) {
        jedisCluster.set(key,value);
        if(this.expire!=0){
            jedisCluster.expire(key,expire);
        }
        return value;
    }

    @Override
    public String set(String key, String value) {
        jedisCluster.set(key,value);
        if(this.expire!=0){
            jedisCluster.expire(key,expire);
        }
        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        jedisCluster.set(key,value);
        if(expire!=0){
            jedisCluster.expire(key,expire);
        }
        return value;
    }

    @Override
    public String set(String key, String value, int expire) {
        jedisCluster.set(key,value);
        if(expire!=0){
            jedisCluster.expire(key,expire);
        }
        return value;
    }

    @Override
    public void del(byte[] key) {
        jedisCluster.del(key);
    }

    @Override
    public void del(String key) {
        jedisCluster.del(key);
    }
}
