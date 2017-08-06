package com.deyi.dao.redis;

import com.deyi.common.Constants;
import com.deyi.entity.BaseObject;
import com.deyi.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2017/8/5.
 */
public class RedisDao{
    private Logger logger = LoggerFactory.getLogger(RedisDao.class);
    private JedisPool jedisPool;


    public  RedisDao(String ip , int port){
        jedisPool= new JedisPool(ip,port);
    }

    public <T extends BaseObject> void  pudObject(T object){
        //object--> 序列化--> byte[]--发送到redis
        Jedis jedis = jedisPool.getResource();
        String stringkey= Constants.REDIS_KEY_PREX+ object.getRedisKey();
        byte[] redisByteValue=SerializeUtil.serialize(object);
        int timeout=60*60;
        jedis.setex(stringkey.getBytes(),timeout,redisByteValue);
    }

    //get byte[] from redis--> deSerialize--> Object
    public <T> T getObject(Long redisKey ,Class<T> targetClass){
        Jedis jedis = jedisPool.getResource();
        T retObj=null;
        try {
            String stringkey=Constants.REDIS_KEY_PREX+ redisKey;
            byte[] redisByteValue=jedis.get(stringkey.getBytes());

            if (redisByteValue!=null||redisByteValue.length!=0){
                retObj =SerializeUtil.deSerialize(redisByteValue, targetClass);
                return  retObj;
            }
        }catch (Exception e){
            logger.error("Error occur when getObject : "+ e);
        }
        return  retObj;

    }

}
