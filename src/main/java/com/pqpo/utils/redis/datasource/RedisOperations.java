package com.pqpo.utils.redis.datasource;

public interface RedisOperations {

	public void set(String key,Object value);
	
	public Object get(String key);
	
	public void disconnect();
}
