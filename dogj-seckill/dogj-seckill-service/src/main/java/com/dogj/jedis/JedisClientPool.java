package com.dogj.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class JedisClientPool implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String filed, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, filed, value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String filed) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, filed);
        return result;
    }

    @Override
    public Long hdel(String key, String... filed) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(key, filed);
        jedis.close();
        return result;
    }

    @Override
    public List<String> hgetAll(String key) {
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.hvals(key);
        jedis.close();
        return list;
    }
}
