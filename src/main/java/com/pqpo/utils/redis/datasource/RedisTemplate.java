package com.pqpo.utils.redis.datasource;

import com.pqpo.utils.redis.serialize.AbstractRedisSerializer;
import com.pqpo.utils.redis.serialize.JdkRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTemplate implements RedisOperations {

	private JedisPool redisPool;

	private final AbstractRedisSerializer<Object> default_serializeTranscoder = new JdkRedisSerializer();
	
	private AbstractRedisSerializer<?> serializeTranscoder = default_serializeTranscoder;

	public void setSerializeTranscoder(AbstractRedisSerializer<?> serializeTranscoder) {
		this.serializeTranscoder = serializeTranscoder;
	}

	public void setRedisPool(JedisPool redisPool) {
		this.redisPool = redisPool;
	}

	public Jedis getJedisClient(){
		return redisPool.getResource();
	}

	public void set(String key, Object value) {
		getJedisClient().set(key.getBytes(serializeTranscoder.getCharset()), serializeTranscoder.serialize(value));
	}

	public Object get(String key) {
		return serializeTranscoder.deserialize(getJedisClient().get(key.getBytes(serializeTranscoder.getCharset())));
	}

	public void disconnect() {
		redisPool.close();
	}

}
