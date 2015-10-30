package com.pqpo.utils.redis.factory;

import org.springframework.beans.factory.FactoryBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

/**
 * 默认非单例，每次请求会新建一个客户端
 * @author Qiulinmin
 *
 */
public class RedisClientFactory implements FactoryBean<Jedis>{

	private String host;
	private Integer port = Protocol.DEFAULT_PORT;
	private Integer timeout = Protocol.DEFAULT_TIMEOUT;
	private String password;
	
	private Boolean isSingleton = Boolean.FALSE;

	public Jedis getObject() throws Exception {
		if(host==null){
			throw new RuntimeException("Redis host must not be null!");
		}
		Jedis jedis = new Jedis(host, port,timeout);
		if(password!=null&&!"".equals(password)){
			jedis.auth(password);
		}
		return jedis;
	}

	@Override
	public Class<?> getObjectType() {
		return Jedis.class;
	}

	@Override
	public boolean isSingleton() {
		return isSingleton;
	}


	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setIsSingleton(Boolean isSingleton) {
		this.isSingleton = isSingleton;
	}
}
