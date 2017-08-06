package com.deyi.entity;

/**
 * Created by Administrator on 2017/8/5.
 */
public class  BaseObject {
    public  Long getRedisKey(){
        throw  new RuntimeException("Please override getRedisKey Method!");
    };
}
