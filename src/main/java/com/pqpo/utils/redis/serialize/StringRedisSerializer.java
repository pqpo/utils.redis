package com.pqpo.utils.redis.serialize;

import java.nio.charset.Charset;

public class StringRedisSerializer extends AbstractRedisSerializer<String> {

	public StringRedisSerializer() {
		this(Charset.forName("UTF8"));
	}

	public StringRedisSerializer(Charset charset) {
		setCharset(charset);
	}
	
	@Override
	public byte[] serialize(String value) {
		return value==null?null:value.getBytes(getCharset());
	}

	@Override
	public String deserialize(byte[] in) {
		return in==null?null:new String(in,getCharset());
	}

}
