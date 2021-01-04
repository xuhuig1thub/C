package com.a.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-14 2:07
 */
@Configuration
public class ResidConfig {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String,Serializable> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }


    @Bean
    public Redisson redisson(){
        org.redisson.config.Config config=new org.redisson.config.Config();
        config.useSingleServer().setAddress("redis://192.168.50.110:6379").setDatabase(0);
        //集群模式,集群节点的地址须使用“redis://”前缀，否则将会报错。
        //此例集群为3节点，各节点1主1从
//        config.useClusterServers().addNodeAddress("redis://192.168.50.110:6379","redis://192.168.50.110:6380",
//                "redis://192.168.50.110:6381","redis://192.168.50.110:6382","redis://192.168.50.110:6383","redis://192.168.50.110:6384").setPassword("passwd123");

        return  (Redisson) Redisson.create(config);

    }
}
