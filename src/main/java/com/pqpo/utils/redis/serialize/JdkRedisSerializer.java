package com.pqpo.utils.redis.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.pqpo.utils.redis.exception.RedisSerializeException;

public class JdkRedisSerializer extends AbstractRedisSerializer<Object> {

	@Override
	public byte[] serialize(Object value) {
		if(value==null){
			throw new RedisSerializeException("Object must not be null");
		}
		if(!(value instanceof Serializable)){
			throw new RedisSerializeException("Object must be serializable");
		}
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(value);
			byte[] result = baos.toByteArray();
			baos.flush();
			return result;
		} catch (IOException e) {
			throw new RedisSerializeException(e.getMessage(),e);
		}finally{
			close(baos);
			close(oos);
		}
	}

	@Override
	public Object deserialize(byte[] in) {
		if(in==null||in.length==0){
			return null;
		}
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(in);
			ois = new ObjectInputStream(bais);
			Object result = ois.readObject();
			return result;
		} catch (IOException e) {
			throw new RedisSerializeException(e.getMessage(),e);
		} catch (ClassNotFoundException e) {
			throw new RedisSerializeException(e.getMessage(),e);
		}finally{
			close(bais);
			close(ois);
		}
	}

}
