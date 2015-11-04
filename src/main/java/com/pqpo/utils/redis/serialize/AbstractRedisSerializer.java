package com.pqpo.utils.redis.serialize;

import java.io.Closeable;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

public abstract class AbstractRedisSerializer<T> implements RedisSerializer<T> {
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	private Charset charset = DEFAULT_CHARSET;
	
	protected  Logger logger = Logger.getLogger(getClass());

	public abstract byte[] serialize(Object value);

	public abstract T deserialize(byte[] in);
	
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	
	public Charset getCharset() {
		return charset;
	}

	public void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				logger.info("Unable to close " + closeable, e); 
			}
		}
	}
}
