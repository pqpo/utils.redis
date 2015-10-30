package com.pqpo.utils.redis.datasource;

import java.io.UnsupportedEncodingException;

import com.pqpo.utils.redis.exception.RedisException;
import com.pqpo.utils.redis.serialize.JdkSerializeTranscoder;
import com.pqpo.utils.redis.serialize.SerializeTranscoder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTemplate implements RedisOperations {

	public static final String CHARSET = "UTF-8";
	
	private JedisPool redisPool;
	
	private final SerializeTranscoder default_serializeTranscoder = new JdkSerializeTranscoder();
	
	private SerializeTranscoder serializeTranscoder = default_serializeTranscoder;
	
	public void setSerializeTranscoder(SerializeTranscoder serializeTranscoder) {
		this.serializeTranscoder = serializeTranscoder;
	}
	
	public void setRedisPool(JedisPool redisPool) {
		this.redisPool = redisPool;
	}
	
	public Jedis getJedisClient(){
		return redisPool.getResource();
	}

	public void set(String key, Object value) {
		try {
			getJedisClient().set(key.getBytes(CHARSET), serializeTranscoder.serialize(value));
		} catch (UnsupportedEncodingException e) {
			throw new RedisException(e.getMessage(), e);
		}
	}

	public Object get(String key) {
		try {
			return serializeTranscoder.deserialize(getJedisClient().get(key.getBytes(CHARSET)));
		} catch (UnsupportedEncodingException e) {
			throw new RedisException(e.getMessage(), e);
		}
	}

	public void disconnect() {
	}
	
}
