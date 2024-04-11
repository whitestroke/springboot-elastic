package com.lct.springbootjar.springbootes.config.redis;

/**
 * @author :lct
 * @date : 2023/9/26
 */
public interface RedisManager {

    byte[] get(byte[] key);
    String get(String key);
    byte[] set(byte[] key, byte[] value);
    String set(String key, String value);
    byte[] set(byte[] key, byte[] value, int expire);
    String set(String key, String value, int expire);
    void del(byte[] key);
    void del(String key);

}
