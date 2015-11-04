package com.pqpo.utils.redis.serialize;

import com.pqpo.utils.redis.exception.RedisSerializeException;

public interface RedisSerializer<T> {

	byte[] serialize(T t) throws RedisSerializeException;
	
	T deserialize(byte[] bytes) throws RedisSerializeException;
	
}
