package com.a.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-12-14 3:22
 */
@RestController
public class GoodController {

    private static final String REDIS_LOCK="atguigu";


@Autowired
    private RedisTemplate<String,String> redisTemplate;
@Value("${server.port}")
    private String serverPort;
@Autowired
private Redisson redisson;

@GetMapping("/buy")
    public String buy() throws Exception {
    System.out.println("buy");
    String value= UUID.randomUUID().toString()+Thread.currentThread().getName();

    //分布式集群锁
    RLock rLock = redisson.getLock(REDIS_LOCK);
    rLock.lock();

    try {
//        Boolean flag=stringRedisTemplate.opsForValue()
//                .setIfAbsent(REDIS_LOCK,value,10L,TimeUnit.SECONDS);
//        if(!flag) return "buying failed";

        String result= redisTemplate.opsForValue().get("goods:001");
        int goodsNumber=result==null?0:Integer.parseInt(result);
        if(goodsNumber>0)
        {
            int realNumber=goodsNumber-1;
//            redisTemplate.opsForValue().set("goods:001",String.valueOf(realNumber));
            System.out.println("successfully bought the stock is "+realNumber+"\t server's port "+serverPort);
            return "successfully bought the stock is "+realNumber+"\t server's port "+serverPort;
        }else{
            System.out.println("good has been sold out \t server's port "+serverPort);
        }
        return "good has been sold out \t server's port "+serverPort;
    } finally {
//        if(stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value))
//        stringRedisTemplate.delete(REDIS_LOCK);


//        while(true){
//            stringRedisTemplate.watch(REDIS_LOCK);
//            if(stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)){
//                stringRedisTemplate.setEnableTransactionSupport(true);
//                stringRedisTemplate.multi();
//                stringRedisTemplate.delete(REDIS_LOCK);
//                List<Object> list = stringRedisTemplate.exec();//var
//                if (list == null) {//null
//                    continue;
//                }
//            }
//            stringRedisTemplate.unwatch();
//            break;
//        }


        //Lua脚本
//        Jedis jedis= RedisUtils.getJedis();
//        String script="if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
//                "then\n" +
//                "    return redis.call(\"del\",KEYS[1])\n" +
//                "else\n" +
//                "    return 0\n" +
//                "end";
//        try{
//            Object o = jedis.eval(script, Collections.singletonList(REDIS_LOCK)
//                    , Collections.singletonList(value));
//            if("1".equals(o.toString())){
//                System.out.println("-----del redis lock ok");
//            }else{
//                System.out.println("-----del redis lock error");
//            }
//        }finally {
//            if(null!=jedis){
//                jedis.close();
//            }
//        }



//        if(rLock.isLocked()&&rLock.isHeldByCurrentThread()){
//                rLock.unlock();
//        }


    }

}


}
