package com.dogj.jedis;

public class JedisClientCluster implements JedisClient {
    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Boolean exists(String key) {
        return null;
    }

    @Override
    public Long expire(String key, int seconds) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long hset(String key, String filed, String value) {
        return null;
    }

    @Override
    public String hget(String key, String filed) {
        return null;
    }

    @Override
    public Long hdel(String key, String... filed) {
        return null;
    }
}
