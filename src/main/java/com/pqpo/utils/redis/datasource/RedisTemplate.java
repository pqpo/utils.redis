package com.pqpo.utils.redis.datasource;

import com.pqpo.utils.redis.serialize.AbstractRedisSerializer;
import com.pqpo.utils.redis.serialize.JdkRedisSerializer;
import com.pqpo.utils.redis.serialize.StringRedisSerializer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SuppressWarnings({"rawtypes","unchecked"})
public class RedisTemplate<K,V> implements RedisOperations<K,V> {

	private JedisPool redisPool;

	private final AbstractRedisSerializer<?> defaultValueSerializeTranscoder = new JdkRedisSerializer();
	private final AbstractRedisSerializer<String> defaultKeySerializeTranscoder = new StringRedisSerializer();
	
	private AbstractRedisSerializer keySerializeTranscoder = defaultKeySerializeTranscoder;
	private AbstractRedisSerializer valueSerializeTranscoder = defaultValueSerializeTranscoder;

	public void setKeySerializeTranscoder(AbstractRedisSerializer<?> keySerializeTranscoder) {
		this.keySerializeTranscoder = keySerializeTranscoder;
	}
	
	public void setValueSerializeTranscoder(AbstractRedisSerializer<?> valueSerializeTranscoder) {
		this.valueSerializeTranscoder = valueSerializeTranscoder;
	}


	public void setRedisPool(JedisPool redisPool) {
		this.redisPool = redisPool;
	}

	public Jedis getJedisClient(){
		return redisPool.getResource();
	}

	@Override
	public void set(K key, V value) {
		getJedisClient().set(encodeKey(key), encodeValue(value));
	}

	@Override
	public V get(K key) {
		return decodeValue(getJedisClient().get(encodeKey(key)));
	}

	@Override
	public boolean setnx(K key, V value) {
		long setnx = getJedisClient().setnx(encodeKey(key), encodeValue(value));
		int success = (int) setnx;
		return success==1;
	}

	@Override
	public long incr(K key, long step) {
		return getJedisClient().incrBy(encodeKey(key), step);
	}

	@Override
	public long derc(K key, long step) {
		return getJedisClient().decrBy(encodeKey(key), step);
	}

	@Override
	public long del(K key) {
		return getJedisClient().del(encodeKey(key));
	}

	public void disconnect() {
		redisPool.close();
	}

	private byte[] encodeKey(K key) {
		return keySerializeTranscoder.serialize(key);
	}
	
	private byte[] encodeValue(V value) {
		return valueSerializeTranscoder.serialize(value);
	}
	
	private V decodeValue(byte[] value){
		return (V) valueSerializeTranscoder.deserialize(value);
	}
}
