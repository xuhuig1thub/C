package com.a.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.Set;

//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPoolConfig;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-19 0:44
 */
//@Configuration
//@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfig {

    private Set<String> nodes;
    private String master;

    @Value("${spring.redis.timeout}")
    private long timeout;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private long maxWait;
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;

    /**
     * 创建一个Redis连接工厂
     * @return
     */
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {

        Set<String> nodes =new HashSet<String>();
        nodes.add("192.168.50.110:6379");
        nodes.add("192.168.50.110:6380");
        nodes.add("192.168.50.110:6381");
        nodes.add("192.168.50.110:6382");
        nodes.add("192.168.50.110:6383");
        nodes.add("192.168.50.110:6384");
        RedisClusterConfiguration redisClusterConfiguration= new RedisClusterConfiguration(nodes);
        redisClusterConfiguration.setPassword("passwd123");
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);

        LettucePoolingClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig)
                .build();
        return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
    }

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
//    public RedisTemplate<String, String> redisTemplate(LettuceConnectionFactory factory) {
    public RedisTemplate<String, String> redisTemplate() {
        //创建Json序列化对象
//        Jackson2JsonRedisSerializer<String> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<String>(String.class);
//
//        //解决查询缓存转换异常的问题
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 将默认序列化改为Jackson2JsonRedisSerializer序列化
        RedisTemplate<String, String> template = new RedisTemplate<String,String>();
        template.setKeySerializer(stringRedisSerializer);// key序列化
        template.setValueSerializer(stringRedisSerializer);// value序列化
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    public void setNodes(Set<String> nodes) {
        this.nodes = nodes;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}