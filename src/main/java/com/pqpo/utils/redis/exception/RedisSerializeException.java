package com.pqpo.utils.redis.exception;

public class RedisSerializeException extends RedisException {

	private static final long serialVersionUID = 1L;
	public RedisSerializeException() {
		super();
	}
	
	public RedisSerializeException(String message) {
		super(message);
	}
	
	public RedisSerializeException(String message,Throwable cause) {
		super(message, cause);
	}

}
