package com.pqpo.utils.redis.datasource;

public interface RedisOperations<K, V> {

	public void set(K key,V value);
	
	public V get(K key);
	
	public boolean setnx(K key,V value);
	
	public long incr(K key,long step);
	
	public long derc(K key,long step);
	
	public long del(K keys);
	
	public void disconnect();
}
