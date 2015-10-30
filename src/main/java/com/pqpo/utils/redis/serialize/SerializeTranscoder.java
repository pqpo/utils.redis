package com.pqpo.utils.redis.serialize;

import java.io.Closeable;

import org.apache.log4j.Logger;

public abstract class SerializeTranscoder {
	protected  Logger logger = Logger.getLogger(getClass());
	  
	  public abstract byte[] serialize(Object value);
	  
	  public abstract Object deserialize(byte[] in);
	  
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
