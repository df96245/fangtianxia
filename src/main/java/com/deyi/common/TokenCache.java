package com.deyi.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by deyi
 */
public class TokenCache {

    private  static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    //LRU算法 (最少使用算法，如果超过最大值则会进行清除), 构建者模式.所以我们初始化容量，最大最小值和time out时间的顺序都可以随便的。因为返回的都是同一个对象.
    //初始化为1000，最大为10000,时长为12个小时。
    private static LoadingCache<String,String> localCache= CacheBuilder.newBuilder().initialCapacity(1000).
            maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
        @Override
        //默认数据加载的实现，当调用get取值的时候没有对应的值则会走到这里.
        public String load(String s) throws Exception {
            //因为以后如果走到这里， 我们返回值是null, 接下来我们根据返回值来调用equals方法。可能会出现空指针，所以返回值用字符串null.
            return "null";
        }
    });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value=null;
        try{
            value =localCache.get(key);
        }catch (Exception e){
            logger.error("get key error",e);
        }
        return value;
    }


}
