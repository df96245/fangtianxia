package com.deyi.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/8/5.
 */
public class SerializeUtil{
    private Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    public static <T> byte[] serialize(T object) {
        if (object==null){
            throw new RuntimeException("serialized obj is null!");
        }
        Schema<T> schema =(Schema<T>) RuntimeSchema.createFrom(object.getClass());
        return  ProtostuffIOUtil.toByteArray((T) object, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }
    public  static  <T> T deSerialize(byte[] data , Class<T> targetClass) {
        if (data==null|| data.length==0){
            throw new RuntimeException("deSerialize data is empty !!!");
        }
        Schema<T> schema = RuntimeSchema.createFrom(targetClass);
        T instance = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data,instance,schema);
        return  instance;
    }
}
