package com.dogj.jedis;

import java.util.List;

public interface JedisClient {
    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String filed, String value);
    String hget(String key, String filed);
    Long hdel(String key, String... filed);
    List<String> hgetAll(String key);
}
