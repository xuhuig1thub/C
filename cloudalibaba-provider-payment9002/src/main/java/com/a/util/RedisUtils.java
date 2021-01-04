package com.a.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-14 4:36
 */
public class RedisUtils {
    private static JedisPool jedisPool;
    static{
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool=new JedisPool(jedisPoolConfig,"192.168.50.110",6379);
    }
    public static Jedis getJedis() throws  Exception{
        if (jedisPool != null) {
        return jedisPool.getResource();
        }
    throw new Exception("Jedispool is not ok");
    }
}
