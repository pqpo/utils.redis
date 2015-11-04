package com.pqpo.utils.redis.serialize;

import com.alibaba.fastjson.JSON;

public class JsonRedisSerializer<T> extends AbstractRedisSerializer<T> {

	private Class<T> type;

	public JsonRedisSerializer(Class<T> type){
		this.type = type;
	}

	@Override
	public byte[] serialize(Object value) {
		return JSON.toJSONString(value).getBytes(getCharset());
	}

	@Override
	public T deserialize(byte[] in) {
		return JSON.parseObject(new String(in,getCharset()), type);
	}

}
