package com.lct.springbootjar.springbootes.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author :lct
 * @date : 2023/9/26
 */
@Component
@ConditionalOnProperty(value = "redis.model",havingValue = "standalone",matchIfMissing = true)
public class RedisManagerJedisStandalone implements RedisManager {

    private int expire = 0;

    @Autowired(required = false)
    private JedisPool jedisPool;

    @Override
    public byte[] get(byte[] key) {
        byte[] value = null;
        try(Jedis jedis = jedisPool.getResource()) {
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            e.printStackTrace();
        }
        return value;
    }
    @Override
    public String get(String key) {
        String value = null;
        try(Jedis jedis = jedisPool.getResource()) {
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            e.printStackTrace();
        }
        return value;
    }
    @Override
    public byte[] set(byte[] key, byte[] value) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    @Override
    public String set(String key, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } catch (Exception e) {
            //释放redis对象
            e.printStackTrace();
        }
        return value;
    }
    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            //释放redis对
            e.printStackTrace();
        }
        return value;
    }
    @Override
    public String set(String key, String value, int expire) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            //释放redis对象
            e.printStackTrace();
        }
        return value;
    }
    @Override
    public void del(byte[] key) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
            e.printStackTrace();
        }
    }
    @Override
    public void del(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
